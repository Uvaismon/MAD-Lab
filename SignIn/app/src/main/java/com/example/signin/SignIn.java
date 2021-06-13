package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity {

    EditText email, password;
    Button signInButton;
    int counter = 0;
    String registeredEmail, registeredPassword;
    Intent successIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        registeredEmail = getIntent().getStringExtra("emailId");
        registeredPassword = getIntent().getStringExtra("password");
        email = findViewById(R.id.emailId);
        password = findViewById((R.id.password));
        signInButton = findViewById(R.id.signIn);
        successIntent = new Intent(SignIn.this, SignInSuccess.class);
    }

    public void signInHandler(View view) {
        String enteredEmail = email.getText().toString();
        String enteredPassword = password.getText().toString();
        if (counter < 2) {
            counter++;
            if (enteredEmail.equals(registeredEmail) && enteredPassword.equals(registeredPassword))
                startActivity(successIntent);
            else
                Toast.makeText(this, "Wrong credentials", Toast.LENGTH_LONG).show();
        }
        else {
            signInButton.setEnabled(false);
            Toast.makeText(this, "Too many wrong tries.", Toast.LENGTH_SHORT).show();
        }
    }

}