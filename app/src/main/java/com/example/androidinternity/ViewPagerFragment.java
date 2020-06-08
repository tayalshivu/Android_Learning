package com.example.androidinternity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidinternity.database.Note;
import com.example.androidinternity.note_adapter.Adapter;
import com.example.androidinternity.view_model.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {
    TextView textView;
    RecyclerView recyclerView;
    ViewPagerAdapter adapter;

    Adapter noteAdapter = new Adapter();

    public static final int ADD_NOTE_REQUEST = 1;
    private NoteViewModel noteViewModel;

    public ViewPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //adapter=new ViewPagerAdapter(getChildFragmentManager());
        adapter=new ViewPagerAdapter(getChildFragmentManager());
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        FloatingActionButton buttonAddNote = view.findViewById(R.id.button_add_note);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog();
            }
        });

        setHasOptionsMenu(true);

        textView = view.findViewById(R.id.textview);
        textView.setText(getArguments().getString("message"));
        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (textView.getText().equals("Fragment 1")){
//            recyclerView.setAdapter(new RViewMyAdapter(Chats()));

            recyclerView.setAdapter(noteAdapter);
            noteViewModel = new ViewModelProvider(getActivity()).get(NoteViewModel.class);
            noteViewModel.getAllNotes().observe(getActivity(), new Observer<List<Note>>() {
                @Override
                public void onChanged(List<Note> notes) {
                    noteAdapter.setNotes(notes);
                }
            });
            textView.setText("");

            noteAdapter.setOnClickListener(new Adapter.onItemClickListener() {
                @Override
                public void onDeleteItem(final Note note) {
                    final AlertDialog.Builder myDialog = new AlertDialog.Builder(getActivity());
                    myDialog.setMessage("Are you sure you want to delete ?\n"+"This cannot be undone")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    noteViewModel.delete(note);
                                    Toast.makeText(getActivity(), "Successfully Deleted....", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog dialog = myDialog.create();
                    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogSlide;
                    dialog.show();
                }
            });
        }

        if (textView.getText().equals("Fragment 2")){
            recyclerView.setAdapter(new RViewMyAdapter(Status()));
            buttonAddNote.setVisibility(View.INVISIBLE);
            textView.setText("");
        }
        if (textView.getText().equals("Fragment 3")){
            buttonAddNote.setVisibility(View.INVISIBLE);
            recyclerView.setAdapter(new RViewMyAdapter(Calls()));
            textView.setText("");
        }
        return view;
    }

    private void CustomDialog() {

        AlertDialog.Builder alertBuilder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View myview = inflater.inflate(R.layout.custom_dialog_room,null);

        final AlertDialog alertDialog = alertBuilder.create();
        alertDialog.setView(myview);
        alertDialog.getWindow().getAttributes().windowAnimations = R.style.DialogFade;
        alertDialog.show();

        final EditText name = myview.findViewById(R.id.name);
        final EditText email = myview.findViewById(R.id.email);
        final NumberPicker picker = myview.findViewById(R.id.number_picker_priority);
        picker.setMinValue(1);
        picker.setMaxValue(10);

        Button addButton = myview.findViewById(R.id.saveInfo);
        Button cancelButton = myview.findViewById(R.id.cancel);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mName = name.getText().toString().trim();
                String mEmail = email.getText().toString().trim();
                int priority = picker.getValue();

                if (TextUtils.isEmpty(mName) || TextUtils.isEmpty(mEmail)){
                    Toast.makeText(getActivity(), "Empty Credentials...", Toast.LENGTH_SHORT).show();
                    return;
                }
                    Note note = new Note(mName,mEmail,priority);
                    noteViewModel.insert(note);
                    Toast.makeText(getActivity(), "Data Inserted....", Toast.LENGTH_SHORT).show();
                    alertDialog.dismiss();
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.item_note,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item) {

        switch (item.getItemId()){
            case R.id.deleteAll:

                if (noteAdapter.getItemCount()!=0){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Sure to Delete All Data ?\nThis cannot be undone");
                    builder.setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    noteViewModel.deleteAllNotes();
                                    Snackbar.make(getView(),"Deleted All Successfully",Snackbar.LENGTH_LONG).show();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.getWindow().getAttributes().windowAnimations=R.style.DialogSlide;
                    alertDialog.show();
                }
                else{
                    Snackbar.make(getView(),"No Item to Delete",Snackbar.LENGTH_LONG).show();
                }

        }

        return super.onOptionsItemSelected(item);
    }

    private ArrayList<RecyclerViewModel> Calls() {

        ArrayList<RecyclerViewModel> models = new ArrayList<>();
        RecyclerViewModel m = new RecyclerViewModel();
        m.setName("Shivam Tayal");
        m.setDesc("");
        m.setTime("");
        m.setCall(R.drawable.ic_call_missed);
        m.setImage(R.drawable.car1);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Aakar");
        m.setDesc("");
        m.setTime("");
        m.setCall(R.drawable.ic_call_made);
        m.setImage(R.drawable.status2);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Mom");
        m.setDesc("");
        m.setTime("");
        m.setCall(R.drawable.ic_call_made);
        m.setImage(R.drawable.status3);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Daddy");
        m.setDesc("");
        m.setCall(R.drawable.ic_call_missed);
        m.setTime("");
        m.setImage(R.drawable.bike1);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Garv");
        m.setDesc("");
        m.setCall(R.drawable.ic_call_received);
        m.setTime("");
        m.setImage(R.drawable.bike1);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Yash");
        m.setDesc("");
        m.setCall(R.drawable.ic_call_received);
        m.setTime("");
        m.setImage(R.drawable.car5);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Shiva");
        m.setDesc("");
        m.setTime("");
        m.setCall(R.drawable.ic_call_made);
        m.setImage(R.drawable.car2);
        models.add(m);

        return models;

    }

    private ArrayList<RecyclerViewModel> Status() {

        ArrayList<RecyclerViewModel> models = new ArrayList<>();
        RecyclerViewModel m = new RecyclerViewModel();
        m.setName("My Status");
        m.setDesc("15 minutes ago");
        m.setTime("...");
        m.setImage(R.drawable.status1);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Aakar");
        m.setDesc("just now");
        m.setTime("");
        m.setImage(R.drawable.status2);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Mom");
        m.setDesc("1 minute ago");
        m.setTime("");
        m.setImage(R.drawable.status3);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Daddy");
        m.setDesc("Today, 11:00");
        m.setTime("");
        m.setImage(R.drawable.bike1);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Garv");
        m.setDesc("2 minutes ago");
        m.setTime("");
        m.setImage(R.drawable.bike1);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Yash");
        m.setDesc("Today, 13:00");
        m.setTime("");
        m.setImage(R.drawable.car5);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Shiva");
        m.setDesc("just now");
        m.setTime("");
        m.setImage(R.drawable.car2);
        models.add(m);

        return models;

    }


    /*private ArrayList<RecyclerViewModel> Chats() {
        ArrayList<RecyclerViewModel> models = new ArrayList<>();
        RecyclerViewModel m = new RecyclerViewModel();
        m.setName("Shivam");
        m.setDesc("Sleeping");
        m.setTime("10:00");
        m.setImage(R.drawable.car4);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Aakash");
        m.setDesc("Do Not Disturb");
        m.setTime("12:02");
        m.setImage(R.drawable.car1);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Mom");
        m.setDesc("Busy");
        m.setTime("13:10");
        m.setImage(R.drawable.bike3);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Daddy");
        m.setDesc("On Call");
        m.setTime("15:25");
        m.setImage(R.drawable.bike4);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Garv");
        m.setDesc("Using Whatsapp");
        m.setTime("01:09");
        m.setImage(R.drawable.bike5);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Yash");
        m.setDesc("Typing...");
        m.setTime("17:09");
        m.setImage(R.drawable.bike1);
        models.add(m);

        m = new RecyclerViewModel();
        m.setName("Shiva");
        m.setDesc("Typing...");
        m.setTime("20:18");
        m.setImage(R.drawable.car5);
        models.add(m);

        return models;
    }*/
}
