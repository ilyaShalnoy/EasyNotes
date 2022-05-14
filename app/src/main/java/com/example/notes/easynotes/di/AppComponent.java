package com.example.notes.easynotes.di;

import android.app.Application;

import com.example.notes.easynotes.presentation.fragments.CreateNotesFragment;
import com.example.notes.easynotes.presentation.fragments.UpdateNotesFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RoomModule.class})
public interface AppComponent {

    void inject(CreateNotesFragment createNotesFragment);
    void inject(UpdateNotesFragment updateNotesFragment);

    Application application();
}
