class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prevGroupEnd = dummy;

        while (true) {
            // Step 1: Check if there are k nodes left
            ListNode kth = getKthNode(prevGroupEnd, k);
            if (kth == null) break;

            ListNode nextGroupStart = kth.next;

            // Step 2: Reverse k nodes
            ListNode prev = nextGroupStart;
            ListNode curr = prevGroupEnd.next;

            while (curr != nextGroupStart) {
                ListNode temp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = temp;
            }

            // Step 3: Connect reversed group
            ListNode temp = prevGroupEnd.next;
            prevGroupEnd.next = kth;
            prevGroupEnd = temp;
        }

        return dummy.next;
    }

    // Helper method to get kth node from current position
    private ListNode getKthNode(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}
