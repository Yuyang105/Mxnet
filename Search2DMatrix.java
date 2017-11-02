class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int start = 0, end = matrix.length * matrix[0].length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[mid / matrix[0].length][mid % matrix[0].length] == target) return true;
            else if (matrix[mid / matrix[0].length][mid % matrix[0].length] > target) end = mid;
            else start =  mid;
        }
        if (matrix[end / matrix[0].length][end % matrix[0].length] == target) return true;
        else if (matrix[start / matrix[0].length][start % matrix[0].length] == target) return true;
        else return false;
    }
}
