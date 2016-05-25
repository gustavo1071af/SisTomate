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
    private double umidade;
    private Boolean chuva;
    private double temperaturaMinima;
    private double temperaturaMaxima;
    private double precipitacao;
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
    public double getUmidade() {
        return umidade;
    }

    /**
     * @param hmidade the umidade to set
     */
    public void setUmidade(double umidade) {
        this.umidade = umidade;
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

    /**
     *
     * @return
     */
    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    /**
     *
     * @param temperaturaMinima
     */
    public void setTemperaturaMinima(double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }

    /**
     *
     * @return
     */
    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    /**
     *
     * @param temperaturaMaxima
     */
    public void setTemperaturaMaxima(double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    /**
     *
     * @return
     */
    public double getPrecipitacao() {
        return precipitacao;
    }

    /**
     *
     * @param precipitacao
     */
    public void setPrecipitacao(double precipitacao) {
        this.precipitacao = precipitacao;
    }
    
    
}
