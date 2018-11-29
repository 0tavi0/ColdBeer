package com.coldbeer.controladores;

import com.coldbeer.entidades.Barril;
import com.coldbeer.entidades.Copo;
import com.coldbeer.entidades.Pagamento;
import com.coldbeer.mail.Mail;

import java.text.DateFormat;
import java.util.Date;


/**
 * Created by Fernando on 16/02/2018.
 */

public class MailControlador {

    private static Mail cabecalhoDoEmail(){
        Mail m = new Mail("coldbeerexpress@gmail.com", ",ZcFl_p3t,NIUyg5GZTr");

       // "coldbeerexpress@gmail.com","fermope.m@gmail.com"
        String[] toArr = {"otavio.le@gmail.com"};
        m.setTo(toArr);

        m.setFrom("coldbeerexpress@gmail.com");
        return m;
    }


    private static void enviar(Mail m){
        try {
            //m.addAttachment("pathDoAnexo");//anexo opcional
            m.send();
        }
        catch(RuntimeException rex){

            System.out.println("Erro RunTimeException");
            System.out.println(rex.getMessage());
        }//erro ignorado

        catch(Exception e) {
            //tratar algum outro erro aqui
            System.out.println("Erro Exception");
            System.out.println(e.getMessage());
        }
    }
    public static void enviarNovoPagamento(Pagamento pagamento){

        Mail m = cabecalhoDoEmail();
        m.setSubject("Novo Pagamento");
        m.setBody(pagamento.toString());

        enviar(m);

    }



    public static void enviarNovoPedido(int quantCopos, Copo c){
        Mail m = cabecalhoDoEmail();
        m.setSubject("Novo Pedido");
        m.setBody("<b>____________________________Dados do Pedido______________________</b><br><br>" +
                    "<b>Data:</b> "+ DateFormat.getDateInstance().format(new Date())+"<br>"+
                    "<b>Hora:</b> "+DateFormat.getTimeInstance().format(new Date())+"<br>"+
                    "<b>Tipo do Copo:</b> "+c.getCapacidade()+"ML<br>"+
                    "<b>Quantidade de Copos:</b> "+quantCopos+"<br>" +
                    "<b>_________________________________________________________________</b>");

        enviar(m);
    }






    public static void enviarEncheuOCopo(Copo c, int quantCopos, Barril b){
        System.out.println(
                "Email Enviado" +
                        "\n" +
                        "____________________________Dados do Pedido______________________\n" +
                        "Tipo do Copo: "+c.getCapacidade()+" ml\n"+
                        "Quantidade de Copos: "+quantCopos+"\n" +
                        "Capacidade Atual do Barril: "+b.getCapacidade()+"\n" +
                        "_________________________________________________________________"
        );
        Mail m = cabecalhoDoEmail();
        m.setSubject("Pedido realizado com Sucesso");
        m.setBody("<b>____________________________Dados do Pedido______________________</b><br><br>" +
                "<b>Data:</b> "+ DateFormat.getDateInstance().format(new Date())+"<br>"+
                "<b>Hora:</b> "+DateFormat.getTimeInstance().format(new Date())+"<br>"+
                "<b>Tipo do Copo:</b> "+c.getCapacidade()+" ML ml<br>"+
                "<b>Quantidade de Copos:</b> "+quantCopos+"<br>" +
                "<b>Capacidade Atual do Barril:</b> "+b.getCapacidade()+" ML<br>" +
                "<b>_________________________________________________________________");

        enviar(m);
    }
}
