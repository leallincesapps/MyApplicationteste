package com.example.estudantebr.myapplicationteste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter_Dias extends RecyclerView.Adapter<DataAdapter_Dias.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(Dia dia);

    }

    private OnItemClickListener mOnItemClickListener;
    private Context context;

    private ArrayList<Dia> diaArrayList;

    public DataAdapter_Dias(Context context, ArrayList<Dia> diaArrayList, OnItemClickListener mOnItemClickListener) {
        this.context = context;
        this.diaArrayList = diaArrayList;
        this.mOnItemClickListener = mOnItemClickListener;

    }




    @Override
    public DataAdapter_Dias.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_dia, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final DataAdapter_Dias.ViewHolder viewHolder, final int position) {

        final Dia dia = diaArrayList.get(position);

        viewHolder.textView_dia.setText("Dia " + Integer.toString(position));
        //eventos
        String string_eventos = "";
        for(Evento evento: dia.getEventos()){
            string_eventos = string_eventos + "\n" + evento.getNome();
        }
        if(string_eventos.equals("")) string_eventos = "(sem eventos)";
        viewHolder.textView_eventos.setText(string_eventos);
        //cardview
        viewHolder.cardView_dia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnItemClickListener != null) mOnItemClickListener.onItemClick(dia);
            }
        });


    }

    @Override
    public int getItemCount() {

        return diaArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{


        private CardView cardView_dia;
        private TextView textView_dia;
        private TextView textView_eventos;


        public ViewHolder(View view) {
            super(view);

            cardView_dia = view.findViewById(R.id.cardView_dia);
            textView_dia = view.findViewById(R.id.textView_dia);
            textView_eventos = view.findViewById(R.id.textView_eventos);


        }


    }

}
