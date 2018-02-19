/** 

用蓄水池抽样的做法做，蓄水池抽样的代码在最下面。

这个题也就是起一个count，看一共有多少个符合要求的target。再基于count，生成一个[0 ~ count-1]的随机数。
基于随机数，以1/count的概率，返回结果。

===== 水塘抽样 =====
Reservoir sampling is a family of randomized algorithms for randomly choosing k samples 
from a list of n items, where n is either a very large or unknown number. Typically n 
is large enough that the list doesn’t fit into main memory. For example, a list of search 
queries in Google and Facebook.

在一个给定长度的数组中随机等概率抽取一个数据很容易，但如果面对的是长度未知的海量数据流呢？
蓄水池采样(Reservoir Sampling)算法就是来解决这个问题的, 它在分析一些大数据集的时候非常有用。

算法描述
1. 先选取数据流中的前k个元素，保存在集合A中；
2. 从第j（k + 1 <= j <= n）个元素开始，每次先以概率p = k/j选择是否让第j个元素留下。
   若j被选中，则从A中随机选择一个元素并用该元素j替换它；否则直接淘汰该元素；
3. 重复步骤2直到结束，最后集合A中剩下的就是保证随机抽取的k个元素。

(1) 当只选择1个样本的时候
    我们总是选择第一个对象，以1/2的概率选第二个，以1/3的概率选第三个，以此类推，以1/m的概率选第m个。
    这样，当该过程结束时，每一个对象都具有相同的被选概率，即1/n。
    
    证明：
        第m个对象最终被选的概率 = 选择m的概率 * 后面所有对象不被选的概率，即：
        P(m) = 1/m * (m/m+ 1 * m+1/m+2 * ... * n-1/n)
             = 1/n

(2) 当选择k个样本的时候
    类似思路，先把读到的前k个对象放入『水库』。
    从第k+1个对象开始，以k/k+1的概率选择第k+1个兑现，以k/k+2的概率选择第k+2个对象，以此类推，以k/m的概率选择第m个。
    如果m被选中，我们随机替换水库中的一个对象，最终每个对象被选中的概率均为k/n。
    
    证明：
        第m个对象最终被选的概率 = 选择m的概率 * (后面对象不被选的概率 + 后面对象被选的概率 * 不替换掉m的概率)
        P(m) = k/m * [(m+1-k/m+1 + k/m+1 * k-1/k) * (m+2-k/m+2 + k/m+2 * k-1/k) * ... * (n-k/n + k/n *k-1/k)]
             = k/m * m/n
             = k/n

(3) 分布式水库抽样
    基本的蓄水池抽样要求对数据流进行顺序读取，要进行容量为k的分布式蓄水池抽样（前面讨论的容量都为1），对于集合中的每一个元素，
    都产生一个[0 ~ 1]的随机数，之后选取随机值最大的前k个元素。这种方法在对大数据集进行分层抽样的时候非常管用。
    
    加权分布式水库抽样
        集合中的数据是有权重的，算法希望数据被抽样选中的概率和该数据的权重成比例。对于每个数据计算一个0-1的值R，
        并求r的n次方根作为该数据的新的R值。这里的n就是该数据的权重。最终算法返回前k个R值最高的数据然后返回。
        根据计算规则，权重越大的数据计算所得的R值越接近1，所以越有可能被返回。

*/

/**
Given an array of integers with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Note:
The array size can be very large. Solution that uses too much extra space will not pass the judge.

Example:

int[] nums = new int[] {1,2,3,3,3};
Solution solution = new Solution(nums);

// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(3);

// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(1);
*/
class Solution {
    private int[] nums;
    private Random r;
    
    public Solution(int[] nums) {
        this.nums = nums;
        r = new Random();
    }
    
    public int pick(int target) {
        int res = -1, count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) continue;

            count++;                         // 取值范围: [0 - count - 1]
            if (r.nextInt(count) == 0)       // random < k, k只取一个
                res = i;
        }
        return res;
    }
}

class ReservoirSampling {
    // a function to randomly select k items from stream[0 .. n -1]
    public int[] reserviorSampling(int[] stream, int k) {
        Random r = new Random();
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = stream[i];
        }
        for (int i = k; i < stream.length; i++) {
            int random = r.nextInt(i + 1);          // 取值范围: [0 - i] 
            if (random < k)
                res[random] = stream[i];
        }
        return res;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
