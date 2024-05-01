package shuhuai.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MPutColorOn {
    private char[] vertexes;
    private int[][] edges;
    private int color;
    private int sum;
    private List<int[]> result;

    public MPutColorOn(char[] vertexes, int[][] edges, int color) {
        setData(vertexes, edges, color);
    }

    public void setData(char[] vertexes, int[][] edges, int color) {
        this.vertexes = new char[vertexes.length];
        System.arraycopy(vertexes, 0, this.vertexes, 0, vertexes.length);
        this.edges = new int[edges.length][edges.length];
        for (int i = 0; i < edges.length; i++) {
            System.arraycopy(edges[i], 0, this.edges[i], 0, edges[i].length);
        }
        this.color = color;
        sum = 0;
        result = new ArrayList<>();
    }

    public int backtrack() {
        backtrack(0, new int[vertexes.length]);
        return sum;
    }

    private boolean check(int i, int[] x) {
        for (int j = 0; j < i; j++) {
            if (edges[i][j] != Integer.MAX_VALUE && x[i] == x[j]) {
                return false;
            }
        }
        return true;
    }

    public void backtrack(int t, int[] x) {
        if (t >= vertexes.length) {
            sum++;
            int[] cur=new int[vertexes.length];
            System.arraycopy(x, 0, cur, 0, x.length);
            result.add(cur);
            return;
        }
        for (int i = 0; i < color; i++) {
            x[t] = i;
            if (check(t, x)) {
                backtrack(t + 1, x);
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
            int color = sc.nextInt();
            MPutColorOn mpco = new MPutColorOn(vertexes, edges, color);
            System.out.println(mpco.backtrack());
            for (int i = 0; i < mpco.result.size(); i++) {
                for (int j = 0; j < mpco.result.get(i).length; j++) {
                    System.out.print(mpco.vertexes[j] + " ");
                }
                System.out.println();
                for (int j = 0; j < mpco.result.get(i).length; j++) {
                    System.out.print(mpco.result.get(i)[j] + " ");
                }
                System.out.println();
            }
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}