package no.ntnu.nauybeng.sudoku;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.GridView;

import com.google.common.collect.ImmutableMap;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

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

    public static Map<String, Integer> difficultyBoardMap = ImmutableMap.of(
            "easy", R.raw.boards_easy,
            "medium", R.raw.boards_medium,
            "hard", R.raw.boards_hard
    );

    public static EditText[][] getGameBoard(Context context, String mode) {
        InputStream inputStream = context.getResources().openRawResource(difficultyBoardMap.get(mode));
        List<String> boardStrings = readResourceToStringList(inputStream);
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
        if (c != '0') {
            editText.setText(String.valueOf(c));
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

    private static List<String> readResourceToStringList(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter(System.getProperty("line.separator"));
        List<String> boardStrings = new ArrayList<>();
        while (scanner.hasNext()) {
            boardStrings.add(scanner.next().trim());
        }
        scanner.close();
        return boardStrings;
    }

}
