package com.example.androidinternity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
//import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ViewPagerWithTabLayout extends AppCompatActivity {
    Toolbar toolbar;
    ViewPagerAdapter adapter;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_with_tab_layout);
        drawerLayout=(DrawerLayout)findViewById(R.id.navigation_drawer);
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        toolbar=(Toolbar)findViewById(R.id.toolbar1);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());
        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        final BaseFragment baseFragment = new BaseFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container,baseFragment,null).commit();
        navigationView.setCheckedItem(R.id.home);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        navigationView.setCheckedItem(R.id.home);
                        if (getSupportFragmentManager().findFragmentById(R.id.fragment_container) instanceof BaseFragment){
                            Toast.makeText(ViewPagerWithTabLayout.this, "Already on Home Page..", Toast.LENGTH_SHORT).show();
                        }
                        else{
//                            FragmentManager fragmentManager = getSupportFragmentManager();
//                            fragmentManager.popBackStack();
                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,baseFragment,null).commit();
                            navigationView.setCheckedItem(R.id.home);
                        }
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.help:
                        navigationView.setCheckedItem(R.id.help);
                        drawerLayout.closeDrawers();
                        HelpFragment helpFragment = new HelpFragment();
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,helpFragment,null);
                        transaction.commit();
                        return true;

                    case R.id.settings:
                        navigationView.setCheckedItem(R.id.settings);
                        drawerLayout.closeDrawers();
                        SettingsFragment settingsFragment = new SettingsFragment();
                        FragmentTransaction transaction1 = getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,settingsFragment,null);
                        transaction1.commit();
                        return true;

                    case R.id.exit:
                        finish();
                        drawerLayout.closeDrawers();
                        return true;

                    case R.id.logout:
                        startActivity(new Intent(ViewPagerWithTabLayout.this,LoginSharedPref.class));
                        SharedPreferences preferences = getSharedPreferences("userinfo",MODE_PRIVATE);
                        preferences.edit().clear().commit();
                        drawerLayout.closeDrawers();
                        finish();
                        return true;
                }

                return false;
            }
        });
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(ViewPagerWithTabLayout.this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();

    }

}
