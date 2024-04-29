package shuhuai.algorithm.greedy;

import java.util.Scanner;

public class SingleSourceShortestPath {
    private char[] vertexes;
    private int[][] edges;
    private int[] path;
    private int[] dist;

    public SingleSourceShortestPath(char[] vertexes, int[][] edges) {
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
        path = new int[vertexes.length];
        dist = new int[vertexes.length];
    }

    public int[] dijkstra(char sourceVertex) {
        int source = 0;
        for (int i = 0; i < vertexes.length; i++) {
            if (vertexes[i] == sourceVertex) {
                source = i;
                break;
            }
        }
        boolean[] visited = new boolean[vertexes.length];
        System.arraycopy(edges[source], 0, dist, 0, edges[source].length);
        for (int i = 0; i < vertexes.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                path[i] = -1;
            } else {
                path[i] = source;
            }
        }
        visited[source] = true;
        for (int i = 0; i < vertexes.length - 1; i++) {
            int min = Integer.MAX_VALUE;
            int temp = source;
            for (int j = 0; j < vertexes.length; j++) {
                if (!visited[j] && dist[j] < min) {
                    min = dist[j];
                    temp = j;
                }
            }
            visited[temp] = true;
            for (int j = 0; j < vertexes.length; j++) {
                if (!visited[j] && edges[temp][j] != Integer.MAX_VALUE && dist[temp] + edges[temp][j] < dist[j]) {
                    dist[j] = dist[temp] + edges[temp][j];
                    path[j] = temp;
                }
            }
        }
        return dist;
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
            SingleSourceShortestPath sss = new SingleSourceShortestPath(vertexes, edges);
            char source = sc.next().charAt(0);
            int[] dist = sss.dijkstra(source);
            for (int i = 0; i < dist.length; i++) {
                System.out.println(vertexes[i] + ": " + dist[i]);
            }
            for (int i = 0; i < sss.path.length; i++) {
                System.out.print(sss.path[i] + " ");
            }
            System.out.println();
            sc.nextLine();
            input = sc.nextLine();
        }
    }
}