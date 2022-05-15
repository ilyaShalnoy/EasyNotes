package com.example.notes.easynotes.presentation.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notes.easynotes.R;
import com.example.notes.easynotes.databinding.ItemNoteBinding;
import com.example.notes.easynotes.model.Notes;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private LayoutInflater layoutInflater;

    List<Notes> notes;

    public NotesAdapter(List<Notes> notes) {
        this.notes = notes;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotesViewHolder(ItemNoteBinding.inflate(layoutInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.bind(position, notes);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        layoutInflater = LayoutInflater.from(recyclerView.getContext());
    }

    static class NotesViewHolder extends RecyclerView.ViewHolder {

        private ItemNoteBinding binding;

        public NotesViewHolder(ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(int position, List<Notes> notes) {
            Notes note = notes.get(position);

            binding.notesTitle.setText(note.notesTitle);
            binding.notesDescription.setText(note.notesDescription);
            binding.notesDate.setText(note.notesDate);

            Context context = itemView.getContext();

            if (note.notesPriority.equals("1")) {
                binding.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.greenPriority));
            } else if (note.notesPriority.equals("2")) {
                binding.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.yellowPriority));
            } else {
                binding.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.redPriority));
            }
        }
    }

}
