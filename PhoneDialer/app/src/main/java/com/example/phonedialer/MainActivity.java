package com.example.phonedialer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView phoneNumberView;
    Button clearButton, callButton, saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clearButton = findViewById(R.id.bClear);
        callButton = findViewById(R.id.bCall);
        saveButton = findViewById(R.id.bSave);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phoneNumberView.setText("");
            }
        });

        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberView.getText().toString();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                startActivity(intent);
            }
        });


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = phoneNumberView.getText().toString();
                Intent intent = new Intent(Intent.ACTION_INSERT);
                intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneNumber);
                startActivity(intent);
            }
        });
    }

    public void inputNumber(View view) {
        Button btn = (Button)view;
        String digit = btn.getText().toString();
        String phoneNumber = phoneNumberView.getText().toString();
        phoneNumberView.setText(phoneNumber+digit);
    }
}