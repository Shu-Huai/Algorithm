package shuhuai.algorithm.dynamicprogramming;

import java.util.*;

public class MatrixMultiplication {
    public List<Object> dp(int[] p) {
        int[][] m = new int[p.length][p.length];
        int[][] s = new int[p.length][p.length];
        for (int i = 1; i < p.length; i++) {
            m[i][i] = 0;
        }
        for (int r = 2; r < p.length; r++) {
            for (int i = 1; i + r - 1 < p.length; i++) {
                int j = i + r - 1;
                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int temp = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (temp < min) {
                        min = temp;
                        s[i][j] = k;
                    }
                }
                m[i][j] = min;
            }
        }
        return getResult(p, m, s);
    }

    private int getNum(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '(' && str.charAt(i) != ' ') {
                int j = i + 1;
                for (; j < str.length(); j++) {
                    if (str.charAt(j) == ')' || str.charAt(j) == ' ') {
                        return Integer.parseInt(str.substring(i, j));
                    }
                }
                if (j == str.length()) {
                    return Integer.parseInt(str.substring(i, j));
                }
            }
        }
        return 0;
    }

    private int recursive(int i, int j, int[][] m, int[] p, int[][] s) {
        if (m[i][j] > 0) {
            return m[i][j];
        }
        if (i == j) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int temp = recursive(i, k, m, p, s) + recursive(k + 1, j, m, p, s) + p[i - 1] * p[k] * p[j];
            if (temp < min) {
                min = temp;
                s[i][j] = k;
            }
        }
        m[i][j] = min;
        return m[i][j];
    }

    private List<Object> getResult(int[] p, int[][] m, int[][] s) {
        Queue<int[]> q = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        q.offer(new int[]{1, p.length - 1});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] == cur[1]) {
                stack.push(" " + cur[0] + " ");
                continue;
            }
            int k = s[cur[0]][cur[1]];
            q.offer(new int[]{cur[0], k});
            q.offer(new int[]{k + 1, cur[1]});
        }
        while (stack.size() > 1) {
            String first = stack.pop();
            String second = stack.pop();
            int firstNum = getNum(first);
            int secondNum = getNum(second);
            if (firstNum > secondNum) {
                String temp = first;
                first = second;
                second = temp;
            }
            String cur = "(" + first + second + ")";
            stack.push(cur);
        }
        return new ArrayList<>() {{
            add(m[1][p.length - 1]);
            add(stack.pop());
        }};
    }

    public List<Object> recursive(int[] p) {
        int[][] m = new int[p.length][p.length];
        int[][] s = new int[p.length][p.length];
        recursive(1, p.length - 1, m, p, s);
        return getResult(p, m, s);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            int[] p = new int[split.length];
            for (int i = 0; i < split.length; i++) {
                p[i] = Integer.parseInt(split[i]);
            }
            List<Object> result = new MatrixMultiplication().dp(p);
            System.out.println(result.get(0));
            System.out.println(result.get(1));
            result = new MatrixMultiplication().recursive(p);
            System.out.println(result.get(0));
            System.out.println(result.get(1));
            input = sc.nextLine();
        }
    }
}