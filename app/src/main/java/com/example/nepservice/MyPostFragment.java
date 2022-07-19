package com.example.nepservice;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class MyPostFragment extends Fragment {

    TextView usernameValueMyPost;

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
        View view = inflater.inflate(R.layout.fragment_my_post, container, false);

        usernameValueMyPost = view.findViewById(R.id.usernameValueMyPost);
        assert this.getArguments() != null;
        usernameValueMyPost.setText(this.getArguments().getString("USER"));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMyPost);

        userName = new ArrayList<>();
        description = new ArrayList<>();
        phoneNumberCardView = new ArrayList<>();
        emailAddressCardView = new ArrayList<>();
        stateId = new ArrayList<>();
        addressCardView = new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        SelectAccommodationMyPostQuery();
        SelectJobMyPostQuery();
        SelectGroceryStoreMyPostQuery();
        SelectRestaurantMyPostQuery();
        recyclerView.setAdapter(new RecyclerAdapter(userName, description, phoneNumberCardView, emailAddressCardView, stateId, addressCardView));
        return view;
    }

    public void SelectAccommodationMyPostQuery() {
        ConnectionHelper c = new ConnectionHelper();
        Connection connection = c.connectionClass();

        try {
            if (connection != null) {
                //query to Insert data nto MSSQL database
                String usernameValueMyPost1 = usernameValueMyPost.getText().toString();
                String selectAccommodationSQl = "SELECT User_Name, Accommodation, State_Id, Phone_Number, Email_Address, Accommodation_Address " +
                        "FROM Accommodation_Table WHERE User_Name = '" + usernameValueMyPost1 + "'";

                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectAccommodationSQl);

                if (rs != null) {
                    while (rs.next()) {
                        try {
                            userName.add(rs.getString("User_Name"));
                            description.add(rs.getString("Accommodation"));
                            phoneNumberCardView.add(rs.getString("Phone_Number"));
                            emailAddressCardView.add(rs.getString("Email_Address"));
                            stateId.add(rs.getString("State_Id"));
                            addressCardView.add(rs.getString("Accommodation_Address"));

                        } catch (Exception exception) {
                            Log.e("Error", exception.getMessage());
                        }
                    }
                }
            }
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }

    }
    public void SelectJobMyPostQuery() {
        ConnectionHelper c = new ConnectionHelper();
        Connection connection = c.connectionClass();

        try {
            if (connection != null) {
                //query to Insert data nto MSSQL database
                String usernameValueMyPost1 = usernameValueMyPost.getText().toString();
                String selectAccommodationSQl = "SELECT User_Name, Job, State_Id, Phone_Number, Email_Address, Job_Address " +
                        "FROM Job_Table WHERE User_Name = '" + usernameValueMyPost1 + "'";

                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectAccommodationSQl);

                if (rs != null) {
                    while (rs.next()) {
                        try {
                            userName.add(rs.getString("User_Name"));
                            description.add(rs.getString("Job"));
                            phoneNumberCardView.add(rs.getString("Phone_Number"));
                            emailAddressCardView.add(rs.getString("Email_Address"));
                            stateId.add(rs.getString("State_Id"));
                            addressCardView.add(rs.getString("Job_Address"));

                        } catch (Exception exception) {
                            Log.e("Error", exception.getMessage());
                        }
                    }
                }
            }
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }

    }
    public void SelectGroceryStoreMyPostQuery() {
        ConnectionHelper c = new ConnectionHelper();
        Connection connection = c.connectionClass();

        try {
            if (connection != null) {
                //query to Insert data nto MSSQL database
                String usernameValueMyPost1 = usernameValueMyPost.getText().toString();
                String selectAccommodationSQl = "SELECT User_Name, Grocery_Store, State_Id, Phone_Number, Email_Address, GroceryStore_Address " +
                        "FROM Grocery_Store_Table WHERE User_Name = '" + usernameValueMyPost1 + "'";

                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectAccommodationSQl);

                if (rs != null) {
                    while (rs.next()) {
                        try {
                            userName.add(rs.getString("User_Name"));
                            description.add(rs.getString("Grocery_Store"));
                            phoneNumberCardView.add(rs.getString("Phone_Number"));
                            emailAddressCardView.add(rs.getString("Email_Address"));
                            stateId.add(rs.getString("State_Id"));
                            addressCardView.add(rs.getString("GroceryStore_Address"));

                        } catch (Exception exception) {
                            Log.e("Error", exception.getMessage());
                        }
                    }
                }
            }
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }

    }
    public void SelectRestaurantMyPostQuery() {
        ConnectionHelper c = new ConnectionHelper();
        Connection connection = c.connectionClass();

        try {
            if (connection != null) {
                //query to Insert data nto MSSQL database
                String usernameValueMyPost1 = usernameValueMyPost.getText().toString();
                String selectAccommodationSQl = "SELECT User_Name, Restaurant, State_Id, Phone_Number, Email_Address, Restaurant_Address " +
                        "FROM Restaurant_Table WHERE User_Name = '" + usernameValueMyPost1 + "'";

                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectAccommodationSQl);

                if (rs != null) {
                    while (rs.next()) {
                        try {
                            userName.add(rs.getString("User_Name"));
                            description.add(rs.getString("Restaurant"));
                            phoneNumberCardView.add(rs.getString("Phone_Number"));
                            emailAddressCardView.add(rs.getString("Email_Address"));
                            stateId.add(rs.getString("State_Id"));
                            addressCardView.add(rs.getString("Restaurant_Address"));

                        } catch (Exception exception) {
                            Log.e("Error", exception.getMessage());
                        }
                    }
                }
            }
        } catch (Exception exception) {
            Log.e("Error", exception.getMessage());
        }

    }
}