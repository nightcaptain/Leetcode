package Leetcode.Leetcode.Array;

import java.util.Arrays;

public class SquaresOfASortedArray_209 {

    /* 暴力解法
    public static int[] sortedSquares(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            nums[i] *= nums[i];
        }
        Arrays.sort(nums);

        return nums;
    }
    */

    public static int[] sortedSquares(int[] nums) {

        int right = nums.length - 1;
        int left = 0;
        int result[] = new int[nums.length];
        int index = result.length - 1;

        while(left <= right) {
            if(nums[left] * nums[left] > nums[right] * nums[right]) {
                result[index--] = nums[left] * nums[left];
                left++;
            } else {
                result[index--] = nums[right] * nums[right];
                right--;
            }
        }
        return result;

    }

    public static void main(String[] args) {
        int[] nums = new int[]{-4,-1,0,3,10};

        System.out.println(Arrays.toString(sortedSquares(nums)));
    }
}
