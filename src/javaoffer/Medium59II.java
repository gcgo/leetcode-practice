package javaoffer;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * 示例 1：
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * 示例 2：
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *
 * 限制：
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 *
 *思路：题目沙雕，O（1）不可能。。。均摊O（1）完全不知道在说什么。。。。。
 * 双端队列思路：维护双端队列单调递减，这样队首总是最大值
 *
 */
public class Medium59II {
	class MaxQueue {
		Queue<Integer> que;
		Deque<Integer> deq;

		public MaxQueue() {
			que = new LinkedList<>();  //队列：插入和删除
			deq = new LinkedList<>();  //双端队列：获取最大值
		}

		public int max_value() {
			return deq.size()>0?deq.peek():-1;  //双端队列的队首为que的最大值
		}

		public void push_back(int value) {
			que.offer(value);  //value入队
			while(deq.size()>0 && deq.peekLast()<value){
				deq.pollLast();  //将deq队尾小于value的元素删掉
			}
			deq.offerLast(value);  //将value放在deq队尾
		}

		public int pop_front() {
			int tmp = que.size()>0?que.poll():-1;  //获得队首元素
			if(deq.size()>0 && tmp==deq.peek()){
				deq.poll();  //如果出队的元素是当前最大值，将deq的队首出队
			}
			return tmp;
		}
	}

	@Test
	public void test1() {

	}

}
