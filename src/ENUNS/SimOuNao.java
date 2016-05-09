/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ENUNS;

/**
 *
 * @author Gustavo
 */
public enum SimOuNao {
    SIM(true),
    NÃO(false);
    
    final Boolean valor;

    private SimOuNao(Boolean valor) {
        this.valor = valor;
    }
    
    /**
     *
     * @return
     */
    public Boolean getValor(){
        return this.valor;
    }
    
}
