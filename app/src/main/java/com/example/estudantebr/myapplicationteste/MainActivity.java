package com.example.estudantebr.myapplicationteste;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity implements DataAdapter_Dias.OnItemClickListener, DialogSelecionarEvento.EndDialogSelecionarEvento{


    private DataAdapter_Dias adapter_dias;
    private ArrayList<Dia> diaArrayList = new ArrayList<>();
    private TextView textView_pontuacao_total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initRecyclerView();

        populateData();
        updateData();
    }

    private void updateData() {
        int pontuacao = 0;

        for(Dia dia : diaArrayList){
            for(Evento evento:dia.getEventos()){
                pontuacao += evento.getPontos();
            }
        }

        //atualizar textview
        textView_pontuacao_total.setText("Pontuação total = " + Integer.toString(pontuacao));

        adapter_dias.notifyDataSetChanged();

        Toast.makeText(this, "dados atualizados", Toast.LENGTH_LONG).show();
    }

    private void initViews() {
        textView_pontuacao_total = findViewById(R.id.textView_pontuacao_total);
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
