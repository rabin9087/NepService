package com.example.nepservice;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements
AccommodationFragment.OnFragmentInteractionListener,
        JobsFragment.OnFragmentInteractionListener,
        GroceryStoreFragment.OnFragmentInteractionListener,
        RestaurantFragment.OnFragmentInteractionListener
       {
           TextView usernameValueMyPost;
           TextView User_FirstName, User_LastName;
           DrawerLayout drawerLayout;
           NavigationView navigationView;
           Toolbar toolbar;
           ActionBarDrawerToggle actionBarDrawerToggle;
           ImageButton  accommodationButton, jobButton, groceryButton, restaurantButton, homeSearchButton;

           @SuppressLint("NonConstantResourceId")
           @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolBar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.menu_open,R.string.menu_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();


        Bundle bundle = getIntent().getExtras();
               assert bundle != null;
               String usernameId1 = bundle.getString("UserName1");
               String value = usernameId1;

            User_FirstName = findViewById(R.id.User_FirstName);
            User_LastName = findViewById(R.id.User_LastName);
               ConnectionHelper c = new ConnectionHelper();
               Connection connection= c.connectionClass();

               try {
                   if (connection != null){

                       //query to Insert data nto MSSQL database
                       String selectAccommodationSQl = "SELECT First_Name, Last_Name FROM Registration_Table WHERE User_Name='"+usernameId1+"'";

                       Statement statement = connection.createStatement();
                       ResultSet rs = statement.executeQuery(selectAccommodationSQl);

                       if (rs != null){
                           while (rs.next()){
                               try {
                                   User_FirstName.setText(rs.getString("First_Name"));
                                   User_LastName.setText(rs.getString("Last_Name"));
                               } catch (Exception exception){
                                   Log.e("Error", exception.getMessage());
                               }
                           }
                       }

                   }
               }catch (Exception exception){
                   Log.e("Error", exception.getMessage());
               }


               navigationView.setNavigationItemSelectedListener(item ->
        {
            switch (item.getItemId()){
                case R.id.nav_home:
                    Log.i("MENU_DRAWER_TAG", "Search item is clicked");
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainerView2, HomeFragment.class, null)
                            .setReorderingAllowed(true).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.nav_myPost:
                    Log.i("MENU_DRAWER_TAG", "MyPost item is clicked");
                            FragmentManager fragmentManager1 = getSupportFragmentManager();
                           FragmentTransaction fragmentTransaction = fragmentManager1.beginTransaction();
                           Bundle bundle1 = new Bundle();
                           bundle1.putString("USER", value);
                           MyPostFragment myPost = new MyPostFragment();
                              myPost.setArguments(bundle1);
                             fragmentTransaction.replace(R.id.fragmentContainerView2, myPost, null).commit();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.nav_settings:
                    Log.i("MENU_DRAWER_TAG", "Settings item is clicked");
                    Toast.makeText(this, "You have selected  Setting menu", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.nav_logOut:
                    Log.i("MENU_DRAWER_TAG", "LogOut item is clicked");
                    Intent mainActivity = new Intent(this, MainActivity.class);
                    startActivity(mainActivity);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    break;
            }
            return true;
        });


        homeSearchButton = findViewById(R.id.homeSearchButton);
        homeSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                SearchDataFragment searchDataFragment = new SearchDataFragment();
                fragmentTransaction.replace(R.id.fragmentContainerView2, searchDataFragment, null).commit();

            }
        });


        accommodationButton = findViewById(R.id.accommodationButton);
        accommodationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle1 = new Bundle();
                bundle1.putString("USER", value);
                AccommodationFragment accommodationFragment = new AccommodationFragment();
                accommodationFragment.setArguments(bundle1);
                fragmentTransaction.replace(R.id.fragmentContainerView2, accommodationFragment, null).commit();

            }
        });

        jobButton = findViewById(R.id.jobButton);
        jobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle1 = new Bundle();
                bundle1.putString("USER", value);
                JobsFragment jobFragment = new JobsFragment();
                jobFragment.setArguments(bundle1);
                fragmentTransaction.replace(R.id.fragmentContainerView2, jobFragment, null).commit();

            }
        });

        groceryButton = findViewById(R.id.groceryButton);
        groceryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle1 = new Bundle();
                bundle1.putString("USER", value);
                GroceryStoreFragment groceryStoreFragment = new GroceryStoreFragment();
                groceryStoreFragment.setArguments(bundle1);
                fragmentTransaction.replace(R.id.fragmentContainerView2, groceryStoreFragment, null).commit();
            }
        });

        restaurantButton = findViewById(R.id.restaurantButton);
        restaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Bundle bundle1 = new Bundle();
                bundle1.putString("USER", value);
                RestaurantFragment restaurantFragment = new RestaurantFragment();
                restaurantFragment.setArguments(bundle1);
                fragmentTransaction.replace(R.id.fragmentContainerView2, restaurantFragment, null).commit();

            }
        });
           }

           @Override

    public void onFragmentInteraction(Uri uri) {
    }

}