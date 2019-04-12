package com.example.estudantebr.myapplicationteste.NovoProjeto.Fragments;

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
import android.widget.Toast;

import com.example.estudantebr.myapplicationteste.DataAdapter_Dias;
import com.example.estudantebr.myapplicationteste.Dia;
import com.example.estudantebr.myapplicationteste.DialogSelecionarEvento;
import com.example.estudantebr.myapplicationteste.Evento;
import com.example.estudantebr.myapplicationteste.MainActivity;
import com.example.estudantebr.myapplicationteste.Perfil.TelaPerfilNovoUsuario;
import com.example.estudantebr.myapplicationteste.R;
import com.example.estudantebr.myapplicationteste.RegrasLogica.LogicadeNiveis;
import com.example.estudantebr.myapplicationteste.TelaInicial.TelaInicial;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Fragment_1 extends Fragment implements DataAdapter_Dias.OnItemClickListener, DialogSelecionarEvento.EndDialogSelecionarEvento, BottomNavigationView.OnNavigationItemSelectedListener {

    public Fragment_1() {
        // Required empty public constructor
    }

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


    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_1, container, false);

        this.view = view;

        //Inicializando as variaveis globais
        nivel = 0;
        experienciaNivelAnterior = 0;
        experienciaAcumulada = 0;
        pontuacaoT = 0;

        //Carregando os dados do arquivo
        carregaArquivo();

        //Funcionamento dos eventos e pontuação
        initViews();
        initRecyclerView();
        populateData();

        //Calculo das pontuações
        updateDataComArquivo();
        //updateData();


        Log.d("On", "valor no on:" + nivel);


        return view;
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

        Toast.makeText(getContext(), "dados atualizados", Toast.LENGTH_LONG).show();

    }

    private void updateDataComArquivo() {

        //Verifica se já existe pontuação salva no arquivo
        SharedPreferences preferencesGravaPonto = getContext().getSharedPreferences("preferenciasperfil", Context.MODE_PRIVATE);

        int pontuacao = pontuacaoT;

        for(Dia dia : diaArrayList){
            for(Evento evento:dia.getEventos()){
                pontuacao += evento.getExperiencia();
            }
        }

        pontuacaoT = pontuacao;
        // ---------------------- Atribuindo valor para experiencia --------------------------------
        //experiencia = pontuacao;
        //Log.i("NivelExperienciaAtual","Experiencia atual: " + experiencia);
        // -----------------------------------------------------------------------------------------

        //---------------------------Verificacao de evolução ---------------------------------------
        LogicadeNiveis logicadeNiveis = new LogicadeNiveis();
        int nivelAtual;
        boolean subiuDeNivel;
        //Verifica em qual nível atual o usuário está
        nivelAtual = logicadeNiveis.novoNivel(logicadeNiveis.verificaProximoNivel(nivel , pontuacaoT ), nivel);
        //Log.i("NivelAtual", "Retorno do nivel Atual: " + nivelAtual );
        //Verifica se usuario evoluiu retorna true ou false
        subiuDeNivel = logicadeNiveis.verificaProximoNivel(nivel,(pontuacaoT - experienciaAcumulada));
        if(subiuDeNivel){
            nivel = nivelAtual;

            //Recebe o valor inteiro da evolução anterior
            experienciaNivelAnterior =  logicadeNiveis.verificanivelAnterior(nivel);

            //Experiencia acumulada recebe os valores das evoluções anteriores e acumula para subtrair com a pontuação total
            experienciaAcumulada = experienciaAcumulada + experienciaNivelAnterior;

            //experiencia = pontuacaoT - experienciaAcumulada;
        }
        //------------------------------------------------------------------------------------------
        //Calculo da experiencia Atual
        experiencia = pontuacaoT - experienciaAcumulada;
        //atualizar textview com os dados corretos
        textView_pontuacao_total.setText("Pontuação total = " + Integer.toString(pontuacao));
        //Verifica para atualizar valor do novo níivel
        logicadeNiveis.verificaProximoNivel(nivel,(pontuacaoT - experienciaAcumulada));
        txtProximoNivel.setText("Proximo Nivel: " + Double.toString(logicadeNiveis.getExperienciaNecessariaParaEvoluir()));
        txtNivelAtual.setText("Nivel: " + nivel);
        txtExperienciaAtual.setText("Experiencia: " + (experiencia));
        adapter_dias.notifyDataSetChanged();

        Toast.makeText(getContext(), "dados atualizados", Toast.LENGTH_LONG).show();

        //int experienciaNecessaria = (int) (logicadeNiveis.getExperienciaNecessariaParaEvoluir() - experiencia);
        //Gravar Pontuação no arquivo
        gravaPontuacaoArquivo(pontuacaoT, nivel, experiencia, 1, experienciaAcumulada );
        //Log.d("Verifica ponto", "Valor: " + preferencesGravaPonto.getInt("experienciaproximonivel",0) );
        //Log.d("Verifica ponto", "Valor(If): " + testaIf);
        //Log.d("Verifica ponto", "Valor: " + preferencesGravaPonto.getInt("nivel",0) );
        //Log.d("Verifica ponto", "Valor: " + preferencesGravaPonto.getInt("experienciaatual",0) );
    }

    private void initViews() {
        textView_pontuacao_total = view.findViewById(R.id.textView_pontuacao_total);
        //-------------------- Inicialização dos componentes da tela -------------------------------

        txtProximoNivel = view.findViewById(R.id.textViewProximoNivelId);
        txtNivelAtual = view.findViewById(R.id.textViewNivelId);
        txtExperienciaAtual = view.findViewById(R.id.TextViewExperienciaId);
        navigationViewMain = view.findViewById(R.id.navigationViewId);
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
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter_dias = new DataAdapter_Dias(getContext(), diaArrayList, this);
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
        dialog.show(getFragmentManager(), "DialogSelecionarEvento");
    }


    @Override
    public void endDialogSelecionarEvento(ArrayList<Evento> arrayList_evento) {
        //atualizar dia editado
        diaArrayList.get(id_dia_editado).getEventos().clear();
        diaArrayList.get(id_dia_editado).getEventos().addAll(arrayList_evento);
        //updateData();
        updateDataComArquivo();
        //updateDataComArquivo();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){

            case R.id.navigation_perfil:

                Intent intentPerfilNovo = new Intent(getContext(), TelaPerfilNovoUsuario.class);
                startActivity(intentPerfilNovo);
                break;

            case R.id.navigation_eventos:
                Intent intentEventos = new Intent(getContext(), MainActivity.class);
                startActivity(intentEventos);
                break;

            case R.id.navigation_tela_inicial:
                Intent intentTelaInicial = new Intent(getContext(), TelaInicial.class);
                startActivity(intentTelaInicial);


                break;

        }

        return false;
    }


    private void openFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void gravaPontuacaoArquivo(int pontoGravar, int nivelGravar, int experienciaAtualGravar, int experienciaProximoNivelGravar, int experienciaAcumulo){
        SharedPreferences preferencesGravaPonto = getContext().getSharedPreferences("preferenciasperfil", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesGravaPonto.edit();

        editor.putInt("pontuacaototal",pontoGravar);
        editor.putInt("nivel", nivelGravar);
        editor.putInt("experienciaatual", experienciaAtualGravar );
        editor.putInt("experienciaproximonivel", experienciaProximoNivelGravar);
        editor.putInt("experienciaacumulada", experienciaAcumulo);
        editor.apply();

    }

    private void carregaArquivo(){
        //Verifica se já existe pontuação salva no arquivo
        SharedPreferences preferencesGravaPonto = getContext().getSharedPreferences("preferenciasperfil", Context.MODE_PRIVATE);

        if(preferencesGravaPonto.contains("pontuacaototal")){
            pontuacaoT = preferencesGravaPonto.getInt("pontuacaototal",0);
            nivel = preferencesGravaPonto.getInt("nivel",0);
            experiencia = preferencesGravaPonto.getInt("experienciaatual",0);
            experienciaAcumulada = preferencesGravaPonto.getInt("experienciaacumulada",0);
            Log.d("Carrega", "Valores carregados: " + pontuacaoT);
            Log.d("Carrega", "Valores carregados: " + nivel);
            Log.d("Carrega", "Valores carregados: " + experiencia);
        }

    }


    @Override
    public void onStop() {
        super.onStop();
        gravaPontuacaoArquivo(pontuacaoT, nivel,experiencia,1, experienciaAcumulada);
    }
}
