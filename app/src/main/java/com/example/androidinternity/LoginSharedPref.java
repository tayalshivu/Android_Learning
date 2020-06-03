package com.example.androidinternity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginSharedPref extends AppCompatActivity {
    EditText user,pass;
    Button reg,log;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_shared_pref);
        user=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        reg=findViewById(R.id.register);
        log=findViewById(R.id.login);
        preferences=getSharedPreferences("userinfo",MODE_PRIVATE);

        if (preferences.contains("username") && preferences.contains("password")){
            startActivity(new Intent(LoginSharedPref.this,ViewPagerWithTabLayout.class));
            finish();
        }

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();
                String regusername = preferences.getString("username","");
                String regpassword = preferences.getString("password","");
                if (username.equals(regusername) && password.equals(regpassword)){
                    startActivity(new Intent(LoginSharedPref.this,ViewPagerWithTabLayout.class));
                    Toast.makeText(LoginSharedPref.this, "Login Successfully....", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginSharedPref.this,RegisterSharedPref.class));
            }
        });
    }
}
