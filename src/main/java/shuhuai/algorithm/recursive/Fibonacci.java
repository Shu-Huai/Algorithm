package shuhuai.algorithm.recursive;

import shuhuai.algorithm.utils.Tester;

import java.lang.reflect.InvocationTargetException;

public class Fibonacci {
    public int recursive(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return recursive(n - 1) + recursive(n - 2);
    }

    public int loop(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int f1 = 1;
        int f2 = 1;
        int result = 0;
        for (int i = 2; i <= n; ++i) {
            result = f1 + f2;
            f2 = f1;
            f1 = result;
        }
        return result;
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        new Tester().testOneInt("shuhuai.algorithm.recursive.Fibonacci", new String[]{"recursive", "loop"});
    }
}