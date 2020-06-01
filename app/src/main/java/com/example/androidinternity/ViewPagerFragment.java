package com.example.androidinternity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ViewPagerFragment extends Fragment {
    TextView textView;
    RecyclerView recyclerView;
    ViewPagerAdapter adapter;

    public ViewPagerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //adapter=new ViewPagerAdapter(getChildFragmentManager());
        adapter=new ViewPagerAdapter(getFragmentManager());
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);
        textView = view.findViewById(R.id.textview);
        textView.setText(getArguments().getString("message"));
        recyclerView=view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (textView.getText().equals("Fragment 1")){
            recyclerView.setAdapter(new RViewMyAdapter(Chats()));
            textView.setText("");
        }
        if (textView.getText().equals("Fragment 2")){
            recyclerView.setAdapter(new RViewMyAdapter(Status()));
            textView.setText("");
        }
        if (textView.getText().equals("Fragment 3")){
            recyclerView.setAdapter(new RViewMyAdapter(Calls()));
            textView.setText("");
        }
        return view;
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


    private ArrayList<RecyclerViewModel> Chats() {
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
    }
}
