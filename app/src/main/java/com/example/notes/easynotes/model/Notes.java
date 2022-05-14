package com.example.notes.easynotes.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Database")
public class Notes {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "notes_title")
    public String notesTitle;

    @ColumnInfo(name = "notes_description")
    public String notesDescription;

    @ColumnInfo(name = "notes_date")
    public String notesDate;

    @ColumnInfo(name = "notes_priority")
    public String notesPriority;

    @ColumnInfo(name = "notes_size_font")
    public String notesSizeFont;

}
