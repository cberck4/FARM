package com.example.pappy_000.farm123;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.pappy_000.farm123.FieldMenu;

public class SingleFieldScreen extends AppCompatActivity {
    String fieldName = FieldMenu.selectedField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_field_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView fieldTitle = findViewById(R.id.textView);
        fieldTitle.setText(fieldName);

        Button chems = findViewById(R.id.button2);
        chems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleFieldScreen.this, ChemicalsListView.class));
            }

        });

        Button details = findViewById(R.id.button3);
        details.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               startActivity(new Intent(SingleFieldScreen.this, FieldDetails.class));
           }
        });
    }

}
