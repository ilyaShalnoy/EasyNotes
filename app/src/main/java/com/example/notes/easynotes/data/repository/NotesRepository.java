package com.example.notes.easynotes.data.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.notes.easynotes.data.database.NotesDao;
import com.example.notes.easynotes.data.database.NotesDatabase;
import com.example.notes.easynotes.model.Notes;

import java.util.List;

import javax.inject.Inject;

public class NotesRepository {

    private final NotesDao notesDao;

    @Inject
    public NotesRepository(NotesDao notesDao) {
        this.notesDao = notesDao;
    }

    public LiveData<List<Notes>> getAllNotes() {
        return notesDao.getAllNotes();
    }

    public LiveData<List<Notes>> getHighNotes() {
        return notesDao.getHighNotes();
    }

    public LiveData<List<Notes>> getMediumNotes() {
        return notesDao.getMediumNotes();
    }

    public LiveData<List<Notes>> getLowNotes() {
        return notesDao.getLowNotes();
    }

    public void insertNotes(Notes notes) {
        notesDao.insertNotes(notes);
    }

    public void deleteNotes(int id) {
        notesDao.deleteNotes(id);
    }

    public void updateNotes(Notes notes) {
        notesDao.updateNotes(notes);
    }

}
