package com.example.estudantebr.myapplicationteste.Perfil;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.estudantebr.myapplicationteste.MainActivity;
import com.example.estudantebr.myapplicationteste.R;
import com.example.estudantebr.myapplicationteste.TelaInicial.TelaInicial;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class TelaPerfilNovoUsuario extends AppCompatActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener {

    private EditText edtNome;
    private EditText edtIdade;
    private EditText edtStatus;
    private EditText edtEmail;
    private EditText edtSenha;

    private ImageView fotoPerfil;
    private Uri imagemParaSalvar;

    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_perfil_novo_usuario);


        findViewById(R.id.imageViewPerfilId).setOnClickListener(this);
        findViewById(R.id.botaoFinalizarId).setOnClickListener(this);
        findViewById(R.id.botaoCarregarId).setOnClickListener(this);

        edtNome = findViewById(R.id.edtNomeId);
        edtIdade = findViewById(R.id.edtIdadeId);
        edtStatus = findViewById(R.id.edtStatusId);
        edtEmail = findViewById(R.id.edtEmailId);
        edtSenha = findViewById(R.id.edtSenhaId);
        fotoPerfil = findViewById(R.id.imageViewPerfilId);
        navigationView = findViewById(R.id.navigationViewId);

        navigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageViewPerfilId:
                Intent pegaFoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(Intent.createChooser(pegaFoto, "Selecione uma imagem"), 123);
                break;

            case R.id.botaoFinalizarId:
                gravaNovoPerfil();
                break;

            case R.id.botaoCarregarId:
                recarregarDados();
                break;
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode == Activity.RESULT_OK){
            if(requestCode == 123) {
                // This is the key line item, URI specifies the name of the data
                Uri imagemSelecionada = data.getData();


                imagemParaSalvar = imagemSelecionada;

                // Removes Uri Permission so that when you restart the device, it will be allowed to reload.
                this.grantUriPermission(this.getPackageName(), imagemParaSalvar, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION;
                if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
                    this.getContentResolver().takePersistableUriPermission(imagemParaSalvar,takeFlags);
                }

                fotoPerfil.setImageURI(imagemSelecionada);
            }
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.navigation_perfil:

                Intent intentPerfilNovo = new Intent(TelaPerfilNovoUsuario.this, TelaPerfilNovoUsuario.class);
                startActivity(intentPerfilNovo);
                break;

            case R.id.navigation_eventos:
                Intent intentEventos = new Intent(TelaPerfilNovoUsuario.this, MainActivity.class);
                startActivity(intentEventos);
                break;

            case R.id.navigation_tela_inicial:

                Intent intentTelaInicial = new Intent(getApplicationContext(), TelaInicial.class);
                startActivity(intentTelaInicial);
                break;
        }
            return true;
    }

    public void gravaNovoPerfil(){

        SharedPreferences preferencesNovoPerfil = getSharedPreferences("preferenciasperfil", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesNovoPerfil.edit();

        editor.putString("nome", edtNome.getText().toString());
        editor.putString("idade", edtIdade.getText().toString() );
        editor.putString("status", edtStatus.getText().toString());
        editor.putString("email", edtEmail.getText().toString());
        editor.putString("senha", edtSenha.getText().toString());

        editor.putString("foto", String.valueOf(imagemParaSalvar));

        editor.apply();
        Toast.makeText(TelaPerfilNovoUsuario.this,"Gravado com sucesso", Toast.LENGTH_LONG).show();


    }

    public void recarregarDados(){
        SharedPreferences preferences = getSharedPreferences("preferenciasperfil", Context.MODE_PRIVATE);
        edtNome.setText(preferences.getString("nome", "vazio"));
        edtIdade.setText(preferences.getString("idade", "vazio"));
        edtStatus.setText(preferences.getString("status","vazio"));
        edtEmail.setText(preferences.getString("email", "vazio"));
        edtSenha.setText(preferences.getString("senha", "vazio"));

        /*Uri fotoSalva = Uri.parse(preferences.getString("foto","vazio"));

        this.grantUriPermission(this.getPackageName(), fotoSalva, Intent.FLAG_GRANT_READ_URI_PERMISSION);
        final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION;
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            this.getContentResolver().takePersistableUriPermission(fotoSalva,takeFlags);
        }

        fotoPerfil.setImageURI(fotoSalva);*/

    }

}
