package shuhuai.algorithm.overview;

import java.util.Arrays;
import java.util.Scanner;

public class GreatestCommonDivisor {
    public int euclid(int m, int n) {
        while (n != 0) {
            int r = m % n;
            m = n;
            n = r;
        }
        return m;
    }

    public int loop(int m, int n) {
        int result = Math.min(m, n);
        while (result > 0) {
            if (m % result == 0 && n % result == 0) {
                return result;
            }
            result--;
        }
        return 0;
    }

    public int prime(int m, int n) {
        if (m == n) {
            return m;
        }
        if (m % n == 0) {
            return n;
        }
        if (n % m == 0) {
            return m;
        }
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (isPrime[i]) {
                int temp = i + 1;
                temp += i + 1;
                while (temp <= n) {
                    if (isPrime[temp - 1]) {
                        isPrime[temp - 1] = false;
                        count++;
                    }
                    temp += i + 1;
                }
            }
        }
        int[] primes = new int[n - count - 1];
        count = 0;
        for (int i = 0; i < n; i++) {
            if (isPrime[i]) {
                primes[count++] = i + 1;
            }
        }
        int result = 1;
        while (Arrays.binarySearch(primes, m) < 0 && Arrays.binarySearch(primes, n) < 0
                && !(Arrays.binarySearch(primes, m) >= 0 && m < n)
                && !(Arrays.binarySearch(primes, n) >= 0 && m > n)) {
            for (int j : primes) {
                if (m % j == 0 && n % j == 0) {
                    m /= j;
                    n /= j;
                    result *= j;
                    break;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GreatestCommonDivisor greatestCommonDivisor = new GreatestCommonDivisor();
        String input = scanner.nextLine();
        while (!input.isEmpty()) {
            String[] nums = input.split(" ");
            int m = Integer.parseInt(nums[0]);
            int n = Integer.parseInt(nums[1]);
            System.out.println(greatestCommonDivisor.euclid(m, n));
            System.out.println(greatestCommonDivisor.loop(m, n));
            System.out.println(greatestCommonDivisor.prime(m, n));
            input = scanner.nextLine();
        }
    }
}