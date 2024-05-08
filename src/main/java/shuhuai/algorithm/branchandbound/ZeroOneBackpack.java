package shuhuai.algorithm.branchandbound;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ZeroOneBackpack {
    public static class Item {
        private int weight;
        private int value;
        private boolean loaded;

        public Item() {
            this.weight = 0;
            this.value = 0;
            this.loaded = false;
        }

        public Item(int weight, int value, boolean loaded) {
            this.weight = weight;
            this.value = value;
            this.loaded = loaded;
        }
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

    public int cal(Item item, int capacityLeft, int currentValue) {
        if (item.weight > capacityLeft) {
            return -1;
        } else {
            return currentValue + item.value;
        }
    }

    public int priority() {
        PriorityQueue<Item> pq = new PriorityQueue<>(Comparator.comparingInt((Item i) -> -cal(i, capacity, 0)));
        pq.add(new Item());
        while (!pq.isEmpty()) {
            Item currentItem = pq.poll();
            if (currentItem.loaded) {
                result = Math.max(result, currentItem.value);
            }
            Item withItem = new Item(currentItem.value, currentItem.weight, true);
            Item withoutItem = new Item(currentItem.value, currentItem.weight, false);
            if (currentItem.weight + items[currentItem.weight].weight <= capacity) {
                withItem.weight += items[currentItem.weight].weight;
                withItem.value += items[currentItem.weight].value;
                pq.add(withItem);
            }
            pq.add(withoutItem);
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
                items[i].weight = Integer.parseInt(split[i]);
            }
            split = sc.nextLine().split(" ");
            for (int i = 0; i < split.length; i++) {
                items[i].value = Integer.parseInt(split[i]);
            }
            int capacity = sc.nextInt();
            ZeroOneBackpack backpack = new ZeroOneBackpack(items, capacity);
            System.out.println(backpack.priority());
            for (int i = 0; i < backpack.items.length; i++) {
                System.out.println(backpack.items[i].weight + " " + backpack.items[i].value + " " + backpack.items[i].loaded);
            }
            System.out.println();
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}