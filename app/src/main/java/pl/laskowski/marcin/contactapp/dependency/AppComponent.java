package pl.laskowski.marcin.contactapp.dependency;

import dagger.Component;
import pl.laskowski.marcin.contactapp.domain.interactor.ContactsInteractor;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

@Component(modules = {
        BackendModule.class
})
public interface AppComponent {

    ContactsInteractor contactsInteractor();

}
