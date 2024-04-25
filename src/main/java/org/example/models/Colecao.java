package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Colecao {
    private int id;
    private String nome;
    private List<Carta> colecao = new ArrayList<>();

    public Colecao() {
    }

    public Colecao(int id, String nome, List<Carta> cartas) {
        this.id = id;
        this.nome = nome;
        this.colecao = cartas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Carta> getColecao() {
        return colecao;
    }

    public void setColecao(List<Carta> colecao) {
        this.colecao = colecao;
    }

    @Override
    public String toString() {
        return "Colecao{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cartas=" + colecao +
                '}';
    }
}
