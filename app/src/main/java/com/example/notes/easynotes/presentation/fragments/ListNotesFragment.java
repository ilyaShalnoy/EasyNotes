package com.example.notes.easynotes.presentation.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notes.easynotes.MyApp;
import com.example.notes.easynotes.R;
import com.example.notes.easynotes.databinding.FragmentListNotesBinding;
import com.example.notes.easynotes.model.Notes;
import com.example.notes.easynotes.presentation.NotesViewModel;
import com.example.notes.easynotes.presentation.fragments.BaseFragment;

import java.util.List;

import javax.inject.Inject;


public class ListNotesFragment extends BaseFragment<FragmentListNotesBinding> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private NotesViewModel notesViewModel;
    private NotesAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MyApp) getActivity().getApplication())
                .getAppComponent()
                .inject(this);

        notesViewModel = new ViewModelProvider(this, viewModelFactory).get(NotesViewModel.class);
    }

    @Override
    protected FragmentListNotesBinding initBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentListNotesBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addNoteButton.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_listNotesFragment_to_createNotesFragment);
        });

        notesViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes -> {
            if (!notes.isEmpty()) {
                binding.createNoteImage.setVisibility(View.INVISIBLE);
                binding.createNoteText.setVisibility(View.INVISIBLE);
            }
            adapter = new NotesAdapter(notes);
            binding.notesList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            binding.notesList.setAdapter(adapter);
            binding.notesList.setHasFixedSize(true);
        });

    }


}