package com.leifpetersen.comp2160.oledbatterytest;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    public static final String ID_EXTRA_NAME = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setUpButtons();
    }

    private void setUpButtons() {
        Button[] buttons = {
                findViewById(R.id.light),
                findViewById(R.id.dark),
                findViewById(R.id.red),
                findViewById(R.id.green),
                findViewById(R.id.blue),
                findViewById(R.id.animated)
        };

        int[] colourIds = {
                R.color.white,
                R.color.black,
                R.color.red,
                R.color.green,
                R.color.blue,
                R.drawable.animated_gradient
        };

        for (int i = 0; i < buttons.length; i++) {
            int current = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, BatteryTestActivity.class);
                    intent.putExtra(ID_EXTRA_NAME, colourIds[current]);
                    startActivity(intent);
                }
            });
        }
    }
}