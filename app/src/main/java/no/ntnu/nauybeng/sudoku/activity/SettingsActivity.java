package no.ntnu.nauybeng.sudoku.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import no.ntnu.nauybeng.sudoku.R;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        findViewById(R.id.englishLanguageBtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setLocale("en");
            }
        });
        findViewById(R.id.norwegianLanguageBtn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setLocale("no");
            }
        });
    }

    public void setLocale(String lang) {
        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        conf.locale = new Locale(lang);
        res.updateConfiguration(conf, res.getDisplayMetrics());
        finishAffinity(); // kill everything
        startActivity(new Intent(this, MainActivity.class));
        startActivity(new Intent(this, SettingsActivity.class));
    }

}
