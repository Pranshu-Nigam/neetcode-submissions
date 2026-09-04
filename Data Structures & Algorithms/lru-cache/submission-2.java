class Node{
    int val;
    int key;
    Node next;
    Node prev;
    Node(int key, int val){
        this.val = val;
        this.key = key;
        this.next = null;
        this.prev = null;
    }
}

class LRUCache {

    Map<Integer, Node> map;
    int size;
    Node head;
    Node tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.size = capacity;
        this.head = new Node(-1,-1);
        this.tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) return -1;

        Node node = map.get(key);
        remove(node);
        insert(node);

        return node.val;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.val = value;
            remove(node);
            insert(node);
        } else {
            if(map.size() == size){
                Node last = tail.prev;
                remove(last);
                map.remove(last.key);
            }
            Node nn = new Node(key, value);
            map.put(key, nn);
            insert(nn);
        }
    }

    public void remove(Node node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void insert(Node node){
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}
