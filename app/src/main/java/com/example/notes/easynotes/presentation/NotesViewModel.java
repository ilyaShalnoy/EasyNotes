package com.example.notes.easynotes.presentation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.notes.easynotes.data.repository.NotesRepository;
import com.example.notes.easynotes.model.Notes;

import java.util.List;

public class NotesViewModel extends ViewModel {

    private NotesRepository repository;

    public NotesViewModel(NotesRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Notes>> getAllNotes() {
        return repository.getAllNotes();
    }

     public void insertNotes(Notes notes) {
        repository.insertNotes(notes);
     }

     public void deleteNotes(int id) {
        repository.deleteNotes(id);
     }

     public void updateNotes(Notes notes) {
        repository.updateNotes(notes);
     }
}
