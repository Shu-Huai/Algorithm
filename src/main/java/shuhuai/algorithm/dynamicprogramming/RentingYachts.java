package shuhuai.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RentingYachts {
    private int[][] r;
    private int[] cost;
    private int result;
    private int[][] path;

    public RentingYachts(int[][] renting) {
        setData(renting);
    }

    public void setData(int[][] r) {
        this.r = new int[r.length][r[0].length];
        for (int i = 0; i < r.length; i++) {
            System.arraycopy(r[i], 0, this.r[i], 0, r[0].length);
        }
        this.cost = new int[r.length];
        this.result = 0;
        this.path = null;
    }

    public int dp() {
        int[] path = new int[r.length];
        cost[0] = 0;
        for (int i = 1; i < r.length; i++) {
            cost[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (cost[j] + r[j][i] < cost[i]) {
                    cost[i] = cost[j] + r[j][i];
                    path[i] = j;
                }
            }
        }
        result = cost[r.length - 1];
        List<int[]> list = new ArrayList<>();
        int i = cost.length - 1;
        while (i > 0) {
            list.add(new int[]{path[i], i});
            i = path[i];
        }
        this.path = list.reversed().toArray(new int[list.size()][]);
        return result;
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
            int[][] r = list.toArray(new int[0][]);
            RentingYachts ms = new RentingYachts(r);
            int result = ms.dp();
            System.out.println(result);
            for (int i = 0; i < ms.path.length; i++) {
                System.out.println(ms.path[i][0] + "->" + ms.path[i][1]);
            }
            input = sc.nextLine();
        }
    }
}