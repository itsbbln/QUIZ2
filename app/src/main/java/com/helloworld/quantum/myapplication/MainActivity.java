package com.helloworld.quantum.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button buttonDiceRoller, buttonBMICalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // This is the menu screen XML

        // Find buttons
        buttonDiceRoller = findViewById(R.id.buttonDiceRoller);
        buttonBMICalculator = findViewById(R.id.buttonBMICalculator);

        // Navigate to Dice Roller activity
        buttonDiceRoller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DiceRollerActivity.class);
                startActivity(intent);
            }
        });

        // Navigate to BMI Calculator activity
        buttonBMICalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BMIActivity.class);
                startActivity(intent);
            }
        });
    }
}
