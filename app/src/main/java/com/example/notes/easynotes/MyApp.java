package com.example.notes.easynotes;

import android.app.Application;

import com.example.notes.easynotes.di.AppComponent;
import com.example.notes.easynotes.di.AppModule;
import com.example.notes.easynotes.di.DaggerAppComponent;

public class MyApp extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
