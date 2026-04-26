import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> parent = new HashMap<>();
        Map<String, String> owner = new HashMap<>();

        for (List<String> acc : accounts) {
            String name = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                parent.put(acc.get(i), acc.get(i));
                owner.put(acc.get(i), name);
            }
        }

        for (List<String> acc : accounts) {
            String p = find(acc.get(1), parent);
            for (int i = 2; i < acc.size(); i++) {
                parent.put(find(acc.get(i), parent), p);
            }
        }

        Map<String, TreeSet<String>> map = new HashMap<>();

        for (String email : parent.keySet()) {
            String root = find(email, parent);
            map.computeIfAbsent(root, k -> new TreeSet<>()).add(email);
        }

        List<List<String>> res = new ArrayList<>();
        for (String root : map.keySet()) {
            List<String> list = new ArrayList<>();
            list.add(owner.get(root));
            list.addAll(map.get(root));
            res.add(list);
        }

        return res;
    }

    private String find(String x, Map<String, String> parent) {
        if (!parent.get(x).equals(x)) {
            parent.put(x, find(parent.get(x), parent));
        }
        return parent.get(x);
    }
}