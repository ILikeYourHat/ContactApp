package pl.laskowski.marcin.contactapp.repository.data;

import com.squareup.moshi.JsonDataException;

import org.joda.time.LocalDate;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import pl.laskowski.marcin.contactapp.model.Contact;

import static org.junit.Assert.assertEquals;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class ContactParserTest {

    private ContactParser parser = new ContactParser();

    @Test
    public void shouldDeserializeNormalJson() {
        ContactJson contactJson = new ContactJsonBuilder()
                .firstName("FirstName")
                .lastName("LastName")
                .bDay("21/09/1999")
                .description("something")
                .avatar1("avatarUrl")
                .build();

        Contact contact = new Contact(
                "FirstName",
                "LastName",
                "avatarUrl",
                new LocalDate(1999,9,21),
                "something");

        assertEquals(contact, parser.fromJson(contactJson));
    }

    @Test
    public void shouldDeserializeMissingDescriptionAndAvatar() {
        ContactJson contactJson = new ContactJsonBuilder()
                .firstName("FirstName")
                .lastName("LastName")
                .bDay("21/09/1999")
                .build();

        Contact contact = new Contact(
                "FirstName",
                "LastName",
                null,
                new LocalDate(1999,9,21),
                null);

        assertEquals(contact, parser.fromJson(contactJson));
    }

    @Test
    public void shouldDeserializeAvatarFromOtherField() {
        ContactJson contactJson = new ContactJsonBuilder()
                .firstName("FirstName")
                .lastName("LastName")
                .bDay("21/09/1999")
                .avatar2("avatarUrl")
                .build();

        Contact contact = new Contact(
                "FirstName",
                "LastName",
                "avatarUrl",
                new LocalDate(1999,9,21),
                null);

        assertEquals(contact, parser.fromJson(contactJson));
    }

    @Test
    public void shouldDeserializeMultipleDateFormats() {
        ContactJson contactJson1 = new ContactJsonBuilder()
                .firstName("FirstName")
                .lastName("LastName")
                .bDay("21/09/1999")
                .build();

        ContactJson contactJson2 = new ContactJsonBuilder()
                .firstName("FirstName")
                .lastName("LastName")
                .bDay("Tue Sep 21 1999 10:25:54.200")
                .build();

        ContactJson contactJson3 = new ContactJsonBuilder()
                .firstName("FirstName")
                .lastName("LastName")
                .bDay("Tue Sep 21 1999 00:00:00")
                .build();

        Contact contact = new Contact(
                "FirstName",
                "LastName",
                null,
                new LocalDate(1999,9,21),
                null);

        assertEquals(contact, parser.fromJson(contactJson1));
        assertEquals(contact, parser.fromJson(contactJson2));
        assertEquals(contact, parser.fromJson(contactJson3));
    }

    @Test(expected = JsonDataException.class)
    public void shouldFailIfFirstNameIsMissing() {
        ContactJson contactJson = new ContactJsonBuilder()
                .lastName("LastName")
                .bDay("21/09/1999")
                .build();

        parser.fromJson(contactJson);
    }

    @Test(expected = JsonDataException.class)
    public void shouldFailIfLastNameIsMissing() {
        ContactJson contactJson = new ContactJsonBuilder()
                .firstName("FirstName")
                .bDay("21/09/1999")
                .build();

        parser.fromJson(contactJson);
    }

    @Test(expected = JsonDataException.class)
    public void shouldFailIfBirthdayIsMissing() {
        ContactJson contactJson = new ContactJsonBuilder()
                .firstName("FirstName")
                .lastName("LastName")
                .build();

        parser.fromJson(contactJson);
    }

    @Test
    public void shouldDeserializeCategoryList(){
        List<Contact> expected = Collections.singletonList(new Contact(
                "FirstName",
                "LastName",
                null,
                new LocalDate(1999,9,21),
                null));

        CategoryJson categoryJson = new CategoryJson();
        categoryJson.name = "Contact";
        categoryJson.items = Collections.singletonList(new ContactJsonBuilder()
                .firstName("FirstName")
                .lastName("LastName")
                .bDay("21/09/1999")
                .build());

        List<CategoryJson> list = Collections.singletonList(categoryJson);

        assertEquals(expected, parser.fromJson(list));
    }

    @Test
    public void shouldDeserializeOnlyKnownCategories(){
        List<Contact> expected = Collections.emptyList();

        CategoryJson categoryJson = new CategoryJson();
        categoryJson.name = "Unknown";
        categoryJson.items = Collections.singletonList(new ContactJsonBuilder()
                .firstName("FirstName")
                .lastName("LastName")
                .bDay("21/09/1999")
                .build());

        List<CategoryJson> list = Collections.singletonList(categoryJson);

        assertEquals(expected, parser.fromJson(list));
    }

}
