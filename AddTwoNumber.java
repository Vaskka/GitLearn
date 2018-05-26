import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.math.*;

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}


class addTwoNumbers {
    
    static ListNode l1;

    static ListNode l2;

    public static void init() {
        ListNode a1 = new ListNode(1);
//        ListNode a2 = new ListNode(4);
//        ListNode a3 = new ListNode(3);

//        a1.next = a2;
//        a2.next = a3;


        ListNode b1 = new ListNode(9);
        ListNode b2 = new ListNode(9);
//        ListNode b3 = new ListNode(4);
        
        b1.next = b2;
//        b2.next = b3;

        l1 = a1;
        l2 = b1;

    }


    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode ele1 = l1;
		ListNode ele2 = l2;
		
		int num1 = 0;
		int num2 = 0;
		
		int supNum = 0;
		
		ListNode header = null;
		ListNode before = null;
		
		boolean isOver1 = false;
		boolean isOver2 = false;
		boolean over = false;
		
    	while (true) {
	
    		if (!isOver1) {
    			num1 = ele1.val;
    		}
    		else {
    			num1 = 0;
    		}
    		
    		if (!isOver2) {
    			num2 = ele2.val;
    		}
    		else {
    			num2 = 0;
    		}
    		
    		int realVal = (num1 + num2) % 10 + supNum;
    		
    		if (realVal > 9 || (num1 + num2) > 9) {
    			supNum = 1;
    		}
    		else {
    			supNum = 0;
    		}
    		
    		ListNode node = new ListNode(realVal % 10);
    		
    		if (header == null) {
    			header = node;
    		}
    		
    		if (before != null) {
    			before.next = node;
    		}
    		before = node;
    		

    		
    		if (!isOver1) {
    			ele1 = ele1.next;
    		}
    		
    		if (!isOver2) {
    			ele2 = ele2.next;
    		}
    		
    		if (ele1 == null) {
    			isOver1 = true;
    		}
    		
    		if (ele2 == null) {
    			isOver2 = true;
    		}
    		
    		if (isOver1 && isOver2 && over) {
    			break;
    		}
    		
    		if (isOver1 && isOver2 && supNum == 1) {
    			over = true;
    		}
    		else {
    			if (isOver1 && isOver2) {
    				break;
    			}
    		}
    		
    	}
    	
    	return header;
    	 
    	
    }

    public static void L(Object o) {
        System.out.println(o);
    }

    public static void main(String argc[]) {
    	init();
        ListNode result = addTwoNumbers(l1, l2);

         L(result.val);
         L(result.next.val);
         L(result.next.next.val);

    }
}