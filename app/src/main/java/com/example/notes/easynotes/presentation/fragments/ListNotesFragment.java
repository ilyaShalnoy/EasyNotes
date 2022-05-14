package com.example.notes.easynotes.presentation.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.notes.easynotes.R;
import com.example.notes.easynotes.databinding.FragmentListNotesBinding;
import com.example.notes.easynotes.presentation.fragments.BaseFragment;


public class ListNotesFragment extends BaseFragment<FragmentListNotesBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }
}