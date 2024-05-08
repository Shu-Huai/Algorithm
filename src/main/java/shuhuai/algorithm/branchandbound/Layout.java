package shuhuai.algorithm.branchandbound;

import java.util.*;

public class Layout {
    private Point start;
    private Point end;
    private int[][] matrix;
    private Point[] route;

    public Layout(int n, int m, Point start, Point end, Point[] blocks) {
        setData(n, m, start, end, blocks);
    }

    public void setData(int n, int m, Point start, Point end, Point[] blocks) {
        this.start = new Point(start.x, start.y);
        this.end = new Point(end.x, end.y);
        Point[] blocks1 = new Point[blocks.length];
        for (int i = 0; i < blocks.length; i++) {
            blocks1[i] = new Point(blocks[i].x, blocks[i].y);
        }
        matrix = new int[n][m];
        for (int[] ints : matrix) {
            Arrays.fill(ints, Integer.MAX_VALUE);
        }
        matrix[start.x][start.y] = 0;
        for (Point item : blocks1) {
            matrix[item.x][item.y] = -1;
        }
        route = new Point[0];
    }

    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private boolean in(int x, int y) {
        return x >= 0 && x < matrix.length && y >= 0 && y < matrix[x].length;
    }

    private void getRoute() {
        route = new Point[matrix[end.x][end.y] + 1];
        Point cur = new Point(end.x, end.y);
        int[] xRange = new int[]{-1, 0, 1, 0};
        int[] yRange = new int[]{0, 1, 0, -1};
        while (matrix[cur.x][cur.y] != 0) {
            route[matrix[cur.x][cur.y]] = new Point(cur.x, cur.y);
            for (int i = 0; i < xRange.length; i++) {
                int curX = cur.x + xRange[i];
                int curY = cur.y + yRange[i];
                if (in(curX, curY) && matrix[curX][curY] == matrix[cur.x][cur.y] - 1) {
                    cur.x = curX;
                    cur.y = curY;
                    break;
                }
            }
        }
        route[0] = new Point(start.x, start.y);
    }

    public int queue() {
        Queue<Point> queue = new LinkedList<>();
        queue.add(start);
        int[] xRange = new int[]{-1, 0, 1, 0};
        int[] yRange = new int[]{0, 1, 0, -1};
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int cur = matrix[point.x][point.y];
            for (int i = 0; i < xRange.length; i++) {
                int curX = point.x + xRange[i];
                int curY = point.y + yRange[i];
                if (in(curX, curY) && matrix[curX][curY] > cur) {
                    matrix[curX][curY] = cur + 1;
                    if (end.x == curX && end.y == curY) {
                        getRoute();
                        return cur + 1;
                    }
                    queue.add(new Point(curX, curY));
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.isEmpty()) {
            int n = Integer.parseInt(input.trim());
            int m = sc.nextInt();
            Point start = new Point(sc.nextInt(), sc.nextInt());
            Point end = new Point(sc.nextInt(), sc.nextInt());
            sc.nextLine();
            input = sc.nextLine();
            List<Point> blocks = new LinkedList<>();
            while (!input.isEmpty()) {
                String[] split = input.split(" ");
                blocks.add(new Point(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
                input = sc.nextLine();
            }
            Layout layout = new Layout(n, m, start, end, blocks.toArray(new Point[0]));
            System.out.println(layout.queue());
            for (int i = 0; i < layout.route.length; i++) {
                System.out.print("(" + layout.route[i].x + ", " + layout.route[i].y + ") ");
            }
            System.out.println();
            input = sc.nextLine();
        }
    }
}