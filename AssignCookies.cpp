#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;


bool sortBySize(const int &a, const int &b)
{
    return a > b;
}

int findContentChildren(vector<int>& g, vector<int>& s) {
    sort(g.begin(), g.end(), sortBySize);
    sort(s.begin(), s.end(), sortBySize);
    int result = 0;
    int index = 0;
    

    for (int i = 0; i < s.size(); i++)
    {
        bool is_get = false;
        for (int j = index; j < g.size(); j++)
        {
            if (s[i] >= g[j]) 
            {
                index = j + 1;
                result++;
                is_get = !is_get;
                break;
            }
        }
        if (!is_get) 
        {
            break;
        }
    }

    return result;

}

vector<int> initA() 
{
    int a[] = {1, 2};
    vector<int> result(a, a + sizeof(a) / sizeof(int));
    return result;
}


vector<int> initB()
{
    int b[] = {1, 2, 3};
    vector<int> result(b, b + sizeof(b) / sizeof(int));
    return result;
}

void showAll(vector<int>& a) {
    vector<int>::iterator it;
    for (it = a.begin(); it != a.end(); it++) 
    {
        cout << *it << endl;
    }
} 


int main()
{
    vector<int> a = initA();

    vector<int> b = initB();

    cout << findContentChildren(a, b);

    system("pause");

    return 0;
}