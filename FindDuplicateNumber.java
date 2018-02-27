/**

题意：给定一个 n + 1 长度的数组，每个元素在 1 到 n 之间。找到重复的元素。

两种做法：
1.      二分法     
        时间复杂度 O(nlogn)  空间复杂度 O(1)
        
        等于是抽屉原理，每次对半分盒子，然后塞不进去的就加盒子。 

2.      Linked Cycle List
        时间复杂度 O(1)      空间复杂度 O(1)

value      2 1 3  + 1
index      0 1 2  + 3

           0 - 2    0 - 2 - 3               无环
           1 - 1    0 - 2 - 3 - 1 - 1 - 1   有环      有重复值
           2 - 3
           3 - 1
        
*/
class Solution {
    // 二分法
    public int findDuplicate1(int[] nums) {
        int min = 0;
        int max = nums.length - 1;
        while (min <= max) {
            int mid = min + (max - min) / 2;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid)
                    count++;
            }
            if (count > mid) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return min;
    }
    
    // 找环
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
