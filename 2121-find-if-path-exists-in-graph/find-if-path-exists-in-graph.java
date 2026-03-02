class Solution {

    public boolean validPath(int n, int[][] edges, int source, int destination) {

        int[] parent = new int[n];
        int[] rank = new int[n];

        // Initialize parent array
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        // Union all edges
        for (int[] edge : edges) {
            union(edge[0], edge[1], parent, rank);
        }

        // Check if both belong to same set
        return find(source, parent) == find(destination, parent);
    }

    private int find(int x, int[] parent) {
        if (parent[x] != x) {
            parent[x] = find(parent[x], parent); // Path compression
        }
        return parent[x];
    }

    private void union(int x, int y, int[] parent, int[] rank) {
        int rootX = find(x, parent);
        int rootY = find(y, parent);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}