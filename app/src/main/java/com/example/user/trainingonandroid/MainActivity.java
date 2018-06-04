package com.example.user.trainingonandroid;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int slope, constant;
    private EditText slopeText, constantText;
    private Button train;
    private Training training;
    private AssetManager assetManager;
    private String path = "graph.pb";
    private List<Weights> weights;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slopeText = findViewById(R.id.slope);
        constantText = findViewById(R.id.constant);
        train = findViewById(R.id.train);
        assetManager = this.getAssets();
        train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slope = Integer.parseInt(slopeText.getText().toString());
                constant = Integer.parseInt(constantText.getText().toString());
                training = new Training(assetManager, path, slope, constant);
                training.loadModel();
                weights = training.train();
            }
        });
    }
}
