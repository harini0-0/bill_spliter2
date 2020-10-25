package com.example.billspliter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class restaurant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

            final EditText personcount = findViewById(R.id.personcount);
            final TextView personcountview = findViewById(R.id.personcountview);
            final TextView itemcountview = findViewById(R.id.itemcountview);
            final EditText itemcount = findViewById(R.id.itemcount);
            personcount.setFilters(new InputFilter[]{new InputFilter.LengthFilter(1)});
            itemcount.setFilters(new InputFilter[]{new InputFilter.LengthFilter(2)});
            Toast.makeText(this, "Input limit set to a single digit", Toast.LENGTH_SHORT).show();
            final Button next1 = findViewById(R.id.next1);
            next1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int num1= Integer.parseInt(personcount.getText().toString());
                    int num2 = Integer.parseInt(itemcount.getText().toString());

                    Intent personname = new Intent(getApplicationContext(), personname.class);

                        personname.putExtra("num", num1);
                        personname.putExtra("num2",num2);
                        startActivity(personname);

                }
            });








    }

    public static boolean isEmpty(String str) {

        if (str == null)
            return true;
        else if (str.toString().trim().length() == 0)
            return true;

        return false;
    }

}
