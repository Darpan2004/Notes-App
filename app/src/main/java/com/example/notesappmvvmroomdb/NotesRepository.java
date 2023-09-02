package com.example.notesappmvvmroomdb;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NotesRepository {
    public NotesDAO notesDAO;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> hightoLow;
    public LiveData<List<Notes>> lowtoHigh;
    public NotesRepository(Application application){
        NotesDatabase notesDatabase = NotesDatabase.getInstance(application);
        notesDAO= notesDatabase.notesDAO();
        getAllNotes=notesDAO.getallNotes();
        lowtoHigh=notesDAO.lowToHigh();
        hightoLow=notesDAO.highToLow();
    }
   public void insertNotes(Notes notes){
        notesDAO.insertNotes(notes);
    }
   public void deleteNote(int id){
        notesDAO.deleteNotes(id);
    }
   public void updateNotes(Notes notes){
        notesDAO.updateNotes(notes);
    }


}
