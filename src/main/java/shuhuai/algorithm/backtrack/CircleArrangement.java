package shuhuai.algorithm.backtrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CircleArrangement {
    private double[] radius;
    private double min;
    private List<double[]> results;

    public CircleArrangement(double[] radius) {
        setData(radius);
    }

    public void setData(double[] radius) {
        this.radius = new double[radius.length];
        System.arraycopy(radius, 0, this.radius, 0, radius.length);
        min = 0;
        results = new ArrayList<>();
    }

    public double backtrack() {
        double[] r = new double[radius.length];
        System.arraycopy(radius, 0, r, 0, radius.length);
        backtrack(0, new double[radius.length], r);
        return min;
    }

    private double center(int t, double[] x, double[] r) {
        double temp = 0;
        for (int i = 0; i < t; i++) {
            double value = x[i] + 2 * Math.sqrt(r[t] * r[i]);
            if (value > temp) {
                temp = value;
            }
        }
        return temp;
    }

    public void backtrack(int t, double[] x, double[] r) {
        if (t >= r.length) {
            double low = 0;
            double high = 0;
            for (int i = 0; i < r.length; i++) {
                if (x[i] - r[i] < low) {
                    low = x[i] - r[i];
                }
                if (x[i] + r[i] > high) {
                    high = x[i] + r[i];
                }
            }
            if (min > high - low) {
                min = high - low;
                double[] cur = new double[r.length];
                System.arraycopy(r, 0, cur, 0, r.length);
                results.add(cur);
            }
            return;
        }
        for (int i = 0; i < r.length; i++) {
            double temp = r[t];
            r[t] = r[i];
            r[i] = temp;
            double centerX = center(t, x, r);
            if (centerX + r[t] + r[0] < min) {
                x[t] = centerX;
                backtrack(t + 1, x, r);
            }
            temp = r[t];
            r[t] = r[i];
            r[i] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            double[] radius = new double[split.length];
            for (int i = 0; i < split.length; i++) {
                radius[i] = Double.parseDouble(split[i]);
            }
            CircleArrangement c = new CircleArrangement(radius);
            System.out.println(c.backtrack());
            for (int i = 0; i < c.results.size(); i++) {
                for (int j = 0; j < c.results.get(i).length; j++) {
                    System.out.print(c.results.get(i)[j] + " ");
                }
                System.out.println();
            }
            input = sc.nextLine();
        }
    }
}