import java.util.HashSet;

public class Solution {

/*
* 1123 0111 1 1
* 1122 2211 0 4
* 1122 1222 3 0
* */


    public String getHint(String secret, String guess) {
        char[] secretArray = secret.toCharArray();
        char[] guessArray = guess.toCharArray();

        int A = 0;
        int B = 0;

        HashSet<Integer> wrap = new HashSet<>();

        int i = 0;
        while (i < guessArray.length) {

            if (guessArray[i] == secretArray[i]) {
                wrap.add(i);
                A++;
            }
            i++;
        }

        for (int j = 0; j < guessArray.length; j++) {

            if (!wrap.contains(j) && guessArray[j] == secretArray[j]) {
                wrap.add(j);
                B++;
            }
        }

        return String.format("%dA%dB", A, B);

    }

}
