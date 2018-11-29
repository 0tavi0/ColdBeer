package com.coldbeer;

import com.coldbeer.entidades.DescontoCopoG;

import java.text.NumberFormat;

/**
 * Created by Fernando on 17/01/2018.
 */

public class ControladorDePrecos {

    private static double calculaPrecoP(int n, double preco){
        return n*preco;
    }


    private static double calculaPrecoG(int n, double preco){
        switch (n){
            case 1: return n*preco;
            case 2: return n*preco*(1-DescontoCopoG.G2.getDesconto());
            case 3: return n*preco*(1-DescontoCopoG.G3.getDesconto());
            default: return n*preco*(1-DescontoCopoG.G3.getDesconto());
        }

    }
    public static double calculaPreco(int n,double p, int tipo){
        if(tipo ==0){
            return  calculaPrecoG(n,p);
        }else{
            return calculaPrecoP(n,p);
        }
    }
    public static String formataPreco(int n, double p, int tipo){
        return formataValorMoeda(calculaPreco(n,p,tipo));
    }

    private static String formataValorMoeda(Object valor){
        return NumberFormat.getCurrencyInstance().format(valor);

    }

}
