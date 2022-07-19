package com.example.nepservice;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

    private final ArrayList<String> userName;
    private final ArrayList<String> description;
    private final ArrayList<String> phoneNumberCardView;
    private final ArrayList<String> emailAddressCardView;
    private final ArrayList<String> stateId;
    private final ArrayList<String> addressCardView;

    public RecyclerAdapter(ArrayList<String> userName, ArrayList<String> description, ArrayList<String>
            phoneNumberCardView, ArrayList<String> emailAddressCardView, ArrayList<String> stateId, ArrayList<String> addressCardView) {
        this.userName = userName;
        this.description = description;
        this.phoneNumberCardView = phoneNumberCardView;
        this.emailAddressCardView = emailAddressCardView;
        this.stateId = stateId;
        this.addressCardView = addressCardView;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{


        TextView itemUserName;
        TextView itemDescription;
        TextView itemPhoneNumberCardView;
        TextView itemEmailAddressCardView;
        TextView itemStateId;
        TextView itemAddressCardView;
        public ViewHolder( View itemView) {
            super(itemView);

            try {
                itemUserName = itemView.findViewById(R.id.usernameCardView);
                itemDescription = itemView.findViewById(R.id.descriptionCardView);
                itemPhoneNumberCardView = itemView.findViewById(R.id.phoneNumberCardView);
                itemEmailAddressCardView = itemView.findViewById(R.id.emailAddressCardView);
                itemStateId = itemView.findViewById(R.id.stateCardView);
                itemAddressCardView = itemView.findViewById(R.id.addressCardView);

            }catch (Exception e){
                Log.e("Error", e.getMessage());
            }
        }
    }
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.recycleradapter, viewGroup, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //viewHolder.itemImage.setImageResource(images[i]);
        viewHolder.itemUserName.setText(userName.get(i));
        viewHolder.itemDescription.setText(description.get(i));
        viewHolder.itemPhoneNumberCardView.setText(phoneNumberCardView.get(i));
        viewHolder.itemEmailAddressCardView.setText(emailAddressCardView.get(i));
        viewHolder.itemStateId.setText(stateId.get(i));
        viewHolder.itemAddressCardView.setText(addressCardView.get(i));
    }

    @Override
    public int getItemCount() {
        return userName.size();
    }
}
