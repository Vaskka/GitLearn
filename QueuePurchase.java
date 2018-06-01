import java.util.List;
import java.util.ArrayList;

class Util {
    public static void L(Object o) {
        System.out.println(o);
    }
}

class Seller implements Cloneable {

    public int leftMoney = 0;

    @Override
    public Object clone() {
        Seller seller = new Seller();
        seller.leftMoney = this.leftMoney;
        return seller;

    }

}


class Node implements Cloneable {
    // public Node before;
    public int money;

    public Node(int money) {
        this.money = money;
    }
    // public int index;

    @Override
    public Object clone() {
        return new Node(this.money);
    }
}


class Result implements Cloneable {
    public List<Node> queue = new ArrayList<>();

    public void show() {
        for (Node node : this.queue) {
            Util.L(node.money);
        }
        Util.L("=======================");
    }

    @Override
    public Object clone() {
        List<Node> newList = new ArrayList<>();
        for (Node n : this.queue) {
            newList.add((Node) n.clone());
        }
        Result result = new Result();
        result.queue = newList;
        
        return result;
    }

}


public class QueuePurchase {

    static List<Result> results = new ArrayList<>();

    static final int BIG_NUMBER = 12;

    static final int SMALL_NUMBER = 15;

    static int count = 0;

    public static void mySolve(Result result, Seller seller, int leftNumber, int leftBig, int leftSmall) {
        if (leftNumber == 0) {
            //result.show();
            count++;
            return;
        }

        Result result1 = (Result) result.clone();
        Result result2 = (Result) result.clone();

        if (leftSmall > 0) {
            // insert 50
            result1.queue.add(new Node(50));
            Seller cloneSeller = (Seller) seller.clone();
            cloneSeller.leftMoney += 50;

            mySolve(result1, cloneSeller, leftNumber - 1, leftBig, leftSmall - 1);
        }

        if (leftBig > 0) {
            // insert 100
            if (seller.leftMoney >= 50) {
                Seller newSeller = (Seller) seller.clone();
                newSeller.leftMoney -= 50;
                result2.queue.add(new Node(100));
                mySolve(result2, newSeller, leftNumber - 1, leftBig - 1, leftSmall);
            }
        }

    }

    /**
     * 1 手持100的人大于手持50的人一定不成立
     * 2 只剩50的人就一种情况 （只剩100的人的情况包含在第一条中）
     * 3 其他情况返回 下一个人是50的 + 下一个人是100的
     */
    public static int answerSolve(int bigNumber, int smallNumber) {
        int result = 0;
        
        if (bigNumber == 0) {
            result = 1;
            return result;
        }

        if (bigNumber > smallNumber) {
            result = 0;
            return result;
        }

        result = answerSolve(bigNumber - 1, smallNumber) + answerSolve(bigNumber, smallNumber - 1);

        return result;
    }

    public static void main(String[] argc) {
        // Result result = new Result();
        // Seller seller = new Seller();
        // mySolve(result, seller, BIG_NUMBER + SMALL_NUMBER, BIG_NUMBER, SMALL_NUMBER);
        // Util.L(count);

        /* answer */
        Util.L(answerSolve(12, 15));
    }


}