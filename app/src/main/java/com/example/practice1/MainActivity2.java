package com.example.practice1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    public static String ENTER_ADDRESS = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent = getIntent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });

        Button send = findViewById(R.id.send_address);
        send.setOnClickListener(v -> {
            EditText inputWebsiteName = findViewById(R.id.enter_address);
            String websiteName = inputWebsiteName.getText().toString();

            Intent intent = getIntent();
            intent.putExtra(ENTER_ADDRESS, websiteName);
            setResult(RESULT_OK, intent);
            finish();
        });
    }




}