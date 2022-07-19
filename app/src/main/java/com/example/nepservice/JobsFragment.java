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


public class JobsFragment extends Fragment {
    TextView usernameValueJob;
    ArrayList<String> userName;
    ArrayList<String> description;
    ArrayList<String> phoneNumberCardView;
    ArrayList<String> emailAddressCardView;
    ArrayList<String> stateId;
    ArrayList<String> addressCardView;

    ImageButton createJobsButton;
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_jobs, container, false);

        usernameValueJob = view.findViewById(R.id.usernameValueJob);
        assert this.getArguments() != null;
        usernameValueJob.setText(this.getArguments().getString("USER"));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewJobs);
        userName = new ArrayList<>();
        description = new ArrayList<>();
        phoneNumberCardView = new ArrayList<>();
        emailAddressCardView = new ArrayList<>();
        stateId = new ArrayList<>();
        addressCardView= new ArrayList<>();

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        SelectJobQuery();

        recyclerView.setAdapter(new RecyclerAdapter(userName, description, phoneNumberCardView, emailAddressCardView, stateId, addressCardView));


        createJobsButton = view.findViewById(R.id.createJobsButton);
        createJobsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                String userName = usernameValueJob.getText().toString();
                bundle.putString("createUserNameJob", userName);
                PostNewJobsFragment postNewJobsFragment = new PostNewJobsFragment();
                postNewJobsFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainerView2, postNewJobsFragment, null).commit();

//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragmentContainerView2, new PostNewJobsFragment()).commit();
            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {
    }

    public void SelectJobQuery(){
        //save data into database
        ConnectionHelper c = new ConnectionHelper();
        Connection connection= c.connectionClass();


        //ResultSet resultSet = null;
        try {
            if (connection != null){

                //query to Select data from MSSQL database
                String selectJobSQl = "SELECT User_Name, Job, State_Id, Phone_Number, Email_Address, Job_Address FROM Job_Table";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(selectJobSQl);

                if (rs != null){
                    while (rs.next()){
                        try {
                            userName.add(rs.getString("User_Name"));
                            description.add(rs.getString("Job"));
                            phoneNumberCardView.add(rs.getString("Phone_Number"));
                            emailAddressCardView.add(rs.getString("Email_Address"));
                            stateId.add(rs.getString("State_Id"));
                            addressCardView.add(rs.getString("Job_Address"));
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