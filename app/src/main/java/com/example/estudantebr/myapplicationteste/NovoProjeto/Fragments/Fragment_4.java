package com.example.estudantebr.myapplicationteste.NovoProjeto.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.estudantebr.myapplicationteste.Database.DBHelper;
import com.example.estudantebr.myapplicationteste.Perfil.TelaPerfilNovoUsuario;
import com.example.estudantebr.myapplicationteste.R;
import com.example.estudantebr.myapplicationteste.models.Perfil;
import com.example.estudantebr.myapplicationteste.models.Usuario;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

import androidx.fragment.app.Fragment;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class Fragment_4 extends Fragment implements View.OnClickListener{

    public Fragment_4() {
        // Required empty public constructor
    }

    private Perfil perfil;
    private Usuario usuario;

    private EditText edtNome;
    private EditText edtIdade;
    private EditText edtStatus;
    private EditText edtEmail;
    private EditText edtSenha;

    private ImageView fotoPerfil;
    private Uri imagemParaSalvar;
    private String bitmapSalvar;

    private View view;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_fragment_4, container, false);

        this.view = view;
        context = getContext();

        view.findViewById(R.id.imageViewPerfilId).setOnClickListener(this);
        view.findViewById(R.id.botaoFinalizarId).setOnClickListener(this);
        view.findViewById(R.id.botaoCarregarId).setOnClickListener(this);

        edtNome = view.findViewById(R.id.edtNomeId);
        edtIdade = view.findViewById(R.id.edtIdadeId);
        edtStatus = view.findViewById(R.id.edtStatusId);
        edtEmail = view.findViewById(R.id.edtEmailId);
        edtSenha = view.findViewById(R.id.edtSenhaId);
        fotoPerfil = view.findViewById(R.id.imageViewPerfilId);

        //usuario = new Usuario("fdsgdsfg", "Pedro", "henrique", 25);

        return view;
   }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageViewPerfilId:

                Intent pegaFoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(pegaFoto, "Selecione uma imagem"), 123);
                break;

            case R.id.botaoFinalizarId:
                gravaNovoPerfilArquivo();
                salvaPerfilSQLite();
                break;

            case R.id.botaoCarregarId:
                recarregarDadosArquivo();
                break;
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 123) {
                // This is the key line item, URI specifies the name of the data
                Uri imagemSelecionada = data.getData();

                //Converter a Imagem Para Bitmap
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), imagemSelecionada);

                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    byte[] byteArray = byteArrayOutputStream .toByteArray();

                    //BASE64
                    String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);

                    //Processo de descompressão e conversão para Bitmap
                    byte[] byteArrayDecoded = Base64.decode(encoded, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(byteArrayDecoded, 0, byteArrayDecoded.length);

                    if (bitmap != null) {
                        bitmapSalvar = encoded;
                        fotoPerfil.setImageBitmap(decodedByte);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


                /*
                // Removes Uri Permission so that when you restart the device, it will be allowed to reload.
                getContext().grantUriPermission(getContext().getPackageName(), imagemParaSalvar, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION;
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                    getContext().getContentResolver().takePersistableUriPermission(imagemParaSalvar, takeFlags);
                }
                * */

                //Caso queira salvar a URI da imagem
                //imagemParaSalvar = imagemSelecionada;

            }

        }
    }

    public void gravaNovoPerfilArquivo(){

        SharedPreferences preferencesNovoPerfil = getContext().getSharedPreferences("preferenciasperfil", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesNovoPerfil.edit();

        editor.putString("nome", edtNome.getText().toString());
        editor.putString("idade", edtIdade.getText().toString() );
        editor.putString("status", edtStatus.getText().toString());
        editor.putString("email", edtEmail.getText().toString());
        editor.putString("senha", edtSenha.getText().toString());
        editor.putString("foto", bitmapSalvar);
        //editor.putString("foto", String.valueOf(imagemParaSalvar));

        editor.apply();


    }

    //Função para Salvar no SQLite o Perfil do Usuário
    public void salvaPerfilSQLite(){


        perfil = new Perfil("teste",
                "UsuarioTeste",
                "Foco,Forca,Fe",
                25,
                bitmapSalvar);

        perfil = new Perfil("teste",
                edtNome.getText().toString(),
                edtStatus.getText().toString(),
                25,
                bitmapSalvar);

        DBHelper dbHelper = new DBHelper(getContext());
        dbHelper.changeDataPerfil(perfil);
    }

    public void recarregarDadosArquivo(){
        SharedPreferences preferences = getContext().getSharedPreferences("preferenciasperfil", Context.MODE_PRIVATE);
        edtNome.setText(preferences.getString("nome", "vazio"));
        edtIdade.setText(preferences.getString("idade", "vazio"));
        edtStatus.setText(preferences.getString("status","vazio"));
        edtEmail.setText(preferences.getString("email", "vazio"));
        edtSenha.setText(preferences.getString("senha", "vazio"));
        String encoded = preferences.getString("foto","vazio");

        //Processo de descompressão e conversão para Bitmap
        byte[] byteArrayDecoded = Base64.decode(encoded, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(byteArrayDecoded, 0, byteArrayDecoded.length);

        fotoPerfil.setImageBitmap(decodedByte);


    }

    public void recarregarDadosSQLite(){



    }

}
