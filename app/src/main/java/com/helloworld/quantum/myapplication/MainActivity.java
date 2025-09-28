package com.helloworld.quantum.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.helloworld.quantum.myapplication.BMIActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LinearLayout diceContainer;
    TextView rollResult, navDiceRoller, navBMI;
    Button button1, button2, button3, button4, rollButton;
    ImageView[] diceImages;
    int[] diceDrawables = {
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
    };
    Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rand = new Random();

        // Navigation
        navDiceRoller = findViewById(R.id.navDiceRoller);
        navBMI = findViewById(R.id.navBMI);

        navDiceRoller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Already on Dice Roller, do nothing
            }
        });

        navBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start BMI Activity
                Intent intent = new Intent(MainActivity.this, BMIActivity.class);
                startActivity(intent);
            }
        });

        // Dice buttons
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        rollButton = findViewById(R.id.rollButton);
        rollResult = findViewById(R.id.rollResult);
        diceContainer = findViewById(R.id.diceContainer);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDiceLayout(R.layout.one_dice);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDiceLayout(R.layout.activity_main);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDiceLayout(R.layout.three_dice);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDiceLayout(R.layout.four_dice);
            }
        });

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        // Load default layout (2 dice)
        loadDiceLayout(R.layout.activity_main);
    }

    private void loadDiceLayout(int layoutId) {
        diceContainer.removeAllViews();
        View diceLayout = getLayoutInflater().inflate(layoutId, null);
        diceContainer.addView(diceLayout);

        int childCount = ((LinearLayout) diceLayout).getChildCount();
        diceImages = new ImageView[childCount];
        for (int i = 0; i < childCount; i++) {
            diceImages[i] = (ImageView) ((LinearLayout) diceLayout).getChildAt(i);
        }

        rollResult.setText("Number of dice: " + childCount);
    }

    private void rollDice() {
        if (diceImages == null) return;
        int sum = 0;
        for (int i = 0; i < diceImages.length; i++) {
            int roll = rand.nextInt(6); // 0-5
            diceImages[i].setImageResource(diceDrawables[roll]);
            sum += roll + 1;
        }
        rollResult.setText("You rolled: " + sum);
    }
}