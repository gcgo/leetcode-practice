package bytedance;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 * <p>
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，
 * 它应该在插入新项目之前，使最不经常使用的项目无效。
 * 在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 * <p>
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 * <p>
 * 示例：
 * <p>
 * LFUCache cache = new LFUCache( 2 );//capacity (缓存容量)
 * <p>
 * cache.put(1,1);
 * cache.put(2,2);
 * cache.get(1);       // 返回 1
 * cache.put(3,3);    // 去除 key 2
 * cache.get(2);       // 返回 -1 (未找到key 2)
 * cache.get(3);       // 返回 3
 * cache.put(4,4);    // 去除 key 1
 * cache.get(1);       // 返回 -1 (未找到 key 1)
 * cache.get(3);       // 返回 3
 * cache.get(4);       // 返回 4
 * <p>
 * 思路：和LRU很像，可以用链表维护顺序，hashmap存具体值。
 */
public class Hard460 {
    public class LFUCache {
        class Node {//双向链表节点
            int key, val, cnt;//cnt是当前节点的频率
            Node prev, next;

            Node(int key, int val) {
                this.key = key;
                this.val = val;
                cnt = 1;
            }
        }

        class DLList {//双向链表
            Node head, tail;//虚拟头尾节点
            int size;

            DLList() {
                head = new Node(0, 0);
                tail = new Node(0, 0);
                head.next = tail;
                tail.prev = head;
            }

            void add(Node node) {//添加，头插
                head.next.prev = node;
                node.next = head.next;
                node.prev = head;
                head.next = node;
                size++;
            }

            void remove(Node node) {//删除，原地
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
            }

            Node removeLast() {//删除尾结点，tail.prev
                if (size > 0) {
                    Node node = tail.prev;
                    remove(node);
                    return node;
                } else return null;
            }
        }

        int capacity, size, min;//min是当前LFU中节点访问的最小频率
        Map<Integer, Node> nodeMap;//缓存
        Map<Integer, DLList> countMap;//把同一频率的节点放在一个链表里维护

        public LFUCache(int capacity) {
            this.capacity = capacity;
            nodeMap = new HashMap<>();
            countMap = new HashMap<>();
        }

        public int get(int key) {//获取数据
            Node node = nodeMap.get(key);
            if (node == null) return -1;
            update(node);//刷新频率
            return node.val;
        }

        public void put(int key, int value) {//存数据
            if (capacity == 0) return;
            Node node;
            if (nodeMap.containsKey(key)) {//如果map里存了该key，就更新value
                node = nodeMap.get(key);
                node.val = value;
                update(node);//然后刷新频率，具体方法在后面
            } else {//如果没存过该key，则新建一个节点存进去
                node = new Node(key, value);
                nodeMap.put(key, node);//加入缓存
                if (size == capacity) {//如果缓存没空间了
                    DLList lastList = countMap.get(min);//删除使用频率最小的那个节点，链表、hashmap双删！！！
                    nodeMap.remove(lastList.removeLast().key);
                    size--;
                }
                size++;
                min = 1;//新节点频率为1
                DLList newList = countMap.getOrDefault(node.cnt, new DLList());//从频率哈希表中找对应该频率的链表
                newList.add(node);//添加节点
                countMap.put(node.cnt, newList);//存入频率哈希表中
            }
        }

        private void update(Node node) {//更新频率
            DLList oldList = countMap.get(node.cnt);
            oldList.remove(node);//先把带原始频率的节点从原来链表里删除
            if (node.cnt == min && oldList.size == 0) min++;//如果该节点就是缓存中访问最少的那个节点，则更新最小频率
            node.cnt++;//更新节点频率
            DLList newList = countMap.getOrDefault(node.cnt, new DLList());//将该节点存入对应频率的链表
            newList.add(node);
            countMap.put(node.cnt, newList);//把链表存入频率哈希表
        }
    }

    //**************************************************************************
    @Test
    public void test1() {

    }

}
