package pl.laskowski.marcin.contactapp.ui.screen.main;

import java.util.List;

import pl.laskowski.marcin.contactapp.dependency.AppComponent;
import pl.laskowski.marcin.contactapp.domain.interactor.ContactsInteractor;
import pl.laskowski.marcin.contactapp.model.Contact;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class MainPresenter {

    private final MainUi ui;
    private final ContactsInteractor interactor;

    public MainPresenter(MainUi ui, AppComponent component) {
        this.ui = ui;
        this.interactor = component.contactsInteractor();
    }

    public void onCreate() {
        syncContacts();
    }

    private void syncContacts() {
        ui.showLoading(true);
        interactor.getContacts()
                .subscribe(contacts -> {
                    ui.showLoading(false);
                    onContactsAcquired(contacts);
                });
    }

    private void onContactsAcquired(List<Contact> contacts) {
        ui.setContacts(contacts);
        ui.showEmptyListPlaceholder(contacts.isEmpty());
    }

    public void onRefresh() {
        interactor.getContacts()
                .subscribe(contacts -> {
                    ui.dismissSwipe();
                    onContactsAcquired(contacts);
                });
    }
}
