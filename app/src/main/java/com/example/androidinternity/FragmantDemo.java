package com.example.androidinternity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class FragmantDemo extends AppCompatActivity implements SendMessageFragment.OnMessageSendListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmant_demo);
        if(findViewById(R.id.fragmentContainer)!=null){
            if (savedInstanceState!=null){
                return;
            }
            SendMessageFragment messageFragment = new SendMessageFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,messageFragment,null).commit();
        }
    }

    @Override
    public void OnMessageSend(String message) {
        ViewMessageFragment viewMessageFragment = new ViewMessageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message",message);
        viewMessageFragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,viewMessageFragment,null);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
