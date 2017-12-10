package pl.laskowski.marcin.contactapp.repository.data;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class ContactJsonBuilder {

    private ContactJson json = new ContactJson();

    public ContactJsonBuilder firstName(String firstName) {
        json.firstName = firstName;
        return this;
    }

    public ContactJsonBuilder lastName(String lastName) {
        json.lastName = lastName;
        return this;
    }

    public ContactJsonBuilder avatar1(String avatar1) {
        json.avatar1 = avatar1;
        return this;
    }

    public ContactJsonBuilder avatar2(String avatar2) {
        json.avatar2 = avatar2;
        return this;
    }

    public ContactJsonBuilder description(String description) {
        json.description = description;
        return this;
    }

    public ContactJsonBuilder bDay(String bDay) {
        json.bDay = bDay;
        return this;
    }

    public ContactJson build() {
        return json;
    }

}
