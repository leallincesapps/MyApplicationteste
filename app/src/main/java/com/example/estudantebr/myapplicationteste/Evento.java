package com.example.estudantebr.myapplicationteste;

public class Evento {

    //TODO: Quais serão os atributos de um evento?

    private int id;
    private String nome;
    private int experiencia;

    public Evento(int id, String nome, int experiencia) {
        this.id = id;
        this.nome = nome;
        this.experiencia = experiencia;
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

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
}
