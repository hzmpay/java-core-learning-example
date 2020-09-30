package com.hzm.freestyle.leetcode.easy;

import com.alibaba.fastjson.JSONObject;

/**
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。  你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * 示例:  给定 nums = [2, 7, 11, 15], target = 9  因为 nums[0] + nums[1] = 2 + 7 = 9 所以返回 [0, 1]
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/two-sum 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author Hezeming
 * @version 1.0
 * @date 2019年11月15日
 */
public class Solution {

    public static void main(String[] args) {

//        System.out.println(JSONObject.toJSONString(twoSum1(new int[]{1, 2, 3, 4, 5, 6}, 11)));
//        System.out.println(JSONObject.toJSONString(twoSum2(IntStream.rangeClosed(1, 100000000).toArray(), 56165149)));
//        System.out.println(JSONObject.toJSONString(twoSum1(IntStream.rangeClosed(1, 100000000).toArray(), 56165149)));
        System.out.println(JSONObject.toJSONString(twoSum(new int[]{3,2,4}, 6)));

    }

    /**
     * 两边循环，根据差值找到对应的值
     *
     * @param nums
     * @param target
     * @return int[]
     * @author Hezeming
     */
    public static int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int diffNum = target - nums[i];
            for (int j = 0; j < nums.length; j++) {
                if (diffNum == nums[j] && i != j) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

}
