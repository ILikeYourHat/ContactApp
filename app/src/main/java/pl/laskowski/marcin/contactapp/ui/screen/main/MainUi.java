package pl.laskowski.marcin.contactapp.ui.screen.main;

import java.util.List;

import pl.laskowski.marcin.contactapp.model.Contact;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

interface MainUi {

    void setContacts(List<Contact> contacts);

    void dismissSwipe();

    void showLoading(boolean visible);

    void showEmptyListPlaceholder(boolean visible);

    void removeFromList(Contact contact);

    void confirmContactDelete(Contact contact);

    void setContactExpanded(Contact contact, boolean expanded);

    void navigateToPickColorActivity(Contact contact);

    void setColor(Contact contact, int color);

}
