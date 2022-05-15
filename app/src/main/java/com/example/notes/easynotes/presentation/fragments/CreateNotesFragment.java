package com.example.notes.easynotes.presentation.fragments;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.notes.easynotes.MyApp;
import com.example.notes.easynotes.R;
import com.example.notes.easynotes.databinding.FragmentCreateNotesBinding;
import com.example.notes.easynotes.model.Notes;
import com.example.notes.easynotes.presentation.NotesViewModel;

import java.util.Date;

import javax.inject.Inject;


public class CreateNotesFragment extends BaseFragment<FragmentCreateNotesBinding> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private NotesViewModel notesViewModel;
    private String title, description;
    private String priority = "1";


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

        binding.greenPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(R.drawable.ic_done);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            priority = "1";
        });
        binding.yellowPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.ic_done);
            binding.redPriority.setImageResource(0);
            priority = "2";
        });
        binding.redPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.ic_done);
            priority = "3";
        });
    }

    private void createNotes(String title, String description) {
        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, YYYY", date.getTime());

        Notes notes = new Notes();
        notes.notesTitle = title;
        notes.notesDescription = description;
        notes.notesDate = sequence.toString();
        notes.notesPriority = priority;

        notesViewModel.insertNotes(notes);

        Toast.makeText(requireContext(), "Note successfully created", Toast.LENGTH_SHORT).show();

    }
}