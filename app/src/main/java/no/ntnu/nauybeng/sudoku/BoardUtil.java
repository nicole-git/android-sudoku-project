package no.ntnu.nauybeng.sudoku;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridView;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class BoardUtil {

    public static final int lightGrey = 0xFFE2E2E2;
    public static final int darkGrey = 0xFFD2D2D2;
    public static final int lightOrange = 0xFFE8CEB5;
    public static final int darkOrange = 0xFFD8BEA5;

    public static final Map<Integer, Integer> colorToggleMap = ImmutableMap.of(
            lightGrey, lightOrange,
            darkGrey, darkOrange,
            lightOrange, lightGrey,
            darkOrange, darkGrey
    );

    public static String[] boxOrigins = {"0:0", "0:3", "0:6", "3:0", "3:3", "3:6", "6:0", "6:3", "6:6"};

    public static int startRow(int boxNr) {
        return Integer.parseInt(boxOrigins[boxNr].split(":")[0]);
    }

    public static int startCol(int boxNr) {
        return Integer.parseInt(boxOrigins[boxNr].split(":")[1]);
    }

    public static EditText[][] getGameBoard(Context context) {
        EditText[][] board = new EditText[9][9];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                board[row][col] = getEditText(context);
            }
        }
        colorBoard(board);
        return board;
    }

    private static EditText getEditText(Context context) {
        EditText editText = new EditText(context);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setLayoutParams(new GridView.LayoutParams(100, 100));
        editText.setPadding(8, 8, 8, 8);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
        editText.setBackgroundColor(lightGrey);
        editText.setGravity(Gravity.CENTER_HORIZONTAL);
        return editText;
    }

    private static void colorBoard(EditText[][] board) {
        for (int boxNr = 0; boxNr < 9; boxNr += 2) {
            for (int row = BoardUtil.startRow(boxNr); row < BoardUtil.startRow(boxNr) + 3; row++) {
                for (int col = BoardUtil.startCol(boxNr); col < BoardUtil.startCol(boxNr) + 3; col++) {
                    board[row][col].setBackgroundColor(darkGrey);
                }
            }
        }
    }

}
