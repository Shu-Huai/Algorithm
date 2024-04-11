package shuhuai.algorithm.recursive;

import java.util.Scanner;

public class ChessBoard {
    private int tile;
    private final int[][] board;

    public ChessBoard(int size) {
        tile = 1;
        board = new int[size][size];
    }

    public void printBoard() {
        for (int[] ints : board) {
            for (int anInt : ints) {
                System.out.print(anInt + "\t");
            }
            System.out.println();
        }
    }

    public void recursive(int startRow, int startCol, int x, int y, int size) {
        if (size == 1) {
            return;
        }
        int t = tile++;
        int s = size / 2;
        if (x < startRow + s && y < startCol + s) {
            recursive(startRow, startCol, x, y, s);
        } else {
            board[startRow + s - 1][startCol + s - 1] = t;
            recursive(startRow, startCol, startRow + s - 1, startCol + s - 1, s);
        }
        if (x < startRow + s && y >= startCol + s) {
            recursive(startRow, startCol + s, x, y, s);
        } else {
            board[startRow + s - 1][startCol + s] = t;
            recursive(startRow, startCol + s, startRow + s - 1, startCol + s, s);
        }
        if (x >= startRow + s && y < startCol + s) {
            recursive(startRow + s, startCol, x, y, s);
        } else {
            board[startRow + s][startCol + s - 1] = t;
            recursive(startRow + s, startCol, startRow + s, startCol + s - 1, s);
        }
        if (x >= startRow + s && y >= startCol + s) {
            recursive(startRow + s, startCol + s, x, y, s);
        } else {
            board[startRow + s][startCol + s] = t;
            recursive(startRow + s, startCol + s, startRow + s, startCol + s, s);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.isEmpty()) {
            String[] arr = input.split(" ");
            int[] nums = new int[arr.length];
            for (int i = 0; i < arr.length; i++) {
                nums[i] = Integer.parseInt(arr[i]);
            }
            ChessBoard chessBoard = new ChessBoard(nums[0]);
            chessBoard.recursive(0, 0, nums[1], nums[2], nums[0]);
            chessBoard.printBoard();
            input = scanner.nextLine();
        }
    }
}