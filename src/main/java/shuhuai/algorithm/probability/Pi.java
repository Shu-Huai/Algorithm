package shuhuai.algorithm.probability;

import java.util.Scanner;

public class Pi {
    private long n;
    private double result;

    public Pi(long n) {
        setData(n);
    }

    public void setData(long n) {
        this.n = n;
        result = 0;
    }

    public double dart() {
        long k = 0;
        Random random = new Random(System.currentTimeMillis());
        for (long i = 0; i < n; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            if (x * x + y * y <= 1) {
                k++;
            }
        }
        result = 4 * k / (double) n;
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            Pi r = new Pi(Long.parseLong(input));
            System.out.println(r.dart());
            input = sc.nextLine();
        }
    }
}