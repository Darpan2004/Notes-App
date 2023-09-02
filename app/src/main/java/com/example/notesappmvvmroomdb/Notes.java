package com.example.notesappmvvmroomdb;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Notes_Database")
public class Notes {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "notesTitle")
    public  String notesTitle;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public String getNotesSubTitle() {
        return notesSubTitle;
    }

    public void setNotesSubTitle(String notesSubTitle) {
        this.notesSubTitle = notesSubTitle;
    }

    public String getNotesDate() {
        return notesDate;
    }

    public void setNotesDate(String notesDate) {
        this.notesDate = notesDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getNotesPriority() {
        return notesPriority;
    }

    public void setNotesPriority(String notesPriority) {
        this.notesPriority = notesPriority;
    }

    @ColumnInfo(name = "notesSubTitle")
    public String notesSubTitle;
    @ColumnInfo(name="notesDate")
    public String notesDate;
    @ColumnInfo(name = "notes")
    public String notes;
    @ColumnInfo(name = "notesPriority")
    public String notesPriority;
}
