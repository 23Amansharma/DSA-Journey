/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // Edge case: empty list, single node, or no rotation needed
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        // 1. Calculate the length and find the old tail
        ListNode oldTail = head;
        int length = 1;
        while (oldTail.next != null) {
            oldTail = oldTail.next;
            length++;
        }

        // 2. Close the loop (make it circular)
        oldTail.next = head;

        // 3. Find the new tail: (length - k % length - 1) steps from the start
        // And the new head: next node after the new tail
        k = k % length;
        int stepsToNewTail = length - k - 1;
        ListNode newTail = head;
        for (int i = 0; i < stepsToNewTail; i++) {
            newTail = newTail.next;
        }
        
        ListNode newHead = newTail.next;

        // 4. Break the circular loop
        newTail.next = null;

        return newHead;
    }
}