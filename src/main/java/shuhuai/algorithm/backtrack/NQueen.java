package shuhuai.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NQueen {
    private int n;
    private int sum;
    private List<int[]> results;

    public NQueen(int n) {
        setData(n);
    }

    public void setData(int n) {
        this.n = n;
        sum = 0;
        results = new ArrayList<>();
    }

    public int backtrack() {
        backtrack(0, new int[n]);
        return sum;
    }

    private boolean placeable(int j, int[] x) {
        for (int i = 0; i < j; i++) {
            if (x[i] == x[j] || Math.abs(x[i] - x[j]) == Math.abs(i - j)) {
                return false;
            }
        }
        return true;
    }

    public void backtrack(int t, int[] x) {
        if (t >= n) {
            sum++;
            int[] cur = new int[n];
            System.arraycopy(x, 0, cur, 0, n);
            results.add(cur);
            return;
        }
        for (int i = 0; i < n; i++) {
            x[t] = i;
            if (placeable(t, x)) {
                backtrack(t + 1, x);
            }
        }
    }

    public int arrangeBacktrack() {
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = i;
        }
        arrangeBacktrack(0, x);
        return sum;
    }

    public void arrangeBacktrack(int t, int[] x) {
        if (t >= n) {
            sum++;
            int[] cur = new int[n];
            System.arraycopy(x, 0, cur, 0, n);
            results.add(cur);
            return;
        }
        for (int i = t; i < n; i++) {
            int temp = x[t];
            x[t] = x[i];
            x[i] = temp;
            if (placeable(t, x)) {
                arrangeBacktrack(t + 1, x);
            }
            temp = x[t];
            x[t] = x[i];
            x[i] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            int n = Integer.parseInt(input);
            NQueen nQueen = new NQueen(n);
            System.out.println(nQueen.backtrack());
            for (int i = 0; i < nQueen.results.size(); i++) {
                for (int j = 0; j < nQueen.results.get(i).length; j++) {
                    System.out.print(nQueen.results.get(i)[j] + " ");
                }
                System.out.println();
            }
            nQueen.setData(n);
            System.out.println(nQueen.arrangeBacktrack());
            for (int i = 0; i < nQueen.results.size(); i++) {
                for (int j = 0; j < nQueen.results.get(i).length; j++) {
                    System.out.print(nQueen.results.get(i)[j] + " ");
                }
                System.out.println();
            }
            input = sc.nextLine();
        }
    }
}