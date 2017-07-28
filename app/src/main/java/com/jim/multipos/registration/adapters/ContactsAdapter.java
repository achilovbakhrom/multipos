package com.jim.multipos.registration.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jim.multipos.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by DEV on 27.07.2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    ArrayList<String> contactType;
    ArrayList<String> contact;

    public ContactsAdapter(ArrayList<String> contactType, ArrayList<String> contact) {
        this.contactType = contactType;
        this.contact = contact;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
                holder.tvContacts.setText(contactType.get(position));
                holder.tvContactsValue.setText(contact.get(position));
    }

    @Override
    public int getItemCount() {
        return contactType.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvContacts)
        TextView tvContacts;
        @BindView(R.id.tvContactsValue)
        TextView tvContactsValue;
        @BindView(R.id.ivRemoveContact)
        ImageView ivRemoveContact;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.ivRemoveContact)
        public void onRemove() {
            contactType.remove(getAdapterPosition());
            contact.remove(getAdapterPosition());
            notifyDataSetChanged();
        }
    }
}
