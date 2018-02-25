/**

既然我们有key和value，所以我们一定需要一个HashMap。既然我们每次都需要重新排序，并且要求时间复杂度为O(1)，所以我们还需要LinkedList用来插入。
LinkedList只能解决O(1)插入，但是我们还需要删除，所以我们需要： ==> HashMap + Double LinkedList,

所以数据结构是这样的：
HashMap<Integer key, Node node> map;
Node head;
Node tail;
int capacity;

*/

class LRUCache {
    private HashMap<Integer, Node> map;
    private int capacity;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        map = new HashMap<Integer, Node>();
        this.capacity = capacity;
        head = null;
        tail = null;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null)                       // 找不着
            return -1;
        
        if (node != tail) {                     // 其实相当于put中更新操作
            if (node == head) {
                head = head.next;
            } else {
                node.pre.next = node.next;
                node.next.pre = node.pre;
            }
            tail.next = node;
            node.pre = tail;
            node.next = null;
            tail = node;
        }
        return node.value;
    }
    
    /**
     尾部放最新，头部是最先进来的。
     
     map: 
     capacity = 1

     2,4

     temp -> 用来删hashmap
      h  <-
     1,1 -> 2,2 -> 3,3 ->null
             h      t
    */
    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            if (node != tail) {
                if (node == head) {
                    head = head.next;              // 如果是头的话，头移给下一个
                } else {
                    node.pre.next = node.next;     // 如果是中间的话，把它原先的首尾连起来
                    node.next.pre = node.pre;      // 把它单独拎出来
                }
                tail.next = node;                  // 把被更新的插到屁股上
                node.pre = tail;
                node.next = null;
                tail = node;
            }
            
        } else {
            Node newNode = new Node(key, value);    // 创建新值，判断容量
            if (capacity == 0) {
                Node temp = head;
                head = head.next;
                map.remove(temp.key);
                capacity++;
            }
            if (head == null && tail == null) {
                head = newNode;
            } else {
                tail.next = newNode;
                newNode.pre = tail;
                newNode.next = null;
            }
            tail = newNode;
            map.put(key, newNode);
            capacity--;
        }
        
    }
    
    class Node {
        int key;
        int value;
        Node next;
        Node pre;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
