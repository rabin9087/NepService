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
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PostNewJobsFragment extends Fragment {

    ImageButton backToJobFragment;
    Button PostNewJobButton;

    TextView usernameJob;
    EditText descriptionJob, Phone_NumberJob, Email_AddressJob;
    RadioButton radioQLDJob, radioNSWJob, radioVICJob, radioSAJob, radioWAJob;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_new_jobs, container, false);

        radioQLDJob = view.findViewById(R.id.radioQLDJob);
        radioNSWJob = view.findViewById(R.id.radioNSWJob);
        radioVICJob = view.findViewById(R.id.radioVICJob);
        radioSAJob = view.findViewById(R.id.radioSAJob);
        radioWAJob = view.findViewById(R.id.radioWAJob);

        usernameJob = view.findViewById(R.id.usernameRestaurant);
        Bundle bundle = this.getArguments();
        usernameJob.setText(this.getArguments().getString("createUserNameJob"));

        descriptionJob = view.findViewById(R.id.descriptionRestaurant);
        Phone_NumberJob = view.findViewById(R.id.Phone_NumberJob);
        Email_AddressJob = view.findViewById(R.id.Email_AddressJob);


        PostNewJobButton = view.findViewById(R.id.PostNewJobButton);
        PostNewJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernameJob1 = usernameJob.getText().toString();
                String descriptionJob1 = descriptionJob.getText().toString();
                String Phone_NumberJob1 = Phone_NumberJob.getText().toString();
                String Email_AddressJob1 = Email_AddressJob.getText().toString();

                String radioQLDJob1 = radioQLDJob.getText().toString();
                String radioNSWJob1 = radioNSWJob.getText().toString();
                String radioVICJob1 = radioVICJob.getText().toString();
                String radioSAJob1 = radioSAJob.getText().toString();
                String radioWAJob1 = radioWAJob.getText().toString();


                if (descriptionJob1.isEmpty()) {
                    Toast.makeText(view.getContext(), "Please Describe Your Post", Toast.LENGTH_SHORT).show();

                } else {
                    //save data into database
                    ConnectionHelper c = new ConnectionHelper();
                    Connection connection = c.connectionClass();

                    try {
                        if (connection != null) {
                            if (usernameJob1.isEmpty()){
                                Toast.makeText(view.getContext(), "Please fill with current user name", Toast.LENGTH_SHORT).show();
                            } else {

                                //query to Insert data nto MSSQL database
                                String sqlInsertJob = "INSERT INTO Job_Table (User_Name, Job, State_Id, Phone_Number, Email_Address) " +
                                        "VALUES ('" + usernameJob1 + "','" + descriptionJob1 + "','" +
                                        radioNSWJob1 + "','" + Phone_NumberJob1 + "','" + Email_AddressJob1 + "');";

                                Statement statement = connection.createStatement();
                                Intent homeActivity = new Intent(getActivity(), HomeActivity.class);
                                homeActivity.putExtra("HA","HA");
                                startActivity(homeActivity);
                                Toast.makeText(view.getContext(), "Your Ad has been posted", Toast.LENGTH_SHORT).show();
                                ResultSet rs = statement.executeQuery(sqlInsertJob);

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