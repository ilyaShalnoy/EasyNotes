package com.example.notes.easynotes.presentation.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.notes.easynotes.MyApp;
import com.example.notes.easynotes.databinding.FragmentCreateNotesBinding;
import com.example.notes.easynotes.model.Notes;
import com.example.notes.easynotes.presentation.NotesViewModel;
import com.example.notes.easynotes.presentation.fragments.BaseFragment;

import javax.inject.Inject;


public class CreateNotesFragment extends BaseFragment<FragmentCreateNotesBinding> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private NotesViewModel notesViewModel;
    private String title, description;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MyApp) getActivity().getApplication())
                .getAppComponent()
                .inject(this);

        notesViewModel = new ViewModelProvider(this, viewModelFactory).get(NotesViewModel.class);

    }

    @Override
    protected FragmentCreateNotesBinding initBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentCreateNotesBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.saveNoteButton.setOnClickListener(v -> {
            title = binding.notesTitle.getText().toString();
            description = binding.notesDescription.getText().toString();

            createNotes(title, description);
        });
    }

    private void createNotes(String title, String description) {
        Notes notes = new Notes();
        notes.notesTitle = title;
        notes.notesDescription = description;

        notesViewModel.insertNotes(notes);

        Toast.makeText(requireContext(), "Note successfully created", Toast.LENGTH_SHORT).show();

    }
}