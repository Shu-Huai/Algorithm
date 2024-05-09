package shuhuai.algorithm.probability;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IntegerFactorization {
    private int n;
    private int[] result;

    public IntegerFactorization(int n) {
        setData(n);
    }

    public void setData(int n) {
        this.n = n;
        result = new int[0];
    }
    
    private int getOne(int n) {
        int m = (int) Math.floor(Math.sqrt(n));
        for (int i = 2; i <= m; i++) {
            if (n % i == 0) {
                return i;
            }
        }
        return n;
    }

    public int[] lasVegas() {
        List<Integer> list = new ArrayList<>();
        while (n != 1) {
            int temp = getOne(n);
            list.add(temp);
            n /= temp;
        }
        result = list.stream().mapToInt(i -> i).toArray();
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            int n = Integer.parseInt(input);
            int[] result = new IntegerFactorization(n).lasVegas();
            for (int j : result) {
                System.out.print(j + " ");
            }
            System.out.println();
            input = sc.nextLine();
        }
    }
}