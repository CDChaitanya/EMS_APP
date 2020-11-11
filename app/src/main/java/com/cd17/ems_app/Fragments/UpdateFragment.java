package com.cd17.ems_app.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

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
                String txt_id = empid.getText().toString();

                if(TextUtils.isEmpty(txt_id))
                    Toast.makeText(context, "ENTER ID TO UPDATE", Toast.LENGTH_SHORT).show();
                else
                {
                    Intent intent = new Intent(getActivity() , UpdateActivity.class);
                    intent.putExtra("key" , txt_id);
                    //UpdateActivity m1 = (UpdateActivity) getActivity();
                    //m1.f1(txt_id);
                    /*Bundle b = new Bundle();
                    b.putInt("key" , Integer.parseInt(txt_id));
                    intent.putExtras(b);*/
                    getActivity().startActivity(intent);
                }
            }
        });

        return rootView;
    }
}