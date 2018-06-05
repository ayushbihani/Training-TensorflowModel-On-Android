package com.example.user.trainingonandroid;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private float slope, constant;
    private EditText slopeText, constantText;
    private Button train;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        slopeText = (EditText)findViewById(R.id.slopText);
        constantText = (EditText)findViewById(R.id.constantText);
        train = (Button)findViewById(R.id.train);
        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slope = Integer.parseInt(slopeText.getText().toString());
                constant = Integer.parseInt(constantText.getText().toString());
                Intent intent = new Intent(MainActivity.this, weightsActivity.class);
                intent.putExtra("slope", slope);
                intent.putExtra("constant",constant);
                startActivity(intent);
            }
        });
    }
}
