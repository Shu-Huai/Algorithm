package shuhuai.algorithm.probability;

import java.util.Scanner;

public class Random {
    private long seed;
    private long a;
    private long c;
    private long m;

    public Random(long seed) {
        this(seed, 1664525L, 1013904223L, Integer.MAX_VALUE);
    }

    public Random(long seed, long a, long c, long m) {
        setData(seed, a, c, m);
    }

    public void setData(long seed, long a, long c, long m) {
        this.seed = seed;
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public long next() {
        seed = (a * seed + c) % m;
        return seed;
    }

    public long next(long begin,long end) {
        long range = end - begin;
        return (long) (begin + nextDouble() * range);
    }

    public double nextDouble() {
        seed = (a * seed + c) % m;
        return (double) seed / (m - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Random r = new Random(System.currentTimeMillis());
        while (!input.isEmpty()) {
            int n = Integer.parseInt(input);
            for (int i = 0; i < n; i++) {
                System.out.print(r.next() + " ");
            }
            System.out.println();
            for (int i = 0; i < n; i++) {
                System.out.print(r.nextDouble() + " ");
            }
            System.out.println();
            input = sc.nextLine();
        }
    }
}