package com.example.pappy_000.farm123;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FieldDetails extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String fieldName = FieldMenu.selectedField;
    private ListView left;
    private ListView right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        left = findViewById(R.id.leftlist);
        right = findViewById(R.id.rightlist);

        List<String> leftArray = new ArrayList<>();
        leftArray.add("Acres");
        leftArray.add("Current Crop");
        leftArray.add("Last Planted");
        leftArray.add("Location");

        final List<String> rightArray = new ArrayList<>();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                leftArray);
        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                rightArray);

        left.setAdapter(arrayAdapter);
        right.setAdapter(arrayAdapter2);

        db.collection("fields")
                .whereEqualTo("name", fieldName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                rightArray.add(document.getDouble("acres").toString());
                                rightArray.add(document.getString("current plant"));
                                rightArray.add(document.getString("last planted"));
                                rightArray.add(document.getString("location"));
                                arrayAdapter2.notifyDataSetChanged();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Data fetch failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
