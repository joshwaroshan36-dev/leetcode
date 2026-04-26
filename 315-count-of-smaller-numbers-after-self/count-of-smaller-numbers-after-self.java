import java.util.*;

class Solution {
    public List<Integer> countSmaller(int[] nums) {
        Integer[] result = new Integer[nums.length];
        Arrays.fill(result, 0);

        int[][] arr = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        mergeSort(arr, 0, nums.length - 1, result);
        return Arrays.asList(result);
    }

    private void mergeSort(int[][] arr, int left, int right, Integer[] result) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, result);
        mergeSort(arr, mid + 1, right, result);
        merge(arr, left, mid, right, result);
    }

    private void merge(int[][] arr, int left, int mid, int right, Integer[] result) {
        List<int[]> temp = new ArrayList<>();
        int i = left, j = mid + 1, count = 0;

        while (i <= mid && j <= right) {
            if (arr[i][0] <= arr[j][0]) {
                result[arr[i][1]] += count;
                temp.add(arr[i++]);
            } else {
                count++;
                temp.add(arr[j++]);
            }
        }

        while (i <= mid) {
            result[arr[i][1]] += count;
            temp.add(arr[i++]);
        }

        while (j <= right) temp.add(arr[j++]);

        for (int k = left; k <= right; k++) {
            arr[k] = temp.get(k - left);
        }
    }
}