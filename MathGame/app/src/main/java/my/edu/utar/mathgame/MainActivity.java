package my.edu.utar.mathgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button compareButton = findViewById(R.id.compare_number_button);
        compareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent compareIntent = new Intent(MainActivity.this, CompareNumberActivity.class);
                startActivity(compareIntent);
            }
        });

        Button arrangeButton = findViewById(R.id.arrange_order_button);
        arrangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent arrangeIntent = new Intent(MainActivity.this, ArrangeOrderActivity.class);
                startActivity(arrangeIntent);
            }
        });

        Button decomposeButton = findViewById(R.id.decompose_number_button);
        decomposeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent arrangeIntent = new Intent(MainActivity.this, DecomposeNumberActivity.class);
                startActivity(arrangeIntent);
            }
        });

    }
}