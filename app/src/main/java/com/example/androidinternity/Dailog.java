package com.example.androidinternity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

public class Dailog extends DialogFragment {
    EditText text;
    DailogListener listener;
    AlertDialog.Builder builder;
    AlertDialog alertDialog;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dailog,null);
        text=view.findViewById(R.id.text);
        builder.setView(view)
                .setTitle("Dailog")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Nothing
                    }
                })
                .setPositiveButton("show", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String sendtext=text.getText().toString();
                        listener.SendText(sendtext);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener=(DailogListener)context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString()+"must implement Dailog listener");
        }
    }

    public interface DailogListener{
        void SendText(String text);
    }
}
