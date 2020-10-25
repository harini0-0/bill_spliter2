package com.example.billspliter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ButtonBarLayout;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Objects;

public class output extends AppCompatActivity {
    public float[] prices;
    public String[] names;
    public int num1;
    public LinearLayout layer;
    @SuppressLint({"NewApi", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        prices= getIntent().getFloatArrayExtra("prices");
        names = getIntent().getStringArrayExtra("names");
        num1= Objects.requireNonNull(getIntent().getExtras()).getInt("num1");
        layer= (LinearLayout) findViewById(R.id.otp);
        for(int i=0;i<names.length;i++) {
            TextView tv = new TextView(this);
            tv.setText(names[i]+ " has to pay " + prices[i] + "Rs");
            tv.setPadding(20,5,60,5);
            tv.setTypeface(tv.getTypeface(), Typeface.BOLD_ITALIC);
            tv.setTextColor(getResources().getColor(R.color.black));
            tv.setTextSize(20);
            layer.addView(tv);
        }
        ImageView vw = new ImageView(this);
        vw.setImageResource(R.drawable.thank);
        vw.setPadding(50,50,0,30);
        layer.addView(vw);
        vw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainact = new Intent(getApplicationContext(), com.example.billspliter.MainActivity.class);
                startActivity(mainact);
            }
        });
    }
}
