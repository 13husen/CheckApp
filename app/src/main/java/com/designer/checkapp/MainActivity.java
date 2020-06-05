package com.designer.checkapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.designer.checkapp.View.LoginActivity;
import com.designer.checkapp.View.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    Button login_view,register_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login_view = findViewById(R.id.loginview);
        register_view = findViewById(R.id.registerview);

        login_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(pindah);
            }
        });


        register_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(pindah);
            }
        });



    }


}
