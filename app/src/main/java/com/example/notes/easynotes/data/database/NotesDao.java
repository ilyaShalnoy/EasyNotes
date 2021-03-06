package com.example.notes.easynotes.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notes.easynotes.model.Notes;

import java.util.List;

@Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_Database")
    LiveData<List<Notes>> getAllNotes();

    @Query("SELECT * FROM Notes_Database WHERE notes_priority = 3")
    LiveData<List<Notes>> getHighNotes();

    @Query("SELECT * FROM Notes_Database WHERE notes_priority = 2")
    LiveData<List<Notes>> getMediumNotes();

    @Query("SELECT * FROM Notes_Database WHERE notes_priority = 1")
    LiveData<List<Notes>> getLowNotes();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertNotes(Notes... notes);

    @Query("DELETE FROM Notes_Database WHERE id=:id")
    void deleteNotes(int id);

    @Update
    void updateNotes(Notes notes);
}
