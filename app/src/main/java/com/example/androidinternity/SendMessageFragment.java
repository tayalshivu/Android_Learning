package com.example.androidinternity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class SendMessageFragment extends Fragment {
    Button send;
    EditText text;
    OnMessageSendListener messageSendListener;

    public interface OnMessageSendListener{
        public void OnMessageSend(String message);
    }

    public SendMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_send_message, container, false);
        send=view.findViewById(R.id.button);
        text=view.findViewById(R.id.editText);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message=text.getText().toString();
                messageSendListener.OnMessageSend(message);
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity)context;
        try {
            messageSendListener=(OnMessageSendListener)activity;
        } catch (ClassCastException e) {
            throw  new ClassCastException(activity.toString()+"must implement MessageSend listener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        text.setText("");
    }
}
