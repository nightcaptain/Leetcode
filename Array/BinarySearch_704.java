//leetcode：https://leetcode.cn/problems/binary-search/description/
package Leetcode.Leetcode.Array;

public class BinarySearch_704 {

    /*
    第一种写法：区间为[left,right]
    public static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        //这里使用<=是因为left == right在区间[left,right]是有意义的
        while(left <= right) {
            int middle = left + ((right - left) / 2);

            if(nums[middle] > target) {
                right = middle - 1;
            } else if (nums[middle] < target) {
                left = middle + 1;
            } else {
                return middle;
            }
        }
        return -1;
    }
    */

    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int middle = left + ((right - left) >> 1);//此处的 >> 是位运算符，相当与 x/2^1并向下取整
            if(nums[middle] == target) {
                return middle;
            } else if (nums[middle] > target) {
                right = middle;
            } else if (nums[middle] < target) {
                left = middle + 1;

            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,5,7,9,11};
        int target = 11;
        System.out.println(search(nums,target));
    }
}
