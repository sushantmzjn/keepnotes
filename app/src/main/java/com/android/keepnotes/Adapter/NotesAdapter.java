package com.android.keepnotes.Adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.keepnotes.Model.Notes;
import com.android.keepnotes.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    Context context;
    ArrayList<Notes> notesArrayList;

    Dialog dialog;

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
    public void onBindViewHolder(@NonNull NotesAdapter.ViewHolder holder,int position) {

        Notes notes = notesArrayList.get(position);
        holder.title.setText(notes.getTitle());
        holder.desc.setText(notes.getDescription());

        holder.option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.option);
                popupMenu.inflate(R.menu.option_menu);
                popupMenu.setOnMenuItemClickListener(item -> {
                    if (item.getItemId() == R.id.menu_edit) {
//                        FragmentActivity fragment = (FragmentActivity) (context);
//                        FragmentManager fm = fragment.getSupportFragmentManager();
//                        BottomAddFragment bottomAddFragment = new BottomAddFragment();
//                        bottomAddFragment.show(fm, "bottom_fragment");

                        final DialogPlus dialogPlus = DialogPlus.newDialog(holder.title.getContext())
                                .setContentHolder(new com.orhanobut.dialogplus.ViewHolder(R.layout.edit_popup))
                                .setExpanded(true, 540).create();

                        View view = dialogPlus.getHolderView();
                        EditText editTitle = view.findViewById(R.id.UpdateTitle);
                        EditText editDescription = view.findViewById(R.id.UpdateDescription);
                        Button btnUpdate = view.findViewById(R.id.Update);

                        editTitle.setText(notes.getTitle());
                        editDescription.setText(notes.getDescription());
                        dialogPlus.show();

                        btnUpdate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Map<String, Object> map = new HashMap<>();
                                map.put("title", editTitle.getText().toString().trim());
                                map.put("description", editDescription.getText().toString().trim());

//
                            }
                        });


                    }
                    if (item.getItemId() == R.id.menu_delete) {
                        Toast.makeText(context, "button clicked", Toast.LENGTH_SHORT).show();
                    }
                    return false;
                });
                popupMenu.show();


            }
        });

    }

    @Override
    public int getItemCount() {
        return notesArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc, option;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.notesTitle);
            desc = itemView.findViewById(R.id.notesDescription);
            option = itemView.findViewById(R.id.option);

        }
    }
}
