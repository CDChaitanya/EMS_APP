package com.cd17.ems_app.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cd17.ems_app.Employee;
import com.cd17.ems_app.R;

import com.cd17.ems_app.DisplayActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

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

        FirebaseFirestore db =FirebaseFirestore.getInstance();
        db.collection("Employees").addSnapshotListener(new EventListener<QuerySnapshot>()
        {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error)
            {
                list.clear();
                for(DocumentSnapshot snap : value)
                {
                    String txt = snap.getString("id") + ": " + snap.getString("fname") + " " + snap.getString("lname");
                    list.add(txt);
                }
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //position=position+1;
                String[] strings = list.get(position).split(":");
                System.out.println(strings[0]+"########");
                String txt_id = String.valueOf(strings[0]);
                Intent intent = new Intent(getActivity(), DisplayActivity.class);
                intent.putExtra("key" , txt_id);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}
