package no.ntnu.nauybeng.sudoku.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import no.ntnu.nauybeng.sudoku.R;

public class StartGameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamestart);
        findViewById(R.id.newEasyGameBtn).setOnClickListener(getClickListenerForDifficulty("easy"));
        findViewById(R.id.newMediumGameBtn).setOnClickListener(getClickListenerForDifficulty("medium"));
        findViewById(R.id.newHardGameBtn).setOnClickListener(getClickListenerForDifficulty("hard"));
    }

    private View.OnClickListener getClickListenerForDifficulty(final String difficulty) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(StartGameActivity.this, GameActivity.class);
                intent.putExtra("game_mode", difficulty);
                startActivity(intent);
            }
        };
    }

}
