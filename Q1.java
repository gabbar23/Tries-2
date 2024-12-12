Time Complexity (TC): O(n * L * 4^L)
Space Complexity (SC): O(n * L + n^2 * L)










class Trie {
    class TrieNode {
        HashMap<Character, TrieNode> child;
        ArrayList<String> children;

        public TrieNode() {
            this.child = new HashMap<>();
            this.children = new ArrayList<>();
        }
    }

    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insertWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            current.child.putIfAbsent(c, new TrieNode());
            current = current.child.get(c);
            current.children.add(word);
        }
    }

    public ArrayList<String> searchWord(String word) {
        TrieNode current = root;
        for (char c : word.toCharArray()) {
            if (current.child.containsKey(c) == false)
                return new ArrayList<>();
            current = current.child.get(c);
        }

        return current.children;

    }
}

class Solution {
    private void helper(List<String> path, Trie trieObj, List<List<String>> result) {
        // base
        if (path.get(0).length() == path.size()) {
            result.add(new ArrayList<>(path));
            return;
        }
        // creat the prefix from current path
        int k = path.size();
        StringBuilder prefix = new StringBuilder();
        for (String word : path) {

            prefix.append(word.charAt(k));
        }
        List<String> prefixWord = trieObj.searchWord(prefix.toString());
        for (String word : prefixWord) {
            path.add(word);
            helper(path, trieObj, result);
            path.remove(path.size() - 1);

        }
    }

    public List<List<String>> wordSquares(String[] words) {
        Trie trieObj = new Trie();
        for (String word : words) {
            trieObj.insertWord(word);
        }
        List<List<String>> result = new ArrayList<>();
        List<String> path = new ArrayList<>();
        for (String word : words) {
            path.add(word);
            helper(path, trieObj, result);
            path.remove(path.size() - 1);
        }
        return result;

    }
}
