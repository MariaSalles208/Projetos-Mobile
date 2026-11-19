package com.example.projetorecyclerview.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;


import com.example.projetorecyclerview.R;
import com.example.projetorecyclerview.activity.CartazActivity;
import com.example.projetorecyclerview.model.Filme;

import java.util.List;

public class AdapterFilmes extends RecyclerView.Adapter<AdapterFilmes.MyViewHolder> {

    private List<Filme> listaFilme;

    public AdapterFilmes(List<Filme> listaFilmes) {
        this.listaFilme = listaFilmes;
    }

    @NonNull
    @Override
    // o método cria as 1ªs visualizações
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista,parent,false);
        return new MyViewHolder(itemLista);// MyViewHolter recebe uma View no construtor
    }

    @Override
    // o método que exibe os dados da view
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Filme filme = listaFilme.get(position);
        holder.titulo.setText(filme.getTituloFilme());
        holder.genero.setText(filme.getGenero());
        holder.ano.setText(filme.getAno());
        holder.titulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(v.getContext(), CartazActivity.class);
                intent.putExtra("filme",filme.getTituloFilme());
                Toast.makeText(v.getContext(), "Filme"+ filme.getTituloFilme(), Toast.LENGTH_SHORT).show();
                v.getContext().startActivity(intent);
            }
        });
        holder.titulo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    @Override
    // tamanho da recyclerView
    public int getItemCount() {
        // número de elementos do recyclerView
        return listaFilme.size();
    }

    // criando um classe dentro de uma classe, pede para criar um construtor
    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo;
        TextView ano;
        TextView genero;
        //construtor
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //refrencias
            titulo = itemView.findViewById(R.id.textViewTitulo);
            ano = itemView.findViewById(R.id.textViewAno);
            genero = itemView.findViewById(R.id.textViewGenero);
        }
    }
}
