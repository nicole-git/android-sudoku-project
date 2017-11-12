package no.ntnu.nauybeng.sudoku;

import android.content.Context;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;

public class EditTextAdapter extends BaseAdapter {

    private Context context;
    private EditText[][] board;

    public EditTextAdapter(Context context) {
        this.context = context;
        this.board = getGameBoard();
    }

    @Override
    public int getCount() {
        return board.length * board[0].length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int row = position / 9;
        int col = position % 9;
        return board[row][col];
    }

    private EditText[][] getGameBoard() {
        EditText[][] board = new EditText[9][9];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                board[row][col] = getEditText(row, col);
            }
        }
        colorBoard(board);
        return board;
    }

    private EditText getEditText(int row, int col) {
        EditText editText = new EditText(context);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        //editText.setText(row + ":" + col);
        editText.setLayoutParams(new GridView.LayoutParams(100, 100));
        editText.setPadding(8, 8, 8, 8);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
        editText.setBackgroundColor(0xFFE2E2E2);
        editText.setGravity(Gravity.CENTER_HORIZONTAL);
        return editText;
    }

    private void colorBoard(EditText[][] board) {
        for (int boxNr = 0; boxNr < 9; boxNr += 2) {
            for (int row = BoardUtil.startRow(boxNr); row < BoardUtil.startRow(boxNr) + 3; row++) {
                for (int col = BoardUtil.startCol(boxNr); col < BoardUtil.startCol(boxNr) + 3; col++) {
                    board[row][col].setBackgroundColor(0xFFD2D2D2);
                }
            }
        }
    }

}
