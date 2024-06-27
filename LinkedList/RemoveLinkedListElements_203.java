package Leetcode.Leetcode.LinkedList;

public class RemoveLinkedListElements_203 {

    /*使用虚拟头节点的方法
    public ListNode removeElements(ListNode head, int val) {

        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode(-1,head);
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if(cur.val == val)
            {
                pre.next = cur.next;
            } else{
                pre = cur;
            }
            cur = cur.next;
        }

        return dummy.next;
    }

     */
    //不使用虚拟头节点
    public static ListNode removeElements(ListNode head, int val) {

        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }
        
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return head;
        
    }
    public static void main(String[] args) {

    }
}
