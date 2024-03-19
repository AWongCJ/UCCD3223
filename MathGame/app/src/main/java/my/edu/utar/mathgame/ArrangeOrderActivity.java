package my.edu.utar.mathgame;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class ArrangeOrderActivity extends Activity {

    TextView tv, tvResult;
    View.OnClickListener ansListener;
    LinearLayout contentOfCircle;
    private static final int NUM_CIRCLES = 6;
    private static final int NUM_EMPTY_CIRCLES = 3;
    //private static final int ANIMATION_DURATION = 200; // milliseconds

    private TextView[] circles;
    private int[] circleValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrange_order);


        tv = findViewById(R.id.activityTitle);
        contentOfCircle = findViewById(R.id.contentArea);


        tv.setBackgroundColor(Color.parseColor("#caffbf"));
        tv.setText("Make the 3 numbers in ascending order!");
        tv.setTextSize(30);

        TextView res = findViewById(R.id.response);
        res.setBackgroundColor(Color.parseColor("#caffbf"));

        contentOfCircle.setBackgroundColor(Color.parseColor("#caffbf"));

        circles = new TextView[NUM_CIRCLES];
        circleValues = new int[NUM_CIRCLES];

        // Find circle TextViews directly by their IDs (assuming unique IDs are assigned)
        circles[0] = findViewById(R.id.circleView1);
        circles[1] = findViewById(R.id.circleView2);
        circles[2] = findViewById(R.id.circleView3);
        circles[3] = findViewById(R.id.circleView4);
        circles[4] = findViewById(R.id.circleView5);
        circles[5] = findViewById(R.id.circleView6);

        initializeCircles();

        // Set click listeners for down circles (bottom 3)
        for (int i = NUM_EMPTY_CIRCLES; i < NUM_CIRCLES; i++) {
            circles[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moveCircleUp(v);
                }
            });
        }
    }

    private void initializeCircles() {
        for (int i = 0; i < NUM_CIRCLES; i++) {
            if (i < NUM_EMPTY_CIRCLES) {
                circles[i].setText("");
                circleValues[i] = 0; // Represent empty circle
            } else {
                circleValues[i] = generateRandomNumber();
                circles[i].setText(String.valueOf(circleValues[i]));
            }
        }
    }

    private int generateRandomNumber() {
        // Adjust range as needed (e.g., 1 to 100)
        return new Random().nextInt(100) + 1;
    }

    private void moveCircleUp(View v) {
        TextView res = findViewById(R.id.response);

        int clickedCircleIndex = -1;
        for (int i = NUM_EMPTY_CIRCLES; i < NUM_CIRCLES; i++) {
            if (circles[i] == v) {
                clickedCircleIndex = i;
                break;
            }
        }

        if (clickedCircleIndex != -1) {
            // Find first empty circle from top
            int emptyCircleIndex = -1;
            for (int i = 0; i < NUM_EMPTY_CIRCLES; i++) {
                if (circleValues[i] == 0) {
                    emptyCircleIndex = i;
                    break;
                }
            }

            if (emptyCircleIndex != -1) {
                // Swap values
                circleValues[emptyCircleIndex] = circleValues[clickedCircleIndex];
                circleValues[clickedCircleIndex] = 0;

                // Animate circle movement
                //animateCircleMovement(circles[clickedCircleIndex], circles[emptyCircleIndex]);

                // Update UI after animation
                circles[emptyCircleIndex].setText(String.valueOf(circleValues[emptyCircleIndex]));
                circles[clickedCircleIndex].setText("");
            }


            if (emptyCircleIndex == 2){

                if ((circleValues[0] < circleValues[1]) && (circleValues[1] < circleValues[2])) {

                    res.setBackgroundColor(Color.GREEN);
                    res.setText("Correct!");
                } else {
                    res.setBackgroundColor(Color.RED);
                    res.setText("Try again!");
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        initializeCircles();
                        // Call again to generate new question
                    }
                }, 800);

            }

        }

         // Delay for 0.3 second (adjust as needed)
    }
}
