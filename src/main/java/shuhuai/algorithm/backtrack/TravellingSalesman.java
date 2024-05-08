package shuhuai.algorithm.backtrack;

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

    public int backtrack() {
        int[] x = new int[vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            x[i] = i;
        }
        backtrack(1, 0, x);
        return min;
    }

    public void backtrack(int t, int cost, int[] x) {
        if (t == vertexes.length - 1) {
            if (edges[x[edges.length - 2]][x[edges.length - 1]] != Integer.MAX_VALUE
                    && edges[x[vertexes.length - 1]][0] != Integer.MAX_VALUE
                    && (cost + edges[x[vertexes.length - 2]][x[vertexes.length - 1]] + edges[x[vertexes.length - 1]][0] < min
                    || min == Integer.MAX_VALUE)) {
                for (int i = 0; i < vertexes.length; i++) {
                    result[i] = vertexes[x[i]];
                }
                min = cost + edges[x[vertexes.length - 2]][x[vertexes.length - 1]] + edges[x[vertexes.length - 1]][0];
            }
            return;
        }
        for (int j = t; j < vertexes.length; j++) {
            if (edges[x[t - 1]][x[j]] != Integer.MAX_VALUE
                    && (cost + edges[x[t - 1]][x[j]] < min || min == Integer.MAX_VALUE)) {
                int temp = x[t];
                x[t] = x[j];
                x[j] = temp;
                cost += edges[x[t - 1]][x[t]];
                backtrack(t + 1, cost + edges[x[t - 1]][x[t]], x);
                temp = x[t];
                x[t] = x[j];
                x[j] = temp;
            }
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
            System.out.println(ts.backtrack());
            for (int i = 0; i < ts.result.length; i++) {
                System.out.print(ts.result[i] + " ");
            }
            System.out.println();
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}