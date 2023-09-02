package com.example.notesappmvvmroomdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesappmvvmroomdb.Activity.InsertNoteActivity;
import com.example.notesappmvvmroomdb.Adapter.NotesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
FloatingActionButton newNotesFloatingActionButton;
NotesViewModel notesViewModel;
RecyclerView notesRecyclerView;
TextView lToHTextView,hToLTextView,noFilterTextView;
List<Notes> filterNotesList;
NotesAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newNotesFloatingActionButton=findViewById(R.id.newNotesFloatingActionButton);
        notesRecyclerView=findViewById(R.id.notesRecyclerView);
        notesViewModel= ViewModelProviders.of(this).get(NotesViewModel.class);
        newNotesFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InsertNoteActivity.class));
            }
        });
        notesViewModel.getAllNotes.observe(this,notes -> {
            notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            adapter=new NotesAdapter(MainActivity.this,notes);
            notesRecyclerView.setAdapter(adapter);
            filterNotesList=notes;
        });

        lToHTextView=findViewById(R.id.lToHTextView);
        hToLTextView=findViewById(R.id.hToLTextView);
        noFilterTextView=findViewById(R.id.noFilterTextView);
        noFilterTextView.setBackgroundResource(R.drawable.filter_selected_shape);
        lToHTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundResource(R.drawable.filter_selected_shape);
                noFilterTextView.setBackgroundResource(R.drawable.filter_un_shape);
                hToLTextView.setBackgroundResource(R.drawable.filter_un_shape);
                notesViewModel.priorityLToH.observe(MainActivity.this,notes -> {
                    notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    adapter=new NotesAdapter(MainActivity.this,notes);
                    notesRecyclerView.setAdapter(adapter);
                    filterNotesList=notes;
                });
            }
        });
        hToLTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundResource(R.drawable.filter_selected_shape);
                noFilterTextView.setBackgroundResource(R.drawable.filter_un_shape);
                lToHTextView.setBackgroundResource(R.drawable.filter_un_shape);
                notesViewModel.priorityHToL.observe(MainActivity.this,notes -> {
                    notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    adapter=new NotesAdapter(MainActivity.this,notes);
                    notesRecyclerView.setAdapter(adapter);
                    filterNotesList=notes;
                });
            }
        });
        noFilterTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setBackgroundResource(R.drawable.filter_selected_shape);
                lToHTextView.setBackgroundResource(R.drawable.filter_un_shape);
                hToLTextView.setBackgroundResource(R.drawable.filter_un_shape);
                notesViewModel.getAllNotes.observe(MainActivity.this,notes -> {
                    notesRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    adapter=new NotesAdapter(MainActivity.this,notes);
                    notesRecyclerView.setAdapter(adapter);
                    filterNotesList=notes;
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_bar,menu);
        MenuItem menuItem=menu.findItem(R.id.app_bar_search);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Notes here...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                NotesFilter(s);
                return false;
            }
        });
        return true;
    }

    private void NotesFilter(String s) {//s is the text that we are typing in search bar
        //Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        ArrayList<Notes> filterName=new ArrayList<>();
        for(Notes notes: this.filterNotesList){
            if (notes != null) {
                if (notes.notesTitle != null && notes.notesSubTitle != null) {
                    if (notes.notesTitle.contains(s) || notes.notesSubTitle.contains(s)) {
                        filterName.add(notes);
                    }
                }
            }
        }
        this.adapter.searchNotes(filterName);
    }
}