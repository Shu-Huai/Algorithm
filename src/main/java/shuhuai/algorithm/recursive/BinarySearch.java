package shuhuai.algorithm.recursive;

import java.util.Scanner;

public class BinarySearch {
    public int loop(int[] nums, int target) {
        int length = nums.length;
        int left = 0;
        int right = length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }

    public int recursive(int[] nums, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] > target) {
            return recursive(nums, target, left, mid - 1);
        }
        return recursive(nums, target, mid + 1, right);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.isEmpty()) {
            String[] arr = input.split(" ");
            int[] nums = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                nums[i] = Integer.parseInt(arr[i]);
            }
            int target = scanner.nextInt();
            System.out.println(new BinarySearch().loop(nums, target));
            System.out.println(new BinarySearch().recursive(nums, target, 0, nums.length - 1));
            scanner.nextLine();
            input = scanner.nextLine();
        }
    }
}