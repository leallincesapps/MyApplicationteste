package com.example.estudantebr.myapplicationteste.models;

public class Perfil {

    private String userId;
    private String nick;
    private String status;
    private int pontuacao;
    private String url_imagem;

    public Perfil(String userId, String nick, String status, int pontuacao, String url_imagem) {
        this.userId = userId;
        this.nick = nick;
        this.status = status;
        this.pontuacao = pontuacao;
        this.url_imagem = url_imagem;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getUrl_imagem() {
        return url_imagem;
    }

    public void setUrl_imagem(String url_imagem) {
        this.url_imagem = url_imagem;
    }
}
