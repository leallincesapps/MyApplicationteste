package com.example.estudantebr.myapplicationteste;


import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.estudantebr.myapplicationteste.TelaInicial.TelaInicial;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;

public class SegundoActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segundo);

        findViewById(R.id.botaoIniciarId).setOnClickListener(this);

        EasyImage.openChooserWithGallery(this, "Select your image:", 0);

    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(SegundoActivity.this, TelaInicial.class);
        startActivity(intent);
    }



}
