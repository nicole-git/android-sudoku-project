package no.ntnu.nauybeng.sudoku;

import android.content.Context;
import android.graphics.Typeface;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridView;

import com.google.common.collect.ImmutableMap;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class BoardUtil {

    public static final int lightGrey = 0xFFE2E2E2;
    public static final int darkGrey = 0xFFD2D2D2;
    public static final int lightOrange = 0xFFE8CEB5;
    public static final int darkOrange = 0xFFD8BEA5;

    public static Map<Integer, Integer> colorToggleMap = ImmutableMap.of(
            lightGrey, lightOrange,
            darkGrey, darkOrange,
            lightOrange, lightGrey,
            darkOrange, darkGrey
    );

    public static EditText[][] getGameBoard(Context context, String mode) {
        List<String> boardStrings = BoardStorageUtil.readBoardsAsList(context, mode);
        String boardString = boardStrings.get(new Random().nextInt(boardStrings.size()));
        EditText[][] board = new EditText[9][9];
        for (int i = 0; i < boardString.length(); i++) {
            board[i / 9][i % 9] = getEditText(context, i, boardString.charAt(i));
        }
        return board;
    }

    public static EditText[][] getEmptyBoard(Context context) {
        EditText[][] board = new EditText[9][9];
        for (int i = 0; i < 81; i++) {
            board[i / 9][i % 9] = getEditText(context, i, '0');
        }
        return board;
    }

    private static EditText getEditText(Context context, int position, char c) {
        String boardColors = "111000111111000111111000111000111000000111000000111000111000111111000111111000111";
        EditText editText = new EditText(context);
        editText.setTextColor(0xFF4477AA);
        editText.setTypeface(null, Typeface.BOLD);
        if (c != '0') {
            editText.setText(String.valueOf(c));
            editText.setEnabled(false);
            editText.setTextColor(0xFF333333);
        }
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setLayoutParams(new GridView.LayoutParams(100, 100));
        editText.setPadding(8, 8, 8, 8);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
        editText.setBackgroundColor(lightGrey);
        if (boardColors.charAt(position) == '1') {
            editText.setBackgroundColor(darkGrey);
        }
        editText.setGravity(Gravity.CENTER_HORIZONTAL);
        return editText;
    }

    public static String getBoardString(EditText[][] board) {
        StringBuilder boardString = new StringBuilder();
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                String text = board[row][col].getText().toString();
                boardString.append(text.isEmpty() ? "0" : text);
            }
        }
        return boardString.toString();
    }

    public static int[][] getNumbers(EditText[][] board) {
        int[][] numbers = new int[9][9];
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                numbers[row][col] = getNumber(board[row][col]);
            }
        }
        return numbers;
    }

    private static int getNumber(EditText editText) {
        try {
            return Integer.parseInt(editText.getText().toString());
        } catch (Exception expected) {
            return 0;
        }
    }
}
