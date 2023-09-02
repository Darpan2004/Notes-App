package com.example.notesappmvvmroomdb.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.notesappmvvmroomdb.Notes;
import com.example.notesappmvvmroomdb.NotesViewModel;
import com.example.notesappmvvmroomdb.R;
import com.example.notesappmvvmroomdb.databinding.ActivityInsertNoteBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InsertNoteActivity extends AppCompatActivity {
    ActivityInsertNoteBinding binding;
    NotesViewModel notesViewModel;
    String priority="1" ;
    String title;
    String subtitle;
    String notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInsertNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);

        binding.greenPriorityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority="1";
                binding.greenPriorityButton.setImageResource(R.drawable.baseline_done_24);
                binding.yellowPriorityButton.setImageResource(0);
                binding.redPriorityButton.setImageResource(0);
            }
        });
        binding.yellowPriorityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                priority="2";
                binding.yellowPriorityButton.setImageResource(R.drawable.baseline_done_24);
                binding.redPriorityButton.setImageResource(0);
                binding.greenPriorityButton.setImageResource(0);
            }
        });
    binding.redPriorityButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            priority="3";
            binding.yellowPriorityButton.setImageResource(0);
            binding.redPriorityButton.setImageResource(R.drawable.baseline_done_24);
            binding.greenPriorityButton.setImageResource(0);

        }
    });
        binding.doneNoteFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title=binding.notesTitleEditText.getText().toString();
                subtitle=binding.notesSubtitileEditText.getText().toString();
                notes=binding.notesData.getText().toString();
                CreateNotes(title,subtitle,notes);
            }
        });

    }

    private void CreateNotes(String title, String subtitle, String notes) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        Notes notes1=new Notes();
        notes1.notesTitle=title;
        notes1.notesSubTitle=subtitle;
        notes1.notes=notes;
        notes1.notesPriority=priority;
        notes1.notesDate=dateFormat.format(date);
        notesViewModel.insertNote(notes1);
        Toast.makeText(this, "Note created Successfully", Toast.LENGTH_SHORT).show();
        finish();//user will  return to mainactivity
    }
}