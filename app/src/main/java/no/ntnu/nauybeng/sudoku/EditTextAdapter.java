package no.ntnu.nauybeng.sudoku;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

public class EditTextAdapter extends BaseAdapter {

    private EditText[][] board;

    public EditTextAdapter(EditText[][] board) {
        this.board = board;
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

}
