package pl.laskowski.marcin.contactapp.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pl.laskowski.marcin.contactapp.R;
import pl.laskowski.marcin.contactapp.model.Contact;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsViewHolder>{

    private final List<Contact> contacts;

    public ContactsAdapter() {
        this.contacts = new ArrayList<>();
    }

    public void update(List<Contact> contacts) {
        this.contacts.clear();
        this.contacts.addAll(contacts);
        notifyDataSetChanged();
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = inflate(R.layout.item_contact, parent);
        return new ContactsViewHolder(item);
    }

    private View inflate(@LayoutRes int res, ViewGroup parent) {
        return LayoutInflater.from(parent.getContext())
                .inflate(res, parent, false);
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.bind(contact);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

}
