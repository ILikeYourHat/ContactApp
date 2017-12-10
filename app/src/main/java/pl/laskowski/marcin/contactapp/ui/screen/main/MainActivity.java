package pl.laskowski.marcin.contactapp.ui.screen.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.laskowski.marcin.contactapp.App;
import pl.laskowski.marcin.contactapp.R;
import pl.laskowski.marcin.contactapp.dependency.AppComponent;
import pl.laskowski.marcin.contactapp.model.Contact;
import pl.laskowski.marcin.contactapp.ui.adapter.ContactsAdapter;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class MainActivity
        extends AppCompatActivity
        implements MainUi {

    @BindView(R.id.activityMain_rvContacts)
    RecyclerView rvContacts;
    @BindView(R.id.activityMain_vSwipeRefresh)
    SwipeRefreshLayout vSwipeRefresh;
    @BindView(R.id.activityMain_vProgressBar)
    ProgressBar vProgressBar;

    private MainPresenter presenter;
    private ContactsAdapter adapter = new ContactsAdapter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this, component());
        initRecyclerView();
        vSwipeRefresh.setOnRefreshListener(() -> presenter.onRefresh());
        presenter.onCreate();
    }

    private void initRecyclerView() {
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        rvContacts.setAdapter(adapter);
        rvContacts.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        adapter.update(contacts);
    }

    @Override
    public void dismissSwipe() {
        vSwipeRefresh.setRefreshing(false);
    }

    @Override
    public void showLoading(boolean visible) {
        vProgressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showEmptyListPlaceholder(boolean b) {

    }

    private AppComponent component() {
        return ((App) getApplication()).getComponent();
    }

}
