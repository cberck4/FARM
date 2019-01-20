package com.example.pappy_000.farm123;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FieldDetails extends AppCompatActivity {
    private ListView left;
    private ListView right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        left = (ListView) findViewById(R.id.leftlist);
        right = (ListView) findViewById(R.id.rightlist);

        List<String> your_array_list321 = new ArrayList<String>();
        your_array_list321.add("Crop:");
        your_array_list321.add("Previous Crop:");
        your_array_list321.add("Size:");

        List<String> your_array_list3211 = new ArrayList<String>();
        your_array_list3211.add("");
        your_array_list3211.add("Corn");
        your_array_list3211.add("Soybeans");
        your_array_list3211.add("124 Acres");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list321 );
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list3211 );

        left.setAdapter(arrayAdapter);
        right.setAdapter(arrayAdapter2);
    }

}
