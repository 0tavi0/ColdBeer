package com.coldbeer.entidades;

/**
 * Created by Fernando on 17/01/2018.
 */

public class Copo {
    private long id;
    private String descricao;
    private int capacidade;
    private double preco;

    public Copo(String descricao, int capacidade, double preco){
        this.descricao = descricao;
        this.capacidade = capacidade;
        this.preco = preco;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Copo "+capacidade+"ml - "+ preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
