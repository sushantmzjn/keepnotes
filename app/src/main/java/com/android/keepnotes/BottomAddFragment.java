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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomAddFragment extends BottomSheetDialogFragment {

    EditText title, description;
    Button submit;

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

        DAONotes dao = new DAONotes();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Notes notes = new Notes(title.getText().toString().trim(), description.getText().toString().trim());
                dao.add(notes).addOnSuccessListener(suc -> {
                    Toast.makeText(getActivity(), "added", Toast.LENGTH_SHORT).show();
                    dismiss();
                }).addOnFailureListener(er -> {
                    Toast.makeText(getActivity(), "" + er.getMessage(), Toast.LENGTH_SHORT).show();
                });
            }
        });


        return view;
    }
}
