package com.helloworld.quantum.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import java.util.Random;

public class DiceRollerActivity extends AppCompatActivity {
    TextView navDiceRoller, navBMI, rollResult;
    Button button1, button2, button3, button4, rollButton;
    ImageView diceImage1, diceImage2, diceImage3, diceImage4;
    ImageView[] diceImages;
    int diceCount = 2; // default: 2 dice
    Random rand;

    int[] diceDrawables = {
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
    };

    int[] colors = {
            Color.RED, Color.BLUE, Color.GREEN,
            Color.YELLOW, Color.MAGENTA, Color.CYAN
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diceroller);

        rand = new Random();

        // Navigation
        navDiceRoller = findViewById(R.id.navDiceRoller);
        navBMI = findViewById(R.id.navBMI);

        navDiceRoller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // already here, do nothing
            }
        });

        navBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiceRollerActivity.this, BMIActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Dice buttons
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        rollButton = findViewById(R.id.rollButton);
        rollResult = findViewById(R.id.rollResult);

        // Dice images
        diceImage1 = findViewById(R.id.diceImage1);
        diceImage2 = findViewById(R.id.diceImage2);
        diceImage3 = findViewById(R.id.diceImage3);
        diceImage4 = findViewById(R.id.diceImage4);

        diceImages = new ImageView[]{diceImage1, diceImage2, diceImage3, diceImage4};

        // Buttons set number of dice
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDiceCount(1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDiceCount(2);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDiceCount(3);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDiceCount(4);
            }
        });

        // Roll dice
        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        // Default: 2 dice
        setDiceCount(2);
    }

    private void setDiceCount(int count) {
        diceCount = count;
        for (int i = 0; i < diceImages.length; i++) {
            if (i < count) {
                diceImages[i].setVisibility(View.VISIBLE);
            } else {
                diceImages[i].setVisibility(View.GONE);
            }
        }
        rollResult.setText("Number of dice: " + count);
    }

    private void rollDice() {
        int sum = 0;
        StringBuilder resultText = new StringBuilder("You rolled: ");

        for (int i = 0; i < diceCount; i++) {
            int roll = rand.nextInt(6); // 0–5
            diceImages[i].setImageResource(diceDrawables[roll]);

            // pick a random color for this dice
            int color = colors[rand.nextInt(colors.length)];

            // get the drawable we just set
            Drawable drawable = diceImages[i].getDrawable();

            if (drawable instanceof VectorDrawableCompat) {
                ((VectorDrawableCompat) drawable).setTint(color);
            } else if (drawable instanceof VectorDrawable) {
                ((VectorDrawable) drawable).setTint(color);
            }

            int value = roll + 1;
            sum += value;

            resultText.append(value);
            if (i < diceCount - 1) {
                resultText.append(" + ");
            }
        }

        resultText.append(" = ").append(sum);
        rollResult.setText(resultText.toString());
    }
}
