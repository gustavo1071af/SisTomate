/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeamento.ENUNS;

import mapeamento.beans.strategy.AbstractStrategyCombate;
import mapeamento.beans.strategy.CombateStrategyDois;
import mapeamento.beans.strategy.CombateStrategyNenhum;
import mapeamento.beans.strategy.CombateStrategyUm;

/**
 *SISTOM-15
 * @author Gustavo
 * @version 1.0 Created on 09/07/2016
 */
public enum Combate {
    NENHUM(new CombateStrategyNenhum()),
    COMBATE1(new CombateStrategyUm()),
    COMBATE2(new CombateStrategyDois());

    /**
     * <p>
     * </p>
     *
     * @param strategy
     */
    private Combate(final AbstractStrategyCombate strategy) {
        setStrategy(strategy);
    }

    private AbstractStrategyCombate strategy;

    /**
     * @return the strategy
     */
    public AbstractStrategyCombate getStrategy() {
        return strategy;
    }

    /**
     * @param strategy the strategy to set
     */
    public void setStrategy(AbstractStrategyCombate strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return getStrategy().getNome();
    }
    
    
}
