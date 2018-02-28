/**

由于从左到右是排好序的，并且每行行首比上一行行位要大，也就是完全排好序了。
那么我们可以扯直了，直接做二分法。

看作是长度是 row * col 的数组，index / col => row, index % col => col。

*/
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return false;
        
        // 二分法，row.len = matrix[0].length
        int low = 0, high = matrix.length * matrix[0].length - 1;
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            if (matrix[mid / matrix[0].length][mid % matrix[0].length] == target)
                return true;
            if (matrix[mid / matrix[0].length][mid % matrix[0].length] < target)
                low = mid;
            else
                high = mid;
        }
        if (matrix[low / matrix[0].length][low % matrix[0].length] == target)
            return true;
        if (matrix[high / matrix[0].length][high % matrix[0].length] == target)
            return true;
        return false;
    }
}
