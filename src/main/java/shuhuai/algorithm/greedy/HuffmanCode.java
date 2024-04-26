package shuhuai.algorithm.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanCode {
    public static class TreeNode {
        private char val;
        private TreeNode left;
        private TreeNode right;
    }

    public static class Tree {
        private TreeNode root;
        private int weight;
    }

    public static class Char {
        private char val;
        private int weight;
        private String code;

        public String toString() {
            return val + ": " + code;
        }
    }

    private Char[] chars;
    private Tree tree;

    public HuffmanCode(Char[] chars) {
        setData(chars);
    }

    public void setData(Char[] chars) {
        this.chars = new Char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            this.chars[i] = new Char();
            this.chars[i].val = chars[i].val;
            this.chars[i].weight = chars[i].weight;
        }
        this.tree = null;
    }

    private void buildTree() {
        PriorityQueue<Tree> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        for (Char aChar : chars) {
            Tree temp = new Tree();
            temp.root = new TreeNode();
            temp.root.val = aChar.val;
            temp.weight = aChar.weight;
            minHeap.offer(temp);
        }
        while (minHeap.size() > 1) {
            Tree t1 = minHeap.poll();
            Tree t2 = minHeap.poll();
            Tree newTree = new Tree();
            newTree.root = new TreeNode();
            newTree.root.left = t1.root;
            assert t2 != null;
            newTree.root.right = t2.root;
            newTree.weight = t1.weight + t2.weight;
            minHeap.offer(newTree);
        }
        tree = minHeap.poll();
    }

    public int greedy() {
        buildTree();
        int totalWeight = 0;
        for (Char aChar : chars) {
            aChar.code = getCharCode(aChar.val);
            totalWeight += aChar.weight * aChar.code.length();
        }
        return totalWeight;
    }

    public String getCharCode(char c) {
        StringBuilder code = new StringBuilder();
        getCharCode(tree.root, c, code);
        return code.reverse().toString();
    }

    private boolean getCharCode(TreeNode node, char c, StringBuilder code) {
        if (node == null) {
            return false;
        }
        if (node.val == c && node.left == null && node.right == null) {
            return true;
        }
        if (getCharCode(node.left, c, code)) {
            code.append('0');
            return true;
        }
        if (getCharCode(node.right, c, code)) {
            code.append('1');
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            Char[] chars = new Char[split.length];
            for (int i = 0; i < split.length; i++) {
                chars[i] = new Char();
                chars[i].val = split[i].charAt(0);
            }
            split = sc.nextLine().split(" ");
            for (int i = 0; i < split.length; i++) {
                chars[i].weight = Integer.parseInt(split[i]);
            }
            HuffmanCode huffman = new HuffmanCode(chars);
            System.out.println(huffman.greedy());
            for (int i = 0; i < huffman.chars.length; i++) {
                System.out.println(huffman.chars[i]);
            }
            input = sc.nextLine();
        }
    }
}