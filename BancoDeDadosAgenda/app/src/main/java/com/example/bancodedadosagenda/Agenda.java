package com.example.bancodedadosagenda;

import androidx.annotation.NonNull;

public class Agenda {
    //Atributos
    private int id;
    private String nome;
    private String sobrenome;
    private int telefone;

    //Atalho Alt Insert
    //Construtor


    public Agenda(int id, String nome, String sobrenome, int telefone) {
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefone = telefone;
    }
    //Sobrescrever o método toString()

    @NonNull
    @Override
    public String toString() {
        return "ID: " + id + " Nome: " + nome + " Sobrenome: " + sobrenome + " Telefone " + telefone;

    }

    //GET E SET
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getsobrenome() {
        return sobrenome;
    }

    public int getTelefone() {
        return telefone;
    }

    //SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
}