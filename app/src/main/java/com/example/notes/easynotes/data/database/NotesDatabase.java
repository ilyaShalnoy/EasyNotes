package com.example.notes.easynotes.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.notes.easynotes.model.Notes;

@Database(entities = {Notes.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {

    public abstract NotesDao notesDao();
}
