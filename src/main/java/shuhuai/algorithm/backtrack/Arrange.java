package shuhuai.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Arrange {
    private int[] nums;
    private int sum;
    private List<int[]> results;

    public Arrange(int[] nums) {
        setData(nums);
    }

    public void setData(int[] nums) {
        this.nums = new int[nums.length];
        System.arraycopy(nums, 0, this.nums, 0, nums.length);
        sum = 0;
        results = new ArrayList<>();
    }

    public int backtrack() {
        int[] x = new int[nums.length];
        System.arraycopy(nums, 0, x, 0, nums.length);
        backtrack(0, x);
        return sum;
    }

    private boolean diff(int t, int i, int[] x) {
        for (int j = t; j < i; j++) {
            if (x[j] == x[i]) {
                return false;
            }
        }
        return true;
    }

    public void backtrack(int t, int[] x) {
        if (t >= x.length) {
            int[] cur = new int[x.length];
            System.arraycopy(x, 0, cur, 0, x.length);
            results.add(cur);
            sum++;
            return;
        }
        for (int i = t; i < x.length; i++) {
            if (diff(t, i, x)) {
                int temp = x[i];
                x[i] = x[t];
                x[t] = temp;
                backtrack(t + 1, x);
                temp = x[i];
                x[i] = x[t];
                x[t] = temp;
            }
        }
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
            Arrange arrange = new Arrange(nums);
            System.out.println(arrange.backtrack());
            for (int i = 0; i < arrange.results.size(); i++) {
                for (int j = 0; j < arrange.results.get(i).length; j++) {
                    System.out.print(arrange.results.get(i)[j] + " ");
                }
                System.out.println();
            }
            input = sc.nextLine();
        }
    }
}