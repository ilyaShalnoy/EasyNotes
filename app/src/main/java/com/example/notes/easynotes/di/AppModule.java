package com.example.notes.easynotes.di;

import android.app.Application;
import android.content.Context;

import com.example.notes.easynotes.MyApp;
import com.example.notes.easynotes.data.database.NotesDao;
import com.example.notes.easynotes.data.database.NotesDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private MyApp application;

    public AppModule(MyApp application) {
        this.application = application;
    }

    @Provides
    MyApp provideMyApplication(){
        return application;
    }

    @Provides
    Application provideAppContext() {
        return application;
    }

}
