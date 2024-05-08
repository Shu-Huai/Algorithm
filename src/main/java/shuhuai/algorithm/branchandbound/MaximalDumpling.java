package shuhuai.algorithm.branchandbound;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MaximalDumpling {
    private char[] vertexes;
    private int[][] edges;
    private boolean[] selected;
    private int result;

    public MaximalDumpling(char[] vertexes, int[][] edges) {
        setData(vertexes, edges);
    }

    public void setData(char[] vertexes, int[][] edges) {
        this.vertexes = new char[vertexes.length];
        System.arraycopy(vertexes, 0, this.vertexes, 0, vertexes.length);
        this.edges = new int[edges.length][edges.length];
        for (int i = 0; i < edges.length; i++) {
            System.arraycopy(edges[i], 0, this.edges[i], 0, edges[i].length);
        }
        selected = new boolean[vertexes.length];
    }

    public int priority() {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> -n.cliqueSize));
        Node root = new Node();
        root.index = 0;
        root.cliqueSize = 0;
        pq.add(root);

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (current.index == vertexes.length) {
                result = current.cliqueSize;
                break;
            }
            if (current.cliqueSize < result) {
                Node leftChild = new Node();
                leftChild.index = current.index + 1;
                leftChild.cliqueSize = current.cliqueSize;
                if (isClique(current.index)) {
                    leftChild.cliqueSize++;
                    selected[current.index] = true;
                }
                pq.add(leftChild);
            }
            Node rightChild = new Node();
            rightChild.index = current.index + 1;
            rightChild.cliqueSize = current.cliqueSize;
            if (rightChild.cliqueSize > result) {
                pq.add(rightChild);
            }
        }
        return result;
    }

    private boolean isClique(int index) {
        for (int i = 0; i < index; i++) {
            if (selected[i] && edges[index][i] == 0) {
                return false;
            }
        }
        return true;
    }

    private static class Node {
        int index;
        int cliqueSize;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            String[] split = input.split(" ");
            char[] vertexes = new char[split.length];
            for (int i = 0; i < vertexes.length; i++) {
                vertexes[i] = split[i].charAt(0);
            }
            int[][] edges = new int[vertexes.length][vertexes.length];
            for (int i = 0; i < vertexes.length; i++) {
                for (int j = 0; j < vertexes.length; j++) {
                    String s = sc.next();
                    if (s.equals("n")) {
                        edges[i][j] = Integer.MAX_VALUE;
                    } else {
                        edges[i][j] = Integer.parseInt(s);
                    }
                }
            }
            MaximalDumpling md = new MaximalDumpling(vertexes, edges);
            System.out.println(md.priority());
            for (int i = 0; i < md.selected.length; i++) {
                System.out.println(md.vertexes[i] + ": " + md.selected[i]);
            }
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}