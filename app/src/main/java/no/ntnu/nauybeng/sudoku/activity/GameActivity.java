package no.ntnu.nauybeng.sudoku.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import no.ntnu.nauybeng.sudoku.BoardUtil;
import no.ntnu.nauybeng.sudoku.R;
import no.ntnu.nauybeng.sudoku.EditTextAdapter;
import no.ntnu.nauybeng.sudoku.SudokuChecker;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        String mode = getIntent().getStringExtra("game_mode");

        final EditText[][] board = BoardUtil.getGameBoard(this, mode);
        final Context context = getApplicationContext();

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

        findViewById(R.id.checkSolutionBtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (SudokuChecker.checkBoard(BoardUtil.getNumbers(board))) {
                    Toast.makeText(context, R.string.correctSolution, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, R.string.incorrectSolution, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
