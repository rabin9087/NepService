package com.example.nepservice;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostNewAccommodationFragment extends Fragment {

    Button PostNewAccommodationButton;
    TextView usernameAccommodation;
    EditText descriptionAccommodation, Phone_NumberAccommodation, Email_AddressAccommodation;
    RadioButton radioQLDAccommodation, radioNSWAccommodation,  radioVICAccommodation, radioSAAccommodation, radioWAAccommodation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_new_accommodation, container, false);

        radioQLDAccommodation = view.findViewById(R.id.radioQLDAccommodation);
        radioNSWAccommodation = view.findViewById(R.id.radioNSWAccommodation);
        radioVICAccommodation = view.findViewById(R.id.radioVICAccommodation);
        radioSAAccommodation = view.findViewById(R.id.radioSAAccommodation);
        radioWAAccommodation = view.findViewById(R.id.radioWAAccommodation);

        usernameAccommodation = view.findViewById(R.id.usernameAccommodation);
        Bundle bundle = this.getArguments();
        usernameAccommodation.setText(this.getArguments().getString("createUserNameAccommodation"));


        descriptionAccommodation = view.findViewById(R.id.descriptionAccommodation);
        Phone_NumberAccommodation = view.findViewById(R.id.Phone_NumberRestaurant);
        Email_AddressAccommodation = view.findViewById(R.id.Email_AddressRestaurant);


        PostNewAccommodationButton = view.findViewById(R.id.PostNewRestaurantButton);
        PostNewAccommodationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String radioQLDAccommodation1 = radioQLDAccommodation.getText().toString();
                String radioNSWAccommodation1 = radioNSWAccommodation.getText().toString();
                String radioVICAccommodation1 = radioVICAccommodation.getText().toString();
                String radioSAAccommodation1 = radioSAAccommodation.getText().toString();
                String radioWAAccommodation1 = radioWAAccommodation.getText().toString();

                String usernameAccommodation1 = usernameAccommodation.getText().toString();
                String descriptionAccommodation1 = descriptionAccommodation.getText().toString();
                String Phone_NumberAccommodation1 = Phone_NumberAccommodation.getText().toString();
                String Email_AddressAccommodation1 =Email_AddressAccommodation.getText().toString();

                if (descriptionAccommodation1.isEmpty()) {
                    Toast.makeText(view.getContext(), "Please Describe Your Post", Toast.LENGTH_SHORT).show();

                } else {
                    //save data into database
                    ConnectionHelper c = new ConnectionHelper();
                    Connection connection = c.connectionClass();

                    try {
                        if (connection != null) {
                            if (usernameAccommodation1.isEmpty()){
                                Toast.makeText(view.getContext(), "Please fill current user name", Toast.LENGTH_SHORT).show();
                            } else {

                                //query to Insert data nto MSSQL database
                                String sqlInsertAccommodation = "INSERT INTO Accommodation_Table (User_Name, Accommodation, State_Id, Phone_Number, Email_Address) " +
                                        "VALUES ('" + usernameAccommodation1 + "','" + descriptionAccommodation1 + "','" +
                                        radioNSWAccommodation1 + "','" + Phone_NumberAccommodation1 + "','" + Email_AddressAccommodation1 + "');";

                                // (radioQLDAccommodation || radioNSWAccommodation1 || radioVICAccommodation ||radioSAAccommodation || radioWAAccommodation)
                                Statement statement = connection.createStatement();

                                Toast.makeText(view.getContext(), "Your Ad has been posted", Toast.LENGTH_SHORT).show();

                                Intent homeActivity = new Intent(getActivity(), HomeActivity.class);
                                homeActivity.putExtra("HA","HA");
                                startActivity(homeActivity);
                                ResultSet rs = statement.executeQuery(sqlInsertAccommodation);
                            }

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

