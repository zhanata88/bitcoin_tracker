package at.ac.univie.hci.btctracker.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.ac.univie.hci.btctracker.R;

/**
 * to show report for the last day on app start
 * the idea is to make some analysis in background and give user a tip
 * what currencies showed good values in the past
 *
 * Created by vanish on 11.05.18.
 */

public class PopReportActivity extends Activity {
    TextView coinList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_popreport);

        coinList = findViewById(R.id.goodCoinList);
        Intent intent = getIntent();
        coinList.setText(intent.getExtras().getString("List"));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*0.5),(int)(height*0.5));
    }

}
