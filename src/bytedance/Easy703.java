package bytedance;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * <p>
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。
 * 每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 * <p>
 * 示例:
 * <p>
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 * 说明:
 * 你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 * <p>
 * 思路：优先队列，小顶堆
 * 优先队列保证每次添加数据后都是升序（默认），这样要求第K大的数，我只要让队列容量保持K，那么队首就是第k大的数。
 */
public class Easy703 {
    class KthLargest {
        final PriorityQueue<Integer> q;
        final int k;

        public KthLargest(int k, int[] a) {
            this.k = k;
            q = new PriorityQueue<>(k);
            for (int n : a)//入队
                add(n);
        }

        public int add(int val) {
            q.offer(val);
            if (q.size() > k) q.poll();//队列容量大于K了就弹出去
            return q.peek();
        }
    }

    @Test
    public void test1() {
        int[] arr = new int[]{4, 5, 8, 2, 9};
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i : arr) {
            queue.offer(i);
        }
        System.out.println(queue);
    }
}
