package com.example.mylogs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;




import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainPage extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    DrawerLayout drawerLayout;
    NavigationView navigationView;

    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setSupportActionBar(new androidx.appcompat.widget.Toolbar(this));
        setContentView(R.layout.activity_main_page);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if(user == null){
            Intent login_page = new Intent(MainPage.this, MainActivity.class);
            startActivity(login_page);
            finish();
        }


        drawerLayout = findViewById(R.id.nav_layout);
        navigationView = findViewById(R.id.navigationview);



         actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.menu_open,R.string.menu_close);
         drawerLayout.addDrawerListener(actionBarDrawerToggle);
         actionBarDrawerToggle.syncState();

         if(getSupportActionBar() != null){
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         }
         navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
           // TextView welcome_text = findViewById(R.id.homepageWelcome);

        if(item.getItemId() == R.id.nav_logout){
        FirebaseAuth.getInstance().signOut();
        Intent login_page = new Intent(MainPage.this, MainActivity.class);
        startActivity(login_page);
        finish();


        } else if (item.getItemId() == R.id.nav_Home) {
            final FragmentManager fragmentManager = getSupportFragmentManager();
            //welcome_text.setText(" ");


            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, HomeDashboard.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();

        } else if (item.getItemId() == R.id.nav_calender) {
            final FragmentManager fragmentManager = getSupportFragmentManager();
            //welcome_text.setText(" ");


            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, Calender.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();

        } else if (item.getItemId() == R.id.nav_calorie) {
            final FragmentManager fragmentManager = getSupportFragmentManager();
            //welcome_text.setText(" ");

            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, CaloriesProgress.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();


        } else if(item.getItemId() == R.id.nav_calculate){
            final FragmentManager fragmentManager = getSupportFragmentManager();
            //welcome_text.setText(" ");


            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, BMRpage.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();
        } else if (item.getItemId() == R.id.nav_nutrition) {

            final FragmentManager fragmentManager = getSupportFragmentManager();
            //welcome_text.setText(" ");


            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, Nutrition.class, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("name") // Name can be null
                    .commit();

        }


            return true;
        }
        });

        // trackcalorie_Navigation();
         //calender_Navigation();
         //calculate_Navigation();
         //logout_Navigation();
         //nutrition_Navigation();


    }





   
}