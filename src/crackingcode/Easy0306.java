package crackingcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 动物收容所。有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。
 * 在收养该收容所的动物时，收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，
 * 或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。
 * 请创建适用于这个系统的数据结构，实现各种操作方法，比如enqueue、dequeueAny、dequeueDog和dequeueCat。
 * 允许使用Java内置的LinkedList数据结构。
 *
 * enqueue方法有一个animal参数，animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗。
 *
 * dequeue*方法返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]。
 *
 * 示例1:
 *
 *  输入：
 * ["AnimalShelf", "enqueue", "enqueue", "dequeueCat", "dequeueDog", "dequeueAny"]
 * [[], [[0, 0]], [[1, 0]], [], [], []]
 *  输出：
 * [null,null,null,[0,0],[-1,-1],[1,0]]
 * 示例2:
 *
 *  输入：
 * ["AnimalShelf", "enqueue", "enqueue", "enqueue", "dequeueDog", "dequeueCat", "dequeueAny"]
 * [[], [[0, 0]], [[1, 0]], [[2, 1]], [], [], []]
 *  输出：
 * [null,null,null,null,[2,1],[0,0],[1,0]]
 * 说明:
 *
 * 收纳所的最大容量为20000
 *
 * 思路：两个队列，一个存猫，一个存狗。。
 * 沙雕题目，是以动物编号区分年龄的。。。。编号小的年龄大
 *
 *
 */
public class Easy0306 {
	class AnimalShelf {
		private Queue<Integer> catQueue;
		private Queue<Integer> dogQueue;

		public AnimalShelf() {
			catQueue = new LinkedList<>();
			dogQueue = new LinkedList<>();
		}

		public void enqueue(int[] animal) {
			if (animal[1] == 0) {//若存进来猫
				catQueue.offer(animal[0]);
			} else {//若存进来狗
				dogQueue.offer(animal[0]);
			}
		}

		public int[] dequeueAny() {
			if (dogQueue.isEmpty() && catQueue.isEmpty()) return new int[]{-1, -1};
			else if (dogQueue.isEmpty()) {
				return dequeueCat();
			} else if (catQueue.isEmpty()) {
				return dequeueDog();
			} else {
				if (dogQueue.peek() < catQueue.peek()) return dequeueDog();
				else return dequeueCat();
			}
		}

		public int[] dequeueDog() {
			if (dogQueue.isEmpty()) return new int[]{-1, -1};
			return new int[]{dogQueue.poll(), 1};
		}

		public int[] dequeueCat() {
			if (catQueue.isEmpty()) return new int[]{-1, -1};
			return new int[]{catQueue.poll(), 0};
		}
	}

	public static void main(String[] args) {
	}

}

