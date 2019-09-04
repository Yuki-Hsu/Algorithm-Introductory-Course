/*
何为前缀树？ 如何生成前缀树？
例子：
一个字符串类型的数组arr1，另一个字符串类型的数组arr2。
*/
public class TrieTree {
    // 前缀树节点信息
    public static class TrieNode {
        // 通过该节点的数量
        public int pass;
        // 以该节点为结尾的字符串数量
        public int end;
        // 指向其余的节点
        // public HashMap<Integer, TrieNode> map;
        public TrieNode[] map;
        public TrieNode() {
            pass = 0;
            end = 0;
            map = new TrieNode[26];
        }
    }
    // 前缀树
    public static class Trie {
        private TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        // 插入字符串
        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chs = word.toCharArray();
            // 循环变化的临时节点，存储root
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                // 判断是否第一次插入该字符
                if (node.map[index] == null) {
                    node.map[index] = new TrieNode();
                }
                node = node.map[index];
                node.pass++;
            }
            node.end++;
        }
        // 删除字符串
        public void delete(String word) {
            if (search(word)) {
                char[] chs = word.toCharArray();
                TrieNode node = root;
                int index = 0;
                for (int i = 0; i < chs.length; i++) {
                    index = chs[i] - 'a';
                    if (node.map[index].pass == 1) {
                        node.map[index] = null;
                        return;
                    }
                node = node.map[index];
                node.pass--;
                }
            node.end--;
            }
        }
        // 查找字符串
        public boolean search(String word) {
            if (word == null) {
                return false;
            }
            char[] chs = word.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.map[index] == null) {
                    return false;
                }
                node = node.map[index];
            }
            // 判断以该字符结尾的节点的 end 值
            return node.end != 0;
        }
        // 查找特定前缀的数量
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.map[index] == null) {
                    return 0;
                }
                node = node.map[index];
            }
            return node.pass;
        }
    }

    // 测试
    public static void main(String[] args) {
        Trie trie = new Trie();
        System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        System.out.println(trie.search("zuo"));
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.insert("zuo");
        trie.insert("zuo");
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.delete("zuo");
        System.out.println(trie.search("zuo"));
        trie.insert("zuoa");
        trie.insert("zuoac");
        trie.insert("zuoab");
        trie.insert("zuoad");
        trie.delete("zuoa");
        System.out.println(trie.search("zuoa"));
        System.out.println(trie.prefixNumber("zuo"));
    }
}