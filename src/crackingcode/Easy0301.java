package crackingcode;

/**
 * 三合一。描述如何只用一个数组来实现三个栈。
 * 你应该实现push(stackNum, value)、pop(stackNum)、isEmpty(stackNum)、peek(stackNum)方法。
 * stackNum表示栈下标，value表示压入的值。
 * 构造函数会传入一个stackSize参数，代表每个栈的大小。
 *
 * 示例1:
 *  输入：
 * ["TripleInOne", "push", "push", "pop", "pop", "pop", "isEmpty"]
 * [[1], [0, 1], [0, 2], [0], [0], [0], [0]]
 *  输出：
 * [null, null, null, 1, -1, -1, true]
 * 说明：当栈为空时`pop, peek`返回-1，当栈满时`push`不压入元素。
 *
 * 示例2:
 *  输入：
 * ["TripleInOne", "push", "push", "push", "pop", "pop", "pop", "peek"]
 * [[2], [0, 1], [0, 2], [0, 3], [0], [0], [0], [0]]
 *  输出：
 * [null, null, null, null, 2, 1, -1, -1]
 *
 * 思路：数组大小应该是3*stackSize,当size满了时候，就push不进去了
 *
 */
public class Easy0301 {
	static class TripleInOne {
		private int[] stack;
		private int[] size;
		private int[] topIndex;
		int stackSize;

		public TripleInOne(int stackSize) {
			stack = new int[3 * stackSize];
			size = new int[3];
			topIndex = new int[3];
			this.stackSize = stackSize;
			//			for (int i = 1; i < 4; i++) {
			//				topIndex[i - 1] = i * stackSize - 1;
			//			}
			topIndex[0] = 1 * stackSize - 1;
			topIndex[1] = 2 * stackSize - 1;
			topIndex[2] = 3 * stackSize - 1;
		}

		public void push(int stackNum, int value) {
			if (size[stackNum] == stackSize) return;
			/*入栈*/
			stack[topIndex[stackNum]] = value;
			/*更新栈顶序号和size*/
			size[stackNum] += 1;
			topIndex[stackNum] -= 1;
		}

		public int pop(int stackNum) {
			if (size[stackNum] == 0) return -1;
			/*出栈*/
			int pop = stack[topIndex[stackNum] + 1];
			/*更新栈顶和size*/
			topIndex[stackNum] += 1;
			size[stackNum] -= 1;
			/*返回pop*/
			return pop;
		}

		public int peek(int stackNum) {
			if (size[stackNum] == 0) return -1;
			return stack[topIndex[stackNum] + 1];
		}

		public boolean isEmpty(int stackNum) {
			return size[stackNum] == 0;
		}
	}

	public static void main(String[] args) {

		TripleInOne tripleInOne = new TripleInOne(18);
		tripleInOne.push(2, 40);
		System.out.println(tripleInOne.pop(2));
		tripleInOne.push(0, 2);
		tripleInOne.push(0, 3);
		System.out.println(tripleInOne.pop(0));
		System.out.println(tripleInOne.pop(0));
		System.out.println(tripleInOne.peek(0));
	}

}

