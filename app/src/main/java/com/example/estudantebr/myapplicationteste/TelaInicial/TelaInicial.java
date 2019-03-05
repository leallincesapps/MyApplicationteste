package com.example.estudantebr.myapplicationteste.TelaInicial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.estudantebr.myapplicationteste.Perfil.TelaPerfil;
import com.example.estudantebr.myapplicationteste.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TelaInicial extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        navigationView = findViewById(R.id.navigationViewId);

        navigationView.setOnNavigationItemSelectedListener(TelaInicial.this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.navigation_perfil:
                getSupportActionBar().setTitle("Perfil");
                Fragment TelaPerfil = com.example.estudantebr.myapplicationteste.Perfil.TelaPerfil.newInstance();
                openFragment(TelaPerfil);
                break;

        }

        return true;
    }

    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
