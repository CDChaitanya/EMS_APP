package com.cd17.ems_app.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Comparator;

//import static androidx.constraintlayout.motion.utils.Easing.CubicEasing.error;


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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Employees").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onEvent(@Nullable QuerySnapshot documentSnapshots, @Nullable FirebaseFirestoreException error) {
                list.clear();
                for(DocumentSnapshot snap : documentSnapshots){
                    Employee em = snap.toObject(Employee.class);
                    String txt = em.getId() + ": " + em.getFname() + " " + em.getLname();
                    list.add(txt);
            }
                //Collections.sort(list);
                //list.sort(Comparator.naturalOrder());
                //list.stream().sorted().collect(Collectors.toList());
               // Collections.sort(list.subList(1, list.size()));
                //list.sort(Sortbyid());
               // list.sort(Comparator.comparingInt(Employee::getId));
                adapter.notifyDataSetChanged();
        }});


       /* DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Employees");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snap : dataSnapshot.getChildren()){
                    Employee em = snap.getValue(Employee.class);
                    String txt = em.getId() + ": " + em.getFname() + " " + em.getLname();
                    list.add(txt);
                   // Log.e(TAG, "User name: " + em.getFname() + ", email " + em.getMail());
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
               // Log.e(TAG, "Failed to read value.", databaseError.toException());

            }
        });*/
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String[] strings = list.get(position).split(":");
                System.out.println(strings[0]+"####");
                String txt_id = String.valueOf(strings[0]);
                Intent intent = new Intent(getActivity(), DisplayActivity.class);
                intent.putExtra("key" , txt_id);
                getActivity().startActivity(intent);
            }
        });


        return view;
    }
}
