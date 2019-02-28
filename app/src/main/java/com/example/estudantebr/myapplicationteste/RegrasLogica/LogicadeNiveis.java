package com.example.estudantebr.myapplicationteste.RegrasLogica;

import android.util.Log;

public class LogicadeNiveis {

    private double experienciaNecessariaParaEvoluir;

    public  LogicadeNiveis(){

    }

    public boolean verificaProximoNivel(int nivelAtual, double experienciaAtual){

        int pontosBase = 2000;
        double EvoluirProximoNivel = pontosBase * (nivelAtual + 1);

        //Teste para verificar XP necessário
        Log.i("EvoluirProximoNivel", "ProximoNivel: " + EvoluirProximoNivel);
        Log.i("ExperienciaAtual", "Experiencia: " + experienciaAtual);
        experienciaNecessariaParaEvoluir = EvoluirProximoNivel;

        if(experienciaAtual >= EvoluirProximoNivel){
            return true;
        }else{
            return false;
        }
    }

    //Verifica Nivel anterior para saber nova quantidade de experiencia
    public int verificanivelAnterior(int nivelAtual){

        int pontosBase = 2000;

        if(nivelAtual <= 1){
            double EvoluirNivelAnterior = pontosBase;
            experienciaNecessariaParaEvoluir = EvoluirNivelAnterior;
        }else{
            double EvoluirNivelAnterior = pontosBase * (nivelAtual);
            experienciaNecessariaParaEvoluir = EvoluirNivelAnterior;
        }

        return (int) experienciaNecessariaParaEvoluir;
    }

    public int novoNivel(boolean verificouProximoNivel, int nivelAtual){

        int novoNivelCalculado = 0;

        if (verificouProximoNivel){

            novoNivelCalculado = nivelAtual + 1;
        }else {
            novoNivelCalculado = nivelAtual;
        }

        return novoNivelCalculado;
    }

    public double getExperienciaNecessariaParaEvoluir() {
        return experienciaNecessariaParaEvoluir;
    }
}
