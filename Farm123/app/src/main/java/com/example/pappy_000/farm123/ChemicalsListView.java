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

public class ChemicalsListView extends AppCompatActivity {
    private ListView lv;
    private ListView lv2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemicals_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lv = (ListView) findViewById(R.id.listy);
        lv2 = (ListView) findViewById(R.id.listy2);

        List<String> your_array_list = new ArrayList<String>();
        your_array_list.add("Herbicide");
        your_array_list.add("Pesticide");

        List<String> your_array_list2 = new ArrayList<String>();
        your_array_list2.add("June 8, 2018");
        your_array_list2.add("July 7, 2018");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list );

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                your_array_list2 );
        lv.setAdapter(arrayAdapter);
        lv2.setAdapter(arrayAdapter2);


    }

}
