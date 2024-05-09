package shuhuai.algorithm.probability;

import java.util.Scanner;

public class Majority {
    private int[] nums;
    private double e;
    private int result;

    public Majority(int[] nums, double e) {
        setData(nums, e);
    }

    public void setData(int[] nums, double e) {
        this.nums = new int[nums.length];
        System.arraycopy(nums, 0, this.nums, 0, nums.length);
        this.e = e;
        result = 0;
    }

    public int monteCarlo() {
        int k = (int) Math.ceil(Math.log(1.0 / e) / Math.log(2));
        for (int i = 0; i < k; i++) {
            Random random = new Random(System.currentTimeMillis());
            int j = (int) random.next(0, nums.length);
            int x = nums[j];
            int count = 0;
            for (int num : nums) {
                if (x == num) {
                    count++;
                }
            }
            if (count > nums.length / 2) {
                result = x;
                return result;
            }
        }
        result = -1;
        return result;
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
            double e = sc.nextDouble();
            Majority m = new Majority(nums, e);
            System.out.println(m.monteCarlo());
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}