package com.example.estudantebr.myapplicationteste.TelaInicial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.estudantebr.myapplicationteste.MainActivity;
import com.example.estudantebr.myapplicationteste.Perfil.TelaPerfil;
import com.example.estudantebr.myapplicationteste.Perfil.TelaPerfilNovoUsuario;
import com.example.estudantebr.myapplicationteste.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TelaInicial extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;
    private TextView boasVindas;
    private TextView textViewTeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        navigationView = findViewById(R.id.navigationViewId);
        boasVindas = findViewById(R.id.textViewBoasVindasId);
        textViewTeste = findViewById(R.id.textViewTesteId);
        navigationView.setOnNavigationItemSelectedListener(TelaInicial.this);

        recarregaDadosArquivo();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.navigation_perfil:
                /*boasVindas.setText("");
                getSupportActionBar().setTitle("Perfil");
                Fragment TelaPerfil = com.example.estudantebr.myapplicationteste.Perfil.TelaPerfil.newInstance();
                openFragment(TelaPerfil);*/

                Intent intentPerfilNovo = new Intent(TelaInicial.this, TelaPerfilNovoUsuario.class);
                startActivity(intentPerfilNovo);
                break;

            case R.id.navigation_eventos:
                Intent intentEventos = new Intent(TelaInicial.this, MainActivity.class);
                startActivity(intentEventos);
                break;

            case R.id.navigation_tela_inicial:

                Intent intentTelaInicial = new Intent(getApplicationContext(),TelaInicial.class);
                startActivity(intentTelaInicial);
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

    private void recarregaDadosArquivo(){

        SharedPreferences preferences = getSharedPreferences("preferenciasperfil", Context.MODE_PRIVATE);

        if(preferences.getAll() == null){
            textViewTeste.setText("Arquivo vazio");
        }else if(preferences.contains("pontuacaototal")){
            int ponto = preferences.getInt("pontuacaototal",0);
            //String pontos = String.valueOf(ponto);
            textViewTeste.setText(String.valueOf(ponto));
            Log.d("Verifica ponto", "Valor: " + preferences.getInt("experienciaproximonivel",0) );
            Log.d("Verifica ponto", "Valor: " + preferences.getInt("nivel",0) );
            Log.d("Verifica ponto", "Valor: " + preferences.getInt("experienciaatual",0) );


        }else{
            textViewTeste.setText(preferences.getString("nome", "vazio"));
            Log.d("Verifica ponto", "Valor: " + preferences.getInt("pontuacaototal",0) );
        }

    }



}
