package com.example.estudantebr.myapplicationteste;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.estudantebr.myapplicationteste.Perfil.TelaPerfil;
import com.example.estudantebr.myapplicationteste.TelaInicial.TelaInicial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class SegundoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        findViewById(R.id.botaoIniciarId).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(SegundoActivity.this, TelaInicial.class);
        startActivity(intent);
    }

}
