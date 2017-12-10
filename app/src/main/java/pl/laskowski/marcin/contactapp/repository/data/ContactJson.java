package pl.laskowski.marcin.contactapp.repository.data;

import com.squareup.moshi.Json;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class ContactJson {

    @Json(name = "firstNamE")
    public String firstName;
    @Json(name = "last_name")
    public String lastName;
    @Json(name = "bDay")
    public String bDay;
    @Json(name = "description")
    public String description;
    @Json(name = "avatar")
    public String avatar1;
    @Json(name = "avataR")
    public String avatar2;

}
