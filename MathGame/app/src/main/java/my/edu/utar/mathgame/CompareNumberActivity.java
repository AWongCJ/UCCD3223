package my.edu.utar.mathgame;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.Random;

public class CompareNumberActivity extends Activity {

    TextView tv, tvResult;
    int num1;
    int num2;
    View.OnClickListener ansListener;
    LinearLayout contentOfCircle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_number);

        tv = findViewById(R.id.activityTitle);
        contentOfCircle = findViewById(R.id.contentArea);
        tvResult = findViewById(R.id.response);

        tv.setBackgroundColor(Color.parseColor("#ffadad"));
        tv.setText("Find the larger number!");
        tv.setTextSize(30);
        contentOfCircle.setBackgroundColor(Color.parseColor("#ffadad"));

        TextView circle1 = findViewById(R.id.circleView1);
        TextView circle2 = findViewById(R.id.circleView2);


        // Generate and display numbers initially
        generateAndCompareNumbers(circle1, circle2);

        // Set click listeners for circle views
        circle1.setOnClickListener(ansListener);
        circle2.setOnClickListener(ansListener);

    }

    public void generateAndCompareNumbers(TextView circle1, TextView circle2) {
        Random random = new Random();
        int number1;
        int number2;
        do {
            number1 = random.nextInt(100) + 1;
            number2 = random.nextInt(100) + 1;
        } while (number1 == number2);

        num1 = number1;
        num2 = number2;
        // Set text immediately after generating numbers
        circle1.setText(String.valueOf(num1));
        circle2.setText(String.valueOf(num2));

        ansListener = new View.OnClickListener() {
            public void onClick(View v) {

                if (((num1 > num2)&&(v.getId() == R.id.circleView1)) ||
                    ((num1 < num2)&&(v.getId() == R.id.circleView2))) {

                    tvResult.setBackgroundColor(Color.GREEN);
                    tvResult.setText("Correct!");
                }
//                else if((num1 > num2)&&(v.getId() == R.id.circleView2)){
//                        tvResult.setBackgroundColor(Color.RED);
//                        tvResult.setText("Try Again!");
//
//                }
                else if(((num1 < num2)&&(v.getId() == R.id.circleView1)) ||
                        ((num1 > num2)&&(v.getId() == R.id.circleView2))){

                        tvResult.setBackgroundColor(Color.RED);
                        tvResult.setText("Try Again!");
                    }
//                else if((num1 < num2)&&(v.getId() == R.id.circleView2)){
//                        tvResult.setBackgroundColor(Color.GREEN);
//                        tvResult.setText("Correct!");
//                }
                tvResult.setTextSize(45);

                // Refresh after showing result (optional delay for feedback)
                new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    generateAndCompareNumbers(circle1, circle2); // Call again to generate new question
                }
            }, 300); // Delay for 1 second (adjust as needed)
            }
        };
    }
}
