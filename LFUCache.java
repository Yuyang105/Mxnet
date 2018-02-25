/**

需要两个HashMap，以及一个Double LinkedList。数据结构如下：

1.  HashMap<Integer key, node> nodeMap;                     // 用来存所有的节点

2.  HashMap<Integer freq, DoubleLinkedList> freqMap;        // 用来存每个freq下，有哪些节点，一旦爆了，从最底下的list里删。

3.  DoubleLinkedList {
        Node dummyHead, dummyTail;                          // 每次往head后面加新的，也就是越靠近tail越旧，每次删也是从tail上删。
        int size;
        void add(Node n);
        void remove(Node n)
        Node removeLast();
    }

4.  Node {
        int key, val, freq;
        Node prev, next;
    }

所以这就很容易实现了：
==================================
                                    HashMap nodeMap
        key key key         key
        |   |   |   ... ... |
        val val val         val     (nodes)
----------------------------------
                                    HashMap freqMap
freq k      key
            
.           key key key
.           key
.
            
freq min    key key                 (Double LinkedList)
==================================

*/

/**
Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/
class LFUCache {
    private HashMap<Integer, Node>  nodeMap;
    private HashMap<Integer, DoubleLinkedList> freqMap;
    private int capacity;
    private int min, size;
    
    public LFUCache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        freqMap = new HashMap<>();
        min = 0;
        size = 0;
    }
    
    public int get(int key) {
        Node cur = nodeMap.get(key);
        if (cur == null) return -1;
        update(cur);
        return cur.val;
    }
    
    public void put(int key, int value) {
        if (capacity == 0) return;
        
        Node cur = nodeMap.get(key);
        if (cur != null) {
            cur.val = value;
            update(cur);
        } else {
            cur = new Node(key, value);
            nodeMap.put(key, cur);
            if (size == capacity) {
                DoubleLinkedList lastList = freqMap.get(min);
                nodeMap.remove(lastList.removeLast().key);
                size--;
            }
            min = 1;
            DoubleLinkedList updateList = freqMap.getOrDefault(cur.freq, new DoubleLinkedList());
            updateList.add(cur);
            freqMap.put(cur.freq, updateList);
            size++;
        } 
    }
    
    private void update(Node node) {
        DoubleLinkedList list = freqMap.get(node.freq);
        list.remove(node);
        
        if (node.freq == min && list.size == 0) 
            min++;
        
        node.freq++;
        DoubleLinkedList updateList = freqMap.getOrDefault(node.freq, new DoubleLinkedList());
        updateList.add(node);
        freqMap.put(node.freq, updateList);
    }
    
    private class Node {
        private int key;
        private int val;
        private int freq;
        private Node prev;
        private Node next;
        
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
            freq = 1;
            prev = null;
            next = null;
        }
    }
    
    private class DoubleLinkedList {
        Node head;      // dummy head
        Node tail;      // dummy tail
        int size;
        
        public DoubleLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        
        public void add(Node node) {
            head.next.prev = node;
            node.next = head.next;
            node.prev = head;
            head.next = node;
            size++;
        }
        
        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
        
        public Node removeLast() {
            if (size > 0) {
                Node node = tail.prev;
                remove(node);
                return node;
            }
            else return null;
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
