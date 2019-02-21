package com.example.estudantebr.myapplicationteste;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.estudantebr.myapplicationteste.RegrasLogica.LogicadeNiveis;

public class MainActivity extends AppCompatActivity {

   private Button botaoTela2;
   private int nivel;
   private double experiencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* VARIAVEIS CRIADAS PARA FAZER OS TESTES INICIAIS
        * -NECESSÁRIO BUSCAR ESSAS INFORMAÇÕES DO BANCO DE DADOS PARA OBTER NIVEL E EXPERIÊNCIA DO USUÁRIO
        * -CASO QUEIRA EFETUAR O TESTE PARA VERIFICAR O NIVEL ATUAL DO USUÁRIO É NECESSÁRIO INFORMAR SEU NIVEL E EXPERIÊCIA
        *
        * */
        nivel = 12;
        experiencia = 24100;

        //Inicializando Construtor da Classe de lógica de niveis
        LogicadeNiveis logicadeNiveis = new LogicadeNiveis();

        /*Variavel de nivel Atual recebe valor do proximo nivel
         - Ocorre a verificação do nivel de experiencia necessario para o proximo nivel.
         - verifica se a experiencial atual é igual ou maior que a necessária para a evolução*///--------------------------------------------
        int nivelAtual;
        nivelAtual = logicadeNiveis.novoNivel(logicadeNiveis.verificaProximoNivel(nivel , experiencia), nivel);

        Log.i("CalculoNivel", "NivelAtual: " + nivelAtual);
        //-----------------------------------------------------------------------------------------------------------------------------------
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
