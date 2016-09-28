package com.example.lmont.iceicebb.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.lmont.iceicebb.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ToolsFragment extends Fragment {

    Button cardToolButton;
    Button diceToolButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tools, container, false);

        //TODO: Find out if this needs to be different inside a fragment...
//        //Set button to direct to Card tool activity
//        cardToolButton = (Button) view.findViewById(cardToolButton);
//        cardToolButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ToolsFragment.this, CardsActivity.class);
//                startActivity(intent);
//            }
//        });
//        //Set button to direct to Dice tool activity
//        diceToolButton = (Button) view.findViewById(R.id.diceToolButton);
//        diceToolButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ToolsFragment.this, DiceActivity.class);
//                startActivity(intent);
//            }
//        });

        return view;
    }

}
