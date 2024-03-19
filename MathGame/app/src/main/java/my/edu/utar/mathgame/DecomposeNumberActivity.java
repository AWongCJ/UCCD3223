
package my.edu.utar.mathgame;

import static java.lang.String.valueOf;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
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
    private ArrayList<Integer> options; // List to store options for user selection

    private static final int NUM_CIRCLES = 4;
    //private static final int ANIMATION_DURATION = 200; // milliseconds

    private TextView[] circles;
    private int[] circleValues;
    int numCirclesClicked;

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

        // Implement logic to display options for user selection (replace with your UI elements)
        // You can use TextViews or Buttons for options
        // Here's a sample using TextViews:
        /*
        TextView option1 = new TextView(this);
        option1.setText(String.format("Option 1: %d + %d", options.get(0), options.get(1)));
        option1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(options.get(0), options.get(1));
            }
        });


        */

        circles[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    TextView circle = (TextView) v;
                    int value = Integer.parseInt(circle.getText().toString());

                    if (numCirclesClicked < 3) {
                        // Add selected value to options list (adjust as needed based on your UI elements)
                        options.add(value);
                        numCirclesClicked++;

                        if (numCirclesClicked == 2) {
                            // Proceed to check answer
                            checkAnswer(options.get(0), options.get(1));
                        }
                    } else {
                        // Handle the case where user clicks more than 2 circles
                        Toast.makeText(DecomposeNumberActivity.this, "You can only select 2 circles!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    private void initializeCircles() {

        int[] randNum = new int[2];

        for (int i = 1; i < 4; i++) {
            circleValues[i] = generateRandomNumber();
            circles[i].setText(valueOf(circleValues[i]));
        }

        do {
            for (int i = 0; i < 2; i++){
                randNum[i] = generateRandomNumberSmall();
            }
        }while(randNum[0] == randNum[1]);

        circleValues[0] = circleValues[randNum[0]] + circleValues[randNum[1]];
        circles[0].setText(valueOf(circleValues[0]));
        tv.setText(String.valueOf(circleValues[randNum[0]])
                + "," + String.valueOf(circleValues[randNum[1]]) + ","
                + randNum[0] + "," + randNum[1]);



        TextView eqn = findViewById(R.id.equation);
        eqn.setBackgroundColor(Color.parseColor("#9bf6ff"));
        eqn.setTextSize(25);
        eqn.setText("Equation: "
                    + String.valueOf(circleValues[0])
                    +"="
                    + String.valueOf(circleValues[randNum[0]])
                    +
                    "+"
                    +String.valueOf(circleValues[randNum[1]]));
    }
    private int generateRandomNumber() {
        // Adjust range as needed (e.g., 1 to 100)
        return new Random().nextInt(50);
    }
    private int generateRandomNumberSmall() {
        // Adjust range as needed (e.g., 1 to 100)
        return new Random().nextInt(3)+1;
    }

    private void checkAnswer(int option1, int option2) {
        if (option1 + option2 == topNumber) {
            tvResult.setText("Correct!");
            tvResult.setTextColor(Color.GREEN);
        } else {
            tvResult.setText("Try Again!");
            tvResult.setTextColor(Color.RED);
        }
    }
}

