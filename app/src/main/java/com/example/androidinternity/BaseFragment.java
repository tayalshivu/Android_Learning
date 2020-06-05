package com.example.androidinternity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment {

    ViewPager pager;
    TabLayout tabLayout;
    ViewPagerAdapter pagerAdapter;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        pager=view.findViewById(R.id.viewPager);
        tabLayout=view.findViewById(R.id.tablayout);
        pagerAdapter=new ViewPagerAdapter(getChildFragmentManager());
        pager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(pager);

        return view;
    }
}
