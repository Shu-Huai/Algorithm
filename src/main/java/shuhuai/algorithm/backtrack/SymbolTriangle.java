package shuhuai.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SymbolTriangle {
    private int n;
    private int sum;
    private List<char[][]> results;

    public SymbolTriangle(int n) {
        setData(n);
    }

    public void setData(int n) {
        this.n = n;
        sum = 0;
        results = new ArrayList<>();
    }

    public int backtrack() {
        int half = n * (n + 1) / 2;
        if (half % 2 == 1) {
            sum = 0;
            return sum;
        }
        backtrack(0, half / 2, 0, new char[n][n]);
        return sum;
    }

    public void backtrack(int t, int half, int count, char[][] drawing) {
        if (count > half || t * (t + 1) / 2 - count > half) {
            return;
        }
        if (t >= n) {
            sum++;
            char[][] cur = new char[n][n];
            for (int i = 0; i < n; i++) {
                System.arraycopy(drawing[i], 0, cur[i], 0, n);
            }
            results.add(cur);
            return;
        }
        char[] symbol = new char[]{'+', '-'};
        for (int i = 0; i < 2; i++) {
            drawing[0][t] = symbol[i];
            count += i;
            for (int j = 1; j <= t; j++) {
                int cur = drawing[j - 1][t - j] == drawing[j - 1][t - j + 1] ? 0 : 1;
                drawing[j][t - j] = symbol[cur];
                count += cur;
            }
            backtrack(t + 1, half, count, drawing);
            for (int j = 1; j <= t; j++) {
                count -= drawing[j][t - j] == '+' ? 0 : 1;
            }
            count -= i;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            int n = Integer.parseInt(input);
            SymbolTriangle st = new SymbolTriangle(n);
            System.out.println(st.backtrack());
            for (int i = 0; i < st.results.size(); i++) {
                for (int j = 0; j < st.results.get(i).length; j++) {
                    for (int k = 0; k < j; k++) {
                        System.out.print(" ");
                    }
                    for (int k = 0; k < st.results.get(i)[j].length; k++) {
                        System.out.print(st.results.get(i)[j][k] + " ");
                    }
                    System.out.println();
                }
                System.out.println();
            }
            input = sc.nextLine();
        }
    }
}