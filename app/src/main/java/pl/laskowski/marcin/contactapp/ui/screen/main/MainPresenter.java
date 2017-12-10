package pl.laskowski.marcin.contactapp.ui.screen.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import pl.laskowski.marcin.contactapp.dependency.AppComponent;
import pl.laskowski.marcin.contactapp.domain.interactor.ContactsInteractor;
import pl.laskowski.marcin.contactapp.model.Contact;
import pl.laskowski.marcin.contactapp.ui.adapter.ContactListener;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class MainPresenter implements ContactListener {

    private final MainUi ui;
    private final ContactsInteractor interactor;
    private List<Contact> contacts = Collections.emptyList();

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

    public void onRefresh() {
        interactor.getContacts()
                .subscribe(contacts -> {
                    ui.dismissSwipe();
                    onContactsAcquired(contacts);
                });
    }

    private void onContactsAcquired(List<Contact> contacts) {
        this.contacts = new ArrayList<>(contacts);
        ui.setContacts(contacts);
        ui.showEmptyListPlaceholder(contacts.isEmpty());
    }

    @Override
    public void onDeleteClicked(Contact contact) {
       ui.confirmContactDelete(contact);
    }

    @Override
    public void onInfoClicked(Contact contact, boolean expanded) {
        ui.setContactExpanded(contact, !expanded);
    }

    public void onDeleteConfirmed(Contact contact) {
        ui.removeFromList(contact);
        contacts.remove(contact);
        ui.showEmptyListPlaceholder(contacts.isEmpty());
    }

}
