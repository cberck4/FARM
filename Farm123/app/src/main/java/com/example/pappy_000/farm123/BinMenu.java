package com.example.pappy_000.farm123;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BinMenu extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<String> spinnerArray = new ArrayList<>();

    public static String selectedBin; // Selected bin from Firestore

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bin_menu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner sItems = findViewById(R.id.spinner);
        sItems.setAdapter(adapter);

        fetchValues(adapter);

        sItems.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sItems.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        Button next = findViewById(R.id.button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedBin = sItems.getSelectedItem().toString();
                startActivity(new Intent(BinMenu.this, SingleBinScreen.class));
            }

        });
    }

    private void fetchValues(final ArrayAdapter<String> adapter) {
        Toast.makeText(getApplicationContext(), "Fetching Data",
                Toast.LENGTH_SHORT).show();
        db.collection("bins")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                spinnerArray.add(document.getString("name"));
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Data fetch failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
