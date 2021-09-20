public class Solution {
    public int shortestSubarray(int[] nums, int k) {
        // if (nums[0] >= k) return 1;

        int[] preS = new int[nums.length + 1];

        for (int i = 0; i < nums.length; i++) {
            preS[i+1] = preS[i] + nums[i];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        deque.offerLast(0);

        int shortest = nums.length  + 1;

        for (int i = 1; i < preS.length; i++) {
            //System.out.println(deque);
            if (preS[deque.peekLast()] >= preS[i]) {
                deque.clear();
                deque.offerLast(i);
            } else {
                while (!deque.isEmpty() && preS[i] - preS[deque.peekLast()] >= k) {
                    shortest = Math.min(shortest, i - deque.peekLast());
                    deque.pollLast();
                }
                while (!deque.isEmpty() && preS[deque.peekFirst()] >= preS[i]) {
                    deque.pollFirst();
                }
                deque.offerFirst(i);
            }
        }


        return shortest > nums.length ? -1 : shortest;
    }
}