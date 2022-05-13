package com.example.notes.easynotes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes.Database")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    String notesTitle;

    String notesDescription;

    String notesDate;

    String notesPriority;

}
