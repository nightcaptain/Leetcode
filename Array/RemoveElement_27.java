package Leetcode.Leetcode.Array;

public class RemoveElement_27 {

    /*暴力做法
    public int removeElement(int[] nums, int val) {
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if(nums[i] == val) {
                for (int j = i+1; j < size; j++) {
                    nums[j - 1] = nums[j];
                }
                i--;
                size--;
            }

        }
        return size;
    }
     */

    //双指针（快慢指针）
    public int removeElement(int[] nums, int val) {
        int slowIndex = 0;
        for (int fastIndex = 0; fastIndex < nums.length; fastIndex++) {
            if(nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex ++;
            }
        }

        return slowIndex;
    }
}

