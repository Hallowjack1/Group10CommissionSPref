package com.demo.group10commissionspref;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    EditText etName, etRealEstate, etRealEstateSold, etCommission;
    Button btnCalculate;

    public static final String mypreference = "commissionpref";
    public static final String Name = "NameKey";
    public static final String RealEstate = "RealEstateKey";
    public static final String RealEstateSold = "RealEstateSoldKey";
    public static final String Commission = "CommissionKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName = findViewById(R.id.etName);
        etRealEstate = findViewById(R.id.etRealEstate);
        etRealEstateSold = findViewById(R.id.etRealEstateSold);
        etCommission = findViewById(R.id.etAgentCommission);
        btnCalculate = findViewById(R.id.btnCalculate);

        sharedPreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = etName.getText().toString();
                int realestate = Integer.parseInt(etRealEstate.getText().toString().trim());
                int realestatesold = Integer.parseInt(etRealEstateSold.getText().toString().trim());
                int commission = Integer.parseInt(etCommission.getText().toString().trim());

                double Vat = (realestate * realestatesold) * 0.1;
                int NetPay = (int) (((realestate * realestatesold) * commission / 100) - Vat);

                SharedPreferences. Editor editor = sharedPreferences.edit();
                editor.putString("NAME", name);
                editor.putInt("REALESTATE", realestate);
                editor.putInt("REALESTATESOLD", realestatesold);
                editor.putInt("COMMISSION", commission);
                editor.putInt("NETPAY", NetPay);
                editor.apply();

                Intent intent = new Intent(MainActivity.this, Result.class);
                startActivity(intent);
                finish();
            }
        });
    }
}