package shuhuai.algorithm.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Tester {
    public void testOneInt(String className, String[] methodName) throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, InstantiationException, IllegalAccessException {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Set<String> methodSet = new HashSet<>(Arrays.asList(methodName));
        while (!input.isEmpty()) {
            int n = Integer.parseInt(input);
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();
            Object instance = clazz.getDeclaredConstructor().newInstance();
            for (Method method : methods) {
                if (methodSet.contains(method.getName())) {
                    System.out.println(method.invoke(instance, n));
                }
            }
            input = scanner.nextLine();
        }
    }
}