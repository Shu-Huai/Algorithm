package shuhuai.algorithm.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Backpack {
    public static class Item {
        private double weight;
        private double value;
        private double selected;

        public Item() {
            weight = 0;
            value = 0;
            selected = 0;
        }

        public Item(double weight, double value, double selected) {
            this.weight = weight;
            this.value = value;
            this.selected = selected;
        }
    }

    private Item[] items;
    private double capacity;
    private double result;

    public Backpack(Item[] items, double capacity) {
        setData(items, capacity);
    }

    public void setData(Item[] items, double capacity) {
        this.items = new Item[items.length];
        for (int i = 0; i < items.length; i++) {
            this.items[i] = new Item(items[i].weight, items[i].value, items[i].selected);
        }
        this.capacity = capacity;
        result = 0;
    }

    public double greedy() {
        Arrays.sort(items, (o1, o2) -> Double.compare(o2.value / o2.weight, o1.value / o1.weight));
        double temp = capacity;
        for (Item item : items) {
            if (item.weight > temp) {
                item.selected = temp / item.weight;
                result += item.selected * item.value;
                break;
            }
            item.selected = 1;
            temp -= item.weight;
            result += item.value;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            Item[] items = new Item[split.length];
            for (int i = 0; i < split.length; i++) {
                items[i] = new Item();
                items[i].weight = Double.parseDouble(split[i]);
            }
            split = sc.nextLine().split(" ");
            for (int i = 0; i < split.length; i++) {
                items[i].value = Double.parseDouble(split[i]);
            }
            double capacity = sc.nextDouble();
            Backpack bp = new Backpack(items, capacity);
            System.out.println(Math.round(bp.greedy() * 100) / 100.0);
            for (Item item : bp.items) {
                System.out.print(Math.round(item.selected * 100) / 100.0 + " ");
            }
            System.out.println();
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}