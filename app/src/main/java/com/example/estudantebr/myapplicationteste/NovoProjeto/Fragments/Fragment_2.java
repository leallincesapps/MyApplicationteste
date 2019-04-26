package com.example.estudantebr.myapplicationteste.NovoProjeto.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.estudantebr.myapplicationteste.R;

import androidx.fragment.app.Fragment;

public class Fragment_2 extends Fragment {

    public Fragment_2() {
        // Required empty public constructor
    }

    private View view;
    private TextView boasVindas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_2, container, false);

        this.view = view;

        boasVindas = view.findViewById(R.id.textViewBoasVindasId);

        return view;

    }




}
