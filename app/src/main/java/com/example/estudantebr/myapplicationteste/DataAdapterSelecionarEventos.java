package com.example.estudantebr.myapplicationteste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class DataAdapterSelecionarEventos extends RecyclerView.Adapter<DataAdapterSelecionarEventos.ViewHolder> {

    private Context context;

    private ArrayList<Boolean> arrayList;//indice = id_evento e valor = ativo/inativo

    public DataAdapterSelecionarEventos(Context context, ArrayList<Boolean> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public DataAdapterSelecionarEventos.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_dialog_selecionar_evento, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapterSelecionarEventos.ViewHolder viewHolder, final int position) {

        String nome_evento = new BancoDadosEventos().getEvento(position).getNome();
        boolean ativo = arrayList.get(position);

        viewHolder.textView_nome_evento.setText(nome_evento);

        viewHolder.aSwitch.setChecked(ativo);

        viewHolder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                arrayList.set(position, isChecked);
            }
        });

    }

    @Override
    public int getItemCount() {

        return arrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        private TextView textView_nome_evento;
        private Switch aSwitch;


        public ViewHolder(View view) {
            super(view);

            textView_nome_evento = view.findViewById(R.id.textView_nome_evento);
            aSwitch = view.findViewById(R.id.aSwitch);


        }


    }

}
