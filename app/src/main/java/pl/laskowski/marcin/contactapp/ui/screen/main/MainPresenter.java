package pl.laskowski.marcin.contactapp.ui.screen.main;

import java.util.List;

import pl.laskowski.marcin.contactapp.domain.interactor.ContactsInteractor;
import pl.laskowski.marcin.contactapp.model.Contact;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class MainPresenter {

    private final MainUi ui;
    private final ContactsInteractor interactor;

    public MainPresenter(MainUi ui, ContactsInteractor interactor) {
        this.ui = ui;
        this.interactor = interactor;
    }

    public void onCreate() {
        syncContacts();
    }

    private void syncContacts() {
        ui.switchStateToLoading();
        interactor.getContacts()
                .subscribe(this::onContactsAcquired);
    }

    private void onContactsAcquired(List<Contact> contacts) {
        if (contacts.isEmpty()) {
            ui.switchStateToEmptyList();
        } else {
            ui.setContacts(contacts);
            ui.switchStateToFullList();
        }
    }

}
