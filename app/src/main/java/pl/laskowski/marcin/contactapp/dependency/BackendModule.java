package pl.laskowski.marcin.contactapp.dependency;

import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;
import pl.laskowski.marcin.contactapp.repository.Backend;
import pl.laskowski.marcin.contactapp.repository.data.ContactParser;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

@Module
public class BackendModule {

    private final String url;

    public BackendModule(String url) {
        this.url = url;
    }

    @Provides
    public Backend providesBackend(Retrofit retrofit) {
        return retrofit.create(Backend.class);
    }

    @Provides
    public Retrofit providesRetrofit(Moshi moshi) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public Moshi providesMoshi() {
        return new Moshi.Builder()
                .add(new ContactParser())
                .build();
    }

}
