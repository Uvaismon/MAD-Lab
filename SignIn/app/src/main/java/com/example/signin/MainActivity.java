package com.example.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaTimestamp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText emailId, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailId = findViewById(R.id.emailId);
        password = findViewById(R.id.password);
    }

    public void signUpHandler(View view) {
        String enteredEmail = emailId.getText().toString();
        String enteredPassword = password.getText().toString();

        if(!isValidPassword(enteredPassword)) {
            Toast.makeText(this, "Password isn't strong enough.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(MainActivity.this, SignIn.class);
        intent.putExtra("emailId", enteredEmail);
        intent.putExtra("password", enteredPassword);
        startActivity(intent);
    }

    public boolean isValidPassword(String password) {
        Pattern lowercase = Pattern.compile("^.*[a-z].*$");
        Pattern uppercase = Pattern.compile("^.*[A-Z].*$");
        Pattern number = Pattern.compile("^.*[0-9].*$");
        Pattern specialCharacter = Pattern.compile("^.*[^a-zA-Z0-9].*$");

        if (!lowercase.matcher(password).matches())
            return false;
        if (!uppercase.matcher(password).matches())
            return false;
        if (!number.matcher(password).matches())
            return false;

        return specialCharacter.matcher(password).matches();
    }
}