package shuhuai.algorithm.dynamicprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class ImageCompress {
    private int[] p;
    private int maxLength;
    private int[] s;
    private int[] b;
    private int[] l;
    private int[] c;
    private List<int[]> result;

    public ImageCompress(int[] p, int maxLength) {
        setData(p, maxLength);
    }

    public void setData(int[] p, int maxLength) {
        this.p = new int[p.length];
        System.arraycopy(p, 0, this.p, 0, p.length);
        this.maxLength = maxLength;
        s = new int[p.length];
        b = new int[p.length];
        l = new int[p.length];
        c = new int[p.length];
        result = new ArrayList<>();
    }

    public void dp() {
        for (int i = 1; i < p.length; i++) {
            b[i] = (int) Math.ceil(Math.log(p[i] + 1) / Math.log(2));
            int bMax = b[i];
            s[i] = s[i - 1] + bMax + 11;
            l[i] = 1;
            c[i] = bMax;
            for (int j = 2; j <= i && j <= maxLength; j++) {
                bMax = Math.max(bMax, b[i - j + 1]);
                int curMax = s[i - j] + j * bMax + 11;
                if (s[i] > curMax) {
                    s[i] = curMax;
                    l[i] = j;
                    c[i] = bMax;
                }
            }
        }
        Stack<int[]> stack = new Stack<>();
        int i = l.length - 1;
        while (i > 0) {
            stack.push(new int[]{l[i], c[i]});
            i = i - l[i];
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入深度数组：");
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            int[] p = new int[split.length + 1];
            for (int i = 1; i <= split.length; i++) {
                p[i] = Integer.parseInt(split[i - 1]);
            }
            System.out.println("请输入最大段长：");
            int maxLength = sc.nextInt();
            ImageCompress iC = new ImageCompress(p, maxLength);
            iC.dp();
            List<int[]> result = iC.result;
            for (int i = 0; i < result.size(); i++) {
                System.out.println("第" + (i + 1) + "段长度：" + result.get(i)[0] + "，需要" + result.get(i)[1] + "位。");
            }
            sc.nextLine();
            System.out.println("请输入深度数组：");
            input = sc.nextLine();
        }
    }
}