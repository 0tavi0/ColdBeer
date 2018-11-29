package com.coldbeer.interfaces;

/**
 * Created by Fernando on 04/01/2018.
 *
 */

public interface EscolherChopInterface {




    /**  Metodo para calcular o valor total que o cliente vai pagar de chop
     *  Baseado na n&uacute;mero de chops dos tamanhos pr&eacute; determinados
     *  Vai ser usado para atualizar o valor na tela e enviar para a pr&oacute;xima tela o valor.
     * */
    double calcularValorTotalDoChop(int numeroChopsP, int numeroChopsG);

    /**
     *   Metodo para verificar se h&aacute; chop suficiente no barril.
     *   Quando for modificado o numero de chops vai ser testado, se n&atilde;o tiver travar na quantidade m&aacute;xima.
     * */
    boolean verificarDisponibilidadeDoChop(int numeroChopsP, int numeroChopsG);


    /* Vai ser usado na tela de enviarArduino
    /** Usado para notificar quando o barril estiver esgotando
    boolean enviarMsgDeBarrilEsgotando();

    /** Usado para notificar quando o barril estiver esgotado travar o aplicativo em uma tela pedindo desculpas por não ter mais chop
    boolean enviarMsgDeBarrilEsgotado();
    */



}
