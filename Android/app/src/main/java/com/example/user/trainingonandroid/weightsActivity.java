package com.example.user.trainingonandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by ayushbihani on 5/6/18.
 */

public class weightsActivity extends AppCompatActivity {

    private Training training;
    private ArrayList<Weights> weights;
    private WeightAdapter adapter;
    private String path = "graph.pb";
    private float slope,constant;
    private ListView list;
    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        slope = intent.getFloatExtra("slope",-5);
        constant = intent.getFloatExtra("constant",-5);
        setContentView(R.layout.weights_layout);
        list= (ListView)findViewById(R.id.list);
        training = new Training(getAssets(), path, slope, constant);
        training.loadModel();
        weights = training.train();
        adapter = new WeightAdapter(weightsActivity.this, weights);
        list.setAdapter(adapter);
    }
}
