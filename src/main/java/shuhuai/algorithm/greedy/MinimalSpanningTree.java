package shuhuai.algorithm.greedy;

import java.util.*;

public class MinimalSpanningTree {
    private char[] vertexes;
    private int[][] edges;
    private List<Edge> kruskalResult;

    public MinimalSpanningTree(char[] vertexes, int[][] edges) {
        setData(vertexes, edges);
    }

    public void setData(char[] vertexes, int[][] edges) {
        this.vertexes = new char[vertexes.length];
        System.arraycopy(vertexes, 0, this.vertexes, 0, vertexes.length);
        this.edges = new int[edges.length][edges[0].length];
        for (int i = 0; i < edges.length; i++) {
            this.edges[i] = new int[edges[i].length];
            System.arraycopy(edges[i], 0, this.edges[i], 0, edges[i].length);
        }
        kruskalResult = new ArrayList<>();
    }

    private static class UnionFind {
        private int[] parent;
        private int[] size;

        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (size[rootP] < size[rootQ]) {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            } else {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            }
        }
    }

    public static class Edge implements Comparable<Edge> {
        private int start;
        private int end;
        private int weight;

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public List<Edge> kruskal() {
        List<Edge> allEdges = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            for (int j = i + 1; j < edges[i].length; j++) {
                if (edges[i][j] != Integer.MAX_VALUE) {
                    Edge edge = new Edge();
                    edge.start = i;
                    edge.end = j;
                    edge.weight = edges[i][j];
                    allEdges.add(edge);
                }
            }
        }
        Collections.sort(allEdges);
        UnionFind uf = new UnionFind();
        uf.parent = new int[vertexes.length];
        uf.size = new int[vertexes.length];
        for (int i = 0; i < vertexes.length; i++) {
            uf.parent[i] = i;
            uf.size[i] = 1;
        }
        for (Edge edge : allEdges) {
            int start = edge.getStart();
            int end = edge.getEnd();
            if (!uf.connected(start, end)) {
                kruskalResult.add(edge);
                uf.union(start, end);
            }
        }
        return kruskalResult;
    }

    public List<Edge> prim() {
        List<Edge> mstEdges = new ArrayList<>();
        boolean[] visited = new boolean[vertexes.length];
        PriorityQueue<Edge> minHeap = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        visited[0] = true;
        for (int j = 0; j < edges[0].length; j++) {
            if (edges[0][j] != Integer.MAX_VALUE) {
                Edge edge = new Edge();
                edge.start = 0;
                edge.end = j;
                edge.weight = edges[0][j];
                minHeap.add(edge);
            }
        }
        while (!minHeap.isEmpty()) {
            Edge edge = minHeap.poll();
            int end = edge.getEnd();
            if (!visited[end]) {
                visited[end] = true;
                mstEdges.add(edge);
                for (int j = 0; j < edges[end].length; j++) {
                    if (edges[end][j] != Integer.MAX_VALUE && !visited[j]) {
                        Edge newEdge = new Edge();
                        newEdge.start = end;
                        newEdge.end = j;
                        newEdge.weight = edges[end][j];
                        minHeap.add(newEdge);
                    }
                }
            }
        }
        return mstEdges;
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
            MinimalSpanningTree mst = new MinimalSpanningTree(vertexes, edges);
            List<Edge> result = mst.kruskal();
            for (Edge edge : result) {
                System.out.println("(" + mst.vertexes[edge.start] + ", " + mst.vertexes[edge.end] + "): " + edge.weight);
            }
            mst.setData(vertexes, edges);
            result = mst.prim();
            for (Edge edge : result) {
                System.out.println("(" + mst.vertexes[edge.start] + ", " + mst.vertexes[edge.end] + "): " + edge.weight);
            }
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}