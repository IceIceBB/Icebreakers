package com.example.lmont.iceicebb.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lmont.iceicebb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MaterialFragment extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_material, container, false);
        ((TextView) view.findViewById(R.id.material)).setText("Cards or Dice");

        return view;
    }

}
