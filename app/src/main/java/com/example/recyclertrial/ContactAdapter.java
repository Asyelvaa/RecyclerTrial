package com.example.recyclertrial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private Context context;
    private List<EPLTeamModel> contactList;
    private ContactAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvname, tvdesc;
        public ImageView ivTeamBadge;

        public MyViewHolder(View view) {
            super(view);
            tvname = view.findViewById(R.id.tvname);
            tvdesc = view.findViewById(R.id.tvdesc);
            ivTeamBadge = view.findViewById(R.id.ivTeambadge);

        }
    }

    public ContactAdapter(Context context, List<EPLTeamModel> contactList, ContactAdapterListener listener) {
        this.context = context;
        this.contactList = contactList;
        this.listener = listener;
    }


    @NonNull
    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_contact, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.MyViewHolder holder, int position) {

        final EPLTeamModel contact = this.contactList.get(position);
        holder.tvname.setText(contact.getTeamName());
        holder.tvdesc.setText(contact.getStadium());
        Glide.with(holder.itemView.getContext()).load(contact.getTeamBadge()).into(holder.ivTeamBadge);

    }

    @Override
    public int getItemCount() {
        return this.contactList.size();
    }

    public interface ContactAdapterListener {
        void onContactSelected(EPLTeamModel contact);
    }
}

