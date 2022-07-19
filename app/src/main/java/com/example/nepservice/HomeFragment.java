package com.example.nepservice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class HomeFragment extends Fragment {
    ArrayList<String> userName;
    ArrayList<String> description;
    ArrayList<String> phoneNumberCardView;
    ArrayList<String> emailAddressCardView;
    ArrayList<String> stateId;
    ArrayList<String> addressCardView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewHome);
        userName = new ArrayList<>();
        description = new ArrayList<>();
        phoneNumberCardView = new ArrayList<>();
        emailAddressCardView = new ArrayList<>();
        stateId = new ArrayList<>();
        addressCardView= new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        SelectAccommodationQuery();
        SelectJobQuery();
        SelectGroceryStoreQuery();
        SelectRestaurantQuery();

        recyclerView.setAdapter(new RecyclerAdapter(userName, description, phoneNumberCardView, emailAddressCardView, stateId,addressCardView ));

        return view;
    }

    public void SelectAccommodationQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        //ResultSet resultSet = null;
        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String selectAccommodationSQl = "SELECT User_Name, Accommodation, State_Id, Phone_Number, Email_Address, Accommodation_Address FROM Accommodation_Table";
                Statement statement = connection.createStatement();
                ResultSet rsA = statement.executeQuery(selectAccommodationSQl);

                if (rsA != null){
                    while (rsA.next()){
                        try {
                            userName.add(rsA.getString("User_Name"));
                            description.add(rsA.getString("Accommodation"));
                            phoneNumberCardView.add(rsA.getString("Phone_Number"));
                            emailAddressCardView.add(rsA.getString("Email_Address"));
                            stateId.add(rsA.getString("State_Id"));
                            addressCardView.add(rsA.getString("Accommodation_Address"));

                        } catch (Exception exception){
                            Log.e("Error", exception.getMessage());
                        }
                    }
                }
            }
        }catch (Exception exception){
            Log.e("Error", exception.getMessage());
        }
    }
    public void SelectJobQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();

        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String selectAccommodationSQl = "SELECT User_Name, Job, State_Id, Phone_Number, Email_Address, Job_Address FROM Job_Table";
                Statement statement = connection.createStatement();
                ResultSet rsA = statement.executeQuery(selectAccommodationSQl);

                if (rsA != null){
                    while (rsA.next()){
                        try {
                            userName.add(rsA.getString("User_Name"));
                            description.add(rsA.getString("Job"));
                            phoneNumberCardView.add(rsA.getString("Phone_Number"));
                            emailAddressCardView.add(rsA.getString("Email_Address"));
                            stateId.add(rsA.getString("State_Id"));
                            addressCardView.add(rsA.getString("Job_Address"));

                        } catch (Exception exception){
                            Log.e("Error", exception.getMessage());
                        }
                    }
                }
            }
        }catch (Exception exception){
            Log.e("Error", exception.getMessage());
        }
    }
    public void SelectGroceryStoreQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();
        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String selectAccommodationSQl = "SELECT User_Name, Grocery_Store, State_Id, Phone_Number, Email_Address, GroceryStore_Address FROM Grocery_Store_Table";
                Statement statement = connection.createStatement();
                ResultSet rsA = statement.executeQuery(selectAccommodationSQl);

                if (rsA != null){
                    while (rsA.next()){
                        try {
                            userName.add(rsA.getString("User_Name"));
                            description.add(rsA.getString("Grocery_Store"));
                            phoneNumberCardView.add(rsA.getString("Phone_Number"));
                            emailAddressCardView.add(rsA.getString("Email_Address"));
                            stateId.add(rsA.getString("State_Id"));
                            addressCardView.add(rsA.getString("GroceryStore_Address"));

                        } catch (Exception exception){
                            Log.e("Error", exception.getMessage());
                        }
                    }
                }
            }
        }catch (Exception exception){
            Log.e("Error", exception.getMessage());
        }
    }
    public void SelectRestaurantQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String selectAccommodationSQl = "SELECT User_Name, Restaurant, State_Id, Phone_Number, Email_Address, Restaurant_Address FROM Restaurant_Table";

                Statement statement = connection.createStatement();
                ResultSet rsA = statement.executeQuery(selectAccommodationSQl);
                if (rsA != null){
                    while (rsA.next()){
                        try {
                            userName.add(rsA.getString("User_Name"));
                            description.add(rsA.getString("Restaurant"));
                            phoneNumberCardView.add(rsA.getString("Phone_Number"));
                            emailAddressCardView.add(rsA.getString("Email_Address"));
                            stateId.add(rsA.getString("State_Id"));
                            addressCardView.add(rsA.getString("Restaurant_Address"));

                        } catch (Exception exception){
                            Log.e("Error", exception.getMessage());
                        }
                    }
                }
            }
        }catch (Exception exception){
            Log.e("Error", exception.getMessage());
        }
    }

}