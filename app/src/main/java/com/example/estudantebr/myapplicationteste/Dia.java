package com.example.estudantebr.myapplicationteste;

import java.util.ArrayList;

public class Dia {
    private int id;
    private long data_milisegundos;
    private ArrayList<Evento> eventos;

    public Dia(int id, long data_milisegundos, ArrayList<Evento> eventos) {
        this.id = id;
        this.data_milisegundos = data_milisegundos;
        this.eventos = eventos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getData_milisegundos() {
        return data_milisegundos;
    }

    public void setData_milisegundos(long data_milisegundos) {
        this.data_milisegundos = data_milisegundos;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }
}
