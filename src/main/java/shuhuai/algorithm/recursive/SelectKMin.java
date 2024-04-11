package shuhuai.algorithm.recursive;

import java.util.Scanner;

public class SelectKMin {
    public int recursive(int[] nums, int left, int right, int k) {
        if (left == right) {
            return nums[left];
        }
        int pivot = nums[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            if (i < j) {
                nums[i] = nums[j];
                i++;
            }
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            if (i < j) {
                nums[j] = nums[i];
                j--;
            }
        }
        nums[i] = pivot;
        j = i - left + 1;
        if (k <= j) {
            return recursive(nums, left, i, k);
        }
        return recursive(nums, i + 1, right, k - j);
    }

    public int recursive(int[] nums, int k) {
        return recursive(nums, 0, nums.length - 1, k);
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
            int k = sc.nextInt();
            System.out.println(new SelectKMin().recursive(nums, k));
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}