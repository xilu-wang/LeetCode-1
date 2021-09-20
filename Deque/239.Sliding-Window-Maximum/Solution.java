public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] ret = new int[nums.length - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // value is Idx
        deque.offerLast(0);
        // add largest from last,
        // add non-largest from first,
        // peek from last

        int l = 0, r = 1, i = 0;

        while (r <= nums.length && l <= nums.length - k) {
            if (deque.isEmpty()) {
                deque.offerLast(r);
                r++;
                continue;
            }

            int curMax = nums[deque.peekLast()];
            int leastMaxIdx = deque.peekFirst();
            while (leastMaxIdx < l) {
                deque.pollFirst();
                leastMaxIdx = deque.peekFirst();
            }

            if (r - l >= k) {
                // need move l++
                if (r - l == k) ret[i++] = curMax;
                while (!deque.isEmpty() && deque.peekLast() <= l) deque.pollLast();
                l++;
            } else if (r - l < k) {
                // need move r++
                if (nums[r] >= curMax) {
                    deque.offerLast(r);
                } else {
                    while (nums[leastMaxIdx] <= nums[r]) {
                        deque.pollFirst();
                        leastMaxIdx = deque.peekFirst();
                    }
                    deque.offerFirst(r);
                }
                r++;
            }
        }

        return ret;
    }
}