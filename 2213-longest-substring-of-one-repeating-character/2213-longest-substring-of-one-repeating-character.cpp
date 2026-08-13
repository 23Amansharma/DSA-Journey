#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct Node {
    int max_len;
    int pref_len;
    int suff_len;
    char pref_char;
    char suff_char;

    // Default constructor for vector allocation
    Node() : max_len(0), pref_len(0), suff_len(0), pref_char(' '), suff_char(' ') {}

    // Constructor for leaf initialization
    Node(char c) : max_len(1), pref_len(1), suff_len(1), pref_char(c), suff_char(c) {}
};

class SegmentTree {
private:
    int n;
    vector<Node> tree;

    Node merge(const Node& left, const Node& right, int left_size, int right_size) {
        Node parent;
        parent.pref_char = left.pref_char;
        parent.suff_char = right.suff_char;
        parent.pref_len = left.pref_len;
        parent.suff_len = right.suff_len;
        parent.max_len = max(left.max_len, right.max_len);

        // Check if boundary characters match to bridge the child nodes
        if (left.suff_char == right.pref_char) {
            int combined_len = left.suff_len + right.pref_len;
            parent.max_len = max(parent.max_len, combined_len);

            // If the left node is uniform, parent prefix length extends into the right node
            if (left.pref_len == left_size) {
                parent.pref_len = left_size + right.pref_len;
            }
            // If the right node is uniform, parent suffix length extends into the left node
            if (right.suff_len == right_size) {
                parent.suff_len = right_size + left.suff_len;
            }
        }
        return parent;
    }

    void build(const string& s, int node, int start, int end) {
        if (start == end) {
            tree[node] = Node(s[start]);
            return;
        }

        int mid = start + (end - start) / 2;
        build(s, 2 * node + 1, start, mid);
        build(s, 2 * node + 2, mid + 1, end);

        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2], mid - start + 1, end - mid);
    }

public:
    SegmentTree(const string& s) {
        n = s.length();
        tree.resize(4 * n);
        build(s, 0, 0, n - 1);
    }

    void update(int node, int start, int end, int idx, char char_val) {
        if (start == end) {
            tree[node] = Node(char_val);
            return;
        }

        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node + 1, start, mid, idx, char_val);
        } else {
            update(2 * node + 2, mid + 1, end, idx, char_val);
        }

        tree[node] = merge(tree[2 * node + 1], tree[2 * node + 2], mid - start + 1, end - mid);
    }

    int getMaxLen() const {
        return tree[0].max_len;
    }
};

class Solution {
public:
    vector<int> longestRepeating(string s, string queryCharacters, vector<int>& queryIndices) {
        ios_base::sync_with_stdio(false);
        cin.tie(NULL);

        SegmentTree st(s);
        int k = queryIndices.size();
        vector<int> ans;
        ans.reserve(k); // Prevent reallocations

        for (int i = 0; i < k; ++i) {
            st.update(0, 0, s.length() - 1, queryIndices[i], queryCharacters[i]);
            ans.push_back(st.getMaxLen());
        }

        return ans;
    }
};
