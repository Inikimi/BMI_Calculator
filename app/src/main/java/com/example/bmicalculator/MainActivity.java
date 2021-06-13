package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etw = (EditText) findViewById(R.id.etWeight);
        final EditText eth = (EditText) findViewById(R.id.etHeight);
        final TextView tv = (TextView) findViewById(R.id.tvBMI);

        findViewById(R.id.btnCalc).setOnClickListener(new View.OnClickListener() {

            @SuppressLint("DefaultLocale")
            @Override
            public void onClick(View v) {

                String str1 = etw.getText().toString();
                String str2 = eth.getText().toString();


                if(TextUtils.isEmpty(str1)){
                    etw.setError("Please enter your weight");
                    etw.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(str2)){
                    eth.setError("Please enter your height");
                    eth.requestFocus();
                    return;
                }

                float weight = Float.parseFloat(str1);
                float height = Float.parseFloat(str2)/100;
                float bmiValue = calculateBMI (weight, height);

                String bmiInterpretation = interpretBMI(bmiValue);

                tv.setText(String.valueOf(String.format("%.1f", bmiValue) + "\n" + bmiInterpretation));

            }
        });
        findViewById(R.id.textView11).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked_btn("https://www.news-medical.net/health/What-is-Body-Mass-Index-(BMI).aspx");
            }
        });

    }

    public void clicked_btn(String url){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private float calculateBMI (float weight, float height) {
        return (float) (weight / (height * height));
    }

    private String interpretBMI(float bmiValue) {

        if (bmiValue < 18.4) {
            return "Underweight" + "\n" +
                    "Malnutrition risk";

        } else if (bmiValue <= 24.9 ) {
            return "Normal weight" + "\n" +
                    "Low risk";

        } else if (bmiValue <= 29.9 ) {
            return "Overweight" + "\n" +
                    "Enhanced risk" ;

        } else if (bmiValue <= 34.9 ) {
            return "Moderately obese" + "\n" +
                    "Medium risk";

        } else if (bmiValue < 39.9 ) {
            return "Severely obese" + "\n" +
                    "High Risk";

        } else {
            return "Very severely obese" + "\n" +
                    "Very high risk";
        }
    }

    @Override
    public void onClick(View v) {

    }
}