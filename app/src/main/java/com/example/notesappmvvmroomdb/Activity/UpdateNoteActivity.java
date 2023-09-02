package com.example.notesappmvvmroomdb.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesappmvvmroomdb.Notes;
import com.example.notesappmvvmroomdb.NotesViewModel;
import com.example.notesappmvvmroomdb.R;
import com.example.notesappmvvmroomdb.databinding.ActivityUpdateNoteBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateNoteActivity extends AppCompatActivity {
    ActivityUpdateNoteBinding binding;
    String priority="1";
    NotesViewModel notesViewModel;
    int id;
    String oldPriority;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);
        Intent intent=getIntent();
        String oldTitle=intent.getStringExtra("oldTitle");
        String oldSubTitle=intent.getStringExtra("oldSubTitle");
        String oldNote=intent.getStringExtra("oldNote");
        oldPriority=intent.getStringExtra("oldPriority");
        if(oldPriority!=null){
            Toast.makeText(this, oldPriority, Toast.LENGTH_SHORT).show();
        if(oldPriority.equals("1")){
            binding.greenPriorityButton.setImageResource(R.drawable.baseline_done_24);
            binding.yellowPriorityButton.setImageResource(0);
            binding.redPriorityButton.setImageResource(0);
        }
        else if(oldPriority.equals("2")){
            binding.yellowPriorityButton.setImageResource(R.drawable.baseline_done_24);
            binding.redPriorityButton.setImageResource(0);
            binding.greenPriorityButton.setImageResource(0);
        }
        else if(oldPriority.equals("3")){
            binding.yellowPriorityButton.setImageResource(0);
            binding.redPriorityButton.setImageResource(R.drawable.baseline_done_24);
            binding.greenPriorityButton.setImageResource(0);
        }}
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
        binding.updateNoteFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTitle=binding.updateTitleEditText.getText().toString();
                String newSubTitle=binding.updateSubTitleEditText.getText().toString();
                String newNote=binding.updateNotesEditText.getText().toString();
                UpdateNotes(newTitle,newSubTitle,newNote,id);
            }
        });

        binding.updateNotesEditText.setText(oldNote);
        binding.updateTitleEditText.setText(oldTitle);
        binding.updateSubTitleEditText.setText(oldSubTitle);
        String s=intent.getStringExtra("id");
         id=Integer.parseInt(s);
    }

    private void UpdateNotes(String newTitle, String newSubTitle, String newNote,int id) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Notes notes2=new Notes();
        notes2.id=id;
        notes2.notesTitle=newTitle;
        notes2.notesSubTitle=newSubTitle;
        notes2.notes=newNote;
        notes2.notesPriority=priority;
        notes2.notesDate=dateFormat.format(date);
        notesViewModel.updateNote(notes2);
        Toast.makeText(this, "Note updated Successfully!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.delete){
            BottomSheetDialog sheetDialog=new BottomSheetDialog(UpdateNoteActivity.this,R.style.BottonSheetStyle);
            View view= LayoutInflater.from(UpdateNoteActivity.this).
                    inflate(R.layout.delete_bottom_sheet,(LinearLayout)findViewById(R.id.bottonSheet));
        sheetDialog.setContentView(view);

        TextView yes,no;
        yes=view.findViewById(R.id.deleteYes);
        no=view.findViewById(R.id.deleteNo);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notesViewModel.deleteNote(id);
                finish();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetDialog.dismiss();
            }
        });
        sheetDialog.show();
        }

        return  true;
    }
}