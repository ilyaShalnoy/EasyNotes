package com.example.notes.easynotes.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.notes.easynotes.data.repository.NotesRepository;
import com.example.notes.easynotes.model.Notes;

import java.util.List;

public class NotesViewModel extends ViewModel {

    private NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;

    public NotesViewModel(NotesRepository repository) {
        this.repository = repository;

        getAllNotes = repository.getAllNotes;
    }

     void insertNotes(Notes notes) {
        repository.insertNotes(notes);
     }

     void deleteNotes(int id) {
        repository.deleteNotes(id);
     }

     void updateNotes(Notes notes) {
        repository.updateNotes(notes);
     }
}