package pl.laskowski.marcin.contactapp;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

import pl.laskowski.marcin.contactapp.dependency.AppComponent;
import pl.laskowski.marcin.contactapp.dependency.BackendModule;
import pl.laskowski.marcin.contactapp.dependency.DaggerAppComponent;

/**
 * Created by Marcin Laskowski.
 * Senfino 2017
 */

public class App extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        initComponent();
    }

    private void initComponent() {
        if (component == null) {
            component = DaggerAppComponent.builder()
                    .backendModule(new BackendModule("http://smagabakery.com"))
                    .build();
        }
    }

    public AppComponent getComponent() {
        return component;
    }

}
