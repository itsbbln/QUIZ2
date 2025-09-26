package com.helloworld.quantum.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz2.R;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView dice1, dice2;
    private TextView diceResult;
    private Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice1 = findViewById(R.id.diceImage1);
        dice2 = findViewById(R.id.diceImage2);
        diceResult = findViewById(R.id.rollResult);
        Button rollButton = findViewById(R.id.rollButton);
        rand = new Random();

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
    }

    private void rollDice() {
        int roll1 = rand.nextInt(6) + 1;
        int roll2 = rand.nextInt(6) + 1;

        diceResult.setText("Result: " + roll1 + " + " + roll2 + " = " + (roll1 + roll2));

        setDiceImage(dice1, roll1);
        setDiceImage(dice2, roll2);

        // Random background colors for each dice
        int[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.MAGENTA, Color.CYAN};
        dice1.setBackgroundColor(colors[rand.nextInt(colors.length)]);
        dice2.setBackgroundColor(colors[rand.nextInt(colors.length)]);

        // Rotate animation
        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        dice1.startAnimation(rotate);
        dice2.startAnimation(rotate);
    }

    private void setDiceImage(ImageView img, int value) {
        switch (value) {
            case 1:
                img.setImageResource(R.drawable.dice1);
                break;
            case 2:
                img.setImageResource(R.drawable.dice2);
                break;
            case 3:
                img.setImageResource(R.drawable.dice3);
                break;
            case 4:
                img.setImageResource(R.drawable.dice4);
                break;
            case 5:
                img.setImageResource(R.drawable.dice5);
                break;
            case 6:
                img.setImageResource(R.drawable.dice6);
                break;
        }
    }
}