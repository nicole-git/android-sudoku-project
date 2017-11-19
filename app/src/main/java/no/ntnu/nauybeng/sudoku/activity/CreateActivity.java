package no.ntnu.nauybeng.sudoku.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import no.ntnu.nauybeng.sudoku.BoardUtil;
import no.ntnu.nauybeng.sudoku.EditTextAdapter;
import no.ntnu.nauybeng.sudoku.R;

public class CreateActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        final EditText[][] board = BoardUtil.getEmptyBoard(this);
        GridView gridview = findViewById(R.id.sudokuGrid);
        gridview.setAdapter(new EditTextAdapter(board));
        final Spinner spinner = findViewById(R.id.difficulySpinner);
        findViewById(R.id.saveBoardBtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               BoardUtil.getBoardString(board);
               spinner.getSelectedItem().toString().toLowerCase();
            }
        });
    }

}
