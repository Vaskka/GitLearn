

public class Solution {



    private static int TLE_Process(int[] nums, int i) {

        if (i == -1) {
            return 0;
        }

        if (i == 0) {
            return nums[0];
        }

        if (i == 1) {
            return nums[1] > nums[0] ? nums[1] : nums[0];
        }

        int max = nums[i];

        for (int j = i; j >= 0; j--) {
            max = Math.max(nums[i] + TLE_Process(nums, i - 2), TLE_Process(nums, i - 1));
        }

        return max;
    }


    // use cache
    private static int cacheProcess(int[] nums, int i, int[] cache) {


        // 在缓存中直接取
        if (cache[i] != -1) {
            return cache[i];
        }

        // 不在cache中，需要算
        int max = nums[i];

        for (int j = i; j >= 0; j--) {

            max = Math.max(nums[i] + cacheProcess(nums, i - 2, cache), cacheProcess(nums, i - 1, cache));
        }
        cache[i] = max;

        return max;

    }



    public int rob(int[] nums) {

        // return TLEprocess(nums, nums.length - 1);
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        int[] cache = new int[nums.length];
        cache[0] = nums[0];
        cache[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        for (int i = 2; i < cache.length; i++) {
            cache[i] = -1;
        }

        return cacheProcess(nums, nums.length - 1, cache);
    }

    // 取消递归 + cache空间优化
    public int robSpaceOptimization(int[] nums) {
        // deal []
        if (nums.length == 0) {
            return 0;
        }

        int lastSecond = nums[0];

        // deal [1]
        if (nums.length == 1) {
            return lastSecond;
        }

        int lastFirst = nums[0] > nums[1] ? nums[0] : nums[1];

        // deal [1, 2]
        if (nums.length == 2) {
            return lastFirst;
        }

        int max = 0;

        // main process 自底向上，尽可能减少cache空间，同时取消递归
        for (int i = 2; i < nums.length; i++) {
            max = Math.max(nums[i] + lastSecond, lastFirst);
            lastSecond = lastFirst;
            lastFirst = max;

        }

        return max;
    }


}
