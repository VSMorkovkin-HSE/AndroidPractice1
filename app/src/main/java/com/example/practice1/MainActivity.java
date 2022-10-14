package com.example.practice1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int SHOW_OTHER_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button toEnterAddress = findViewById(R.id.to_enter_address);
        toEnterAddress.setOnClickListener(this::onClick);

        Button toBrowser = findViewById(R.id.to_browser);
        toBrowser.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.to_enter_address:
                Intent intent = new Intent(this, MainActivity2.class);
                startActivityForResult(intent, SHOW_OTHER_ACTIVITY);
                break;
            case R.id.to_browser:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW);

                TextView receivedAddressTextView = findViewById(R.id.received_address);
                String address = receivedAddressTextView.getText().toString();
                browserIntent.setData(Uri.parse(fixAddress(address)));

                browserIntent.addCategory(Intent.CATEGORY_BROWSABLE);

                if (browserIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(browserIntent);
                }

        }
    }

    private String fixAddress(String address) {
        StringBuilder finalAddress = new StringBuilder();
        String protocol = "https://";
        String web = "www.";

        if (!address.startsWith(protocol)) {
            finalAddress.append(protocol);
            if (!address.startsWith(web)) {
                finalAddress.append(web);
            }
        }
        finalAddress.append(address);

        return finalAddress.toString();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SHOW_OTHER_ACTIVITY && resultCode == RESULT_OK) {
            String receivedAddress = data.getStringExtra(MainActivity2.ENTER_ADDRESS);
            TextView receivedAddressTextView = findViewById(R.id.received_address);
            receivedAddressTextView.setText(receivedAddress);
        }
    }


}