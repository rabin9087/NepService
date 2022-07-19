package com.example.nepservice;

import android.net.Uri;
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


public class GroceryStoreFragment extends Fragment {
    TextView usernameValueGroceryStore;
    ArrayList<String> userName;
    ArrayList<String> description;
    ArrayList<String> phoneNumberCardView;
    ArrayList<String> emailAddressCardView;
    ArrayList<String> stateId;
    ArrayList<String> addressCardView;
    private final int[] image = {R.drawable.home };

    ImageButton createGroceryStoreButton;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

           View view = inflater.inflate(R.layout.fragment_grocery_stores, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewGroceryStore);
        usernameValueGroceryStore = view.findViewById(R.id.usernameValueGroceryStore);
        assert this.getArguments() != null;
        usernameValueGroceryStore.setText(this.getArguments().getString("USER"));

        userName = new ArrayList<>();
        description = new ArrayList<>();
        phoneNumberCardView = new ArrayList<>();
        emailAddressCardView = new ArrayList<>();
        stateId = new ArrayList<>();
        addressCardView= new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        SelectGroceryStoreQuery();

        recyclerView.setAdapter(new RecyclerAdapter(userName, description, phoneNumberCardView, emailAddressCardView, stateId, addressCardView));

        createGroceryStoreButton =view.findViewById(R.id.createGroceryStoreButton);
        createGroceryStoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                String userName = usernameValueGroceryStore.getText().toString();
                bundle.putString("createUserNameGroceryStore", userName);
                PostNewGroceryStoresFragment postNewGroceryStoresFragment = new PostNewGroceryStoresFragment();
                postNewGroceryStoresFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, postNewGroceryStoresFragment, null).commit();

//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragmentContainerView2, new PostNewGroceryStoresFragment()).commit();
            }
        });

            return view;

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void SelectGroceryStoreQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        //ResultSet resultSet = null;
        try {
            if (connection != null){

                //query to Insert data nto MSSQL database
                String selectGroceryStoreSQl = "SELECT User_Name, Grocery_Store, State_Id, Phone_Number, Email_Address, GroceryStore_Address FROM Grocery_Store_Table";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectGroceryStoreSQl);

                if (rs != null){
                    while (rs.next()){
                        try {
                            userName.add(rs.getString("User_Name"));
                            description.add(rs.getString("Grocery_Store"));
                            phoneNumberCardView.add(rs.getString("Phone_Number"));
                            emailAddressCardView.add(rs.getString("Email_Address"));
                            stateId.add(rs.getString("State_Id"));
                            addressCardView.add(rs.getString("GroceryStore_Address"));

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