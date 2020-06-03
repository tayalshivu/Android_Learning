package com.example.androidinternity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
//import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ViewPagerWithTabLayout extends AppCompatActivity {
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    TabLayout tabLayout;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_with_tab_layout);
        drawerLayout=(DrawerLayout)findViewById(R.id.navigation_drawer);
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        pager=(ViewPager)findViewById(R.id.viewPager);
        tabLayout=(TabLayout)findViewById(R.id.tablayout);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
        setSupportActionBar(toolbar);
        ViewPagerFragment fragment;
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        item.setChecked(true);
                        Toast.makeText(ViewPagerWithTabLayout.this, "You are already on home page", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.exit:
                        item.setChecked(true);
                        finish();
                        drawerLayout.closeDrawers();
                    case R.id.logout:
                        item.setChecked(true);
                        startActivity(new Intent(ViewPagerWithTabLayout.this,LoginSharedPref.class));
                        drawerLayout.closeDrawers();
                        finish();
                }

                return false;
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(ViewPagerWithTabLayout.this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

    }

//    private ArrayList<RecyclerViewModel> getList(){
//        ArrayList<RecyclerViewModel> models = new ArrayList<>();
//        RecyclerViewModel m = new RecyclerViewModel();
//        m.setName("Shivam Tayal");
//        m.setDesc("Typing...");
//        m.setImage(R.drawable.car4);
//        models.add(m);
//
//        return models;
//    }

}
