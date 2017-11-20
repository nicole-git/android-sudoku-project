package no.ntnu.nauybeng.sudoku.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import no.ntnu.nauybeng.sudoku.BoardUtil;
import no.ntnu.nauybeng.sudoku.EditTextAdapter;
import no.ntnu.nauybeng.sudoku.BoardStorageUtil;
import no.ntnu.nauybeng.sudoku.R;

public class CreateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        final EditText[][] board = BoardUtil.getEmptyBoard(this);
        GridView gridview = findViewById(R.id.sudokuGrid);
        gridview.setAdapter(new EditTextAdapter(board));
        final Context context = getApplicationContext();
        final Spinner spinner = findViewById(R.id.difficulySpinner);
        findViewById(R.id.saveBoardBtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String difficulty = spinner.getSelectedItem().toString().toLowerCase();
                String boardString = BoardUtil.getBoardString(board);
                Log.wtf("CREATE", "Saving board. Difficulty: '" + difficulty + "'");
                Log.wtf("CREATE", "Saving board. Board string: '" + boardString + "'");
                if (BoardStorageUtil.addBoard(context, difficulty, boardString)) {
                    Toast.makeText(context, R.string.boardSaveWorked, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, R.string.boardSaveFailed, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Log.wtf("easy boards", BoardStorageUtil.readBoards(context, "easy"));
        Log.wtf("medium boards", BoardStorageUtil.readBoards(context, "medium"));
        Log.wtf("hard boards", BoardStorageUtil.readBoards(context, "hard"));

    }

}
