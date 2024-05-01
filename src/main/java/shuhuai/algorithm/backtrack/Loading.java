package shuhuai.algorithm.backtrack;

import java.util.Scanner;

public class Loading {
    private int[] weights;
    private int[] capacity;
    private int max;
    private int[] loaded;
    private boolean[] visited;

    public Loading(int[] weights, int[] capacity) {
        setData(weights, capacity);
    }

    public void setData(int[] weights, int[] capacity) {
        this.weights = new int[weights.length];
        System.arraycopy(weights, 0, this.weights, 0, weights.length);
        this.capacity = new int[capacity.length];
        System.arraycopy(capacity, 0, this.capacity, 0, capacity.length);
        max = Integer.MIN_VALUE;
        loaded = new int[this.weights.length];
        visited = new boolean[this.weights.length];
    }

    public boolean backtrack() {
        int sum = 0;
        for (int weight : this.weights) {
            sum += weight;
        }
        backtrack(0, 0, new boolean[weights.length], sum, 1);
        sum = 0;
        for (int i = 0; i < this.weights.length; i++) {
            if (visited[i]) {
                loaded[i] = 1;
            } else {
                sum += this.weights[i];
            }
        }
        max = Integer.MIN_VALUE;
        backtrack(0, 0, new boolean[weights.length], sum, 2);
        for (int i = 0; i < this.weights.length; i++) {
            if (visited[i]) {
                loaded[i] = 2;
            }
            if (loaded[i] == 0) {
                return false;
            }
        }
        return true;
    }

    public void backtrack(int i, int curWeight, boolean[] x, int remain, int ship) {
        if (i >= weights.length) {
            if (curWeight > max) {
                max = curWeight;
                System.arraycopy(x, 0, visited, 0, x.length);
            }
            return;
        }
        if (loaded[i] == 0 && curWeight + weights[i] <= capacity[ship - 1]) {
            x[i] = true;
            backtrack(i + 1, curWeight + weights[i], x, remain - weights[i], ship);
        }
        if (curWeight + remain - weights[i] > max) {
            x[i] = false;
            backtrack(i + 1, curWeight, x, remain - weights[i], ship);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            int[] weights = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                weights[i] = Integer.parseInt(split[i]);
            }
            int capacityA = sc.nextInt();
            int capacityB = sc.nextInt();
            Loading l = new Loading(weights, new int[]{capacityA, capacityB});
            System.out.println(l.backtrack());
            for (int i = 0; i < l.weights.length; i++) {
                System.out.println(l.weights[i] + ": " + l.loaded[i]);
            }
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}