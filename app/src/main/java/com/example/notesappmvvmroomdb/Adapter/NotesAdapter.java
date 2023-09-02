package com.example.notesappmvvmroomdb.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesappmvvmroomdb.Activity.InsertNoteActivity;
import com.example.notesappmvvmroomdb.Activity.UpdateNoteActivity;
import com.example.notesappmvvmroomdb.MainActivity;
import com.example.notesappmvvmroomdb.Notes;
import com.example.notesappmvvmroomdb.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {

    MainActivity mainActivity;
    List<Notes> notes;
    List<Notes> allNotesitem;
    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity=mainActivity;
        this.notes=notes;
        allNotesitem=new ArrayList<>(notes);
    }
    public void searchNotes(List<Notes> filteredName){
        this.notes=filteredName;
        notifyDataSetChanged();
    }

    @Override
    public notesViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.item_notes,parent,false));
    }

    @Override
    public void onBindViewHolder( notesViewHolder holder, int position) {
        Notes note=notes.get(position);
        //getItemId(position);

        if (note != null) {
            if ("1".equals(note.notesPriority)) {
                holder.notesPriorityView.setBackgroundResource(R.drawable.green_shape);
            } else if ("2".equals(note.notesPriority)) {
                holder.notesPriorityView.setBackgroundResource(R.drawable.yellow_shape);
            } else {
                holder.notesPriorityView.setBackgroundResource(R.drawable.red_shape);
            }
        } else {
            Toast.makeText(mainActivity, "sorry", Toast.LENGTH_SHORT).show();
            // Handle the case where 'note' is null, perhaps log an error or provide a default behavior.
        }

        holder.notesTitleTextView.setText(note.notesTitle);
        holder.notesSubTitleTextView.setText(note.notesSubTitle);
        holder.notesDateTextView.setText(note.notesDate);
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                Intent intent = new Intent(view.getContext(), UpdateNoteActivity.class);
                intent.putExtra("oldPriority",note.notesPriority);
                intent.putExtra("oldTitle",holder.notesTitleTextView.getText().toString());
                intent.putExtra("oldSubTitle",holder.notesSubTitleTextView.getText().toString());
                intent.putExtra("oldNote",note.notes);
                intent.putExtra("id",String.valueOf(note.id));
                //Toast.makeText(mainActivity, String.valueOf(note.id), Toast.LENGTH_SHORT).show();
               // Toast.makeText(view.getContext(), id, Toast.LENGTH_SHORT).show();
                view.getContext().startActivity(intent);
                //  Toast.makeText(view.getContext(), "Note created Successfully", Toast.LENGTH_SHORT).show();
                return true;// returning true instead of false, works for me
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class notesViewHolder  extends RecyclerView.ViewHolder{
        TextView notesTitleTextView;
        TextView notesSubTitleTextView;
        TextView notesDateTextView;
        View notesPriorityView;
        public notesViewHolder(View itemView) {
            super(itemView);
            notesTitleTextView=itemView.findViewById(R.id.notesTitleTextView);
            notesSubTitleTextView=itemView.findViewById(R.id.notesSubTItleTextView);
            notesDateTextView=itemView.findViewById(R.id.notesDateTextView);
            notesPriorityView=itemView.findViewById(R.id.notesPriorityView);
        }
    }

}
