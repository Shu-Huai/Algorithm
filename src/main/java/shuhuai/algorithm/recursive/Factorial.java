package shuhuai.algorithm.recursive;

import shuhuai.algorithm.utils.Tester;

import java.lang.reflect.InvocationTargetException;

public class Factorial {
    public int recursive(int n) {
        if (n == 0) {
            return 1;
        }
        return n * recursive(n - 1);
    }

    public int loop(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        new Tester().testOneInt("shuhuai.algorithm.recursive.Factorial", new String[]{"recursive", "loop"});
    }
}