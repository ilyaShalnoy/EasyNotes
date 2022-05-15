package com.example.notes.easynotes.presentation.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.notes.easynotes.MyApp;
import com.example.notes.easynotes.R;
import com.example.notes.easynotes.databinding.FragmentListNotesBinding;
import com.example.notes.easynotes.model.Notes;
import com.example.notes.easynotes.presentation.NotesViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class ListNotesFragment extends BaseFragment<FragmentListNotesBinding> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private NotesViewModel notesViewModel;
    private NotesAdapter adapter;
    private List<Notes> filterNotesList;

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
    public void onResume() {
        super.onResume();
        //close searchView on return
        binding.searchNotesBtn.setIconified(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.addNoteButton.setOnClickListener(v ->  {
            Navigation.findNavController(v).navigate(R.id.action_listNotesFragment_to_createNotesFragment);
        });

        //filter high
        binding.filterHigh.setOnClickListener(v -> {
            binding.filterHigh.setBackgroundResource(R.drawable.bg_btn_filter_selected);
            binding.filterMedium.setBackgroundResource(R.drawable.bg_btn_filter);
            binding.filterLow.setBackgroundResource(R.drawable.bg_btn_filter);

            notesViewModel.getHighNotes().observe(getViewLifecycleOwner(), notes -> {
                filterNotesList = notes;
                setAdapter(notes);
            });
        });

        //filter medium
        binding.filterMedium.setOnClickListener(v -> {
            binding.filterHigh.setBackgroundResource(R.drawable.bg_btn_filter);
            binding.filterMedium.setBackgroundResource(R.drawable.bg_btn_filter_selected);
            binding.filterLow.setBackgroundResource(R.drawable.bg_btn_filter);

            notesViewModel.getMediumNotes().observe(getViewLifecycleOwner(), notes -> {
                filterNotesList = notes;
                setAdapter(notes);
            });
        });

        //filter low
        binding.filterLow.setOnClickListener(v -> {
            binding.filterHigh.setBackgroundResource(R.drawable.bg_btn_filter);
            binding.filterMedium.setBackgroundResource(R.drawable.bg_btn_filter);
            binding.filterLow.setBackgroundResource(R.drawable.bg_btn_filter_selected);

            notesViewModel.getLowNotes().observe(getViewLifecycleOwner(), notes -> {
                filterNotesList = notes;
                setAdapter(notes);
            });
        });

        //filter get all notes
        binding.filterAllNotes.setOnClickListener(v -> {
            notesViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes -> {
                filterNotesList = notes;
                setAdapter(notes);
            });
        });

        //get all notes
        notesViewModel.getAllNotes().observe(getViewLifecycleOwner(), notes -> {
            if (!notes.isEmpty()) {
                binding.createNoteImage.setVisibility(View.INVISIBLE);
                binding.createNoteText.setVisibility(View.INVISIBLE);
            }
            setAdapter(notes);
            filterNotesList = notes;
        });

        binding.searchNotesBtn.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    binding.titleApp.setVisibility(View.GONE);
                } else {
                    binding.titleApp.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.searchNotesBtn.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                notesFilter(newText);
                return false;
            }
        });
    }

    private void notesFilter(String newText) {

        ArrayList<Notes> newFilterNotesList = new ArrayList<>();
        for (Notes note : this.filterNotesList) {
            if (note.notesTitle.contains(newText)) {
                newFilterNotesList.add(note);
            }
            adapter.searchNotes(newFilterNotesList);
        }

    }

    private void setAdapter(List<Notes> notes) {
        adapter = new NotesAdapter(notes);
        binding.notesList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.notesList.setAdapter(adapter);
        binding.notesList.setHasFixedSize(true);
    }
}