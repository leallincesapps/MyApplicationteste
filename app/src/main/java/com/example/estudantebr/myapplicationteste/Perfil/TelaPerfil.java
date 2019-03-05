package com.example.estudantebr.myapplicationteste.Perfil;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.estudantebr.myapplicationteste.R;

public class TelaPerfil extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
       return inflater.inflate(R.layout.activity_tela_perfil, container,false );

    }

    public static TelaPerfil newInstance(){

        return new TelaPerfil();
    }
}
