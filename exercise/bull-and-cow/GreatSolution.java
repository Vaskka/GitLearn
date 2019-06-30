import java.util.HashSet;

public class Solution {

/*
* 1123 0111 1 1
* 1122 2211 0 4
* 1122 1222 3 0
* */


    public String getHint(String secret, String guess) {

        int[] ab = new int[2];
        int n = secret.length();

        int[] cnt = new int[10];
        for (int i = 0; i < n; ++i) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s == g) {
                ab[0]++;
                continue;
            }
            cnt[s - '0']++;
        }

        //cal B
        for (int i = 0; i < n; ++i) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s != g && cnt[g - '0'] > 0) {
                cnt[g - '0']--;
                ab[1]++;
            }
        }
        return ab[0] + "A" + ab[1] + "B";
    }

}
