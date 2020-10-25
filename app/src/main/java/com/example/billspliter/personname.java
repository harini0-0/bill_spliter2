package com.example.billspliter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONObject;

public class personname extends AppCompatActivity {
    public int num1,num2;
    public String names[];
    public LinearLayout name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personname);

            num1=getIntent().getExtras().getInt("num");
            num2=getIntent().getExtras().getInt("num2");
            name = (LinearLayout) findViewById(R.id.name);
            names = new String[num1];
            Button button= new Button(this);

        for(int i=0;i<num1;i++) {
                EditText personnameview = new EditText(this);
                personnameview.setHint("Enter Name "+(i+1));
                personnameview.setId(i);
                //personnameview.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT));
                personnameview.setFilters(new InputFilter[]{new InputFilter.LengthFilter(15)});
                personnameview.setPadding(30,45,60,15);
                personnameview.setTypeface(personnameview.getTypeface(), Typeface.BOLD_ITALIC);
                personnameview.setHintTextColor(getResources().getColor(R.color.brown));
                name.addView(personnameview);
                name.invalidate();
        }
        button.setText(R.string.next1);
        button.setLayoutParams(new LinearLayout.LayoutParams(250,95));
        button.setBackgroundResource(R.drawable.brow);
        name.addView(button);
        name.invalidate();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0;i<num1;i++){
                    EditText personnameview = (EditText) name.getChildAt(i);
                    names[i]= personnameview.getText().toString();

                }
                Intent items = new Intent(getApplicationContext(), com.example.billspliter.items.class);
                items.putExtra("num", num1);
                items.putExtra("num2",num2);
                items.putExtra("names",names);
                startActivity(items);
            }
        });





    }
}
