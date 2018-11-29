package com.coldbeer.entidades;

/**
 * Created by Fernando on 17/01/2018.
 */

public enum DescontoCopoG {
    G2(
            0.05   //Desconto de 2 Copos G
    ),
    G3(
            0.10   //Desconto de 3 Copos G
    );
    private double desconto;

    private DescontoCopoG(double desconto){
        this.desconto = desconto;
    }

    public double getDesconto() {
        return desconto;
    }
}
