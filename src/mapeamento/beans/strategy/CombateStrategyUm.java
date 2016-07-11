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
public class CombateStrategyUm extends AbstractStrategyCombate{
    
    public CombateStrategyUm() {
        super();
        setNome("teste1");
        setDuracao(8);
        setPreco(200.00);
        setResultado(1.5);
        
    }

    @Override
    public Double getResultadoPelaIteracao(int iteracao) {
        return null;
    }

    
}
