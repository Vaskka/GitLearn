import  java.io.*;
import  java.util.*;

/**
 * 超级素数
 * 本身是素数
 * 每降低一位最高位仍然是素数
 */
public class SuperNumber {
    
    static PrintWriter stdOut = new PrintWriter(System.out, true);

    static BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    static void L(Object o) {
        stdOut.println(o);
    }

    static boolean isRealNumber(int number) {
        if (number == 1) {
            return false;
        }

        for (int i = 2; i < (int) (Math.sqrt(number) + 1); i++) {
            if (number % i == 0) {
                return false;
            }

        }
        return true;
    }
    
    public static void main(String[] argc) {
        
        int count = 0;
        int theBiggest = 0;

        try {
            // 假设n大于1小于10
            int n = Integer.valueOf(stdIn.readLine());

            int begin = 0;
            int end = 0;

            // 当前位拿到最小的数 111...n
            for (int i = 0; i < n; i+=1) {
                begin += ((int) Math.pow(10, i));
            } 

            // 当前为最大的数 9999..n
            for (int i = 0; i < n; i++) {
                end += 9 * ((int) Math.pow(10, i));
            }

            // 枚举 (偶数一定不满足 所以进行奇循环)
            for (int i = begin; i <= end; i+=2) {
                boolean flag = true;
                int current = i;
                
                // 储存每位
                int[] nums = new int[n];
                
                // 拿到每一位同时检查有没有0
                for (int j = 0; j < nums.length; j++) {
                    nums[j] = current % 10;
                    if (nums[j] == 0) {
                        flag = false;
                        break;
                    }
                    current /= 10;
                }

                if (flag) {

                    // 检查去掉高一位是否还是素数
                    for (int j = 0; j < n; j++) {
                        int subNumber = 0;
                        for (int k = 0; k < n - j; k++) {
                            subNumber += ((int) Math.pow(10, k) * nums[k]);
                        }
                        
                        if (!isRealNumber(subNumber)) {
                            flag = false;
                            break;
                        }
                    }
                }

                if (flag) {
                    stdOut.println(i);
                    theBiggest = i;
                    count++;
                }
            }
        }
        catch (IOException e) {

        }
        finally {
            stdOut.println("The all of SuperNumbers is " + String.valueOf(count));
            stdOut.println("The biggest SuperNumber is " + String.valueOf(theBiggest));
        }

    }
}