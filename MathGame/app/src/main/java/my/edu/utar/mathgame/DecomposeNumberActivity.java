
package my.edu.utar.mathgame;

import static java.lang.String.valueOf;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class DecomposeNumberActivity extends Activity {

    private TextView tv, tvResult;
    private LinearLayout contentOfCircle;
    private int topNumber;
    View.OnClickListener ansListener;
    int[] randNum = new int[2];
    private ArrayList<Integer> options; // List to store options for user selection

    private static final int NUM_CIRCLES = 4;
    //private static final int ANIMATION_DURATION = 200; // milliseconds

    private TextView[] circles;
    private int[] circleValues;
    int numCirclesClicked = 0, result = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decompose_number);

        tv = findViewById(R.id.activityTitle);
        contentOfCircle = findViewById(R.id.contentArea);
        TextView eqn = findViewById(R.id.equation); // Assuming the correct answer layout has id "correctAnswer"
        tvResult = findViewById(R.id.response);


        tv.setBackgroundColor(Color.parseColor("#9bf6ff"));
        tv.setText("\nChoose 2 compositions of the number!");
        tv.setTextSize(30);
        contentOfCircle.setBackgroundColor(Color.parseColor("#9bf6ff"));
        eqn.setBackgroundColor(Color.parseColor("#9bf6ff"));
        tvResult.setBackgroundColor(Color.parseColor("#9bf6ff"));
        tvResult.setTextSize(25);

        circles = new TextView[NUM_CIRCLES];
        circleValues = new int[NUM_CIRCLES];

        // Find circle TextViews directly by their IDs (assuming unique IDs are assigned)
        circles[0] = findViewById(R.id.circleView1);
        circles[1] = findViewById(R.id.circleView2);
        circles[2] = findViewById(R.id.circleView3);
        circles[3] = findViewById(R.id.circleView4);

        initializeCircles();
        circles[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.circleView2) {
                    if (numCirclesClicked <=1) {
                        result = result + circleValues[1];
                        numCirclesClicked++;
                        circles[1].setBackgroundColor(Color.parseColor("#ffadad"));

                        if (numCirclesClicked == 2) {
                            circles[1].setBackgroundColor(Color.parseColor("#ffadad"));
                            eqn.setTextSize(25);
                            eqn.setText("Equation: "
                                    + String.valueOf(circleValues[0])
                                    + "="
                                    + String.valueOf(circleValues[randNum[0]])
                                    +
                                    "+"
                                    + String.valueOf(circleValues[randNum[1]]));
                            if (result == circleValues[0]) {
                                tvResult.setText("Correct!");
                                tvResult.setBackgroundColor(Color.GREEN);
                            } else {
                                tvResult.setText("Try Again!");
                                tvResult.setBackgroundColor(Color.RED);
                            }
                            refreshActivity(v);
                        }
                    }
                }
            }
        });
        circles[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.circleView3) {
                    if (numCirclesClicked <= 1) {
                        result = result + circleValues[2];
                        numCirclesClicked++;
                        circles[2].setBackgroundColor(Color.parseColor("#ffadad"));

                        if (numCirclesClicked == 2) {
                            circles[2].setBackgroundColor(Color.parseColor("#ffadad"));
                            eqn.setTextSize(25);
                            eqn.setText("Equation: "
                                    + String.valueOf(circleValues[0])
                                    + "="
                                    + String.valueOf(circleValues[randNum[0]])
                                    +
                                    "+"
                                    + String.valueOf(circleValues[randNum[1]]));
                            if (result == circleValues[0]) {
                                tvResult.setText("Correct!");
                                tvResult.setBackgroundColor(Color.GREEN);
                            } else {
                                tvResult.setText("Try Again!");
                                tvResult.setBackgroundColor(Color.RED);
                            }
                            refreshActivity(v);
                        }
                    }
                }
            }
        });
        circles[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.circleView4) {
                    if (numCirclesClicked <= 1) {
                        result = result + circleValues[3];
                        numCirclesClicked++;
                        circles[3].setBackgroundColor(Color.parseColor("#ffadad"));

                        if (numCirclesClicked == 2) {
                            circles[3].setBackgroundColor(Color.parseColor("#ffadad"));
                            eqn.setTextSize(25);
                            eqn.setText("Equation: "
                                    + String.valueOf(circleValues[0])
                                    + "="
                                    + String.valueOf(circleValues[randNum[0]])
                                    +
                                    "+"
                                    + String.valueOf(circleValues[randNum[1]]));
                            if (result == circleValues[0]) {
                                tvResult.setText("Correct!");
                                tvResult.setBackgroundColor(Color.GREEN);
                            } else {
                                tvResult.setText("Try Again!");
                                tvResult.setBackgroundColor(Color.RED);
                            }
                        }
                        refreshActivity(v);

                    }
                }
            }
        });

    }

    private void initializeCircles() {

        //int[] randNum = new int[2];

        do {
            for (int i = 1; i < 4; i++) {
                circleValues[i] = generateRandomNumber();
                circles[i].setText(valueOf(circleValues[i]));
            }
        } while ((circleValues[1] == circleValues[2]) ||
                (circleValues[2] == circleValues[3]) ||
                (circleValues[1] == circleValues[3]));

        do {
            for (int i = 0; i < 2; i++) {
                randNum[i] = generateRandomNumberSmall();
            }
        } while (randNum[0] == randNum[1]);

        circleValues[0] = circleValues[randNum[0]] + circleValues[randNum[1]];
        circles[0].setText(valueOf(circleValues[0]));
    }
    private int generateRandomNumber() {
        // Adjust range as needed (e.g., 1 to 100)
        return new Random().nextInt(50)+2;
    }
    private int generateRandomNumberSmall() {
        // Adjust range as needed (e.g., 1 to 100)
        return new Random().nextInt(3)+1;
    }

    public void refreshActivity (View v){

        Intent intent = getIntent();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish(); // Call again to generate new question
                startActivity(intent);
            }
        }, 1000);


    }

}

