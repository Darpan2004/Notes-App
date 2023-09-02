package com.example.notesappmvvmroomdb;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {
    public NotesRepository notesRepository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> priorityLToH;
    public LiveData<List<Notes>> priorityHToL;
    public NotesViewModel(Application application) {
        super(application);
         notesRepository=new NotesRepository(application);
        getAllNotes=notesRepository.getAllNotes;
        priorityHToL=notesRepository.hightoLow;
        priorityLToH=notesRepository.lowtoHigh;
    }
  public   void insertNote(Notes notes){
        notesRepository.insertNotes(notes);
    }
   public void deleteNote(int id){
        notesRepository.deleteNote(id);
    }
   public void updateNote(Notes notes){
        notesRepository.updateNotes(notes);
    }

}
