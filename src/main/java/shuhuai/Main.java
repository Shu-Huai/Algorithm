package shuhuai;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入类名：");
        String input = scanner.nextLine();
        while (!input.isEmpty()) {
            try {
                Class<?> clazz = Class.forName("shuhuai.algorithm." + input);
                Method[] methods = clazz.getDeclaredMethods();
                for (Method method : methods) {
                    String methodName = method.getName();
                    if (("main").equalsIgnoreCase(methodName)) {
                        method.invoke(null, (Object) null);
                    }
                }
                System.out.println("请输入类名：");
                input = scanner.nextLine();
            } catch (ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
                System.out.println("输入有误");
                System.out.println("请输入类名：");
                input = scanner.nextLine();
            }
        }
    }
}