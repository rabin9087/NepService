package com.example.nepservice;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class RestaurantFragment extends Fragment {
    TextView usernameValueRestaurant;

    ArrayList<String> userName;
    ArrayList<String> description;
    ArrayList<String> phoneNumberCardView;
    ArrayList<String> emailAddressCardView;
    ArrayList<String> stateId;
    ArrayList<String> addressCardView;

ImageButton createRestaurantButton;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_restaurants, container, false);


        usernameValueRestaurant = view.findViewById(R.id.usernameValueRestaurant);
        assert this.getArguments() != null;
        usernameValueRestaurant.setText(this.getArguments().getString("USER"));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewRestaurants);
        userName = new ArrayList<>();
        description = new ArrayList<>();
        phoneNumberCardView = new ArrayList<>();
        emailAddressCardView = new ArrayList<>();
        stateId = new ArrayList<>();
        addressCardView= new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        SelectRestaurantQuery();

        recyclerView.setAdapter(new RecyclerAdapter(userName, description, phoneNumberCardView, emailAddressCardView, stateId, addressCardView));


        createRestaurantButton = view.findViewById(R.id.createRestaurantButton);
        createRestaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                String userName = usernameValueRestaurant.getText().toString();
                bundle.putString("createUserNameRestaurant", userName);
                PostNewRestaurantFragment postNewRestaurantFragment = new PostNewRestaurantFragment();
                postNewRestaurantFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, postNewRestaurantFragment, null).commit();

//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragmentContainerView2, new PostNewRestaurantFragment()).commit();
            }
        });


        return view;
    }


    public interface OnFragmentInteractionListener {

    }

    public void SelectRestaurantQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        //ResultSet resultSet = null;
        try {
            if (connection != null){

                //query to Select data from MSSQL database
                String selectRestaurantSQl = "SELECT User_Name, Restaurant, State_Id, Phone_Number, Email_Address, Restaurant_Address FROM Restaurant_Table";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectRestaurantSQl);

                if (rs != null){
                    while (rs.next()){
                        try {
                            userName.add(rs.getString("User_Name"));
                            description.add(rs.getString("Restaurant"));
                            phoneNumberCardView.add(rs.getString("Phone_Number"));
                            emailAddressCardView.add(rs.getString("Email_Address"));
                            stateId.add(rs.getString("State_Id"));
                            addressCardView.add(rs.getString("Restaurant_Address"));

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