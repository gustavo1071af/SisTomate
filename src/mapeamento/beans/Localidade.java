/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapeamento.beans;

import java.util.Date;
import java.util.List;

/**
 *SISTOM-8
 * @author Gus
 */
public class Localidade {

    private int id;
    private String estacao;
    private String latitude;
    private String longitude;
    private String altitude;
    private Date coletaInicio;
    private Date coletaFim;
    private List<DadosMeteorologicos> dadosMeteorologicos;

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
     * @return the estacao
     */
    public String getEstacao() {
        return estacao;
    }

    /**
     * @param estacao the estacao to set
     */
    public void setEstacao(String estacao) {
        this.estacao = estacao;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the altitude
     */
    public String getAltitude() {
        return altitude;
    }

    /**
     * @param altitude the altitude to set
     */
    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    /**
     * @return the coletaInicio
     */
    public Date getColetaInicio() {
        return coletaInicio;
    }

    /**
     * @param coletaInicio the coletaInicio to set
     */
    public void setColetaInicio(Date coletaInicio) {
        this.coletaInicio = coletaInicio;
    }

    /**
     * @return the coletaFim
     */
    public Date getColetaFim() {
        return coletaFim;
    }

    /**
     * @param coletaFim the coletaFim to set
     */
    public void setColetaFim(Date coletaFim) {
        this.coletaFim = coletaFim;
    }

    /**
     * @return the dadosMeteorologicos
     */
    public List<DadosMeteorologicos> getDadosMeteorologicos() {
        return dadosMeteorologicos;
    }

    /**
     * @param dadosMeteorologicos the dadosMeteorologicos to set
     */
    public void setDadosMeteorologicos(List<DadosMeteorologicos> dadosMeteorologicos) {
        this.dadosMeteorologicos = dadosMeteorologicos;
    }

}
