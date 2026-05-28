import java.util.*;

class Solution {
    public int[] stringIndices(String[] wc, String[] wq) {
        // root[0] = bestIndex, root[1] = HashMap for children
        Object[] root = new Object[]{-1, new HashMap<Character, Object[]>()};
        
        // Helper to update best index based on length and original index
        java.util.function.BiConsumer<Object[], Integer> update = (node, idx) -> {
            int old = (int) node[0];
            if (old == -1 || wc[idx].length() < wc[old].length()) node[0] = idx;
        };

        // 1. Trie Build (Reverse Order)
        for (int i = 0; i < wc.length; i++) {
            update.accept(root, i);
            Object[] curr = root;
            for (int j = wc[i].length() - 1; j >= 0; j--) {
                var children = (Map<Character, Object[]>) curr[1];
                curr = children.computeIfAbsent(wc[i].charAt(j), k -> new Object[]{-1, new HashMap<Character, Object[]>()});
                update.accept(curr, i);
            }
        }

        // 2. Query Process
        int[] ans = new int[wq.length];
        for (int i = 0; i < wq.length; i++) {
            Object[] curr = root;
            for (int j = wq[i].length() - 1; j >= 0; j--) {
                var children = (Map<Character, Object[]>) curr[1];
                if (!children.containsKey(wq[i].charAt(j))) break;
                curr = children.get(wq[i].charAt(j));
            }
            ans[i] = (int) curr[0];
        }
        return ans;
    }
}
