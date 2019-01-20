package com.example.pappy_000.farm123;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChemicalsListView extends AppCompatActivity {
    private ListView lv;
    private ListView lv2;

    EditText chemicalName;
    EditText chemicalDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemicals_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = findViewById(R.id.listy);
        lv2 = findViewById(R.id.listy2);

        final List<String> chemNames = new ArrayList<>();
        chemNames.add("Herbicide");
        chemNames.add("Pesticide");

        final List<String> chemDates = new ArrayList<>();
        chemDates.add("June 8, 2018");
        chemDates.add("July 7, 2018");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                chemNames );

        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                chemDates );
        lv.setAdapter(arrayAdapter);
        lv2.setAdapter(arrayAdapter2);

        chemicalName = findViewById(R.id.chemName);
        chemicalDate = findViewById(R.id.chemDay);

        FloatingActionButton addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (validateForm()) {
                    chemNames.add(chemicalName.getText().toString());
                    chemDates.add(chemicalDate.getText().toString());
                    arrayAdapter.notifyDataSetChanged();
                    arrayAdapter2.notifyDataSetChanged();

                    chemicalName.setText("");
                    chemicalDate.setText("");
               }
           }
        });
    }

    private boolean validateForm() {
        boolean valid = true;

        chemicalName = findViewById(R.id.chemName);
        String name = chemicalName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            chemicalName.setError("Name required");
            valid = false;
        } else {
            chemicalName.setError(null);
        }

        chemicalDate = findViewById(R.id.chemDay);
        String date = chemicalDate.getText().toString();
        if (TextUtils.isEmpty(date)) {
            chemicalDate.setError("Date required");
            valid = false;
        } else {
            chemicalDate.setError(null);
        }

        return valid;
    }

}
