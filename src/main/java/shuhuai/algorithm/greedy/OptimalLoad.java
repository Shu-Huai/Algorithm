package shuhuai.algorithm.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class OptimalLoad {
    private int[] weights;
    private int count;
    private int capacity;

    public OptimalLoad(int[] weights, int capacity) {
        setData(weights, capacity);
    }

    public void setData(int[] weights, int capacity) {
        this.weights = new int[weights.length];
        System.arraycopy(weights, 0, this.weights, 0, weights.length);
        this.capacity = capacity;
    }

    public int greedy() {
        Arrays.sort(weights);
        int temp = capacity;
        for (int weight : weights) {
            if (weight > temp) {
                break;
            }
            temp -= weight;
            count++;
        }
        return count;
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
            int capacity = sc.nextInt();
            OptimalLoad optimalLoad = new OptimalLoad(weights, capacity);
            System.out.println(optimalLoad.greedy());
            for (int i = 0; i < optimalLoad.count; i++) {
                System.out.print(optimalLoad.weights[i] + " ");
            }
            System.out.println();
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}