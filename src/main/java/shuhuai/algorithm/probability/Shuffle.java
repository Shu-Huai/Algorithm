package shuhuai.algorithm.probability;

import java.util.Scanner;

public class Shuffle {
    private int[] nums;

    public Shuffle(int[] nums) {
        setData(nums);
    }

    public void setData(int[] nums) {
        this.nums = new int[nums.length];
        System.arraycopy(nums, 0, this.nums, 0, nums.length);
    }

    public int[] random() {
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < nums.length; i++) {
            int j = (int) random.next(i, nums.length);
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            int[] nums = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                nums[i] = Integer.parseInt(split[i]);
            }
            int n = scanner.nextInt();
            Shuffle shuffle = new Shuffle(nums);
            for (int i = 0; i < n; i++) {
                int[] result = shuffle.random();
                for (int k : result) {
                    System.out.print(k + " ");
                }
                System.out.println();
            }
            scanner.nextLine();
            input = scanner.nextLine();
        }
    }
}