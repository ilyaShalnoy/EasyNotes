package com.example.notes.easynotes.presentation.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
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

        private final ItemNoteBinding binding;

        public NotesViewHolder(ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(int position, List<Notes> notes) {
            Notes note = notes.get(position);
            Context context = itemView.getContext();

            binding.notesTitle.setText(note.notesTitle);
            binding.notesDescription.setText(note.notesDescription);
            binding.notesDate.setText(note.notesDate);

            switch (note.notesPriority) {
                case "1":
                    binding.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.greenPriority));
                    break;
                case "2":
                    binding.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.yellowPriority));
                    break;
                case "3":
                    binding.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.redPriority));
            }

            itemView.setOnClickListener(v -> {
                final ListNotesFragmentDirections.ActionListNotesFragmentToUpdateNotesFragment action =
                        ListNotesFragmentDirections.actionListNotesFragmentToUpdateNotesFragment(note);

                Navigation.findNavController(v).navigate(action);
            });
        }
    }

}
