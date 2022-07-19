package com.example.nepservice;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;


public class ForgetFragment extends Fragment {

    EditText phoneNumberVerification;
    Button resetPasswordButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//
       View view = inflater.inflate(R.layout.fragment_forget, container, false);
//
//        phoneNumberVerification = view.findViewById(R.id.phoneNumberVerification);
//        resetPasswordButton = view.findViewById(R.id.resetPasswordButton);
//
//        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////save data into database
//                ConnectionHelper c = new ConnectionHelper();
//                Connection connection= c.connectionClass();
//
//
//                //ResultSet resultSet = null;
//                try {
//                    if (connection != null){
//
//
//                        String phoneNumberVerification1 = phoneNumberVerification.getText().toString();
//
//                        //query to Insert data nto MSSQL database
//                        String selectAccommodationSQl = "SELECT Phone_Number, Email_Address FROM Registration_Table";
//
//                        Statement statement = connection.createStatement();
//                        ResultSet rs = statement.executeQuery(selectAccommodationSQl);
//
//                        if (rs != null){
//                            while (rs.next()){
//                                try {
//                                    String phnVer1  = rs.getString("Phone_Number");
//                                    String emailVer1 = rs.getString("Email_Address");
//
//                                    ProgressBar progressBar = view.findViewById(R.id.progressbar_sending_otp);
//
//                                    if (phoneNumberVerification1.equals(phnVer1) || phoneNumberVerification1.equals(emailVer1))
//                                    {
//
//                                        progressBar.setVisibility(View.VISIBLE);
//                                        resetPasswordButton.setVisibility(View.INVISIBLE);
//
//                                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
//                                                phoneNumberVerification1, 60, TimeUnit.SECONDS, ForgetFragment.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//                                                    @Override
//                                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
//
//                                                    }
//
//                                                    @Override
//                                                    public void onVerificationFailed(@NonNull FirebaseException e) {
//
//                                                    }
//                                                });
//
//
//
//                                        Bundle bundle = new Bundle();
//                                        bundle.putString("textMobile", phoneNumberVerification1);
//                                        EnterOTPFragment enterOTPFragment = new EnterOTPFragment();
//                                        enterOTPFragment.setArguments(bundle);
//                                        phoneNumberVerification.setText(phoneNumberVerification1);
//                                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.passwordReset, enterOTPFragment).commit();
//                                        Toast.makeText(view.getContext(), "OTP has been sent to : " + phoneNumberVerification1, Toast.LENGTH_SHORT).show();
//                                    } else {
//
//                                        //Toast.makeText(this.getContext(), "Please Check Phone Number", Toast.LENGTH_SHORT).show();
//                                        phoneNumberVerification.setText("");
//                                    }
//                                } catch (Exception exception){
//                                    Log.e("Error", exception.getMessage());
//                                }
//
//                            }
//                        }
//                    }
//                }catch (Exception exception){
//                    Log.e("Error", exception.getMessage());
//                }
//
//                //forgetPasswordQuery();
//
//            }
//        });

        return view;

    }

//    public void forgetPasswordQuery() {
//    }
}