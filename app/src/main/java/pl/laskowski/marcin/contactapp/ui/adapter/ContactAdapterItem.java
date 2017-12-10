package pl.laskowski.marcin.contactapp.ui.adapter;

import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;

import pl.laskowski.marcin.contactapp.model.Contact;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class ContactAdapterItem {

    @NonNull
    private final Contact contact;
    @ColorInt
    private int backgroundColor;
    private boolean expanded;

    public ContactAdapterItem(@NonNull Contact contact) {
        this.contact = contact;
        this.backgroundColor = Color.TRANSPARENT;
        this.expanded = false;
    }

    @NonNull
    public Contact getContact() {
        return contact;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

}
