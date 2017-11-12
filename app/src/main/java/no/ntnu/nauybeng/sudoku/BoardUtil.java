package no.ntnu.nauybeng.sudoku;

public class BoardUtil {

    public static String[] boxOrigins = {"0:0", "0:3", "0:6", "3:0", "3:3", "3:6", "6:0", "6:3", "6:6"};

    public static int startRow(int boxNr) {
        return Integer.parseInt(boxOrigins[boxNr].split(":")[0]);
    }

    public static int startCol(int boxNr) {
        return Integer.parseInt(boxOrigins[boxNr].split(":")[1]);
    }

}
