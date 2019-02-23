package com.example.estudantebr.myapplicationteste;

public class Evento {

    //TODO: Quais serão os atributos de um evento?

    private int id;
    private String nome;
    private int pontos;

    public Evento(int id, String nome, int pontos) {
        this.id = id;
        this.nome = nome;
        this.pontos = pontos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
}
