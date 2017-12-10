package pl.laskowski.marcin.contactapp.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.joda.time.LocalDate;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class Contact {

    //==============================================================================================
    // FIELDS
    //==============================================================================================

    @NonNull
    private final String firstName;
    @NonNull
    private final String lastName;
    @Nullable
    private final String avatarUrl;
    @NonNull
    private final LocalDate birthdayDate;
    @Nullable
    private final String description;

    //==============================================================================================
    // CONSTRUCTOR
    //==============================================================================================

    public Contact(
            @NonNull String firstName,
            @NonNull String lastName,
            @Nullable String avatarUrl,
            @NonNull LocalDate birthdayDate,
            @Nullable String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarUrl = avatarUrl;
        this.birthdayDate = birthdayDate;
        this.description = description;
    }

    //==============================================================================================
    // PUBLIC METHODS
    //==============================================================================================

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    @Nullable
    public String getAvatarUrl() {
        return avatarUrl;
    }

    @NonNull
    public LocalDate getBirthdayDate() {
        return birthdayDate;
    }

    @Nullable
    public String getDescription() {
        return description;
    }

    //==============================================================================================
    // UTIL METHODS
    //==============================================================================================

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;

        Contact contact = (Contact) o;

        if (!firstName.equals(contact.firstName)) return false;
        if (!lastName.equals(contact.lastName)) return false;
        if (avatarUrl != null ? !avatarUrl.equals(contact.avatarUrl) : contact.avatarUrl != null)
            return false;
        if (!birthdayDate.equals(contact.birthdayDate)) return false;
        return description != null ? description.equals(contact.description) : contact.description == null;
    }

    @Override
    public int hashCode() {
        int result = firstName.hashCode();
        result = 31 * result + lastName.hashCode();
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        result = 31 * result + birthdayDate.hashCode();
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", birthdayDate=" + birthdayDate +
                ", description='" + description + '\'' +
                '}';
    }

    public boolean hasDescription() {
        return description != null;
    }
}
