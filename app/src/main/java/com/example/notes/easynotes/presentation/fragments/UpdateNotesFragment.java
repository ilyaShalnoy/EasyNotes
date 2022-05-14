package com.example.notes.easynotes.presentation.fragments;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.notes.easynotes.databinding.FragmentUpdateNotesBinding;
import com.example.notes.easynotes.presentation.fragments.BaseFragment;

public class UpdateNotesFragment extends BaseFragment<FragmentUpdateNotesBinding> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected FragmentUpdateNotesBinding initBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentUpdateNotesBinding.inflate(inflater, container, false);
    }


}