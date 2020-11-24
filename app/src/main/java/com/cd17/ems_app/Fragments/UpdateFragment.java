package com.cd17.ems_app.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cd17.ems_app.R;
import com.cd17.ems_app.UpdateActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

public class UpdateFragment extends Fragment
{
    private EditText empid;
    private Button update;

    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_update, container, false);
        context = rootView.getContext();
        empid = rootView.findViewById(R.id.empid);
        update = rootView.findViewById(R.id.update_btn);

        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String txt_id = empid.getText().toString().trim();

                if(TextUtils.isEmpty(txt_id))
                    Toast.makeText(context, "ENTER ID TO UPDATE", Toast.LENGTH_SHORT).show();
                else
                {
                    CollectionReference ref = FirebaseFirestore.getInstance().collection("Employees");

                    Intent intent = new Intent(getActivity() , UpdateActivity.class);
                    intent.putExtra("key" , txt_id);
                    getActivity().startActivity(intent);

                }
            }
        });

        return rootView;
    }
}