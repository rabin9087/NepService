package com.example.nepservice;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class AccommodationFragment extends Fragment {

    TextView usernameValueAccommodation;

    ArrayList<String> userName;
    ArrayList<String> description;
    ArrayList<String> phoneNumberCardView;
    ArrayList<String> emailAddressCardView;
    ArrayList<String> stateId;
    ArrayList<String> addressCardView;

    ImageButton createAccommodationButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState
    ) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_accommodations,container, false);

        usernameValueAccommodation = view.findViewById(R.id.usernameValueAccommodation);
        assert this.getArguments() != null;
        usernameValueAccommodation.setText(this.getArguments().getString("USER"));


        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewAccommodation);

        userName = new ArrayList<>();
        description = new ArrayList<>();
        phoneNumberCardView = new ArrayList<>();
        emailAddressCardView = new ArrayList<>();
        stateId = new ArrayList<>();
        addressCardView= new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        SelectAccommodationQuery();

        recyclerView.setAdapter(new RecyclerAdapter(userName, description, phoneNumberCardView, emailAddressCardView, stateId, addressCardView));

        createAccommodationButton =view.findViewById(R.id.createAccommodationButton);
        createAccommodationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                String userName = usernameValueAccommodation.getText().toString();
                bundle.putString("createUserNameAccommodation", userName);
                PostNewAccommodationFragment postNewAccommodationFragment = new PostNewAccommodationFragment();
                postNewAccommodationFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, postNewAccommodationFragment, null).commit();

//
            }
        });

        return view;

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void SelectAccommodationQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        //ResultSet resultSet = null;
        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String selectAccommodationSQl = "SELECT User_Name, Accommodation, State_Id, Phone_Number, Email_Address, Accommodation_Address FROM Accommodation_Table;";

                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectAccommodationSQl);

                if (rs != null){
                    while (rs.next()){
                        try {
                            userName.add(rs.getString("User_Name"));
                            description.add(rs.getString("Accommodation"));
                            phoneNumberCardView.add(rs.getString("Phone_Number"));
                            emailAddressCardView.add(rs.getString("Email_Address"));
                            stateId.add(rs.getString("State_Id"));
                            addressCardView.add(rs.getString("Accommodation_Address"));

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