package pl.laskowski.marcin.contactapp.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.laskowski.marcin.contactapp.model.Contact;
import pl.laskowski.marcin.contactapp.repository.Backend;
import pl.laskowski.marcin.contactapp.repository.data.ContactParser;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class ContactsInteractor {

    private final Backend backend;
    private final ContactParser parser;

    @Inject
    public ContactsInteractor(Backend backend) {
        this.backend = backend;
        this.parser = new ContactParser();
    }

    public Single<List<Contact>> getContacts() {
        return backend.getContacts()
                .map(contacts -> parser.fromJson(contacts))
                .flatMapObservable(Observable::fromIterable)
                .distinct()
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
