package pl.laskowski.marcin.contactapp.ui.screen.main;

import java.util.List;

import pl.laskowski.marcin.contactapp.model.Contact;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

interface MainUi {

    void switchState(State state);

    void setContacts(List<Contact> contacts);

    enum State {
        FULL_LIST,
        LOADING,
        EMPTY_LIST
    }

}
