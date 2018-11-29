package com.coldbeer.entidades;

/**
 * Created by Fernando on 28/01/2018.
 */

public class Barril {
    private long id;
    private String data_instalacao;
    private int capacidade;
    private int status;

    public Barril(String data, int capacidade){
        this.setData_instalacao(data);
        this.setCapacidade(capacidade);
        this.setStatus(0);
    }
    public Barril(long id, String data, int capacidade, int status){
        this.setId(id);
        this.setData_instalacao(data);
        this.setCapacidade(capacidade);
        this.setStatus(status);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData_instalacao() {
        return data_instalacao;
    }

    public void setData_instalacao(String data_instalacao) {
        this.data_instalacao = data_instalacao;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    public void dimCapacidade(int capacidade) {
        this.capacidade -= capacidade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Barril instalado: "+getData_instalacao()+" - Capacidade restante: "+getCapacidade()+" - Status: "+getStatus()+"\n";
    }
}
