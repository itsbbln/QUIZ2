package com.helloworld.quantum.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class BMIActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bmi);

        // ✅ Navigation
        TextView navDiceRoller = findViewById(R.id.navDiceRoller);
        TextView navBMI = findViewById(R.id.navBMI);

        navDiceRoller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BMIActivity.this, DiceRollerActivity.class);
                startActivity(intent);
                finish();
            }
        });

        navBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // already here, do nothing
            }
        });

        // ✅ Button for BMI
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateBMI();
            }
        });
    }

    private void calculateBMI() {
        EditText heightText = findViewById(R.id.heightInput);
        EditText weightText = findViewById(R.id.weightInput);

        String heightStr = heightText.getText().toString();
        String weightStr = weightText.getText().toString();

        if (heightStr.isEmpty() || weightStr.isEmpty()) {
            return; // Do nothing if inputs are empty
        }

        double height = Double.parseDouble(heightStr);
        double weight = Double.parseDouble(weightStr);

        double BMI = weight / (height * height);

        // Format BMI to 1 decimal place
        DecimalFormat df = new DecimalFormat("#.#");
        String BMI_trimmed = df.format(BMI);

        // Show BMI value
        TextView BMIResult = findViewById(R.id.BMIResult);
        BMIResult.setText(BMI_trimmed);

        // Determine BMI category
        String BMI_Cat;
        if (BMI < 18.5) {
            BMI_Cat = "Underweight";
        } else if (BMI < 25) {
            BMI_Cat = "Normal";
        } else if (BMI < 30) {
            BMI_Cat = "Overweight";
        } else {
            BMI_Cat = "Obese";
        }

        // Show BMI category
        TextView BMICategory = findViewById(R.id.BMICategory);
        BMICategory.setText(BMI_Cat);
    }
}
