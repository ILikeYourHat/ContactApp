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

    private final List<ContactAdapterItem> contactItems;
    private final ContactListener listener;

    public ContactsAdapter(ContactListener listener) {
        this.listener = listener;
        this.contactItems = new ArrayList<>();
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
        ContactAdapterItem contact = contactItems.get(position);
        holder.bind(contact, listener);
    }

    @Override
    public int getItemCount() {
        return contactItems.size();
    }

    public void update(List<Contact> contacts) {
        this.contactItems.clear();
        for (Contact contact : contacts) {
            this.contactItems.add(new ContactAdapterItem(contact));
        }
        notifyDataSetChanged();
    }

    public void remove(Contact contact) {
        int index = findIndex(contact);
        if (index != -1) {
            contactItems.remove(index);
            notifyItemRemoved(index);
        }
    }

    public void setExpanded(Contact contact, boolean expanded) {
        int index = findIndex(contact);
        if (index != -1) {
            ContactAdapterItem real = contactItems.get(index);
            real.setExpanded(expanded);
            notifyItemChanged(index);
        }
    }

    private int findIndex(Contact contact) {
        for (int i = 0; i < contactItems.size(); i++) {
            if (contactItems.get(i).getContact().equals(contact)) {
                return i;
            }
        }
        return -1;
    }

}
