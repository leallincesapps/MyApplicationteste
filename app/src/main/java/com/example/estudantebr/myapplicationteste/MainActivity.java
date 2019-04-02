package com.example.estudantebr.myapplicationteste;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estudantebr.myapplicationteste.Perfil.TelaPerfilNovoUsuario;
import com.example.estudantebr.myapplicationteste.RegrasLogica.LogicadeNiveis;
import com.example.estudantebr.myapplicationteste.TelaInicial.TelaInicial;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements DataAdapter_Dias.OnItemClickListener, DialogSelecionarEvento.EndDialogSelecionarEvento, BottomNavigationView.OnNavigationItemSelectedListener {


    private DataAdapter_Dias adapter_dias;
    private ArrayList<Dia> diaArrayList = new ArrayList<>();
    private TextView textView_pontuacao_total;

    private BottomNavigationView navigationViewMain;

    private int nivel;
    private int experiencia;
    private int experienciaNivelAnterior ;
    private int experienciaAcumulada;
    private int pontuacaoT;

    private TextView txtProximoNivel;
    private TextView txtNivelAtual;
    private TextView txtExperienciaAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initRecyclerView();

        populateData();
        carregaArquivo();
        //updateDataComArquivo();
        updateData();
        
        nivel = 0;
        experienciaNivelAnterior = 0;
        experienciaAcumulada = 0;
        pontuacaoT = 0;

        Log.d("On", "valor no on:" + nivel);


    }

    private void updateData() {

        int pontuacao = 0;

        for(Dia dia : diaArrayList){
            for(Evento evento:dia.getEventos()){
                pontuacao += evento.getExperiencia();
            }
        }

        // ---------------------- Atribuindo valor para experiencia --------------------------------
        experiencia = pontuacao;
        //Log.i("NivelExperienciaAtual","Experiencia atual: " + experiencia);
        // -----------------------------------------------------------------------------------------

        //---------------------------Verificacao de evolução ---------------------------------------
        LogicadeNiveis logicadeNiveis = new LogicadeNiveis();
        int nivelAtual;
        boolean subiuDeNivel;
        //Verifica em qual nível atual o usuário está
        nivelAtual = logicadeNiveis.novoNivel(logicadeNiveis.verificaProximoNivel(nivel , experiencia ), nivel);
        //Log.i("NivelAtual", "Retorno do nivel Atual: " + nivelAtual );
        //Verifica se usuario evoluiu retorna true ou false
        subiuDeNivel = logicadeNiveis.verificaProximoNivel(nivel,(experiencia - experienciaAcumulada));
        if(subiuDeNivel){
            nivel = nivelAtual;

            //Recebe o valor inteiro da evolução anterior
            experienciaNivelAnterior =  logicadeNiveis.verificanivelAnterior(nivel);

            //Experiencia acumulada recebe os valores das evoluções anteriores e acumula para subtrair com a pontuação total
            experienciaAcumulada = experienciaAcumulada + experienciaNivelAnterior;


        }
        //------------------------------------------------------------------------------------------

        //atualizar textview
        textView_pontuacao_total.setText("Pontuação total = " + Integer.toString(pontuacao));
        txtProximoNivel.setText("Proximo Nivel: " + Double.toString(logicadeNiveis.getExperienciaNecessariaParaEvoluir()));
        txtNivelAtual.setText("Nivel: " + nivel);
        txtExperienciaAtual.setText("Experiencia: " + (experiencia - experienciaAcumulada));
        adapter_dias.notifyDataSetChanged();

        Toast.makeText(this, "dados atualizados", Toast.LENGTH_LONG).show();

    }

    private void updateDataComArquivo() {

        //Verifica se já existe pontuação salva no arquivo
        SharedPreferences preferencesGravaPonto = getSharedPreferences("preferenciasperfil", Context.MODE_PRIVATE);

        int pontuacao = pontuacaoT;
        /*experiencia = 0;

        if(preferencesGravaPonto.contains("pontuacaototal")){
            pontuacao = preferencesGravaPonto.getInt("pontuacaototal",0);
            nivel = preferencesGravaPonto.getInt("nivel",0);
            experiencia = preferencesGravaPonto.getInt("experienciaatual",0);
        }*/



        for(Dia dia : diaArrayList){
            for(Evento evento:dia.getEventos()){
                pontuacao += evento.getExperiencia();
            }
        }

        boolean testaIf = false;
        if(testaIf = preferencesGravaPonto.contains("pontuacaototal"));

        if(!preferencesGravaPonto.contains("experienciaatual")){
            experiencia = pontuacao;
            pontuacaoT = pontuacao;
        }
        // ---------------------- Atribuindo valor para experiencia --------------------------------
        //experiencia = pontuacao;
        //Log.i("NivelExperienciaAtual","Experiencia atual: " + experiencia);
        // -----------------------------------------------------------------------------------------

        //---------------------------Verificacao de evolução ---------------------------------------
        LogicadeNiveis logicadeNiveis = new LogicadeNiveis();
        int nivelAtual;
        boolean subiuDeNivel;
        //Verifica em qual nível atual o usuário está
        nivelAtual = logicadeNiveis.novoNivel(logicadeNiveis.verificaProximoNivel(nivel , experiencia ), nivel);
        //Log.i("NivelAtual", "Retorno do nivel Atual: " + nivelAtual );
        //Verifica se usuario evoluiu retorna true ou false
        subiuDeNivel = logicadeNiveis.verificaProximoNivel(nivel,(experiencia - experienciaAcumulada));
        if(subiuDeNivel){
            nivel = nivelAtual;

            //Recebe o valor inteiro da evolução anterior
            experienciaNivelAnterior =  logicadeNiveis.verificanivelAnterior(nivel);

            //Experiencia acumulada recebe os valores das evoluções anteriores e acumula para subtrair com a pontuação total
            experienciaAcumulada = experienciaAcumulada + experienciaNivelAnterior;


        }
        //------------------------------------------------------------------------------------------

        //atualizar textview
        textView_pontuacao_total.setText("Pontuação total = " + Integer.toString(pontuacao));
        txtProximoNivel.setText("Proximo Nivel: " + Double.toString(logicadeNiveis.getExperienciaNecessariaParaEvoluir()));
        txtNivelAtual.setText("Nivel: " + nivel);
        txtExperienciaAtual.setText("Experiencia: " + (experiencia));
        adapter_dias.notifyDataSetChanged();

        Toast.makeText(this, "dados atualizados", Toast.LENGTH_LONG).show();

        //int experienciaNecessaria = (int) (logicadeNiveis.getExperienciaNecessariaParaEvoluir() - experiencia);
        //Gravar Pontuação no arquivo
        //gravaPontuacaoArquivo(pontuacao, nivel, experiencia, 1 );
        //Log.d("Verifica ponto", "Valor: " + preferencesGravaPonto.getInt("experienciaproximonivel",0) );
        //Log.d("Verifica ponto", "Valor(If): " + testaIf);
        //Log.d("Verifica ponto", "Valor: " + preferencesGravaPonto.getInt("nivel",0) );
        //Log.d("Verifica ponto", "Valor: " + preferencesGravaPonto.getInt("experienciaatual",0) );
    }

    private void initViews() {
        textView_pontuacao_total = findViewById(R.id.textView_pontuacao_total);
        //-------------------- Inicialização dos componentes da tela -------------------------------

        txtProximoNivel = findViewById(R.id.textViewProximoNivelId);
        txtNivelAtual = findViewById(R.id.textViewNivelId);
        txtExperienciaAtual = findViewById(R.id.TextViewExperienciaId);
        navigationViewMain = findViewById(R.id.navigationViewId);
        navigationViewMain.setOnNavigationItemSelectedListener(this);

        //------------------------------------------------------------------------------------------
    }

    private void populateData() {
        //carregar data para teste (mil dias)
        for(int i = 0; i < 1000; i++){
            diaArrayList.add(new Dia(i,-1,new ArrayList<Evento>()));
        }

        adapter_dias.notifyDataSetChanged();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter_dias = new DataAdapter_Dias(this, diaArrayList, this);
        recyclerView.setAdapter(adapter_dias);
    }


    private int id_dia_editado = -1;



   @Override
    public void onItemClick(Dia dia) {
        id_dia_editado = dia.getId();
        //clicado no dia
        //abrir dialog selecionar evento
        DialogSelecionarEvento dialog = new DialogSelecionarEvento();
        dialog.setListening(this);
        dialog.show(getSupportFragmentManager(), "DialogSelecionarEvento");
    }


    @Override
    public void endDialogSelecionarEvento(ArrayList<Evento> arrayList_evento) {
        //atualizar dia editado
        diaArrayList.get(id_dia_editado).getEventos().clear();
        diaArrayList.get(id_dia_editado).getEventos().addAll(arrayList_evento);
        updateData();
        //updateDataComArquivo();
        //updateDataComArquivo();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
       switch (menuItem.getItemId()){

           case R.id.navigation_perfil:

               Intent intentPerfilNovo = new Intent(MainActivity.this, TelaPerfilNovoUsuario.class);
               startActivity(intentPerfilNovo);
               break;

           case R.id.navigation_eventos:
               Intent intentEventos = new Intent(MainActivity.this, MainActivity.class);
               startActivity(intentEventos);
               break;

           case R.id.navigation_tela_inicial:
               Intent intentTelaInicial = new Intent(getApplicationContext(), TelaInicial.class);
               startActivity(intentTelaInicial);


               break;

       }

       return false;
    }


    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void gravaPontuacaoArquivo(int pontoGravar, int nivelGravar, int experienciaAtualGravar, int experienciaProximoNivelGravar){
        SharedPreferences preferencesGravaPonto = getSharedPreferences("preferenciasperfil", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesGravaPonto.edit();

        editor.putInt("pontuacaototal",pontoGravar);
        editor.putInt("nivel", nivelGravar);
        editor.putInt("experienciaatual", experienciaAtualGravar );
        editor.putInt("experienciaproximonivel", experienciaProximoNivelGravar);
        editor.apply();

    }

    private void carregaArquivo(){
        //Verifica se já existe pontuação salva no arquivo
        SharedPreferences preferencesGravaPonto = getSharedPreferences("preferenciasperfil", Context.MODE_PRIVATE);

        pontuacaoT = 0;
        experiencia = 0;

        if(preferencesGravaPonto.contains("pontuacaototal")){
            pontuacaoT = preferencesGravaPonto.getInt("pontuacaototal",0);
            nivel = preferencesGravaPonto.getInt("nivel",0);
            experiencia = preferencesGravaPonto.getInt("experienciaatual",0);
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        gravaPontuacaoArquivo(pontuacaoT, nivel,experiencia,1);
    }
}
