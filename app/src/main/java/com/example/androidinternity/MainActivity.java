package com.example.androidinternity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements Dailog.DailogListener {
    Button openDailog;
    TextView showText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openDailog=(Button)findViewById(R.id.opendialod);
        showText=(TextView)findViewById(R.id.showtext);
    }

    public void OpenDialog(View view) {
        Dailog dialog = new Dailog();
        dialog.show(getSupportFragmentManager(),"dialog");
    }

    @Override
    public void SendText(String text) {
        showText.setText(text);
    }

    public void FragmentDemo(View view) {
        startActivity(new Intent(MainActivity.this,FragmantDemo.class));
    }
}
