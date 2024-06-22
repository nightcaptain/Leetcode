package Leetcode.Leetcode.Array;

public class MinimumSizeSubarraySum_209 {
    public static int minSubArrayLen(int target, int[] nums) {

        int result = Integer.MAX_VALUE;
        int sum = 0;
        int subLength = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    subLength = j - i + 1;
                    result = result < subLength ? result : subLength;
                    break;

                }
            }
        }
        return result == Integer.MAX_VALUE ? 0 : result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1,2,4,3};
        int target = 7;

        System.out.println(minSubArrayLen(target,nums));
    }
}
