package pl.laskowski.marcin.contactapp.ui.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.laskowski.marcin.contactapp.App;
import pl.laskowski.marcin.contactapp.R;
import pl.laskowski.marcin.contactapp.dependency.AppComponent;
import pl.laskowski.marcin.contactapp.model.Contact;
import pl.laskowski.marcin.contactapp.ui.adapter.ContactsAdapter;
import pl.laskowski.marcin.contactapp.ui.router.Keys;
import pl.laskowski.marcin.contactapp.ui.router.Requests;
import pl.laskowski.marcin.contactapp.ui.screen.colorpicker.ColorPickerActivity;

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
    View vProgressBar;
    @BindView(R.id.activityMain_vEmptyListPlaceholder)
    View vEmptyListPlaceholder;

    private MainPresenter presenter;
    private ContactsAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter = new MainPresenter(this, component());
        adapter = new ContactsAdapter(presenter);
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
    public void showEmptyListPlaceholder(boolean visible) {
        vEmptyListPlaceholder.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void removeFromList(Contact contact) {
        adapter.remove(contact);
    }

    @Override
    public void confirmContactDelete(Contact contact) {
        new AlertDialog.Builder(this)
                .setMessage(R.string.activityMain_deleteConfirmationDialog_message)
                .setPositiveButton(R.string.activityMain_deleteConfirmationDialog_positiveButton, (dialogInterface, i) -> presenter.onDeleteConfirmed(contact))
                .setNegativeButton(R.string.activityMain_deleteConfirmationDialog_negativeButton, null)
                .show();
    }

    @Override
    public void setContactExpanded(Contact contact, boolean expanded) {
        adapter.setExpanded(contact, expanded);
    }

    @Override
    public void navigateToPickColorActivity(Contact contact) {
        Intent intent = new Intent(this, ColorPickerActivity.class);
        intent.putExtra(Keys.CONTACT, Parcels.wrap(contact));
        startActivityForResult(intent, Requests.PICK_COLOR);
    }

    @Override
    public void setColor(Contact contact, @ColorInt int color) {
        adapter.setColor(contact, color);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Requests.PICK_COLOR && resultCode == RESULT_OK) {
            Contact contact = Parcels.unwrap(data.getParcelableExtra(Keys.CONTACT));
            int color = data.getIntExtra(Keys.COLOR, 0);
            presenter.onColorPicked(contact, color);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private AppComponent component() {
        return ((App) getApplication()).getComponent();
    }

}
