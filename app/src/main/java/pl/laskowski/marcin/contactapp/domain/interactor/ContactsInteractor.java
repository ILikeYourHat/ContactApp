package pl.laskowski.marcin.contactapp.domain.interactor;

import org.joda.time.LocalDate;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import pl.laskowski.marcin.contactapp.model.Contact;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class ContactsInteractor {

    public Observable<List<Contact>> getContacts() {
        return Observable.just(Collections.singletonList(new Contact(
                "FirstName",
                "LastName",
                "http://collider.com/wp-content/uploads/bender_futurama.jpg",
                new LocalDate(1992, 1, 12),
                "Description")))
                .delay(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
