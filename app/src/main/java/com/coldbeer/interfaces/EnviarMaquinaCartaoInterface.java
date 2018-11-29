package com.coldbeer.interfaces;

/**
 * Created by Fernando on 04/01/
 *
 *
 */

public interface EnviarMaquinaCartaoInterface {

   /**
    * Envia para a máquina o valor da compra e retorna se foi confirmado ou não o pagamento.
    * @param valor valor da venda
    * */
    boolean enviarPagamentoCartao(double valor);

    /**
     * metodo para o andamento da venda, utiliza o @metodo enviarPagamentoCartao para verificar e especifica o que fazer
     * com cada resposta.
     * */
    boolean verificarPagamento();
}
