class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums1.length; i++) {
            boolean flag = false;
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == nums1[i]) {
                    for (int k = j; k < nums2.length; k++) {
                        if (nums2[k] > nums1[i]) {
                            nums1[i] = nums2[k];
                            flag = true;
                            break;
                        }
                    }
                    break;
                }
            }
            if (!flag) nums1[i] = -1;
        }
        return nums1;
    }
}
