package bytedance;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现一个拥有如下方法的线程安全有限阻塞队列：
 * <p>
 * BoundedBlockingQueue(int capacity) 构造方法初始化队列，其中capacity代表队列长度上限。
 * void enqueue(int element) 在队首增加一个element. 如果队列满，调用线程被阻塞直到队列非满。
 * int dequeue() 返回队尾元素并从队列中将其删除. 如果队列为空，调用线程被阻塞直到队列非空。
 * int size() 返回当前队列元素个数。
 * 你的实现将会被多线程同时访问进行测试。
 * 每一个线程要么是一个只调用enqueue方法的生产者线程，要么是一个只调用dequeue方法的消费者线程。
 * size方法将会在每一个测试用例之后进行调用。
 * <p>
 * 请不要使用内置的有限阻塞队列实现，否则面试将不会通过。
 * <p>
 * 示例 1:
 * 输入:
 * 1
 * 1
 * ["BoundedBlockingQueue","enqueue","dequeue","dequeue","enqueue","enqueue","enqueue","enqueue","dequeue"]
 * [[2],[1],[],[],[0],[2],[3],[4],[]]
 * <p>
 * 输出:
 * [1,0,2,2]
 * <p>
 * 解释:
 * 生产者线程数目 = 1
 * 消费者线程数目 = 1
 * <p>
 * BoundedBlockingQueue queue = new BoundedBlockingQueue(2);   // 使用capacity = 2初始化队列。
 * <p>
 * queue.enqueue(1);   // 生产者线程将1插入队列。
 * queue.dequeue();    // 消费者线程调用dequeue并返回1。
 * queue.dequeue();    // 由于队列为空，消费者线程被阻塞。
 * queue.enqueue(0);   // 生产者线程将0插入队列。消费者线程被解除阻塞同时将0弹出队列并返回。
 * queue.enqueue(2);   // 生产者线程将2插入队列。
 * queue.enqueue(3);   // 生产者线程将3插入队列。
 * queue.enqueue(4);   // 生产者线程由于队列长度已达到上限2而被阻塞。
 * queue.dequeue();    // 消费者线程将2从队列弹出并返回。生产者线程解除阻塞同时将4插入队列。
 * queue.size();       // 队列中还有2个元素。size()方法在每组测试用例最后调用。
 *  
 * 示例 2:
 * 输入:
 * 3
 * 4
 * ["BoundedBlockingQueue","enqueue","enqueue","enqueue","dequeue","dequeue","dequeue","enqueue"]
 * [[3],[1],[0],[2],[],[],[],[3]]
 * <p>
 * 输出:
 * [1,0,2,1]
 * <p>
 * 解释:
 * 生产者线程数目 = 3
 * 消费者线程数目 = 4
 * <p>
 * BoundedBlockingQueue queue = new BoundedBlockingQueue(3);   // 使用capacity = 3初始化队列。
 * <p>
 * queue.enqueue(1);   // 生产者线程P1将1插入队列。
 * queue.enqueue(0);   // 生产者线程P2将0插入队列。
 * queue.enqueue(2);   // 生产者线程P3将2插入队列。
 * queue.dequeue();    // 消费者线程C1调用dequeue。
 * queue.dequeue();    // 消费者线程C2调用dequeue。
 * queue.dequeue();    // 消费者线程C3调用dequeue。
 * queue.enqueue(3);   // 其中一个生产者线程将3插入队列。
 * queue.size();       // 队列中还有1个元素。
 * <p>
 * 由于生产者/消费者线程的数目可能大于1，我们并不知道线程如何被操作系统调度，即使输入看上去隐含了顺序。
 * 因此任意一种输出[1,0,2]或[1,2,0]或[0,1,2]或[0,2,1]或[2,0,1]或[2,1,0]都可被接受。
 * <p>
 * 思路：
 */
public class Medium1188 {
    static class BoundedBlockingQueue {
        private volatile int size;
        private volatile int capacity;
        private Node head;//虚拟头结点
        private Node tail;//虚拟尾结点
        private final Lock lock = new ReentrantLock();
        Condition notEmpty = lock.newCondition();
        Condition notFull = lock.newCondition();

        class Node {
            int val;
            Node next;
            Node prev;

            Node(int val) {
                this.val = val;
            }
        }

        public BoundedBlockingQueue(int capacity) {
            this.capacity = capacity;
            head = new Node(-1);
            tail = new Node(-1);
            head.next = tail;
            tail.prev = head;
        }

        public void enqueue(int element) throws InterruptedException {
            lock.lock();
            try {
                while (size == capacity) notFull.await();//暂停入队方法
                Node newNode = new Node(element);
                //新节点头插
                newNode.next = head.next;
                head.next.prev = newNode;
                head.next = newNode;
                newNode.prev = head;
                notEmpty.signal();//唤醒出队方法
                size++;
            } finally {
                lock.unlock();
            }
        }

        public int dequeue() throws InterruptedException {
            lock.lock();
            try {
                while (size == 0) notEmpty.await();//暂停出队方法
                //尾部出队
                Node node = tail.prev;
                tail.prev = null;
                node.next = null;
                tail = node;
                size--;
                notFull.signal();//唤醒人队方法
                return node.val;
            } finally {
                lock.unlock();
            }
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {//跑多线程不要用Test类，就用main函数
        BoundedBlockingQueue blockingQueue = new BoundedBlockingQueue(2);

        Thread t1 = new Thread(() -> {
            try {
                blockingQueue.enqueue(1);
                blockingQueue.enqueue(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                blockingQueue.enqueue(3);
                blockingQueue.enqueue(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t3 = new Thread(() -> {
            try {
                for (int i = 1; i <= 4; i++) {
                    int x = blockingQueue.dequeue();
                    System.out.println(x);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t1.start();
        t2.start();
        t3.start();
    }
}
