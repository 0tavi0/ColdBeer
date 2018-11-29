package com.coldbeer.entidades;

/**
 * Created by Fernando on 17/02/2018.
 */

public class Pagamento {
    private int metodo;
    private int tipoParcelamento;
    private int numeroParcelas;
    private String valorTotal;
    private String codigoVenda;
    private int retorno;
    private String tempo;
    private String data;

    public Pagamento(int metodo, int tipoParcelamento, int numeroParcelas, String valorTotal, String codigoVenda, int retorno, String tempo, String data){
        setMetodo(metodo);
        setTipoParcelamento(tipoParcelamento);
        setNumeroParcelas(numeroParcelas);
        setValorTotal(valorTotal);
        setCodigoVenda(codigoVenda);
        setRetorno(retorno);
        setTempo(tempo);
        setData(data);
    }


    public int getMetodo() {
        return metodo;
    }

    public void setMetodo(int metodo) {
        this.metodo = metodo;
    }

    public int getTipoParcelamento() {
        return tipoParcelamento;
    }

    public void setTipoParcelamento(int tipoParcelamento) {
        this.tipoParcelamento = tipoParcelamento;
    }

    public int getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(int numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public String getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getCodigoVenda() {
        return codigoVenda;
    }

    public void setCodigoVenda(String codigoVenda) {
        this.codigoVenda = codigoVenda;
    }

    public int getRetorno() {
        return retorno;
    }

    public void setRetorno(int retorno) {
        this.retorno = retorno;
    }

    public String getTempo() {
        return tempo;
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String retorno;
        retorno = "_____________________________Pagamento___________________________\n";
        retorno = retorno + "Método de pagamento (1- Crédito e 2-Débito): "+getMetodo()+"\n";
        retorno = retorno + "Valor Total: "+getValorTotal()+"\n";
        retorno = retorno + "Código da Venda: "+getCodigoVenda()+"\n";
        retorno = retorno + "Resposta do pagamento: "+getRetorno()+"\n";
        retorno = retorno + "Data: "+getData()+"\n";
        retorno = retorno + "Hora: "+getTempo()+"\n";
        retorno = "_____________________________Pagamento___________________________\n";



        return retorno;
    }
}
