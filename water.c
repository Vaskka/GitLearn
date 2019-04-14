#include <stdio.h>
#include <pthread.h>
#include <unistd.h>
#include <semaphore.h>
#include <sys/time.h>

int h_count = 0;
int o_count = 0;

int h2o_count = 0;


pthread_mutex_t h_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t o_mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t h2o_mutex = PTHREAD_MUTEX_INITIALIZER;
sem_t h_ready_sem;
sem_t h2o_sem;


void * h_func(void * param) {

    while (1) {
        usleep(rand() % 1000000);
        pthread_mutex_lock(&h_mutex);


        if (h_count == 2) {
            sem_post(&h_ready_sem);
            continue;
        }

        h_count++;    
        printf("generate a h atomic. now h_count=%d, o_count=%d, h2o_count=%d\n", h_count, o_count, h2o_count);

        pthread_mutex_unlock(&h_mutex);
    }

    return NULL;
}


void * o_func(void * param) {

    while (1) {
        usleep(rand() % 1000000);
        pthread_mutex_lock(&o_mutex);

        if (o_count > 0) {
            sem_wait(&h_ready_sem);

            printf("h is ready now consume 2 h atomics and one o atomic. now h_count=%d, o_count=%d, h2o_count=%d\n", h_count, o_count, h2o_count);
            h_count-=2;
            o_count--;

            sem_post(&h2o_sem);
        }
        o_count++;

        pthread_mutex_unlock(&o_mutex);
    }

    return NULL;
}

void * h2o_func(void * param) {

    while (1) {
                usleep(rand() % 1000000);
        pthread_mutex_lock(&h2o_mutex);

        sem_wait(&h2o_sem);

        printf("generate a h2o. now h_count=%d, o_count=%d, h2o_count=%d\n", h_count, o_count, h2o_count);
        h2o_count++;
        pthread_mutex_unlock(&h2o_mutex);
    }

    return NULL;
}

int main() {


    sem_init(&h_ready_sem, 0, 0);
    sem_init(&h2o_sem, 0, 0);

    pthread_t ph;
    pthread_t po;
    pthread_t ph2o;


    pthread_create(&ph, NULL, h_func, NULL);
    pthread_create(&po, NULL, o_func, NULL);
    pthread_create(&ph2o, NULL, h2o_func, NULL);

    pthread_join(ph, NULL);
    pthread_join(po, NULL);
    pthread_join(ph2o, NULL);


    return 0;
}
