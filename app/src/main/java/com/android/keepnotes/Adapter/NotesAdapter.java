package com.android.keepnotes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.keepnotes.Model.Notes;
import com.android.keepnotes.R;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    Context context;
    ArrayList<Notes> notesArrayList;

    public NotesAdapter(Context context, ArrayList<Notes> notesArrayList) {
        this.context = context;
        this.notesArrayList = notesArrayList;
    }

    @NonNull
    @Override
    public NotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notesviewdesign, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder, int position) {

        Notes notes = notesArrayList.get(position);
        holder.title.setText(notes.getTitle());
        holder.desc.setText(notes.getDescription());

    }

    @Override
    public int getItemCount() {
        return notesArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notesTitle);
            desc = itemView.findViewById(R.id.notesDescription);
        }
    }
}
