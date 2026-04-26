import java.util.*;

class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), 1, n, k);
        return res;
    }

    private void backtrack(List<List<Integer>> res, List<Integer> temp, int start, int n, int k) {
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i <= n; i++) {
            temp.add(i);
            backtrack(res, temp, i + 1, n, k);
            temp.remove(temp.size() - 1);
        }
    }
}