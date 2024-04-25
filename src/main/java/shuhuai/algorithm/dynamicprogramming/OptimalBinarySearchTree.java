package shuhuai.algorithm.dynamicprogramming;

import shuhuai.algorithm.utils.Printer;
import shuhuai.algorithm.utils.TreeHelper;

import java.util.Scanner;

public class OptimalBinarySearchTree {
    private float[][] e;
    private float[][] w;
    private int[][] root;
    private float[] p;
    private float[] q;
    private float result;
    private String[] tree;

    public OptimalBinarySearchTree(float[] p, float[] q) {
        setData(p, q);
    }

    public void setData(float[] p, float[] q) {
        this.p = new float[p.length];
        System.arraycopy(p, 0, this.p, 0, p.length);
        this.q = new float[q.length];
        System.arraycopy(q, 0, this.q, 0, q.length);
        e = new float[p.length + 1][p.length];
        w = new float[p.length + 1][p.length];
        root = new int[p.length][p.length];
        result = 0;
        tree = null;
    }

    public float dp() {
        for (int i = 1; i <= p.length; i++) {
            w[i][i - 1] = q[i - 1];
            e[i][i - 1] = 0;
        }
        for (int len = 1; len < p.length; len++) {
            for (int i = 1; i <= p.length - len; i++) {
                int j = i + len - 1;
                w[i][j] = w[i][j - 1] + p[j] + q[j];
                e[i][j] = Integer.MAX_VALUE;
                for (int r = i; r <= j; r++) {
                    float t = e[i][r - 1] + e[r + 1][j] + w[i][j];
                    if (t < e[i][j]) {
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }
        }
        result = e[1][p.length - 1];
        TreeNode tree = buildTree(1, p.length - 1);
        TreeHelper<TreeNode> treeHelper = new TreeHelper<>(TreeNode.class);
        try {
            this.tree = treeHelper.deBuild(tree);
        } catch (Exception ex) {
            return result;
        }
        return result;
    }

    @SuppressWarnings({"unused"})
    private static class TreeNode {
        private int val;
        private TreeNode left;
        private TreeNode right;
    }

    private TreeNode buildTree(int i, int j) {
        TreeNode r = new TreeNode();
        r.val = root[i][j];
        if (i == j) {
            return r;
        }
        r.left = buildTree(i, root[i][j] - 1);
        r.right = buildTree(root[i][j] + 1, j);
        return r;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            float[] p = new float[split.length];
            for (int i = 0; i < split.length; i++) {
                p[i] = Float.parseFloat(split[i]);
            }
            split = sc.nextLine().split(" ");
            float[] q = new float[split.length];
            for (int i = 0; i < split.length; i++) {
                q[i] = Float.parseFloat(split[i]);
            }
            OptimalBinarySearchTree tree = new OptimalBinarySearchTree(p, q);
            tree.dp();
            System.out.println(tree.result);
            new Printer<>().printArray(tree.tree);
            input = sc.nextLine();
        }
    }
}