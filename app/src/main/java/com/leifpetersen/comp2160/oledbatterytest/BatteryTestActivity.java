package com.leifpetersen.comp2160.oledbatterytest;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.SweepGradient;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BatteryTestActivity extends AppCompatActivity {
    private final int FADE_DURATION = 1000;
    private ConstraintLayout page;
    private int backgroundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_battery_test);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent receivedIntent = getIntent();
        backgroundId = receivedIntent.getIntExtra(MainActivity.ID_EXTRA_NAME, 0);

        page = findViewById(R.id.main);
        page.setBackgroundResource(backgroundId);

        page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BatteryTestActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            try {
                AnimationDrawable animation = (AnimationDrawable) page.getBackground();

                animation.setEnterFadeDuration(FADE_DURATION);
                animation.setExitFadeDuration(FADE_DURATION);
                animation.start();
            } catch (ClassCastException e) {
                // Not an animation.
            }
        }
    }
}