package com.example.estudantebr.myapplicationteste.models;

public class Conquista{

    private String id;
    private String userId;
    private long data;
    private int tipo_conquista;

    public Conquista(String id, String userId, long data, int tipo_conquista) {
        this.id = id;
        this.userId = userId;
        this.data = data;
        this.tipo_conquista = tipo_conquista;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public int getTipo_conquista() {
        return tipo_conquista;
    }

    public void setTipo_conquista(int tipo_conquista) {
        this.tipo_conquista = tipo_conquista;
    }
}


