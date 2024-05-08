package shuhuai.algorithm.branchandbound;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TravellingSalesman {
    private int[][] edges;
    private char[] vertexes;
    private char[] result;
    private int min;

    public TravellingSalesman(int[][] edges, char[] vertexes) {
        setData(edges, vertexes);
    }

    public void setData(int[][] edges, char[] vertexes) {
        this.edges = new int[edges.length][edges[0].length];
        for (int i = 0; i < edges.length; i++) {
            System.arraycopy(edges[i], 0, this.edges[i], 0, edges[i].length);
        }
        this.vertexes = new char[vertexes.length];
        System.arraycopy(vertexes, 0, this.vertexes, 0, vertexes.length);
        result = new char[vertexes.length];
        min = Integer.MAX_VALUE;
    }

    public int priority() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.cost));
        pq.add(new Node(0, 0, new boolean[vertexes.length], new char[vertexes.length]));
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (current.level == vertexes.length - 1) {
                if (edges[current.path[current.level - 1] - 'A'][current.path[0] - 'A'] != Integer.MAX_VALUE) {
                    current.cost += edges[current.path[current.level - 1] - 'A'][current.path[0] - 'A'];
                    if (current.cost < min) {
                        min = current.cost;
                        result = Arrays.copyOf(current.path, current.path.length);
                    }
                }
            } else {
                for (int i = 0; i < vertexes.length; i++) {
                    if (!current.visited[i]
                            && edges[current.path[current.level - 1] - 'A'][vertexes[i] - 'A'] != Integer.MAX_VALUE) {
                        boolean[] newVisited = Arrays.copyOf(current.visited, current.visited.length);
                        newVisited[i] = true;
                        char[] newPath = Arrays.copyOf(current.path, current.path.length);
                        newPath[current.level] = vertexes[i];
                        int newCost = current.cost + edges[current.path[current.level - 1] - 'A'][vertexes[i] - 'A'];
                        pq.add(new Node(current.level + 1, newCost, newVisited, newPath));
                    }
                }
            }
        }
        return min;
    }

    private static class Node {
        int level;
        int cost;
        boolean[] visited;
        char[] path;

        Node(int level, int cost, boolean[] visited, char[] path) {
            this.level = level;
            this.cost = cost;
            this.visited = visited;
            this.path = path;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            char[] vertexes = new char[split.length];
            for (int i = 0; i < vertexes.length; i++) {
                vertexes[i] = split[i].charAt(0);
            }
            int[][] edges = new int[vertexes.length][vertexes.length];
            for (int i = 0; i < vertexes.length; i++) {
                for (int j = 0; j < vertexes.length; j++) {
                    String s = sc.next();
                    if (s.equals("n")) {
                        edges[i][j] = Integer.MAX_VALUE;
                    } else {
                        edges[i][j] = Integer.parseInt(s);
                    }
                }
            }
            TravellingSalesman ts = new TravellingSalesman(edges, vertexes);
            System.out.println(ts.priority());
            for (int i = 0; i < ts.result.length; i++) {
                System.out.print(ts.result[i] + " ");
            }
            System.out.println();
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}