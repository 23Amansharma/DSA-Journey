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
    public ListNode reverseList(ListNode head) {

        // prev pointer initially null hoga
        // ye reversed linked list ka previous node track karega
        ListNode prev = null;

        // current pointer head se start karega
        ListNode current = head;

        // jab tak current node null nahi ho jata
        while (current != null) {

            // nextnode  me next node ko temporarily store kar liya
            // taki link reverse karte waqt list lost na ho
            ListNode nextnode = current.next;

            // current node ka next ab prev ko point karega
            // yahi step list ko reverse karta hai
            current.next = prev;

            // prev ko aage move kar diya
            prev = current;

            // current ko next node par move kar diya
            current = nextnode;
        }

        // loop ke end me prev hi reversed list ka head hoga
        return prev;
    }
}
