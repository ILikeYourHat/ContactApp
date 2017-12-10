package pl.laskowski.marcin.contactapp.repository.data;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.JsonDataException;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import pl.laskowski.marcin.contactapp.model.Contact;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class ContactParser {

    private static final String CONTACTS_CATEGORY_NAME = "Contact";
    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormat.forPattern("dd/MM/yyyy"),
            DateTimeFormat.forPattern("EEE MMM dd yyyy HH:mm:ss.SSS").withLocale(Locale.US),
            DateTimeFormat.forPattern("EEE MMM dd yyyy HH:mm:ss").withLocale(Locale.US));

    @FromJson
    List<Contact> fromJson(List<CategoryJson> list) {
        List<Contact> contacts = new ArrayList<>();
        for (CategoryJson categoryJson : list) {
            if (CONTACTS_CATEGORY_NAME.equals(categoryJson.name)) {
                for (ContactJson contactJson : categoryJson.items) {
                    contacts.add(fromJson(contactJson));
                }
            }
        }
        return contacts;
    }

    Contact fromJson(ContactJson contactJson) {
        validate(contactJson);
        return new Contact(
                contactJson.firstName,
                contactJson.lastName,
                contactJson.avatar1 != null ? contactJson.avatar1 : contactJson.avatar2,
                toLocalDate(contactJson.bDay),
                contactJson.description);
    }

    private void validate(ContactJson json) {
        if (json.firstName == null) throw new JsonDataException("firstName is null");
        if (json.lastName == null) throw new JsonDataException("lastName is null");
        if (json.bDay == null) throw new JsonDataException("bDay is null");
    }

    private LocalDate toLocalDate(String s) {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return formatter.parseLocalDate(s);
            } catch (IllegalArgumentException e) {
                // Go on to the next format
            }
        }
        throw new JsonDataException("Cannot parse date " + s);
    }

}
