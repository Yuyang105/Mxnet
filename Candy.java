/**
两种做法

第一种，需要额外空间，过两次数组。
    先从前往后看，如果比前一个rating高，比前一个孩子多发一颗。
    再从后往前看，如果比后一个rating高，要么维持自己的candies数量，要么比后一个孩子多发一颗。

第二种，O(n) time, O(1) space, one pass。
    其实，这题只有三种情况：
        1. cur rating > pre rating  =>  cur value = pre value + 1;
        
        ratings:    |       candies:    |
                   ||                  ||
                  |||                 |||
            
              
        2. cur rating == pre rating =>  cur value = 1;
        
        ratings:            candies:    
                   ||                  |
                  |||                 |||
                  
        3. cur rating < pre rating => 我们就暂时不知道怎么处理
        
        ratings:    |       candies:     |
                   |||                  |||
                  |||||                ||||||
        
            这种情况，我们就开始count，它连续下降了多少次。还有prev的大小，如果prev不够我们连续下降的空间，增加prev
            countdown的大小：countDown * (countDown + 1) / 2; ... 这个就是最高点是count，最低点是1，求和公式...
            prev不够高的话： countdown - prev + 1;

*/

/**
There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
*/
class Solution {
    // One pass, O(1) space
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        
        int prev = 1, countdown = 0, res = 1;
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                if (countdown > 0) {
                    // 求和公式
                    res += countdown * (countdown + 1) / 2;
                    // prev is not tall enough
                    if (countdown >= prev) {
                        res += countdown - prev + 1;
                    }
                    prev = 1;
                    countdown = 0;
                }
                prev = ratings[i] == ratings[i - 1] ? 1 : prev + 1;
                res += prev;
            } else {
                countdown++;
            }
        }
        // 还有多的countdown的话
        if (countdown > 0) {
            res += countdown * (countdown + 1) / 2;
            if (countdown >= prev) {
                res += countdown - prev + 1;
            }
        }
        return res; 
    }
    
    
    // Two pass, additional space
    public int candy1(int[] ratings) {
        if (ratings == null || ratings.length == 0) return 0;
        
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);
        
        for (int i = 1; i < candies.length; i++) {
            if (ratings[i] > ratings[i - 1])
                candies[i] = candies[i - 1] + 1;
        }
        
        int res = candies[candies.length - 1];
        for (int i = candies.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            
            res += candies[i];
        }

        return res;
    }
}
