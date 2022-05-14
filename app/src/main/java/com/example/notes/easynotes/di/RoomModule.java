package com.example.notes.easynotes.di;

import android.app.Application;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Room;

import com.example.notes.easynotes.data.database.NotesDao;
import com.example.notes.easynotes.data.database.NotesDatabase;
import com.example.notes.easynotes.data.repository.NotesRepository;
import com.example.notes.easynotes.presentation.CustomViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    private final NotesDatabase database;

    public RoomModule(Application application) {
        this.database = Room.databaseBuilder(
                application,
                NotesDatabase.class,
                "Notes.db"
        ).build();
    }

    @Singleton
    @Provides
    NotesRepository provideListRepository(NotesDao notesDao) {
        return new NotesRepository(notesDao);
    }

    @Singleton
    @Provides
    NotesDao provideNotesData(NotesDatabase database) {
        return database.notesDao();
    }

    @Singleton
    @Provides
    NotesDatabase provideNotesDatabase(Application application) {
        return database;
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(NotesRepository repository) {
        return new CustomViewModelFactory(repository);
    }

}
