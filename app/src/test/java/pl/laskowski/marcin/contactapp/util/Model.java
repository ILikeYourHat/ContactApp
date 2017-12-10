package pl.laskowski.marcin.contactapp.util;

import org.joda.time.LocalDate;

import java.util.Arrays;
import java.util.List;

import pl.laskowski.marcin.contactapp.model.Contact;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class Model {

    public static final List<Contact> CONTACTS = Arrays.asList(
            new Contact(
                    "Mark",
                    "Cukierberg",
                    null,
                    new LocalDate(1992, 12, 1),
                    "sum description"),
            new Contact(
                    "Bender",
                    "Bender",
                    "http://collider.com/wp-content/uploads/bender_futurama.jpg",
                    new LocalDate(2100, 1, 1),
                    "bmsma"),
            new Contact(
                    "Tester",
                    "Testowy",
                    null,
                    new LocalDate(1992, 12, 1),
                    null)
    );

    public static final Contact CONTACT = new Contact(
            "Mark",
            "Cukierberg",
            null,
            new LocalDate(1992, 12, 1),
            "sum description");

}
