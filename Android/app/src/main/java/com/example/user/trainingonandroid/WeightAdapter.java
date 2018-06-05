package com.example.user.trainingonandroid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayushbihani on 5/6/18.
 */

public class WeightAdapter extends BaseAdapter{
    private ArrayList<Weights> list;
    private Context context;

    public WeightAdapter(Context context, ArrayList<Weights> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final WeightsHolder holder;
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.row, viewGroup,false);
        }
            holder = new WeightsHolder();
            holder.slope = (TextView)view.findViewById(R.id.slope_);
            holder.constant = (TextView)view.findViewById(R.id.constant_);
            holder.iteration = (TextView)view.findViewById(R.id.iteration);
            Weights w = list.get(i);
            holder.iteration.setText("Iteration:".concat(String.valueOf(w.getIteration())));
            holder.constant.setText("Constant:".concat(String.valueOf(w.getB())));
            holder.slope.setText("Slope:".concat(String.valueOf(w.getW())));
        return view;
    }

    private class WeightsHolder{
        private TextView slope, constant, iteration;
    }

    public void refresh(ArrayList<Weights> weights) {
        this.list = weights;
        notifyDataSetChanged();
    }
}
