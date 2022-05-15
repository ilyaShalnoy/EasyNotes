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
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Date;

import javax.inject.Inject;

public class UpdateNotesFragment extends BaseFragment<FragmentUpdateNotesBinding> {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private String edPriority = "1";
    private NotesViewModel viewModel;
    private Notes oldNote;

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
        oldNote = data.getData();


        binding.updateNotesTitle.setText(oldNote.notesTitle);
        binding.updateNotesDescription.setText(oldNote.notesDescription);

        switch (oldNote.notesPriority) {
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

        binding.btnDeleteNote.setOnClickListener(v -> {
            deleteNotes(view);
        });

    }

    private void updateNotes(String edTitle, String edDescription, View view) {

        Date date = new Date();
        CharSequence sequence = DateFormat.format("MMMM d, yyyy", date.getTime());

        Notes updateNotes = new Notes();
        updateNotes.id = oldNote.id;
        updateNotes.notesTitle = edTitle;
        updateNotes.notesDescription = edDescription;
        updateNotes.notesDate = sequence.toString();
        updateNotes.notesPriority = edPriority;

        viewModel.updateNotes(updateNotes);

        Navigation.findNavController(view).popBackStack();
    }

    private void deleteNotes(View view) {

        BottomSheetDialog sheetDialog = new BottomSheetDialog(
                requireContext(),
                R.style.AppBottomSheetDialogTheme
        );
        sheetDialog.setContentView(R.layout.dialog_delete_notes);

        final View yesBtnDialog = sheetDialog.findViewById(R.id.dialog_yes);
        final View noBtnDialog = sheetDialog.findViewById(R.id.dialog_no);

        assert yesBtnDialog != null;
        yesBtnDialog.setOnClickListener(v -> {
            viewModel.deleteNotes(oldNote.id);
            Navigation.findNavController(view).navigate(R.id.action_updateNotesFragment_to_listNotesFragment);
            sheetDialog.dismiss();
        });

        assert noBtnDialog != null;
        noBtnDialog.setOnClickListener(v -> {
            sheetDialog.dismiss();
        });

        sheetDialog.show();

    }
}