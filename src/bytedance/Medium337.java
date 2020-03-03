package bytedance;

import org.junit.Test;

/**
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。
 * 这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。
 * 一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 *
 * 示例 2:
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 *
 * 思路：父亲偷了，娃就不偷；父亲不偷，娃可以偷可以不偷
 *
 *
 */
public class Medium337 {
//*********************************************暴力递归，时间太长***************************************
    public int rob(TreeNode root) {
        if (root == null) return 0;
        return Math.max(robHelper(root, true), robHelper(root, false));
    }

    private int robHelper(TreeNode root, boolean robOrNot) {
        if (root == null) return 0;
        int money = 0;
        if (robOrNot) {//如果抢
            money += root.val;
            money += robHelper(root.left, !robOrNot);//娃就不能抢
            money += robHelper(root.right, !robOrNot);
        } else {//如果不抢
            money += Math.max(robHelper(root.left, robOrNot), robHelper(root.left, !robOrNot));//娃抢不抢都行
            money += Math.max(robHelper(root.right, robOrNot), robHelper(root.right, !robOrNot));
        }
        return money;
    }
    //*******************************动态规划***********************************************

    /**
     * 我们使用一个大小为2的数组来表示 int[] res = new int[2] 0代表不偷，1代表偷
     * 优化在不用根据不同情况去重复调用递归，产生重复计算
     * 而是一次计算把结果都返回来，根据不同情况操作结果
     */
    public int rob2(TreeNode root) {
        int[] result = robHelper2(root);
        return Math.max(result[0], result[1]);
    }

    public int[] robHelper2(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = robHelper2(root.left);
        int[] right = robHelper2(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    @Test
    public void test1() {
        TreeNode root1 = new TreeNode(4);
        root1.left = new TreeNode(1);
        root1.left.left = new TreeNode(2);
        root1.left.left.left = new TreeNode(3);

        TreeNode root2 = new TreeNode(4);
        root2.left = new TreeNode(1);
        root2.left.left = new TreeNode(2);
        root2.left.left.left = new TreeNode(3);
        System.out.println(rob(root1));
    }
}
