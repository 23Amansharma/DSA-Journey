/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tempA = headA;
        ListNode tempB = headB;
        int size1 = 0,size2 = 0;
        while(tempA != null){
            size1++;
            tempA = tempA.next;
        }
         while(tempB != null){
            size2++;
            tempB = tempB.next;
        }
        int steps = Math.abs(size1-size2);
         tempA = headA;
        tempB = headB;
        if(size1>size2){
            for(int i = 1;i<= steps;i++)
            tempA = tempA.next;
        }
        else{
            for(int i = 1;i<=steps;i++){
            tempB = tempB.next;
            }
        }
        while(tempA!=tempB){
             if (tempA == null || tempA == null) return null; 
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return tempA;
    }
}