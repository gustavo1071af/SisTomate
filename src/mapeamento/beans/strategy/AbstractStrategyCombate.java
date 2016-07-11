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
public abstract class AbstractStrategyCombate {
    
    private String nome;
    
    private int duracao;
    
    private Double preco;
    
    private Double Resultado;

    public AbstractStrategyCombate() {
        super();
    }
    
    

    /**
     * @return the duracao
     */
    public int getDuracao() {
        return duracao;
    }

    /**
     * @param duracao the duracao to set
     */
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    /**
     * @return the preco
     */
    public Double getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    /**
     * @return the Resultado
     */
    public Double getResultado() {
        return Resultado;
    }

    /**
     * @param Resultado the Resultado to set
     */
    public void setResultado(Double Resultado) {
        this.Resultado = Resultado;
    }
    
    /**
     *
     * @param iteracao
     * @return
     */
    public abstract Double getResultadoPelaIteracao(final int iteracao);

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    /**
     *
     * @return
     */
    public String getInfoString(){
         String baseString = "Nome: %s Duração: %s Preco: %s";
         String retorno = String.format(baseString, this.nome, this.duracao, this.preco);
         return retorno;
     }
    
}
