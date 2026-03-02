import java.util.Random;

public class Solution {

    private Random rand = new Random();

    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int target = n - k;  // kth largest = (n-k)th smallest
        return quickSelect(nums, 0, n - 1, target);
    }

    private int quickSelect(int[] nums, int left, int right, int target) {

        while (left <= right) {

            int pivotIndex = left + rand.nextInt(right - left + 1);
            int pivot = nums[pivotIndex];

            // 3-way partition
            int lt = left;      // < pivot
            int gt = right;     // > pivot
            int i = left;

            while (i <= gt) {
                if (nums[i] < pivot) {
                    swap(nums, lt++, i++);
                } else if (nums[i] > pivot) {
                    swap(nums, i, gt--);
                } else {
                    i++;
                }
            }

            if (target < lt) {
                right = lt - 1;
            } else if (target > gt) {
                left = gt + 1;
            } else {
                return nums[target];   // inside equal zone
            }
        }

        return -1;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}