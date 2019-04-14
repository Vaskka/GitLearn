
/**
 * 读优先
 * 1. 同时只有一个线程读或写
 * 2. 有写线程占据文件资源的时候，其他任何线程只能等待
 * 3. 有读线程占据文件资源的时候，可以让读线程进行读取
 */
 
#include <stdio.h>
#include <pthread.h>
#include <time.h>
#include <stdlib.h>
#include <unistd.h>
#include <semaphore.h>
#include <sys/time.h>

#define NUM_WIRTER 5
#define NUM_READER 5

typedef struct timeval tv;

typedef struct my_data
{
    /* data */
    pthread_t pid;

    int id;

} my_data;

tv start;


int buffer = -1;

int writers = 0;

sem_t mutex;

sem_t write_lock;

sem_t read_lock;

// 计算毫秒时间间隔
int64_t getBlankMicroSec(const tv* start, const tv* end) {

    return (long long)((end->tv_sec * 1e6 + end->tv_usec) - (start->tv_sec * 1e6 + start->tv_usec)) % 1000000;
}

// 计算秒时间间隔
int64_t getBlankSec(const tv* start, const tv* end) {

    return getBlankMicroSec(start, end) / 1e6;
}


void * reader (void * param) {
    int id = (*((my_data *) param)).id;
    int pid = (*((my_data *) param)).pid;

    
    while (1) {
        if (buffer < 0) {
            continue;
        }
        usleep(rand() % 500000);
        sem_wait(&mutex);
        tv curr;
        gettimeofday(&curr, NULL);

        while (writers);
        // read

        tv _curr;
        gettimeofday(&_curr, NULL);
        printf("pid:%13d, r, %d.%d, %ld.%ld\n", pid, getBlankSec(&start, &curr), getBlankMicroSec(&start, &curr), getBlankSec(&start, &_curr), getBlankMicroSec(&start, &_curr));


        sem_post(&mutex);
    }

    return NULL;
}


void * writer(void * param) {

    int id = (*((my_data *) param)).id;
    int pid = (*((my_data *) param)).pid;

    while (1) {
        usleep(rand() % 500000);
        sem_wait(&write_lock);

        tv curr;
        gettimeofday(&curr, NULL);
        
        while (writers);
        writers++;

        sem_wait(&mutex);

        // write

        buffer = id;

        tv _curr;
        gettimeofday(&_curr, NULL);
        printf("pid:%13d, w, %d.%d, %ld.%ld\n", pid, getBlankSec(&start, &curr), getBlankMicroSec(&start, &curr), getBlankSec(&start, &_curr), getBlankMicroSec(&start, &_curr));

        sem_post(&mutex);

        writers--;
        sem_post(&write_lock);
    }

    return NULL;
}






int main() {


    gettimeofday(&start,NULL);


    srand((unsigned int) time(NULL));

    sem_init(&read_lock, 0, 0);
    sem_init(&write_lock, 0, 1);
    sem_init(&mutex, 0, 1);

    pthread_t tid_writer[NUM_WIRTER];
    pthread_t tid_reader[NUM_READER];

    // gettimeofday(&tv,NULL);

    for (int i = 0; i < NUM_READER; i++) {

        my_data d = {tid_reader[i], i};
        pthread_create(&tid_reader[i], NULL, reader, (void *)(&d));
    }

    for (int i = 0; i < NUM_WIRTER; i++) {
        my_data d = {tid_writer[i], i};
        pthread_create(&tid_writer[i], NULL, writer, (void *) (&tid_writer[i]));
    }


    for (int i = 0; i < NUM_READER; i++) {
        pthread_join(tid_reader[i], NULL);
    }

    for (int i = 0; i < NUM_WIRTER; i++) {
        pthread_join(tid_writer[i], NULL);
    }
    return 0;
}
