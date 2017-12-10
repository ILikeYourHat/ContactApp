package pl.laskowski.marcin.contactapp.ui.adapter;

import pl.laskowski.marcin.contactapp.model.Contact;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public interface ContactListener {

    void onDeleteClicked(Contact contact);

    void onInfoClicked(Contact contact, boolean expanded);

    void onLongClicked(Contact contact);

}
