package com.demo.group10commissionspref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {
    TextView tvName, tvNetPay;
    Button btnBack;
    SharedPreferences sharedPreferences;
    public static final String mypreference = "commissionpref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        tvName = findViewById(R.id.tvName);
        tvNetPay = findViewById(R.id.tvNetPay);
        btnBack = findViewById(R.id.btnBack);

        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        String sName = sharedPreferences.getString("NAME", "");
        tvName.setText("Agent Name: " + sName);

        int sNetPay = sharedPreferences.getInt("NETPAY", 0);
        tvNetPay.setText("Commission Net Pay is: " + sNetPay);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(Result.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}