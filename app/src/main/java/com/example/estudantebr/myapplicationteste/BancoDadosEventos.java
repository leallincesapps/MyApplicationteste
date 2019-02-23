package com.example.estudantebr.myapplicationteste;

import java.util.ArrayList;

public class BancoDadosEventos {

    public static int EVENTO_PRIMEIRO_ACESSO = 0;
    public static int EVENTO_ENTROU_DOIS_DIAS_SEGUIDOS = 1;
    public static int EVENTO_ENTROU_SETE_DIAS_SEGUIDOS = 2;
    public static int EVENTO_ENTROU_TRINTA_DIAS_SEGUIDOS = 3;
    public static int EVENTO_COMPLETOU_DIETA = 4;

    public Evento getEvento(int id_evento){

        //procurar evento
        for(Evento evento:getTodosEventos()){
            if(evento.getId() == id_evento){
                return evento;
            }
        }

        return null;//evento nao encontrado
    }

    public ArrayList<Evento> getTodosEventos(){

        ArrayList<Evento> arrayList = new ArrayList<>();

        int id_evento;
        String nome_evento;
        int pontos_evento;

        //NOVO EVENTO
        id_evento = EVENTO_PRIMEIRO_ACESSO;
        nome_evento = "Primeiro acesso ao aplicativo";
        pontos_evento = 10;
        arrayList.add(new Evento(id_evento, nome_evento, pontos_evento));

        //NOVO EVENTO
        id_evento = EVENTO_ENTROU_DOIS_DIAS_SEGUIDOS;
        nome_evento = "Entrou no app por dois dias seguidos";
        pontos_evento = 10;
        arrayList.add(new Evento(id_evento, nome_evento, pontos_evento));

        //NOVO EVENTO
        id_evento = EVENTO_ENTROU_SETE_DIAS_SEGUIDOS;
        nome_evento = "Entrou no app por sete dias seguidos";
        pontos_evento = 10;
        arrayList.add(new Evento(id_evento, nome_evento, pontos_evento));

        //NOVO EVENTO
        id_evento = EVENTO_ENTROU_TRINTA_DIAS_SEGUIDOS;
        nome_evento = "Entrou no app por 30 dias seguidos";
        pontos_evento = 10;
        arrayList.add(new Evento(id_evento, nome_evento, pontos_evento));

        //NOVO EVENTO
        id_evento = EVENTO_COMPLETOU_DIETA;
        nome_evento = "Completou uma dieta";
        pontos_evento = 10;
        arrayList.add(new Evento(id_evento, nome_evento, pontos_evento));


        return arrayList;
    }





}