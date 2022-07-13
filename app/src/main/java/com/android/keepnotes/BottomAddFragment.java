package com.android.keepnotes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.keepnotes.Model.Notes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class BottomAddFragment extends BottomSheetDialogFragment {

    EditText title, description;
    Button submit;
    DatabaseReference databaseReference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NORMAL, R.style.BottomSheetDialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottomsheet, container, false);

        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);
        submit = view.findViewById(R.id.submit);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        DAONotes dao = new DAONotes();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Notes notes = new Notes(title.getText().toString().trim(), description.getText().toString().trim());
//                dao.add(notes).addOnSuccessListener(suc -> {
//                    Toast.makeText(getActivity(), "added", Toast.LENGTH_SHORT).show();
//                    dismiss();
//                }).addOnFailureListener(er -> {
//                    Toast.makeText(getActivity(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
//                });

                insertData();
            }
        });


        return view;
    }

    private void insertData(){
        String title1 = title.getText().toString();
        String desc = description.getText().toString();
        String id = databaseReference.push().getKey();

        Notes notes = new Notes(title1, desc);
        databaseReference.child("Notes").child(id).setValue(notes)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "notes added", Toast.LENGTH_SHORT).show();
                            dismiss();
                        }else {
                            Toast.makeText(getActivity(), "failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
