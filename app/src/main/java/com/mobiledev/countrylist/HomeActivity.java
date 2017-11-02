package com.mobiledev.countrylist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mobiledev.countrylist.ui.FragmentHome;

/**
 * Created by Manu on 11/1/2017.
 */

public class HomeActivity extends AppCompatActivity {

    private TextView headerTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        headerTitle = findViewById(R.id.headerTitle);
        navigate(new FragmentHome());
    }

    public void navigate(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.commit();
    }

    public void updateTitle(String title){
        headerTitle.setText(title);
    }

}
