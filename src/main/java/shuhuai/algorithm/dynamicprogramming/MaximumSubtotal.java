package shuhuai.algorithm.dynamicprogramming;

import shuhuai.algorithm.utils.Printer;

import java.util.Arrays;
import java.util.Scanner;

public class MaximumSubtotal {
    public int[] enumeration(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int begin = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum > maxSum) {
                    maxSum = sum;
                    begin = i;
                    end = j;
                }
            }
        }
        return new int[]{begin, end};
    }

    public int[] cleverEnumeration(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int begin = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    begin = i;
                    end = j;
                }
            }
        }
        return new int[]{begin, end};
    }

    private int sum(int[] nums, int begin, int end) {
        int sum = 0;
        for (int i = begin; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }

    private int[] recursive(int[] nums, int begin, int end) {
        if (begin == end) {
            return new int[]{begin, end};
        }
        int mid = begin + (end - begin) / 2;
        int[] leftIndex = recursive(nums, begin, mid);
        int[] rightIndex = recursive(nums, mid + 1, end);
        int leftSum = sum(nums, leftIndex[0], leftIndex[1]);
        int rightSum = sum(nums, rightIndex[0], rightIndex[1]);
        int curBegin = 0;
        int temp = 0;
        int curLeftSum = Integer.MIN_VALUE;
        for (int i = mid; i >= begin; i--) {
            temp += nums[i];
            if (temp > curLeftSum) {
                curLeftSum = temp;
                curBegin = i;
            }
        }
        temp = 0;
        int curRightSum = Integer.MIN_VALUE;
        int curEnd = 0;
        for (int i = mid + 1; i <= end; i++) {
            temp += nums[i];
            if (temp > curRightSum) {
                curRightSum = temp;
                curEnd = i;
            }
        }
        int sum = curLeftSum + curRightSum;
        if (sum < leftSum || sum < rightSum) {
            if (leftSum > rightSum) {
                return leftIndex;
            }
            return rightIndex;
        }
        return new int[]{curBegin, curEnd};
    }

    public int[] recursive(int[] nums) {
        return recursive(nums, 0, nums.length - 1);
    }

    public int[] dp(int[] nums) {
        int max = Integer.MIN_VALUE;
        int b = -1;
        int begin = -1;
        int end = 0;
        int tempBegin = 0; // 用于记录临时的起始索引
        for (int i = 0; i < nums.length; i++) {
            if (b < 0) {
                b = nums[i];
                tempBegin = i; // 更新临时的起始索引
            } else {
                b += nums[i];
            }
            if (max < b) {
                max = b;
                begin = tempBegin; // 更新最大子数组的起始索引
                end = i;
            }
        }
        return new int[]{begin, end};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            int[] nums = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                nums[i] = Integer.parseInt(split[i]);
            }
            MaximumSubtotal ms = new MaximumSubtotal();
            int[] result = ms.enumeration(nums);
            new Printer<Integer>().printArray(Arrays.stream(nums).boxed().toArray(Integer[]::new),
                    result[0], result[1] + 1);
            System.out.println(ms.sum(nums, result[0], result[1]));
            result = ms.cleverEnumeration(nums);
            new Printer<Integer>().printArray(Arrays.stream(nums).boxed().toArray(Integer[]::new),
                    result[0], result[1] + 1);
            System.out.println(ms.sum(nums, result[0], result[1]));
            result = ms.recursive(nums);
            new Printer<Integer>().printArray(Arrays.stream(nums).boxed().toArray(Integer[]::new),
                    result[0], result[1] + 1);
            System.out.println(ms.sum(nums, result[0], result[1]));
            result = ms.dp(nums);
            new Printer<Integer>().printArray(Arrays.stream(nums).boxed().toArray(Integer[]::new),
                    result[0], result[1] + 1);
            System.out.println(ms.sum(nums, result[0], result[1]));
            input = sc.nextLine();
        }
    }
}