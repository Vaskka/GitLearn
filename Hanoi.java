public class Hanoi {

    static double count = 0;

    static void L(Object o) {
        System.out.println(o);
    }

    /**
     * @param n int the number of init things
     */
    static double getMoveCount(int n) {
        if (n == 1) {
            return 1;
        }
        else {
            return getMoveCount(n - 1) * 2 + 1;
        }
    }


    static void directlyMove(String one, String another) {
        L(one + "->" + another);
        count++;
    }


    /**
     * 移动过程：
     * 如果只剩一个，移动这一个
     * 如果不是，先将n-1个借助final移动到中间，剩下一个直接移动（保证最大的在下面）
     * 再将中间桩借助初始桩移动到final
     */
    static void showAllWays(int n, String initBase, String middleBase, String finalBase) {
        if (n == 1) {
            directlyMove(initBase, finalBase);
        }
        else {
            showAllWays(n - 1, initBase, finalBase, middleBase);

            directlyMove(initBase, finalBase);

            showAllWays(n - 1, middleBase, initBase, finalBase);

        }
    }

    public static void main(String[] argc) {
        showAllWays(4, "A", "B", "C");
        L(count);
    }

}