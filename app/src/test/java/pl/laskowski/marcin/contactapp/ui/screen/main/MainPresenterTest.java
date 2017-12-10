package pl.laskowski.marcin.contactapp.ui.screen.main;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;

import io.reactivex.Observable;
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
    ContactsInteractor interactor;

    private MainPresenter presenter;
    private InOrder inOrder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        inOrder = inOrder(ui);
        presenter = new MainPresenter(ui, interactor);
    }

    @Test
    public void shouldSyncContacts() {
        when(interactor.getContacts())
                .thenReturn(Observable.just(Model.CONTACTS));

        presenter.onCreate();
        inOrder.verify(ui).switchStateToLoading();
        inOrder.verify(ui).setContacts(Model.CONTACTS);
        inOrder.verify(ui).switchStateToFullList();
        verifyNoMoreInteractions(ui);
    }

    @Test
    public void shouldSyncEmptyContacts() {
        when(interactor.getContacts())
                .thenReturn(Observable.just(Collections.emptyList()));

        presenter.onCreate();
        inOrder.verify(ui).switchStateToLoading();
        inOrder.verify(ui).switchStateToEmptyList();
        verifyNoMoreInteractions(ui);
    }

}
