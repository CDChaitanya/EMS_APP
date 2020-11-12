package com.cd17.ems_app.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cd17.ems_app.Employee;
import com.cd17.ems_app.DisplayActivity;
import com.cd17.ems_app.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//import static androidx.constraintlayout.motion.utils.Easing.CubicEasing.error;
import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class DisplayFragment extends Fragment
{

    private ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_display, container, false);

        listView = view.findViewById(R.id.listView);

        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item,list);
        listView.setAdapter(adapter);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Employees");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    Employee em = snap.getValue(Employee.class);
                    String txt = em.getId() + " : " + em.getFname() + " " + em.getLname();
                    list.add(txt);
                   // Log.e(TAG, "User name: " + em.getFname() + ", email " + em.getMail());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
               // Log.e(TAG, "Failed to read value.", databaseError.toException());

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                position=position+1;
                String txt_id = String.valueOf(position);
                Intent intent = new Intent(getActivity(), DisplayActivity.class);
                intent.putExtra("key" , txt_id);
                getActivity().startActivity(intent);
            }
        });


        return view;
    }
}