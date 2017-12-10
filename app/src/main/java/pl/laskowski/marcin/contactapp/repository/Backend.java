package pl.laskowski.marcin.contactapp.repository;

import java.util.List;

import io.reactivex.Single;
import pl.laskowski.marcin.contactapp.repository.data.CategoryJson;
import retrofit2.http.GET;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public interface Backend {

    @GET("zadanie/zadanie.json")
    Single<List<CategoryJson>> getContacts();

}
