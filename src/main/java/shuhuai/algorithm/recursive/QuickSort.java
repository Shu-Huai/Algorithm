package shuhuai.algorithm.recursive;

import java.util.Arrays;
import java.util.Scanner;

public class QuickSort {
    public void recursive(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = nums[left];
        int i = left;
        int j = right;
        while (i < j) {
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        nums[left] = nums[i];
        nums[i] = pivot;
        recursive(nums, left, i - 1);
        recursive(nums, i + 1, right);
    }

    public void recursive(int[] nums) {
        recursive(nums, 0, nums.length - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] arr = input.split(" ");
            int[] nums = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                nums[i] = Integer.parseInt(arr[i]);
            }
            new QuickSort().recursive(nums);
            System.out.println(Arrays.toString(nums));
            input = sc.nextLine();
        }
    }
}