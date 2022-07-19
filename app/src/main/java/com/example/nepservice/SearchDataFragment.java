package com.example.nepservice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SearchDataFragment extends Fragment {

    EditText editSearch;

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
        View view = inflater.inflate(R.layout.fragment_search_data, container, false);

        editSearch = view.findViewById(R.id.data);

        RecyclerView recyclerView = view.findViewById(R.id.searchRecycleView);

        ImageButton SearchImageButton = view.findViewById(R.id.searchData);

        SearchImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = new ArrayList<>();
                description = new ArrayList<>();
                phoneNumberCardView = new ArrayList<>();
                emailAddressCardView = new ArrayList<>();
                stateId = new ArrayList<>();
                addressCardView= new ArrayList<>();

                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

                AccommodationQuery();
                JobQuery();
                GroceryStoreQuery();
                RestaurantQuery();
                recyclerView.setAdapter(new RecyclerAdapter(userName, description, phoneNumberCardView, emailAddressCardView, stateId, addressCardView));

            }
        });


        return  view;
    }

    public void AccommodationQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        //ResultSet resultSet = null;
        try {
            if (connection != null){
                //query to Insert data nto MSSQL database
                String searchFromDatabase1 = editSearch.getText().toString();
                String selectAccommodationSQl = "SELECT User_Name, Accommodation, State_Id, Phone_Number, Email_Address, " +
                        "Accommodation_Address FROM Accommodation_Table WHERE Accommodation LIKE '%"+searchFromDatabase1+"%';";
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
    public void JobQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();

        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String searchFromDatabase1 = editSearch.getText().toString();
                String selectAccommodationSQl = "SELECT User_Name, Job, State_Id, Phone_Number, Email_Address, Job_Address " +
                        "FROM Job_Table WHERE Job LIKE '%"+searchFromDatabase1+"%';";
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
    public void GroceryStoreQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();
        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String searchFromDatabase1 = editSearch.getText().toString();
                String selectAccommodationSQl = "SELECT User_Name, Grocery_Store, State_Id, Phone_Number, Email_Address, GroceryStore_Address " +
                        "FROM Grocery_Store_Table WHERE Grocery_Store LIKE '%"+searchFromDatabase1+"%';";
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
    public void RestaurantQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        try {
            if (connection != null){

                //query to Insert data nto MSSQL database


                String searchFromDatabase1 = editSearch.getText().toString();
                String selectAccommodationSQl = "SELECT User_Name, Restaurant, State_Id, Phone_Number, Email_Address," +
                        " Restaurant_Address FROM Restaurant_Table WHERE Restaurant LIKE '%"+searchFromDatabase1+"%';";

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