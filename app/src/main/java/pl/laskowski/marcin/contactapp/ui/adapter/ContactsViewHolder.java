package pl.laskowski.marcin.contactapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.joda.time.LocalDate;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.laskowski.marcin.contactapp.R;
import pl.laskowski.marcin.contactapp.model.Contact;
import pl.laskowski.marcin.contactapp.ui.utils.CircleTransform;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */
public class ContactsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.itemContact_ivAvatar)
    ImageView ivAvatar;
    @BindView(R.id.itemContact_tvFirstName)
    TextView tvFirstName;
    @BindView(R.id.itemContact_tvLastName)
    TextView tvLastName;
    @BindView(R.id.itemContact_tvBirthdayDate)
    TextView tvBithdayDate;
    @BindView(R.id.itemContact_tvDescription)
    TextView tvDescription;

    public ContactsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Contact contact) {
        setFirstName(contact.getFirstName());
        setLastName(contact.getLastName());
        setAvatar(contact.getAvatarUrl());
        setBirthdayDate(contact.getBirthdayDate());
        setDescription(contact.getDescription());
    }

    private void setAvatar(String avatarUrl) {
        Picasso.with(itemView.getContext())
                .load(avatarUrl)
                .transform(new CircleTransform())
                .placeholder(R.drawable.placeholder_image)
                .into(ivAvatar);
    }

    private void setFirstName(String firstName) {
        tvFirstName.setText(firstName);
    }

    private void setLastName(String lastName) {
        tvLastName.setText(lastName);
    }

    private void setBirthdayDate(LocalDate birthdayDate) {
        tvBithdayDate.setText(birthdayDate.toString("dd.MM.yyyy"));
    }

    private void setDescription(String description) {
        if (description != null) {
            tvDescription.setVisibility(View.VISIBLE);
            tvDescription.setText(description);
        } else {
            tvDescription.setVisibility(View.GONE);
        }
    }

}