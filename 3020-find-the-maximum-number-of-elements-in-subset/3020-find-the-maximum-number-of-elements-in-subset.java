class Solution {
    public int maximumLength(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        for (int n : nums) {
            m.put(n, m.getOrDefault(n, 0) + 1);
        }

        int r = 1;
        if (m.containsKey(1)) {
            int c = m.get(1);
            r = (c % 2 == 0) ? c - 1 : c;
            m.remove(1);
        }

        for (int k : m.keySet()) {
            int l = 0;
            long x = k;
            while (x <= 1000000000 && m.getOrDefault((int) x, 0) >= 2) {
                l += 2;
                x *= x;
            }
            if (x <= 1000000000 && m.getOrDefault((int) x, 0) >= 1) {
                l++;
            } else {
                l--;
            }
            r = Math.max(r, l);
        }

        return r;
    }
}