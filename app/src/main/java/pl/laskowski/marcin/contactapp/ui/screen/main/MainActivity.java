package pl.laskowski.marcin.contactapp.ui.screen.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ViewFlipper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.laskowski.marcin.contactapp.App;
import pl.laskowski.marcin.contactapp.R;
import pl.laskowski.marcin.contactapp.dependency.AppComponent;
import pl.laskowski.marcin.contactapp.domain.interactor.ContactsInteractor;
import pl.laskowski.marcin.contactapp.model.Contact;
import pl.laskowski.marcin.contactapp.ui.adapter.ContactsAdapter;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class MainActivity
        extends AppCompatActivity
        implements MainUi {

    @BindView(R.id.activityMain_vfContent)
    ViewFlipper vfContent;
    @BindView(R.id.activityMain_rvContacts)
    RecyclerView rvContacts;

    private MainPresenter presenter;
    private ContactsAdapter adapter = new ContactsAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this, component());
        presenter.onCreate();
        initRecyclerView();
    }

    private void initRecyclerView() {
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        rvContacts.setAdapter(adapter);
        rvContacts.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void switchState(State state) {
        vfContent.setDisplayedChild(state.ordinal());
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        adapter.update(contacts);
    }


    private AppComponent component() {
        return ((App) getApplication()).getComponent();
    }

}
