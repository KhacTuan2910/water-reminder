package com.example.waterreminder;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity {
    private EditText edt_name, edt_age, edt_weight, edt_heigh;
    private Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        addListener();
        reveived_data();


    }



    public void init() {
        edt_name = findViewById(R.id.edt_name);
        edt_age = findViewById(R.id.edt_age);
        edt_weight = findViewById(R.id.edt_weight);
        edt_heigh = findViewById(R.id.edt_heigh);
        btn_save = findViewById(R.id.btn_save);
    }

    private void sendData() {
        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);

        String mWeight = edt_weight.getText().toString().trim();
        String mName = edt_name.getText().toString().trim();
        String mAge = edt_age.getText().toString().trim();
        String mHeigh = edt_heigh.getText().toString().trim();

        intent.putExtra("weight_data", mWeight);
        intent.putExtra("name_data", mName);
        intent.putExtra("age_data", mAge);
        intent.putExtra("heigh_data", mHeigh);

        startActivity(intent);
    }

    private void reveived_data() {
        Intent intent = getIntent();
        String fWeight = intent.getStringExtra("fweight");
        String fName = intent.getStringExtra("fname");
        String fAge = intent.getStringExtra("fage");
        String fHeigh = intent.getStringExtra("fheigh");
        if (fWeight != null && fName != null || fAge != null || fHeigh != null) {
            edt_name.setText(fName);
            edt_age.setText(fAge);
            edt_heigh.setText(fHeigh);
            edt_weight.setText(fWeight);
        }
    }


    public void addListener() {
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(edt_name.getText())) {
                    edt_name.setError("Name is required");
                    return;
                }
                if (TextUtils.isEmpty(edt_age.getText())) {
                    edt_age.setError("Age is required");
                    return;
                }
                if (TextUtils.isEmpty(edt_weight.getText())) {
                    edt_weight.setError("Weight is required");
                    return;
                }
                if (TextUtils.isEmpty(edt_heigh.getText())) {
                    edt_heigh.setError("Heigh is required");
                    return;
                } else {
                    sendData();

                }
            }
        });
    }
}