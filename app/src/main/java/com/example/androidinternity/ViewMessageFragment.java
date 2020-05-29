package com.example.androidinternity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewMessageFragment extends Fragment {
    TextView viewMessage;

    public ViewMessageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_view_message, container, false);
        viewMessage=view.findViewById(R.id.textView);
        Bundle bundle = getArguments();
        String message = bundle.getString("message");
        viewMessage.setText(message);
        return view;
    }
}
