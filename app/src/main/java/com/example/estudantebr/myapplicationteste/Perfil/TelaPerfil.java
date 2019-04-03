package com.example.estudantebr.myapplicationteste.Perfil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.estudantebr.myapplicationteste.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TelaPerfil extends Fragment implements View.OnClickListener {

    private EditText edtNome;
    private EditText edtIdade;
    private EditText edtStatus;
    private ImageView fotoPerfil;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.activity_tela_perfil, container,false );

        rootView.findViewById(R.id.imageViewPerfilId).setOnClickListener(this);
        rootView.findViewById(R.id.botaoFinalizarId).setOnClickListener(this);

        edtNome = rootView.findViewById(R.id.edtNomeId);
        edtIdade = rootView.findViewById(R.id.edtIdadeId);
        edtStatus = rootView.findViewById(R.id.edtStatusId);
        fotoPerfil = rootView.findViewById(R.id.imageViewPerfilId);

        return rootView;

    }

    public static TelaPerfil newInstance(){

        return new TelaPerfil();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.imageViewPerfilId:
                Intent pegaFoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(pegaFoto, "Selecione uma imagem"), 123);

                break;

            case R.id.botaoFinalizarId:
                //SharedPreferences preferences = getContext().getSharedPreferences("preferencias", Context.MODE_PRIVATE);


                break;

        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 123) {
                Uri imagemSelecionada = data.getData();
                fotoPerfil.setImageURI(imagemSelecionada);
            }
        }

    }
}
