package com.example.nepservice;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostNewRestaurantFragment extends Fragment {

    ImageButton backToRestaurantFragment;
    Button PostNewRestaurantButton;
    TextView usernameRestaurant;
    EditText descriptionRestaurant, Phone_NumberRestaurant, Email_AddressRestaurant, restaurantAddressRestaurant;
    RadioButton radioQLDRestaurant, radioNSWRestaurant,  radioVICRestaurant, radioSARestaurant, radioWARestaurant;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view = inflater.inflate(R.layout.fragment_post_new_restaurant, container, false);

        usernameRestaurant = view.findViewById(R.id.usernameRestaurant);
        Bundle bundle = this.getArguments();
        usernameRestaurant.setText(this.getArguments().getString("createUserNameRestaurant"));

        descriptionRestaurant = view.findViewById(R.id.descriptionRestaurant);
        Phone_NumberRestaurant = view.findViewById(R.id.Phone_NumberRestaurant);
        Email_AddressRestaurant = view.findViewById(R.id.Email_AddressRestaurant);
        restaurantAddressRestaurant = view.findViewById(R.id.restaurantAddressRestaurant);

        radioQLDRestaurant = view.findViewById(R.id.radioQLDRestaurant);
        radioNSWRestaurant = view.findViewById(R.id.radioNSWRestaurant);
        radioVICRestaurant = view.findViewById(R.id.radioVICRestaurant);
        radioSARestaurant = view.findViewById(R.id.radioSARestaurant);
        radioWARestaurant = view.findViewById(R.id.radioWARestaurant);

        PostNewRestaurantButton = view.findViewById(R.id.PostNewRestaurantButton);
        PostNewRestaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameRestaurant1 = usernameRestaurant.getText().toString();
                String descriptionRestaurant1 = descriptionRestaurant.getText().toString();
                String Phone_NumberRestaurant1 = Phone_NumberRestaurant.getText().toString();
                String Email_AddressRestaurant1 = Email_AddressRestaurant.getText().toString();
                String restaurantAddressRestaurant1 = restaurantAddressRestaurant.getText().toString();

                String radioQLDGroceryStore1 = radioQLDRestaurant.getText().toString();
                String radioNSWRestaurant1 = radioNSWRestaurant.getText().toString();
                String radioVICRestaurant1 = radioVICRestaurant.getText().toString();
                String radioSARestaurant1 = radioSARestaurant.getText().toString();
                String radioWARestaurant1 = radioWARestaurant.getText().toString();

                if (descriptionRestaurant1.isEmpty()) {
                    Toast.makeText(view.getContext(), "Please Describe Your Post", Toast.LENGTH_SHORT).show();

                } else {
                    //save data into database
                    ConnectionHelper c = new ConnectionHelper();
                    Connection connection = c.connectionClass();

                    try {
                        if (connection != null) {

                            //query to Insert data nto MSSQL database
                            String sqlInsertAccommodation = "INSERT INTO Restaurant_Table (User_Name, Restaurant, State_Id, Restaurant_Address, Phone_Number, Email_Address) " +
                                    "VALUES ('" + usernameRestaurant1 + "','" + descriptionRestaurant1 + "','" +
                                    radioNSWRestaurant1 + "','" + restaurantAddressRestaurant1 + "','" + Phone_NumberRestaurant1 + "','" + Email_AddressRestaurant1 + "');";

                            // (radioQLDAccommodation || radioNSWAccommodation1 || radioVICAccommodation ||radioSAAccommodation || radioWAAccommodation)
                            Statement statement = connection.createStatement();

                            Toast.makeText(view.getContext(), "Your Ad has been posted", Toast.LENGTH_SHORT).show();

                            Intent homeActivity = new Intent(getActivity(), HomeActivity.class);
                            homeActivity.putExtra("HA","HA");
                            startActivity(homeActivity);
                            ResultSet rs = statement.executeQuery(sqlInsertAccommodation);


                        } else {
                            Toast.makeText(view.getContext(), "Check Connection", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception exception) {
                        Log.e("Error", exception.getMessage());
                    }
                }
            }
        });

        return view;
    }
}