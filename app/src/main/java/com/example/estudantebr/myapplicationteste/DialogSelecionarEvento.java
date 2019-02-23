package com.example.estudantebr.myapplicationteste;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DialogSelecionarEvento extends DialogFragment {

    public DialogSelecionarEvento() {
    }

    private EndDialogSelecionarEvento endDialogSelecionarEvento;

    public interface EndDialogSelecionarEvento {

        void endDialogSelecionarEvento(ArrayList<Evento> arrayList_evento);
    }

    public void setListening(EndDialogSelecionarEvento endDialogSelecionarEvento){
        this.endDialogSelecionarEvento = endDialogSelecionarEvento;
    }


    private DataAdapterSelecionarEventos adapter_selecionarEventos;
    private ArrayList<Boolean> arrayList = new ArrayList<>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_selecionar_evento, null);
        alertDialog.setView(view);

        for(Evento evento : new BancoDadosEventos().getTodosEventos()){
            arrayList.add(false);
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter_selecionarEventos = new DataAdapterSelecionarEventos(getContext(), arrayList);
        recyclerView.setAdapter(adapter_selecionarEventos);

        alertDialog.setTitle("Selecione os eventos para este dia");

        alertDialog.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                ArrayList<Evento> arrayList_evento = new ArrayList<>();
                for(int i = 0; i < arrayList.size(); i++){
                    if(arrayList.get(i)){
                        //evento marcado como verdadeiro para o dia
                        Evento evento = new BancoDadosEventos().getEvento(i);

                        if(evento!= null)
                            arrayList_evento.add(evento);
                    }
                }

                if(endDialogSelecionarEvento != null) endDialogSelecionarEvento.endDialogSelecionarEvento(arrayList_evento);
                dismiss();

            }

        });




        alertDialog.setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        });

        return alertDialog.create();
    }



}
