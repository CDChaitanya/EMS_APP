package com.cd17.ems_app.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cd17.ems_app.Employee;
import com.cd17.ems_app.MainActivity;
import com.cd17.ems_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteFragment extends Fragment
{
    private EditText id;
    private Button DeleteButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_delete, container, false);

        id = rootView.findViewById(R.id.empid);
        DeleteButton = rootView.findViewById(R.id.delete_btn);

        DeleteButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String txt_id = id.getText().toString();
                if(TextUtils.isEmpty(txt_id))
                    Toast.makeText(getActivity(), "ENTER ID TO DELETE", Toast.LENGTH_SHORT).show();
                else
                {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Employees");
                    reference.addValueEventListener(new ValueEventListener()
                    {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                        {
                            Toast.makeText(getActivity(), txt_id, Toast.LENGTH_SHORT).show();
                            for(DataSnapshot snap : dataSnapshot.getChildren())
                            {
                                Employee emp = snap.getValue(Employee.class);
                                if(Integer.parseInt(txt_id) == Integer.parseInt(emp.getId()) )
                                {
                                    System.out.println("DELETING THE DATA " + emp.getId());
                                    snap.getRef().removeValue();
                                    Toast.makeText(getActivity(), "Employee Deleted", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getActivity() , MainActivity.class);
                                    getActivity().startActivity(intent);
                                }
                                else {
                                    Toast.makeText(getActivity(), "NOT FOUND", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
        return rootView;
    }
}