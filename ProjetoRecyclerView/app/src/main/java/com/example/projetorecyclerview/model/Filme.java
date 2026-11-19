package com.example.projetorecyclerview.model;


import java.io.Serializable;

public class Filme implements Serializable {
    private String tituloFilme;
    private String genero;
    private String ano;

    public Filme(String tituloFilme,  String ano,String genero) {
        this.tituloFilme = tituloFilme;
        this.genero = genero;
        this.ano = ano;
    }

    public String getTituloFilme() {
        return tituloFilme;
    }

    public void setTituloFilme(String tituloFilme) {
        this.tituloFilme = tituloFilme;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
