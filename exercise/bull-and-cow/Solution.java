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

        HashSet<Integer> A = new HashSet<>();
        HashSet<Integer> B = new HashSet<>();

        int i = 0;
        while (i < guessArray.length) {

            if (guessArray[i] == secretArray[i]) {
                A.add(i);
            }
            i++;
        }

        for (int j = 0; j < guessArray.length; j++) {
            if (A.contains(j)) {
                continue;
            }
            for (int k = 0; k < secretArray.length; k++) {
                if (!A.contains(k) && !B.contains(k) && guessArray[j] == secretArray[k]) {
                    B.add(k);
                    break;
                }
            }
        }

        return String.format("%dA%dB", A.size(), B.size());

    }

}
