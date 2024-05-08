package shuhuai.algorithm.branchandbound;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Loading {
    private int[] weights;
    private int capacity;
    private int max;
    private boolean[] loaded;

    public Loading(int[] weights, int capacity) {
        setData(weights, capacity);
    }

    public void setData(int[] weights, int capacity) {
        this.weights = new int[weights.length];
        System.arraycopy(weights, 0, this.weights, 0, weights.length);
        this.capacity = capacity;
        max = 0;
        loaded = new boolean[weights.length];
    }

    public static class Node {
        private final Node parent;
        private final boolean loaded;
        private final int weight;

        public Node(Node parent, boolean loaded, int weight) {
            this.parent = parent;
            this.loaded = loaded;
            this.weight = weight;
        }
    }

    public int queue() {
        Queue<Node> queue = new LinkedList<>();
        int i = 0;
        queue.add(new Node(null, false, 0));
        queue.add(new Node(null, false, -1));
        int remain = 0;
        for (int weight : weights) {
            remain += weight;
        }
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.weight == -1 && i < weights.length) {
                queue.add(cur);
                remain -= weights[i];
                i++;
                continue;
            }
            if (i < weights.length) {
                if (cur.weight + weights[i] <= capacity) {
                    max = Math.max(max, cur.weight + weights[i]);
                    queue.offer(new Node(cur, true, cur.weight + weights[i]));
                }
                if (cur.weight + remain > max) {
                    queue.offer(new Node(cur, false, cur.weight));
                }
            } else {
                if (cur.weight >= 0 && cur.weight <= capacity) {
                    if (max == cur.weight) {
                        Node temp = cur;
                        for (int j = i - 1; j >= 0; j--) {
                            loaded[j] = temp.loaded;
                            temp = temp.parent;
                        }
                    }
                }
            }
        }
        return max;
    }

    public int priority() {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> b.weight - a.weight);
        int i = 0;
        pq.add(new Node(null, false, 0));
        int remain = 0;
        for (int weight : weights) {
            remain += weight;
        }
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (i < weights.length) {
                if (cur.weight + weights[i] <= capacity) {
                    max = Math.max(max, cur.weight + weights[i]);
                    pq.offer(new Node(cur, true, cur.weight + weights[i]));
                }
                if (cur.weight + remain > max) {
                    pq.offer(new Node(cur, false, cur.weight));
                }
            } else {
                if (cur.weight >= 0 && cur.weight <= capacity) {
                    if (max == cur.weight) {
                        Node temp = cur;
                        for (int j = i - 1; j >= 0; j--) {
                            loaded[j] = temp.loaded;
                            temp = temp.parent;
                        }
                    }
                }
            }
            if (cur.weight == -1 && i < weights.length) {
                remain -= weights[i];
                i++;
            }
        }
        return max;
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
            Loading l = new Loading(weights, capacity);
            System.out.println(l.queue());
            for (int i = 0; i < l.loaded.length; i++) {
                System.out.print(l.loaded[i] + " ");
            }
            System.out.println();
            l.setData(weights, capacity);
            System.out.println(l.priority());
            for (int i = 0; i < l.loaded.length; i++) {
                System.out.print(l.loaded[i] + " ");
            }
            System.out.println();
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}