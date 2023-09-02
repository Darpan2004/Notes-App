package com.example.notesappmvvmroomdb;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDAO {
    @Query("select*from Notes_Database")
    public LiveData<List<Notes>> getallNotes();
    @Insert
    public void insertNotes(Notes notes);
    @Update
    public void updateNotes(Notes notes);
    @Query("delete from Notes_Database where id=:id")
    public  void deleteNotes(int id);
    @Query("select *from Notes_Database order by notesPriority desc")
        public LiveData<List<Notes>> highToLow();
    @Query("select *from Notes_Database order by notesPriority asc")
    public LiveData<List<Notes>> lowToHigh();
}
