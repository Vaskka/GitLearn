#include <iostream>
#include <vector>

using namespace std;


vector<int> twoSum(vector<int>& nums, int target)
{
    for (int i = 0; i < nums.size(); i++) {
        for (int j = i + 1; j < nums.size(); j++) {
            if (target == nums[j] + nums[i]) {
                vector<int> result;
                result.push_back(i);
                result.push_back(j);
                return result;
            }
        }


    }

}

vector<int> init() 
{
    int a[] = {2, 7, 11, 15};

    vector<int> nums(a, a + sizeof(a) / sizeof(int));
    return nums;
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
    vector<int> nums = init();

    vector<int> result = twoSum(nums, 9);

    showAll(result);

    system("pause");

    return 0;
}