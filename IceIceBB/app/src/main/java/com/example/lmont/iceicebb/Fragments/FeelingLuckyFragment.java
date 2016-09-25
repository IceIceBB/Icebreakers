package com.example.lmont.iceicebb.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lmont.iceicebb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeelingLuckyFragment extends Fragment {




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feeling_lucky, container, false);
        ((TextView)view.findViewById(R.id.feeling_lucky)).setText("Question Of The Day!\n How many plar bears does it take to break the ice ?");

        return view;
    }

}
