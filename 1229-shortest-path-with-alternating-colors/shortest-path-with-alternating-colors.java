import java.util.*;

class Solution {
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        List<Integer>[] red = new ArrayList[n];
        List<Integer>[] blue = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            red[i] = new ArrayList<>();
            blue[i] = new ArrayList<>();
        }

        for (int[] e : redEdges) red[e[0]].add(e[1]);
        for (int[] e : blueEdges) blue[e[0]].add(e[1]);

        int[][] dist = new int[n][2];
        for (int[] d : dist) Arrays.fill(d, Integer.MAX_VALUE);

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        q.offer(new int[]{0, 1});
        dist[0][0] = dist[0][1] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0], color = cur[1];

            List<Integer>[] graph = color == 0 ? blue : red;

            for (int next : graph[node]) {
                if (dist[next][1 - color] == Integer.MAX_VALUE) {
                    dist[next][1 - color] = dist[node][color] + 1;
                    q.offer(new int[]{next, 1 - color});
                }
            }
        }

        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int min = Math.min(dist[i][0], dist[i][1]);
            res[i] = (min == Integer.MAX_VALUE) ? -1 : min;
        }
        return res;
    }
}