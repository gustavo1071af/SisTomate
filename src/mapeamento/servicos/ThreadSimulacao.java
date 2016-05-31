/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeamento.servicos;

import mapeamento.PainelDeSimulacao;

/**
 *
 * @author Gustavo
 */
public class ThreadSimulacao implements Runnable{

    private final PainelDeSimulacao painelDeSimulacao;

    public ThreadSimulacao(final PainelDeSimulacao painelDeSimulacao) {
        this.painelDeSimulacao = painelDeSimulacao;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                //final MapaParaSimulacao mapaParaSimulacao = painelDeSimulacao.getMapaParaSimulacao();
                //final MeuJPanel[][] matrizpainel = mapaParaSimulacao.getMatrizpainel();
                //final Talhao talhao = mapaParaSimulacao.getTalhao();
               
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

    }
    
}
