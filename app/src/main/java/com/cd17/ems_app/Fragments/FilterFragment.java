package com.cd17.ems_app.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cd17.ems_app.R;

public class FilterFragment extends Fragment
{
    private ListView lv;
    String items[] = new String [] {"Year of Experience","City","Department","Role"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_filter, container, false);

        lv = v.findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.list_item,items);

        return v;
    }
}