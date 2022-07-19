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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class PostNewGroceryStoresFragment extends Fragment {

    ImageButton backToGroceryStoreFragment;
    Button PostNewGroceryStoreButton;
    TextView usernameGroceryStore;
    EditText descriptionGroceryStore, Phone_NumberGroceryStore, Email_AddressGroceryStore, AddressGroceryStore;
    RadioGroup radioGroupGroceryStore;
    RadioButton radioQLDGroceryStore, radioNSWGroceryStore,  radioVICGroceryStore, radioSAGroceryStore, radioWAGroceryStore;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_post_new_grocery_stores, container, false);

        usernameGroceryStore = view.findViewById(R.id.usernameGroceryStore);
        Bundle bundle = this.getArguments();
        usernameGroceryStore.setText(this.getArguments().getString("createUserNameGroceryStore"));

        descriptionGroceryStore = view.findViewById(R.id.descriptionGroceryStore);
        Phone_NumberGroceryStore = view.findViewById(R.id.Phone_NumberGroceryStore);
        Email_AddressGroceryStore = view.findViewById(R.id.Email_AddressGroceryStore);
        AddressGroceryStore = view.findViewById(R.id.AddressGroceryStore);

        radioQLDGroceryStore = view.findViewById(R.id.radioQLDGroceryStore);
        radioNSWGroceryStore = view.findViewById(R.id.radioNSWGroceryStore);
        radioVICGroceryStore = view.findViewById(R.id.radioVICGroceryStore);
        radioSAGroceryStore = view.findViewById(R.id.radioSAGroceryStore);
        radioWAGroceryStore = view.findViewById(R.id.radioWAGroceryStore);



        PostNewGroceryStoreButton = view.findViewById(R.id.PostNewGroceryStoreButton);
        PostNewGroceryStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameGroceryStore1 = usernameGroceryStore.getText().toString();
                String descriptionGroceryStore1 = descriptionGroceryStore.getText().toString();
                String Phone_NumberGroceryStore1 = Phone_NumberGroceryStore.getText().toString();
                String Email_AddressGroceryStore1 = Email_AddressGroceryStore.getText().toString();
                String restaurantAddressGroceryStore1 = AddressGroceryStore.getText().toString();

                //String radioGroceryStore1 = radioGroupGroceryStore.toString();

                String radioQLDGroceryStore1 = radioQLDGroceryStore.getText().toString();
                String radioNSWGroceryStore1 = radioNSWGroceryStore.getText().toString();
                String radioVICGroceryStore1 = radioVICGroceryStore.getText().toString();
                String radioSAGroceryStore1 = radioSAGroceryStore.getText().toString();
                String radioWAGroceryStore1 = radioWAGroceryStore.getText().toString();

                if (descriptionGroceryStore1.isEmpty()) {
                    Toast.makeText(view.getContext(), "Please Describe Your Post", Toast.LENGTH_SHORT).show();

                } else {
                    //save data into database
                    ConnectionHelper c = new ConnectionHelper();
                    Connection connection = c.connectionClass();

                    try {
                        if (connection != null) {

                            //query to Insert data nto MSSQL database
                            String sqlInsertGroceryStore = "INSERT INTO Grocery_Store_Table (User_Name, Grocery_Store, State_Id, GroceryStore_Address, Phone_Number, Email_Address) " +
                                    "VALUES ('" + usernameGroceryStore1 + "','" + descriptionGroceryStore1 + "','" +
                                    radioNSWGroceryStore1 + "','" + restaurantAddressGroceryStore1 + "','" + Phone_NumberGroceryStore1 + "','" + Email_AddressGroceryStore1 + "');";

                            // (radioQLDAccommodation || radioNSWAccommodation1 || radioVICAccommodation ||radioSAAccommodation || radioWAAccommodation)
                            Statement statement = connection.createStatement();
                            try {
                                Intent homeActivity = new Intent(getActivity(), HomeActivity.class);
                                homeActivity.putExtra("HA","HA");
                                startActivity(homeActivity);
                            } catch (Exception e){
                                Log.e("Activity not Starter", e.getMessage());
                            }

                            Toast.makeText(view.getContext(), "Your Ad has been posted", Toast.LENGTH_SHORT).show();
                            ResultSet rs = statement.executeQuery(sqlInsertGroceryStore);


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