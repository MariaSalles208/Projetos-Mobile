package com.example.bancodedadoslocal;

import androidx.annotation.NonNull;

public class Produto {
    //Atributos
    private int id;
    private String nome;
    private double preco;
    private int quantidade;

    //Atalho Alt Insert
    //Construtor


    public Produto(int id, String nome, double preco, int quantidade) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.quantidade = quantidade;
    }
    //Sobrescrever o método toString()

    @NonNull
    @Override
    public String toString() {
        return "ID: " + id + " Nome: " + nome + " R$ " + preco + " Qtde: " + quantidade;

    }

    //GET E SET
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setId(int id) {
        this.id = id;
    }

    //SETTERS
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
