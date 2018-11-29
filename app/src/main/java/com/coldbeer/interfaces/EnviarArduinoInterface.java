package com.coldbeer.interfaces;

/**
 * Created by Fernando on 04/01/2018.
 *
 */

public interface EnviarArduinoInterface {
    /**
    * metodo para enviar para encher um copo de chop.
    * @param tamanhoChop esse parametro vai especificar os tipos de chop
    * */
    boolean enviarArduino(int tamanhoChop);
    /**
     * metodo para atualizar a capacidade do barril
     * @param ml designa o quanto de chop foi retirado do barril em ML
     * */
    void atualizarCapacidadeBarril(int ml);

    /**
     * metodo para informar que o barril está com sua capacidade baixa.
     * */
    boolean enviarMsgDeBarrilEsgotando();

    /**
     * metodo para informar que o barril está vazio.
     * */
    boolean enviarMsgDeBarrilEsgotado();

}
