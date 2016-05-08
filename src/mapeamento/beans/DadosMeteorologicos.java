/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapeamento.beans;

import java.util.Date;

/**
 *SISTOM-8
 * @author Gus
 */
public class DadosMeteorologicos {

    private int id;
    private Date data;
    private double temperaturaMedia;
    private int umidade;
    private Boolean chuva;
    private Localidade localidade;
   

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the temperaturaMedia
     */
    public double getTemperaturaMedia() {
        return temperaturaMedia;
    }

    /**
     * @param temperaturaMedia the temperaturaMedia to set
     */
    public void setTemperaturaMedia(double temperaturaMedia) {
        this.temperaturaMedia = temperaturaMedia;
    }

    /**
     * @return the humidade
     */
    public int getUmidade() {
        return umidade;
    }

    /**
     * @param humidade the humidade to set
     */
    public void setUmidade(int humidade) {
        this.umidade = humidade;
    }

    /**
     * @return the chuva
     */
    public Boolean getChuva() {
        return chuva;
    }

    /**
     * @param chuva the chuva to set
     */
    public void setChuva(Boolean chuva) {
        this.chuva = chuva;
    }

    /**
     * @return the localidade
     */
    public Localidade getLocalidade() {
        return localidade;
    }

    /**
     * @param localidade the localidade to set
     */
    public void setLocalidade(Localidade localidade) {
        this.localidade = localidade;
    }
    
    
}
