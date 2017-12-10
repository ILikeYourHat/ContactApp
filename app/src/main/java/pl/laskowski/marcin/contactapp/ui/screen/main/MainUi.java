package pl.laskowski.marcin.contactapp.ui.screen.main;

import java.util.List;

import pl.laskowski.marcin.contactapp.model.Contact;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

interface MainUi {

    void switchStateToLoading();

    void switchStateToFullList();

    void setContacts(List<Contact> contacts);

    void switchStateToEmptyList();

}
