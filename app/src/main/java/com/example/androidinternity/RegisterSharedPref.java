package com.example.androidinternity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterSharedPref extends AppCompatActivity {
    EditText user,pass;
    Button register;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_shared_pref);
        user=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        register=findViewById(R.id.register);
        preferences=getSharedPreferences("userinfo",MODE_PRIVATE);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.getText().toString();
                String password = pass.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("username",username);
                editor.putString("password",password);
                editor.commit();
                Toast.makeText(RegisterSharedPref.this, "Registered.....", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterSharedPref.this,LoginSharedPref.class));
                finish();
            }
        });
    }
}
