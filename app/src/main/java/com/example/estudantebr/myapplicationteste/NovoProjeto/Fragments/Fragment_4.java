package com.example.estudantebr.myapplicationteste.NovoProjeto.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.estudantebr.myapplicationteste.Database.DBHelper;
import com.example.estudantebr.myapplicationteste.R;
import com.example.estudantebr.myapplicationteste.models.Usuario;

import androidx.fragment.app.Fragment;

public class Fragment_4 extends Fragment {

    public Fragment_4() {
        // Required empty public constructor
    }

    private Usuario usuario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_4, container, false);

        usuario = new Usuario("fdsgdsfg", "Pedro", "henrique", 25);

        new DBHelper(getContext()).changeData(usuario);


        return view;
   }


}
