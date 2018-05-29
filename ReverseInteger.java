

class ReverseInteger {

    public static void L(Object o) {
        System.out.println(o);
    }

    public int reverse(int x) {
        boolean isNegative = false;
        
        if (x < 0) {
            isNegative = true;
        }
        x = (int)Math.abs(x);
        int[] nums = new int[10];
        
        int i = 0;
        while (x > 0) {
            int num = x % 10;
            nums[i] = num;
            x /= 10;
            i++;
        }

        long result = 0;
        for (int j = 0; j < i; j++) {
            result += (nums[j] * (long) Math.pow(10, i - j - 1));
        }

        if (result > Integer.MAX_VALUE) {
            return 0;
        }

        if (isNegative) {
            result = -result;
        }

        return (int)result;
    }

    public static void main(String argc[]) {

        L((new ReverseInteger()).reverse(-312));


    }

}