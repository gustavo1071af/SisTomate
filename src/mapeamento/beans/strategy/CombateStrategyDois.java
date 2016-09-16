/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeamento.beans.strategy;

/**
 *SISTOM-15
 * @author Gustavo
 * @version 1.0 Created on 09/07/2016
 */
public class CombateStrategyDois extends AbstractStrategyCombate{
    
    public CombateStrategyDois() {
        super();
        setNome("PARA-AT");
        setDuracao(5);
        setPreco(150.00);
        setResultado(0.4);
        
    }

    @Override
    public Double getResultadoPelaIteracao(int iteracao) {
        return null;
    }

    
}
