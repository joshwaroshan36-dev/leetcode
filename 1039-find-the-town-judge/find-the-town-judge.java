class Solution {
    public int findJudge(int n, int[][] trust) {

        int[] inDegree = new int[n + 1];
        int[] outDegree = new int[n + 1];

        // Build degree arrays
        for (int[] t : trust) {
            outDegree[t[0]]++;  // a trusts someone
            inDegree[t[1]]++;   // b is trusted
        }

        // Find the judge
        for (int i = 1; i <= n; i++) {
            if (inDegree[i] == n - 1 && outDegree[i] == 0) {
                return i;
            }
        }

        return -1;
    }
}