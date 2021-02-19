package com.example.appllamadas2;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;


import android.content.Intent;
import android.net.Uri;

import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mDialButton = (Button) findViewById(R.id.btn_dial);



        mDialButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                    // Cada vez que realice una operaciÃ³n que requiera un permiso peligroso,
                    // debe verificar si el usuario ha otorgado ese permiso.
                    // Si no es asÃ­, debe solicitar que se le conceda.
                          makePhoneCall(view);
            }
        });
    }
    public void makePhoneCall(View view) {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            callPhone();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                }
            }
        }
    }

    private void callPhone() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            final EditText mPhoneNoEt = (EditText) findViewById(R.id.et_phone_no);
            String phoneNo = mPhoneNoEt.getText().toString();
            if (!TextUtils.isEmpty(phoneNo)) {
                String dial = "tel:" + phoneNo;
                // Cada vez que realice una operaciÃ³n que requiera un permiso peligroso,
                // debe verificar si el usuario ha otorgado ese permiso.
                // Si no es asÃ­, debe solicitar que se le conceda.
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }else {
                Toast.makeText(MainActivity.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
            }

        }
    }

}