package com.example.notes.easynotes.presentation;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.notes.easynotes.data.repository.NotesRepository;

public class CustomViewModelFactory implements ViewModelProvider.Factory {

    private final NotesRepository repository;

    public CustomViewModelFactory(NotesRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NotesViewModel.class)) {
            return (T) new NotesViewModel(repository);
        }

        else throw new IllegalArgumentException("View Model Not Found");
    }
}
