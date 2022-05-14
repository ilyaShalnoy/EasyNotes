package com.example.notes.easynotes.di;

import android.app.Application;

import com.example.notes.easynotes.presentation.NotesViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RoomModule.class})
public interface AppComponent {

    Application application();
}
