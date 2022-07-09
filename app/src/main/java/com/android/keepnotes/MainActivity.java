package com.android.keepnotes;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.keepnotes.Adapter.NotesAdapter;
import com.android.keepnotes.Model.Notes;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ImageView btnMenu;
    Dialog dialog;
    FloatingActionButton floatingActionButton;

    RecyclerView recyclerView;
    ArrayList<Notes> notesArrayList;
    NotesAdapter notesAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        btnMenu = findViewById(R.id.menu);
        recyclerView = findViewById(R.id.recyclerNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        floatingActionButton = findViewById(R.id.fabAdd);
        dialog = new Dialog(this);

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuDialog();
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomAddFragment bottomAddFragment = new BottomAddFragment();
                bottomAddFragment.show(getSupportFragmentManager(), "bottomAddFragment");
            }
        });


        notesArrayList = new ArrayList<>();
        notesAdapter = new NotesAdapter(MainActivity.this, notesArrayList);
        recyclerView.setAdapter(notesAdapter);

        DAONotes dao = new DAONotes();
        dao.get().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                notesArrayList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Notes notes = dataSnapshot.getValue(Notes.class);
                    notesArrayList.add(notes);

                }
                notesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void menuDialog() {
        dialog.setContentView(R.layout.menudialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}