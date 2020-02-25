package com.example.myapplication.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView=findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState==null){
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MoviesFragment("now_playing")).commit();
        navigationView.setCheckedItem(R.id.Now_Playing_item);}
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.Now_Playing_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new MoviesFragment("now_playing")).commit();
                break;
            case R.id.upcoming_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MoviesFragment("upcoming")).commit();
                break;
            case R.id.Top_Rated_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MoviesFragment("top_rated")).commit();
                break;
            case R.id.Populor_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MoviesFragment("popular")).commit();
                break;
            case R.id.favourite_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MoviesFragment("Favourite")).commit();
                break;




        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
