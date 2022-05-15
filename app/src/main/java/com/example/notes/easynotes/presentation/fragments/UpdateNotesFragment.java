package com.example.notes.easynotes.presentation.fragments;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.notes.easynotes.MyApp;
import com.example.notes.easynotes.R;
import com.example.notes.easynotes.databinding.FragmentUpdateNotesBinding;
import com.example.notes.easynotes.model.Notes;
import com.example.notes.easynotes.presentation.NotesViewModel;

import java.util.Date;

import javax.inject.Inject;

public class UpdateNotesFragment extends BaseFragment<FragmentUpdateNotesBinding> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private String edPriority = "1";
    private NotesViewModel viewModel;
    private Notes OldNote;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((MyApp) getActivity().getApplication())
                .getAppComponent()
                .inject(this);

        viewModel = new ViewModelProvider(this, viewModelFactory).get(NotesViewModel.class);

    }

    @Override
    protected FragmentUpdateNotesBinding initBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentUpdateNotesBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        final UpdateNotesFragmentArgs data = UpdateNotesFragmentArgs.fromBundle(getArguments());
        OldNote = data.getData();

        binding.updateNotesTitle.setText(OldNote.notesTitle);
        binding.updateNotesDescription.setText(OldNote.notesDescription);

        switch (OldNote.notesPriority) {
            case "1":
                binding.greenPriority.setImageResource(R.drawable.ic_done);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(0);
                break;
            case "2":
                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(R.drawable.ic_done);
                binding.redPriority.setImageResource(0);
                break;
            case "3":
                binding.greenPriority.setImageResource(0);
                binding.yellowPriority.setImageResource(0);
                binding.redPriority.setImageResource(R.drawable.ic_done);
        }

        binding.greenPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(R.drawable.ic_done);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);
            edPriority = "1";
        });
        binding.yellowPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(R.drawable.ic_done);
            binding.redPriority.setImageResource(0);
            edPriority = "2";
        });
        binding.redPriority.setOnClickListener(v -> {
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
            binding.redPriority.setImageResource(R.drawable.ic_done);
            edPriority = "3";
        });

        binding.updateNoteButton.setOnClickListener(v -> {

            String edTitle = binding.updateNotesTitle.getText().toString();
            String edDescription = binding.updateNotesDescription.getText().toString();

            updateNotes(edTitle, edDescription, v);
        });
    }

    private void updateNotes(String edTitle, String edDescription, View view) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());

        Notes updateNotes = new Notes();
        updateNotes.id = OldNote.id;
        updateNotes.notesTitle = edTitle;
        updateNotes.notesDescription = edDescription;
        updateNotes.notesDate = sequence.toString();
        updateNotes.notesPriority = edPriority;

        viewModel.updateNotes(updateNotes);

        Navigation.findNavController(view).popBackStack();
    }
}