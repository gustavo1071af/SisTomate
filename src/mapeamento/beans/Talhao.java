/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeamento.beans;

import java.util.Date;

/**
 *
 * @author Gustavo
 */
public class Talhao {
    private String areaCultivada;//id do talhao
    private Date dataPlantio;
    private Date dataColheira;
    private int qtdRuas;
    private int qtd_TomatesPorLinhas;

    public String getAreaCultivada() {
        return areaCultivada;
    }

    public void setAreaCultivada(String areaCultivada) {
        this.areaCultivada = areaCultivada;
    }

    public Date getDataPlantio() {
        return dataPlantio;
    }

    public void setDataPlantio(Date dataPlantio) {
        this.dataPlantio = dataPlantio;
    }

    public Date getDataColheira() {
        return dataColheira;
    }

    public void setDataColheira(Date dataColheira) {
        this.dataColheira = dataColheira;
    }

    public int getQtdRuas() {
        return qtdRuas;
    }

    public void setQtdRuas(int qtdRuas) {
        this.qtdRuas = qtdRuas;
    }

    public int getQtd_TomatesPorLinhas() {
        return qtd_TomatesPorLinhas;
    }

    public void setQtd_TomatesPorLinhas(int qtd_TomatesPorLinhas) {
        this.qtd_TomatesPorLinhas = qtd_TomatesPorLinhas;
    }
    
}
