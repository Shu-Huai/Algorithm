package shuhuai.algorithm.backtrack;

import java.util.Arrays;
import java.util.Scanner;

public class ZeroOneBackpack {
    public static class Item {
        private int weight;
        private int value;
        private boolean loaded;
    }

    private Item[] items;
    private int capacity;
    private int result;

    public ZeroOneBackpack(Item[] items, int capacity) {
        setData(items, capacity);
    }

    public void setData(Item[] items, int capacity) {
        this.items = new Item[items.length];
        for (int i = 0; i < items.length; i++) {
            this.items[i] = new Item();
            this.items[i].weight = items[i].weight;
            this.items[i].value = items[i].value;
            this.items[i].loaded = items[i].loaded;
        }
        this.capacity = capacity;
        result = 0;
    }

    public int backtrack() {
        Arrays.sort(items, (o1, o2) -> Double.compare((double) o2.value / o2.weight, (double) o1.value / o1.weight));
        backtrack(0, 0, 0, new boolean[items.length]);
        return result;
    }

    private double maxRemain(int t, int curWeight, int curValue) {
        int remain = capacity - curWeight;
        double max = curValue;
        while (t < items.length && items[t].weight <= remain) {
            remain -= items[t].weight;
            max += items[t].value;
            t++;
        }
        if (t < items.length) {
            max += (double) (items[t].value * remain) / items[t].weight;
        }
        return max;
    }

    public void backtrack(int t, int curWeight, int curValue, boolean[] x) {
        if (t >= items.length) {
            result = curValue;
            for (int i = 0; i < items.length; i++) {
                items[i].loaded = x[i];
            }
            return;
        }
        if (curWeight + items[t].weight <= capacity) {
            x[t] = true;
            backtrack(t + 1, curWeight + items[t].weight, curValue + items[t].value, x);
            x[t] = false;
        }
        if (maxRemain(t + 1, curWeight, curValue) > result) {
            backtrack(t + 1, curWeight, curValue, x);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            Item[] items = new Item[split.length];
            for (int i = 0; i < split.length; i++) {
                items[i] = new Item();
                items[i].weight = Integer.parseInt(split[i]);
            }
            split = sc.nextLine().split(" ");
            for (int i = 0; i < split.length; i++) {
                items[i].value = Integer.parseInt(split[i]);
            }
            int capacity = sc.nextInt();
            ZeroOneBackpack backpack = new ZeroOneBackpack(items, capacity);
            System.out.println(backpack.backtrack());
            for (int i = 0; i < backpack.items.length; i++) {
                System.out.println(backpack.items[i].weight + " " + backpack.items[i].value + " " + backpack.items[i].loaded);
            }
            System.out.println();
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}