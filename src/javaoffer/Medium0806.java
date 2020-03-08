package javaoffer;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 在经典汉诺塔问题中，有 3 根柱子及 N 个不同大小的穿孔圆盘，盘子可以滑入任意一根柱子。
 * 一开始，所有盘子自上而下按升序依次套在第一根柱子上(即每一个盘子只能放在更大的盘子上面)。
 * 移动圆盘时受到以下限制:
 * (1) 每次只能移动一个盘子;
 * (2) 盘子只能从柱子顶端滑出移到下一根柱子;
 * (3) 盘子只能叠在比它大的盘子上。
 *
 * 请编写程序，用栈将所有盘子从第一根柱子移到最后一根柱子。
 * 你需要原地修改栈。
 *
 * 示例1:
 *  输入：A = [2, 1, 0], B = [], C = []
 *  输出：C = [2, 1, 0]
 * 示例2:
 *  输入：A = [1, 0], B = [], C = []
 *  输出：C = [1, 0]
 * 提示:
 *
 * A中盘子的数目不大于14个。
 *
 * 思路：
 */
public class Medium0806 {
	public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
		Deque<Integer> stackA = new ArrayDeque<>();
		Deque<Integer> stackB = new ArrayDeque<>();
		Deque<Integer> stackC = new ArrayDeque<>();
		for (Integer n : A) {
			stackA.push(n);
		}
		move(stackA.size(), stackA, stackB, stackC);
		while (!stackC.isEmpty()) C.add(stackC.removeLast());
	}

	private void move(int size, Deque<Integer> stackA, Deque<Integer> stackB, Deque<Integer> stackC) {
		if (size == 1) {
			stackC.push(stackA.pop());
			return;
		}
		move(size - 1, stackA, stackC, stackB);
		stackC.push(stackA.pop());
		move(size - 1, stackB, stackA, stackC);
	}

	/*不用栈-------------------------------*/
	public void hanota2(List<Integer> A, List<Integer> B, List<Integer> C) {
		move2(A.size(), A, B, C);
	}

	public void move2(int n, List<Integer> A, List<Integer> B, List<Integer> C) {
		/*如果只有一个盘子，则直接从A移动到C*/
		if (n == 1) {
			C.add(A.remove(A.size() - 1));
			//注意：题目给的盘子是从大到小给的，所以这里remove的是【A.size() - 1】
			return;
		}
		/*否则，先把上面n-1个盘子从A移动到B*/
		move2(n - 1, A, C, B);
		/*接着，把最后一个盘子从A移动到C*/
		C.add(A.remove(A.size() - 1));
		/*最后，把B上的n-1个盘子移动到C上*/
		move2(n - 1, B, A, C);
	}

	@Test
	public void test1() {
		List<Integer> A = new ArrayList<>();
		List<Integer> B = new ArrayList<>();
		List<Integer> C = new ArrayList<>();
		A.add(2);
		A.add(1);
		A.add(0);
		hanota(A, B, C);
		System.out.println(C);
	}
}
