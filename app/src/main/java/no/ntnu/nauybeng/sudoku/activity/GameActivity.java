package no.ntnu.nauybeng.sudoku.activity;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;

import no.ntnu.nauybeng.sudoku.BoardUtil;
import no.ntnu.nauybeng.sudoku.R;
import no.ntnu.nauybeng.sudoku.EditTextAdapter;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        String mode = getIntent().getStringExtra("game_mode");

        final EditText[][] board = BoardUtil.getGameBoard(this, mode);

        GridView gridview = findViewById(R.id.sudokuGrid);
        gridview.setAdapter(new EditTextAdapter(board));

        gridview.setLongClickable(true);
        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                EditText editText = board[position / 9][position % 9];
                int backgroundColor = ((ColorDrawable) editText.getBackground()).getColor();
                editText.setBackgroundColor(BoardUtil.colorToggleMap.get(backgroundColor));
                return true;
            }
        });
    }

}
