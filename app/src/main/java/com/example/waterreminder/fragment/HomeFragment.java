package com.example.waterreminder.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.water.OnItemClickListener;
import com.example.water.Water;
import com.example.water.WaterAdapter;
import com.example.waterreminder.MainActivity;
import com.example.waterreminder.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView rv_water;
    public WaterAdapter waterAdapter;
    public TextView total_water;
    ArrayList<Water> getList;

    private TextView txtProgress;
    private ProgressBar progressBar;
    private int pStatus = 0;
    private Handler handler = new Handler();
    private MainActivity mainActivity;
    String weight = "";
    int sumWater = 0;
    int drink = 0;
    public static final String MyPREFERENCES = "MyPrefs" ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rv_water = view.findViewById(R.id.rv_water);
        total_water = view.findViewById(R.id.total_water);
        txtProgress = view.findViewById(R.id.txtProgress);
        progressBar = view.findViewById(R.id.progressBar);

        SharedPreferences sharedpreferences ;
        sharedpreferences = getActivity().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        mainActivity = (MainActivity) getActivity();
        weight = mainActivity.getWeight();
        if (weight != null) {
            sumWater = (int) Float.parseFloat(weight) * 30;
        }
        total_water.setText(sumWater + "ml");

        GridLayoutManager linearLayoutManager = new GridLayoutManager(getActivity(), 4, RecyclerView.VERTICAL, false);
        rv_water.setLayoutManager(linearLayoutManager);

        String divideWater = String.valueOf(sumWater / 8);
        String divideWaterPlus = String.valueOf(Integer.parseInt(divideWater) + sumWater- Integer.parseInt(divideWater)*8);
        getList = new ArrayList<>();
        getList.add(new Water(R.drawable.ic_water_reminder, "Tiger", divideWater + "ml", "Time: 6h00"));
        getList.add(new Water(R.drawable.ic_water_reminder, "Tiger", divideWater + "ml", "Time: 8h00 "));
        getList.add(new Water(R.drawable.ic_water_reminder, "Tiger", divideWater + "ml", "Time: 9h00 "));
        getList.add(new Water(R.drawable.ic_water_reminder, "Tiger", divideWater + "ml", "Time: 11h00 "));
        getList.add(new Water(R.drawable.ic_water_reminder, "Tiger", divideWaterPlus + "ml", "Time: 13h00 "));
        getList.add(new Water(R.drawable.ic_water_reminder, "Tiger", divideWater + "ml", "Time: 15h00 "));
        getList.add(new Water(R.drawable.ic_water_reminder, "Tiger", divideWater + "ml", "Time: 17h00 "));
        getList.add(new Water(R.drawable.ic_water_reminder, "Tiger", divideWater + "ml", "Time: 19h00 "));


        waterAdapter = new WaterAdapter(getList, new OnItemClickListener() {
            int total = 0;

            @Override
            public void onItemClick(Water water, int position) {
                Water water1 = new Water(R.drawable.ic_done, water.getTitle(), water.getDes(), water.getTime());
                getList.set(position, water1);
                waterAdapter.notifyDataSetChanged();

                String[] totals = water.getDes().split("ml");

                if (water.getId() != R.drawable.ic_done) {
                    total = Integer.parseInt(totals[0]) + total;
                    drink = drink +1;

                    if (total > sumWater) {
                        total = sumWater;
                    }
                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            if (pStatus <= 87) {
                                pStatus = pStatus + 13;
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(pStatus);
                                        txtProgress.setText(pStatus + "%");
                                    }
                                });
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                            } else {
                                pStatus = 100;
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressBar.setProgress(pStatus);
                                        txtProgress.setText(pStatus + "%");
                                    }
                                });
                            }
                        }
                    }).start();
                }
//                total_water.setText(String.valueOf(drink));
                total_water.setText(total + "ml of " + sumWater + "ml");
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("TAG_NAME", String.valueOf(drink));
                editor.commit();


            }

        });

        rv_water.setAdapter(waterAdapter);
        return view;
    }


}
