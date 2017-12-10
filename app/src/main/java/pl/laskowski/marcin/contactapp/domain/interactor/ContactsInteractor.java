package pl.laskowski.marcin.contactapp.domain.interactor;

import org.joda.time.LocalDate;

import java.util.Arrays;
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
        return Observable.just(Arrays.asList(
                new Contact(
                        "FirstName",
                        "LastName",
                        "http://www.collider.com/wp-content/uploads/bender_futurama.jpg",
                        new LocalDate(3000, 2, 22),
                        "Description"),
                new Contact(
                        "FirstName2",
                        "LastName2",
                        null,
                        new LocalDate(1992, 1, 12),
                        null)))
                .delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
