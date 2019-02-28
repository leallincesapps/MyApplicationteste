package com.example.estudantebr.myapplicationteste;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.estudantebr.myapplicationteste.RegrasLogica.LogicadeNiveis;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements DataAdapter_Dias.OnItemClickListener, DialogSelecionarEvento.EndDialogSelecionarEvento{


    private DataAdapter_Dias adapter_dias;
    private ArrayList<Dia> diaArrayList = new ArrayList<>();
    private TextView textView_pontuacao_total;

    private int nivel;
    private int experiencia;
    private int experienciaNivelAnterior ;
    private int experienciaAcumulada;

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
        updateData();

        nivel = 0;
        experienciaNivelAnterior = 0;
        experienciaAcumulada = 0;
    }

    private void updateData() {
        int pontuacao = 0;

        for(Dia dia : diaArrayList){
            for(Evento evento:dia.getEventos()){
                pontuacao += evento.getPontos();
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

    private void initViews() {
        textView_pontuacao_total = findViewById(R.id.textView_pontuacao_total);
        //-------------------- Inicialização dos componentes da tela -------------------------------

        txtProximoNivel = findViewById(R.id.textViewProximoNivelId);
        txtNivelAtual = findViewById(R.id.textViewNivelId);
        txtExperienciaAtual = findViewById(R.id.TextViewExperienciaId);
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


    @Override
    public void onBackPressed() {
        Toast.makeText(MainActivity.this, "tchauuu!", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
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

    }

}
