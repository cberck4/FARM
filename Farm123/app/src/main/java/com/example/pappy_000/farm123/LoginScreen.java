package com.example.pappy_000.farm123;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener{
    private FirebaseAuth mAuth;
    EditText emailField;
    EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.loginButton).setOnClickListener(this);
        findViewById(R.id.createAccount).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.emailBox);
        passwordField = findViewById(R.id.passwordBox);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void createAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           FirebaseUser user = mAuth.getCurrentUser();
                           updateUI(user);
                       } else {
                           Toast.makeText(getApplicationContext(), "Authentication failed.",
                                   Toast.LENGTH_SHORT).show();
                           updateUI(null);
                       }
                   }
                });
    }

    private void signIn(String email, String password) {
        if (!validateForm()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()) {
                           FirebaseUser user = mAuth.getCurrentUser();
                           //updateUI(user);
                           Toast.makeText(getApplicationContext(), "Logging in...",
                                   Toast.LENGTH_SHORT).show();
                           startActivity(new Intent(LoginScreen.this, MainMenu.class));
                       } else {
                           Toast.makeText(getApplicationContext(), "Authentication failed.",
                                   Toast.LENGTH_SHORT).show();
                           updateUI(null);
                       }
                   }
                });
    }

    private boolean validateForm() {
        boolean valid = true;

        emailField = findViewById(R.id.emailBox);
        String email = emailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailField.setError("E-mail required");
            valid = false;
        } else {
            emailField.setError(null);
        }

        passwordField = findViewById(R.id.passwordBox);
        String password = passwordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordField.setError("Password required");
            valid = false;
        } else {
            passwordField.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            signIn(emailField.getText().toString(), passwordField.getText().toString());
        } else {
            return;
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.loginButton) {
            signIn(emailField.getText().toString(), passwordField.getText().toString());
        } else if (i == R.id.createAccount) {
            createAccount(emailField.getText().toString(), passwordField.getText().toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
