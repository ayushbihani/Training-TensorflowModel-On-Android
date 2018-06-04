package com.example.user.trainingonandroid;

import android.content.pm.PackageInstaller;
import android.content.res.AssetManager;

import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;
import org.tensorflow.Tensors;
import org.tensorflow.contrib.android.TensorFlowInferenceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by User on 6/1/2018.
 */

public class Training {
    private AssetManager assetManager;
    private String MODEL_PATH;
    private float m, c;
    private TensorFlowInferenceInterface tensorFlowInferenceInterface;
    private List<Weights> weights;

    Training(AssetManager assetManager, String path, float m, float c){
        this.assetManager = assetManager;
        this.MODEL_PATH = path;
        this.m = m;
        this.c = c;
        weights = new ArrayList<>();
    }
    public void loadModel(){
        tensorFlowInferenceInterface = new TensorFlowInferenceInterface(assetManager, MODEL_PATH);
    }

    public List<Weights> train(){
        if(tensorFlowInferenceInterface!=null){
            byte[] def = tensorFlowInferenceInterface.graph().toGraphDef();
            try(Graph graph = new Graph();
                Session session = new Session(graph);){
                graph.importGraphDef(def);
                session.runner().addTarget("init").run();
                Random random = new Random();
                for(int i = 1; i<=7; i++){
                    for(int n = 0; n < 500; n++){
                        float in = random.nextFloat();
                        try(Tensor<Float> input = Tensors.create(in);
                            Tensor<Float> target = Tensors.create(m*in + c);
                        ){
                            session.runner().feed("input", input)
                                            .feed("target", target)
                                            .addTarget("train")
                                            .run();
                        }
                    }
                    weights.add(getVariables(session));
                }
            }
        }
        return weights;
    }

    public Weights getVariables(Session sess){
        List<Tensor<?>> values = sess.runner().fetch("W/read").fetch("b/read").run();
        Weights w = new Weights(values.get(0).floatValue(), values.get(1).floatValue());
        System.out.printf("W = %f\tb = %f\n", values.get(0).floatValue(), values.get(1).floatValue());
        for(Tensor<?> t:values){
            t.close();
        }
        return w;
    }
}
