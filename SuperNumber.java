import  java.io.*;
import  java.util.*;


public class SuperNumber {
    
    static PrintWriter stdOut = new PrintWriter(System.out, true);

    static BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

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
        try {
            stdOut.println(reverse(13));
            int n = Integer.valueOf(stdIn.readLine());

            int begin = 0;
            int end = 0;


            for (int i = 0; i < n; i+=1) {
                begin += ((int) Math.pow(10, i));
            } 

            for (int i = 0; i < n; i++) {
                end += 7 * ((int) Math.pow(10, i));
            }

            for (int i = begin; i <= end; i++) {
                boolean flag = true;
                
                if (!isRealNumber(i)) {
                    continue;
                }
                
                
                
                // int num = reverse(i);
                // num /= 10;
                // num = reverse(num);
                // while (num > 0) {
                //     if (!isRealNumber(num)) {
                //         flag = false;
                //         break;
                //     }
                //     stdOut.println(num);

                //     num = reverse(i);
                //     num /= 10;
                //     num = reverse(num);

                // }

                if (flag) {
                    stdOut.println(i);
                }
            }
        }
        catch (IOException e) {

        }


    }
}