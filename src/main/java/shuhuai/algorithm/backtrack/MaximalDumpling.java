package shuhuai.algorithm.backtrack;

import java.util.Scanner;

public class MaximalDumpling {
    private char[] vertexes;
    private int[][] edges;
    private boolean[] selected;
    private int result;

    public MaximalDumpling(char[] vertexes, int[][] edges) {
        setData(vertexes, edges);
    }

    public void setData(char[] vertexes, int[][] edges) {
        this.vertexes = new char[vertexes.length];
        System.arraycopy(vertexes, 0, this.vertexes, 0, vertexes.length);
        this.edges = new int[edges.length][edges.length];
        for (int i = 0; i < edges.length; i++) {
            System.arraycopy(edges[i], 0, this.edges[i], 0, edges[i].length);
        }
        selected = new boolean[vertexes.length];
    }

    public int backtrack() {
        backtrack(0, new boolean[vertexes.length], 0);
        return result;
    }

    private boolean linked(int t, boolean[] x) {
        for (int i = 0; i < t; i++) {
            if (x[i] && edges[t][i] == Integer.MAX_VALUE) {
                return false;
            }
        }
        return true;
    }

    public void backtrack(int t, boolean[] x, int curResult) {
        if (t >= vertexes.length) {
            if (curResult > result) {
                System.arraycopy(x, 0, selected, 0, vertexes.length);
                result = curResult;
            }
            return;
        }
        if (linked(t, x)) {
            x[t] = true;
            backtrack(t + 1, x, curResult + 1);
            x[t] = false;
        }
        if (curResult + vertexes.length - 1 > result) {
            backtrack(t + 1, x, curResult);
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
            MaximalDumpling md = new MaximalDumpling(vertexes, edges);
            System.out.println(md.backtrack());
            for (int i = 0; i < md.selected.length; i++) {
                System.out.println(md.vertexes[i] + ": " + md.selected[i]);
            }
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}