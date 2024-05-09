package shuhuai.algorithm.probability;

import java.util.Scanner;

public class DefiniteIntegral {
    private long n;
    private double result;

    public DefiniteIntegral(long n) {
        setData(n);
    }

    public void setData(long n) {
        this.n = n;
        result = 0;
    }

    private double f(double x) {
        return x * x;
    }

    public double dart() {
        long k = 0;
        Random random = new Random(System.currentTimeMillis());
        for (long i = 0; i < n; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (y <= f(x)) {
                k++;
            }
        }
        result = (double) k / n;
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            DefiniteIntegral r = new DefiniteIntegral(Long.parseLong(input));
            System.out.println(r.dart());
            input = sc.nextLine();
        }
    }
}