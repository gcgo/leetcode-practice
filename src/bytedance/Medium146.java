package bytedance;

import org.junit.Test;

import java.util.HashMap;

/**
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * <p>
 * 获取数据 get(key) - 如果密钥 (key) 存在于缓存中，则获取密钥的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果密钥不存在，则写入其数据值。当缓存容量达到上限时，
 * 它应该在写入新数据之前删除最近最少使用的数据值，从而为新的数据值留出空间。
 * <p>
 * 进阶:
 * <p>
 * 你是否可以在 O(1) 时间复杂度内完成这两种操作？
 */
public class Medium146 {
	/**
	 * LRU缓存由一个双向链表、一个hashmap组成
	 * 链表负责维护顺序，hashmap负责快速定位链表节点。
	 *
	 * 链表提供方法：
	 * 1头插
	 * 2就地删除
	 * 3删除尾部节点
	 *
	 * map结构：存的是<key,Node>
	 *
	 * LRU方法逻辑：
	 * get:
	 * 1）通过map先找到对应的node，并获取node中存的value
	 * 2）调用put方法，重新插入该值（详见put内部逻辑）
	 *
	 * put:
	 * 1）先根据传进来的key,value 新建一个Node。
	 * 2）通过map判断该key在不在map中，若存在执行3），不存在执行4）
	 * 3）存在的话，先操作链表，删除旧的节点，再头插新节点，即活跃对象前移。把这个node更新到map中。
	 * 4）不存在的话，若容量cap等于当前size，则执行5）淘汰队尾那个节点，容量够的话，直接执行6）
	 * 5）先操作链表删除队尾节点，在操作map删除对应节点
	 * 6）操作链表头插新节点，操作map存入新节点。
	 */
    class LRUCache {
        private HashMap<Integer, Node> map;
        private DoubleList cache;
        // 最大容量
        private int cap;

        public LRUCache(int capacity) {
            this.cap = capacity;
            map = new HashMap<>();
            cache = new DoubleList();
        }

        public int get(int key) {
            if (!map.containsKey(key))
                return -1;
            int val = map.get(key).val;//get得到一个node，node存有key、val
            // 利用 put 方法把该数据提前！！！！
            put(key, val);//若有该值会自动删除，再重新插入头部
            return val;
        }

        public void put(int key, int value) {
            // 先把新节点 x 做出来
            Node x = new Node(key, value);

            if (map.containsKey(key)) {//如果本来就存在
                // 删除旧的节点，新的插到头部
                cache.remove(map.get(key));
                cache.addFirst(x);
                // 更新 map 中对应的数据
                map.put(key, x);
            } else {//如果是新插入的：
                if (cap == cache.size()) {//如果数组满了:
                    // 删除链表最后一个数据
                    Node last = cache.removeLast();
                    map.remove(last.key);
                }
                // 直接添加到头部
                cache.addFirst(x);
                map.put(key, x);
            }

        }

        class Node {
            public int key, val;
            public Node next, prev;

            public Node(int k, int v) {
                this.key = k;
                this.val = v;
            }
        }

        class DoubleList {
            private Node head, tail; // 头尾虚节点
            private int size; // 链表元素数

            public DoubleList() {
                head = new Node(0, 0);
                tail = new Node(0, 0);
                head.next = tail;
                tail.prev = head;
                size = 0;
            }

            // 在链表头部添加节点 x
            public void addFirst(Node x) {
                x.next = head.next;
                x.prev = head;
                head.next.prev = x;
                head.next = x;
                size++;
            }

            // 删除链表中的 x 节点（x 一定存在）
            public void remove(Node x) {
                x.prev.next = x.next;
                x.next.prev = x.prev;
                size--;
            }

            // 删除链表中最后一个节点，并返回该节点
            public Node removeLast() {
                if (tail.prev == head)
                    return null;
                Node last = tail.prev;
                remove(last);
                return last;
            }

            // 返回链表长度
            public int size() {
                return size;
            }
        }

    }

    @Test
    public void test1() {

    }
}
