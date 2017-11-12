package no.ntnu.nauybeng.sudoku.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import no.ntnu.nauybeng.sudoku.R;
import no.ntnu.nauybeng.sudoku.EditTextAdapter;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        String mode = getIntent().getStringExtra("game_mode");

        GridView gridview = findViewById(R.id.sudokuGrid);
        gridview.setAdapter(new EditTextAdapter(this));
    }

}
