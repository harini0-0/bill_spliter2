package com.example.billspliter;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

public class processing extends AppCompatActivity {
    public int num1,num2;
    public  float disc,tx;
    public String[] itemnames;
    public  String[] names;
    public float[] amt;
    public int[] qty;
    public Boolean[][] checks;
    public EditText discount;
    public EditText tax;
    public float[] prices;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);

        num1 = Objects.requireNonNull(getIntent().getExtras()).getInt("num1");
        num2 =getIntent().getExtras().getInt("num2");
        names = new String[num1];
        itemnames = new String[num2];
        checks= new Boolean[num1][num2];
        itemnames =getIntent().getExtras().getStringArray("itemnames");
        names =getIntent().getExtras().getStringArray("names");
        amt =getIntent().getExtras().getFloatArray("amt");
        qty =getIntent().getExtras().getIntArray("qty");
        Boolean[][] objectArray = (Boolean[][])  getIntent().getExtras().getSerializable("checks");
        for(int i=0;i<num1;i++){
            for (int k=0;k<num2;k++){
                checks[i][k]= (boolean)objectArray[i][k];
            }

        }
        prices= new float[num1];
        discount = (EditText)findViewById(R.id.discount);
        tax = (EditText)findViewById(R.id.tax);
        Button output1 = (Button)findViewById(R.id.output1);
        output1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disc = Float.parseFloat(discount.getText().toString());
                tx = Float.parseFloat(tax.getText().toString());
                for(int i=0;i<num2;i++) {
                    int t = 0;
                    for (int l = 0; l < num1; l++) {
                        if (checks[l][i]) {
                            t++;
                        }
                    }

                    for (int k = 0; k < num1; k++) {
                        if (checks[k][i]) {
                            prices[k] += (amt[i] * qty[i] * (1 - (disc / 100))) / t;
                        }
                    }
                }
                for (int i=0;i<num1;i++)
                {
                    prices[i]+=(tx/3);
                }
                Intent output = new Intent(getApplicationContext(), com.example.billspliter.output.class);
                output.putExtra("prices",prices);
                output.putExtra("names",names);
                output.putExtra("num1",num1);
                startActivity(output);
            }
        });
    }
}
