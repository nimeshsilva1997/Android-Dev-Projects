package com.example.myapplication;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private Fragment[] fragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragments = new Fragment[]{ new MenuFragment(), new MapFragment(), new OrderFragment()};
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fl, fragments[0])
                .add(R.id.fl,fragments[1])
                .add(R.id.fl,fragments[2])
                .hide(fragments[1])
                .hide(fragments[2])
                .show(fragments[0])
                .commit();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragments[0]);
        transaction.hide(fragments[1]);
        if (menuItem.getItemId()==R.id.menu){
            transaction.show(fragments[0]);
            transaction.commit();
            return true;
        }else if (menuItem.getItemId()==R.id.map){
            transaction.show(fragments[1]);
            transaction.commit();
            return true;
        }else if (menuItem.getItemId()==R.id.order){
            transaction.show(fragments[2]);
            transaction.commit();
            return true;
        }



        return false;
    }
}
