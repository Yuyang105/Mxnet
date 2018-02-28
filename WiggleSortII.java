/**

找中位数很重要，但是为了避免先排序引入O(nlogn)的复杂度，我们选择使用QuickSelect。

调用完QuickSelect以后，中位数之前的数都比中位数要大。

大于中位数的，从左往右放，放奇数位index。
小于中位数的，从右往左放，放偶数位index。
中位数最后放。

index:          0 1 2 3 4 5
samll half:     M   S   S  
large half:       L   L   M

Original idx:   0 1 2 3 4 5
Mapped idx:     1 3 5 0 2 4

为了得到新的index: (1 + 2 * index) % (n | 1); 



*/
class Solution {
    public void wiggleSort(int[] nums) {
        int median = findKthLargest(nums, (nums.length + 1) / 2);
        int n = nums.length;
        int left = 0, right = n - 1;
        int index = 0;
        while (index <= right) {
            if (nums[newIndex(index, n)] > median) {
                swap(nums, newIndex(left++, n), newIndex(index++, n));
            } else if (nums[newIndex(index, n)] < median) {
                swap(nums, newIndex(right--, n), newIndex(index, n));
            } else {
                index++;
            }
        }
    }
    
    private int newIndex(int index, int n) {
        return (1 + 2 * index) % (n | 1);
    }
    
    private int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length < k) return -1;
        
        int left = 0, right = nums.length - 1;
        while (true) {
            int pos = partition(nums, left, right);
            if (pos + 1 == k)
                return nums[pos];
            else if (pos + 1 > k)
                right = pos - 1;
            else
                left = pos + 1;
        }
    }
    
    private int partition(int[] nums, int left, int right) {
        int pivot = nums[left];
        int l = left + 1;
        int r = right;
        while (l <= r) {
            if (nums[l] < pivot && nums[r] > pivot)
                swap(nums, l, r);
            if (nums[l] >= pivot)
                l++;
            if (nums[r] <= pivot)
                r--;
        }
        swap(nums, left, r);
        return r;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
