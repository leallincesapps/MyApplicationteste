package com.example.estudantebr.myapplicationteste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   private Button botaoTela2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoTela2 = findViewById(R.id.botaoTela2Id);

        botaoTela2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Abrir segunda activity", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,SegundoActivity.class));
            }
        });
    }


    @Override
    public void onBackPressed() {
        Toast.makeText(MainActivity.this, "tchauuu!", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

}
