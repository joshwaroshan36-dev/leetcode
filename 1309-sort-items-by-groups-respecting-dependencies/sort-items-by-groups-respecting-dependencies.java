import java.util.*;

class Solution {
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        for (int i = 0; i < n; i++) {
            if (group[i] == -1) group[i] = m++;
        }

        List<List<Integer>> groupGraph = new ArrayList<>();
        List<List<Integer>> itemGraph = new ArrayList<>();

        for (int i = 0; i < m; i++) groupGraph.add(new ArrayList<>());
        for (int i = 0; i < n; i++) itemGraph.add(new ArrayList<>());

        int[] groupIndegree = new int[m];
        int[] itemIndegree = new int[n];

        for (int i = 0; i < n; i++) {
            for (int prev : beforeItems.get(i)) {
                itemGraph.get(prev).add(i);
                itemIndegree[i]++;

                if (group[i] != group[prev]) {
                    groupGraph.get(group[prev]).add(group[i]);
                    groupIndegree[group[i]]++;
                }
            }
        }

        List<Integer> groupOrder = topoSort(groupGraph, groupIndegree, m);
        List<Integer> itemOrder = topoSort(itemGraph, itemIndegree, n);

        if (groupOrder.isEmpty() || itemOrder.isEmpty()) return new int[0];

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int item : itemOrder) {
            map.computeIfAbsent(group[item], k -> new ArrayList<>()).add(item);
        }

        List<Integer> result = new ArrayList<>();
        for (int g : groupOrder) {
            result.addAll(map.getOrDefault(g, new ArrayList<>()));
        }

        return result.stream().mapToInt(i -> i).toArray();
    }

    private List<Integer> topoSort(List<List<Integer>> graph, int[] indegree, int size) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        List<Integer> res = new ArrayList<>();
        while (!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);
            for (int next : graph.get(cur)) {
                if (--indegree[next] == 0) q.offer(next);
            }
        }
        return res.size() == size ? res : new ArrayList<>();
    }
}