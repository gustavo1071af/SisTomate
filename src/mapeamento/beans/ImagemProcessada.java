/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapeamento.beans;

import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *SISTOM-3
 * @author Gus
 */
public class ImagemProcessada implements Cloneable{

    private String nomearquivo;
    private int vermelhos;
    private int verdes;
    private int pretos;
    private int estado;
    private Tomates tomate;
    //Não guarda no BD 
    //SISTOM-10
    private Double estadoComVariacao;

    /**
     *
     * @return
     */
    public String getNomeArquivo() {
        return nomearquivo;
    }

    /**
     *
     * @param nomearquivo
     */
    public void setNomeArquivo(String nomearquivo) {
        this.nomearquivo = nomearquivo;
    }

    /**
     *
     * @return
     */
    public int getVermelhos() {
        return vermelhos;
    }

    /**
     *
     * @param vermelhos
     */
    public void setVermelhos(int vermelhos) {
        this.vermelhos = vermelhos;
    }

    /**
     *
     * @return
     */
    public int getVerdes() {
        return verdes;
    }

    /**
     *
     * @param verdes
     */
    public void setVerdes(int verdes) {
        this.verdes = verdes;
    }

    /**
     *
     * @return
     */
    public int getPretos() {
        return pretos;
    }

    /**
     *
     * @param pretos
     */
    public void setPretos(int pretos) {
        this.pretos = pretos;
    }

    /**
     *
     * @return
     */
    public int getEstado() {
        return estado;
    }

    /**
     *
     * @param estado
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    /**
     *
     * @return
     */
    public Tomates getTomate() {
        return tomate;
    }

    /**
     *
     * @param tomate
     */
    public void setTomate(Tomates tomate) {
        this.tomate = tomate;
    }
    
    
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ImagemProcessada other = (ImagemProcessada) obj;
        if (this.vermelhos != other.vermelhos) {
            return false;
        }
        if (this.verdes != other.verdes) {
            return false;
        }
        if (this.pretos != other.pretos) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.nomearquivo, other.nomearquivo)) {
            return false;
        }
        if (!Objects.equals(this.tomate, other.tomate)) {
            return false;
        }
        return true;
    }

    /**
     * @return the estadoComVariacao
     */
    public Double getEstadoComVariacao() {
        return this.estadoComVariacao;
    }
    
    /**
     * 
     */
    public void setEstadoComVariacaoInicio() {
        this.estadoComVariacao = new Double(estado);
       
    }

    /**
     * @param estadoComVariacao the estadoComVariacao to set
     */
    public void setEstadoComVariacao(Double estadoComVariacao) {
        this.estadoComVariacao = estadoComVariacao;
    }

    @Override
    public ImagemProcessada clone(){
        ImagemProcessada novaImagemProcessada = new ImagemProcessada();
        try {
            novaImagemProcessada = (ImagemProcessada)super.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ImagemProcessada.class.getName()).log(Level.SEVERE, null, ex);
        }
        return novaImagemProcessada;
    }
    
    
}
