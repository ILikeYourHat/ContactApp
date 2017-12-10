package pl.laskowski.marcin.contactapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>{

    public ContactsAdapter() {
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder {

        public ContactsViewHolder(View itemView) {
            super(itemView);
        }

    }

}
