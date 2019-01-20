package com.example.pappy_000.farm123;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.pappy_000.farm123.BinMenu;

public class SingleBinScreen extends AppCompatActivity {
    String binName = BinMenu.selectedBin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_bin_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView binTitle = findViewById(R.id.textView5);
        binTitle.setText(binName);

        ImageButton fieldMap = findViewById(R.id.imageButton4);
        fieldMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SingleBinScreen.this, FieldMaps.class));
            }
        });

    }

}
