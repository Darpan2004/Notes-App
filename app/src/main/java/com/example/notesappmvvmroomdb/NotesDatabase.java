package com.example.notesappmvvmroomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
@Database(entities = Notes.class,version = 1,exportSchema = false)
public abstract  class NotesDatabase extends RoomDatabase {
    public abstract NotesDAO notesDAO();
    public static NotesDatabase instance;
    public static NotesDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(), NotesDatabase.class,
                    "Notes_Database").allowMainThreadQueries().build();
        }
        return instance;
    }
}
