package pl.laskowski.marcin.contactapp.repository.data;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class CategoryJson {

    @Json(name = "Category Name")
    public String name;
    @Json(name = "items")
    public List<ContactJson> items;

}
