package shuhuai.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CircuitBoardArrangement {
    private int[][] b;
    private int min;
    private List<int[]> results;

    public CircuitBoardArrangement(int[][] b) {
        setData(b);
    }

    public void setData(int[][] b) {
        this.b = new int[b.length][b[0].length];
        for (int i = 0; i < b.length; i++) {
            System.arraycopy(b[i], 0, this.b[i], 0, b[0].length);
        }
        min = Integer.MAX_VALUE;
        results = new ArrayList<>();
    }

    public int backtrack() {
        int[] x = new int[b.length];
        for (int i = 0; i < b.length; i++) {
            x[i] = i;
        }
        backtrack(0, 0, x, new int[b.length], new int[b.length]);
        return min;
    }

    public void backtrack(int t, int cur, int[] x, int[] now, int[] total) {
        if (t >= b.length) {
            if (cur < min) {
                min = cur;
                int[] temp = new int[b.length];
                System.arraycopy(x, 0, temp, 0, b.length);
                results.add(temp);
            }
            return;
        }
        for (int j = t; j < b.length; j++) {
            int temp = 0;
            for (int k = 0; k < b[j].length; k++) {
                now[k] += b[x[j]][k];
                if (now[k] > 0 && total[k] != now[k]) {
                    temp++;
                }
            }
            if (cur > temp) {
                temp = cur;
            }
            if (temp < min) {
                int swap = x[t];
                x[t] = x[j];
                x[j] = swap;
                backtrack(t + 1, temp, x, now, total);
                swap = x[t];
                x[t] = x[j];
                x[j] = swap;
            }
            for (int k = 0; k < b[j].length; k++) {
                now[k] -= b[x[j]][k];
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            List<int[]> list = new ArrayList<>();
            while (!input.isEmpty()) {
                String[] split = input.split(" ");
                int[] nums = new int[split.length];
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = Integer.parseInt(split[i]);
                }
                list.add(nums);
                input = sc.nextLine();
            }
            int[][] b = new int[list.size()][list.getFirst().length];
            for (int i = 0; i < list.size(); i++) {
                System.arraycopy(list.get(i), 0, b[i], 0, list.getFirst().length);
            }
            CircuitBoardArrangement c = new CircuitBoardArrangement(b);
            System.out.println(c.backtrack());
            for (int i = 0; i < c.results.size(); i++) {
                for (int j = 0; j < c.results.get(i).length; j++) {
                    System.out.print(c.results.get(i)[j] + " ");
                }
                System.out.println();
            }
            input = sc.nextLine();
        }
    }
}