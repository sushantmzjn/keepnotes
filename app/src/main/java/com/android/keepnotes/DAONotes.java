package com.android.keepnotes;

import com.android.keepnotes.Model.Notes;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAONotes {

    private DatabaseReference databaseReference;

    DAONotes() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Notes.class.getSimpleName());
    }

    public Task<Void> add(Notes notes) {
        return databaseReference.push().setValue(notes);
    }

}
