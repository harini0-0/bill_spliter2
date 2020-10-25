package com.example.billspliter;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Objects;

public class items extends AppCompatActivity {

    public int num1,num2;
    public LinearLayout item;
    public String[] itemnames;
    public  String[] names;
    public float[] amt;
    public int[] qty;
    @SuppressLint({"NewApi", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        num1 = Objects.requireNonNull(getIntent().getExtras()).getInt("num");
        num2 =getIntent().getExtras().getInt("num2");
        names = new String[num1];
        Button next3 = new Button(this);
        names= getIntent().getExtras().getStringArray("names");
        item=(LinearLayout)findViewById(R.id.item);
        itemnames = new String[num2];
        amt = new float[num2];
        qty = new int[num2];
        for(int i=0;i<num2;i++){
            LinearLayout rel = new LinearLayout(this);
            rel.setOrientation(LinearLayout.HORIZONTAL);
            EditText itemnameview = new EditText(this);
            EditText amount = new EditText(this);
            EditText quantity = new EditText(this);
            itemnameview.setHint("Enter Item "+(i+1));
            itemnameview.setTypeface(itemnameview.getTypeface(), Typeface.BOLD_ITALIC);
            itemnameview.setHintTextColor(getResources().getColor(R.color.brown));
            itemnameview.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
            itemnameview.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT));
            itemnameview.setInputType(InputType.TYPE_CLASS_TEXT);
            amount.setInputType(InputType.TYPE_CLASS_NUMBER);
            amount.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
            amount.setHint("Enter ItemPrice");
            amount.setHintTextColor(getResources().getColor(R.color.brown));
            amount.setTypeface(amount.getTypeface(), Typeface.ITALIC);
            amount.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT));
            quantity.setInputType(InputType.TYPE_CLASS_NUMBER);
            quantity.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
            quantity.setHint("Quantity");
            quantity.setHintTextColor(getResources().getColor(R.color.brown));
            quantity.setTypeface(quantity.getTypeface(), Typeface.ITALIC);
            quantity.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT));
            item.addView(itemnameview);
            item.addView(rel);
            rel.addView(amount);
            rel.addView(quantity);
            for (int j=0;j<num1;j++)
            {
                CheckBox check = new CheckBox(this);
                check.setText(names[j]);
                item.addView(check);
            }
            item.invalidate();
        }
        next3.setLayoutParams(new LinearLayout.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT));
        next3.setText("Next");
        next3.setBackgroundResource(R.drawable.brow);
        next3.setLayoutParams(new LinearLayout.LayoutParams(250,95));
        item.addView(next3);
        next3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean[][] checks = new Boolean[num1][num2];
                int j=2;
                for (int i=0;i<num2;i++){
                    EditText itemnameview = (EditText) item.getChildAt(i*(2+num1));
                    LinearLayout rel = (LinearLayout) item.getChildAt((i*(num1+2))+1);
                    EditText amount = (EditText) rel.getChildAt(0);
                    EditText quantity = (EditText) rel.getChildAt(1);
                    itemnames[i]= itemnameview.getText().toString();
                    amt[i]= Float.parseFloat(amount.getText().toString());
                    qty[i]= Integer.parseInt(quantity.getText().toString());
                    for (int k=0;k<num1;k++) {
                        CheckBox check = (CheckBox) item.getChildAt(j);
                        checks[k][i]=check.isChecked();
                        j++;
                    }
                    j+=2;
                }
                Intent processing= new Intent(getApplicationContext(), com.example.billspliter.processing.class);
                processing.putExtra("num1",num1);
                processing.putExtra("num2",num2);
                processing.putExtra("itemnames",itemnames);
                processing.putExtra("names",names);
                processing.putExtra("amt",amt);
                processing.putExtra("qty",qty);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("checks",checks);
                processing.putExtras(mBundle);
                //processing.putExtra("checks",checks);
                startActivity(processing);

            }
        });
    }
}
