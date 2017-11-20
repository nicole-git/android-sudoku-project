package no.ntnu.nauybeng.sudoku;

import java.util.Arrays;

public class SudokuChecker {

    public static boolean checkBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            int[] row = getRow(board, i);
            int[] col = board[i].clone();
            int[] box = getBox(board, i);
            if (!(validate(col) && validate(row) && validate(box))) {
                return false;
            }
        }
        return true;
    }

    private static int[] getBox(int[][] board, int i) {
        int[] square = new int[9];
        for (int j = 0; j < 9; j++) {
            square[j] = board[(i / 3) * 3 + j / 3][i * 3 % 9 + j % 3]; // black magic
        }
        return square;
    }

    private static int[] getRow(int[][] board, int i) {
        int[] row = new int[9];
        for (int j = 0; j < 9; j++) {
            row[j] = board[j][i];
        }
        return row;
    }

    private static boolean validate(int[] arrayToCheck) {
        Arrays.sort(arrayToCheck);
        return Arrays.equals(arrayToCheck, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
    }
}