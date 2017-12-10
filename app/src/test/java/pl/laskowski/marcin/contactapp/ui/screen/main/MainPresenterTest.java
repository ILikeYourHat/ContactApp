package pl.laskowski.marcin.contactapp.ui.screen.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import io.reactivex.Single;
import pl.laskowski.marcin.contactapp.dependency.AppComponent;
import pl.laskowski.marcin.contactapp.domain.interactor.ContactsInteractor;
import pl.laskowski.marcin.contactapp.util.Model;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class MainPresenterTest {

    @Mock
    MainUi ui;
    @Mock
    AppComponent component;
    @Mock
    ContactsInteractor interactor;

    private MainPresenter presenter;
    private InOrder inOrder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        inOrder = inOrder(ui);
        when(component.contactsInteractor()).thenReturn(interactor);
        presenter = new MainPresenter(ui, component);
    }

    @Test
    public void shouldSyncContacts() {
        when(interactor.getContacts())
                .thenReturn(Single.just(Model.CONTACTS));

        presenter.onCreate();
        inOrder.verify(ui).switchState(MainUi.State.LOADING);
        inOrder.verify(ui).setContacts(Model.CONTACTS);
        inOrder.verify(ui).switchState(MainUi.State.FULL_LIST);
        verifyNoMoreInteractions(ui);
    }

    @Test
    public void shouldSyncEmptyContacts() {
        when(interactor.getContacts())
                .thenReturn(Single.just(Collections.emptyList()));

        presenter.onCreate();
        inOrder.verify(ui).switchState(MainUi.State.LOADING);
        inOrder.verify(ui).switchState(MainUi.State.EMPTY_LIST);
        verifyNoMoreInteractions(ui);
    }

}
