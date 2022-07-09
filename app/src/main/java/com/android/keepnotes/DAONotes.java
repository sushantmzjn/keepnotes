package com.android.keepnotes;

import com.android.keepnotes.Model.Notes;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class DAONotes {

    private DatabaseReference databaseReference;

    DAONotes() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Notes.class.getSimpleName());
    }

    //insert data
    public Task<Void> add(Notes notes) {
        return databaseReference.push().setValue(notes);
    }

    //get data
    public Query get() {
        return databaseReference.orderByKey();
    }

}
