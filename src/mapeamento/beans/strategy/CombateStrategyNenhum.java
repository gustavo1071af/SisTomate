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
public class CombateStrategyNenhum extends AbstractStrategyCombate{
    
    public CombateStrategyNenhum() {
        super();
        setNome("");
        setDuracao(0);
        setPreco(0.0);
        setResultado(0.0);
        
    }

    @Override
    public Double getResultadoPelaIteracao(int iteracao) {
        return null;
    }

    
}
