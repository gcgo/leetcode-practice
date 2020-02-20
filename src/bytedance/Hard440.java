package bytedance;

import org.junit.Test;

/**！！！！！！！！！！！！！！！！！字节跳动必考！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
/**！！！！！！！！！！！！！！！！！字节跳动必考！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
/**！！！！！！！！！！！！！！！！！字节跳动必考！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
/**！！！！！！！！！！！！！！！！！字节跳动必考！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
/**！！！！！！！！！！！！！！！！！字节跳动必考！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
/**！！！！！！！！！！！！！！！！！字节跳动必考！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
/**！！！！！！！！！！！！！！！！！字节跳动必考！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
/**！！！！！！！！！！！！！！！！！字节跳动必考！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
 * 给定整数 n 和 k，找到 1 到 n 中字典序第 k 小的数字。
 *
 * 注意：1 ≤ k ≤ n ≤ 10^9。
 *
 * 示例 :
 *
 * 输入:
 * n: 13   k: 2
 *
 * 输出:
 * 10
 *
 * 解释:
 * 字典序的排列是 [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9]，所以第二小的数字是 10。
 * 思路：
 * 字典序，简而言之，就是根据数字的前缀进行排序。
 * 字典树思想，由于n≤ 10^9，所以根节点为1-9，9棵树，每一层从0到9（十叉树），先序遍历即是字典序。
 * 定义一个函数，getChildrenCount（current，n），返回的是以current该节点开头在所给范围n内的数字数量。
 * 如果返回的数量>K，则表示第k个数就在该current树下，否则再去找下一棵树。
 * current为当前值，向下一层即，current*10,向右移动一位，current+1。
 */
public class Hard440 {
    public int findKthNumber(int n, int k) {
        long current = 1; // 从字典第一位1开始查找
        k--; // 除去第一位
        // 当k变为0时，当前节点即是结果
        while (k > 0) {
            // 取得当前节点的所有子节点数量
            int childrenCount = getChildrenCount(current, n);
            // 如果k小于等于当前节点的所有子节点数，说明第k个数在当前节点的树目录中
            if (k <= childrenCount) {
                // 将当前节点纵向向下移动一层，消费掉k的一位
                k--;
                // 向下纵向移动，当前数变为原来十倍
                current *= 10;
            }
            // 如果k大于当前节点的所有子节点数，说明第k位在当前节点的所有子节点之后
            else if (k > childrenCount) {//可以直接写else，但为了代码清晰写成else if
                // 移动到树的右边一个节点，消费掉2个节点间的子节点数量
                k -= (childrenCount + 1);//加1是去掉了新换的树的根节点
                // 向右移动，当前数变为原来加一
                current++;
            }
        }
        return (int) current;
    }
    // 取得当前节点的所有子节点数
    private int getChildrenCount(long current, int n) {
        long childrenCount = 0; // 所有子节点数
        long maxChild = current; // 当前层理论最大子节点的值
        long maxChildrenCount = 1; // 当前层理论最多节点数量
        while (current * 10 <= n) {//因为一层是10个节点，每次循环是计算一层，一层一层统计累加。
            // 字典序是纵向优先，纵向超了，再看横向
            // 每向下一层，当前层的子节点数是上一层的10倍，再加上之前层的子节点为总数
            //先预赋值：
            childrenCount = childrenCount + maxChildrenCount * 10;
            // 当前层理论最多节点数量乘以10，为了下一层计算使用
            maxChildrenCount *= 10;
            // 当前层理论最大子节点的值为为上一层最大值乘以10再加上9
            maxChild = maxChild * 10 + 9;
            // 如果理论最大值大于n，则在当前层实际子节点数量=目前总个数-（理论最大值maxChild-所给的最大值n）
            if (maxChild > n) {
                childrenCount = childrenCount - (maxChild - n);
                break;
            }
            // 否则当前层都算上还不到n，则向下移动一层，当前数变为原来10倍
            current *= 10;
        }
        return (int) childrenCount;
    }

    //自己写一遍
    public int findKthNumber2(int n, int k) {
        long current = 1;
        k--;
        while (k>0){
            int childCount = getChildrenCount2(current,n);
            if (k<=childCount){
                //证明在这棵树下
                k--;
                current*=10;
            }else {
                //换一棵树
                k-=(childCount+1);
                current++;
            }
        }
        return (int) current;
    }

    private int getChildrenCount2(long current, int n) {
        //三个变量
        long childCount = 0;
        long maxThisLevel = current;
        long maxThisLevelCount=1;

        while (current*10<=n){
            childCount=childCount+maxThisLevelCount*10;
            maxThisLevelCount*=10;
            maxThisLevel=maxThisLevel*10+9;
            if (maxThisLevel>n){
                childCount=childCount-(maxThisLevel-n);
                break;
            }
            current*=10;
        }
        return (int) childCount;
    }

    @Test
    public void test1() {
        int kthNumber = findKthNumber2(13, 4);
        System.out.println(kthNumber);
    }
}
