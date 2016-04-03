/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapeamento.beans;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Gus
 */
public class Tomates {

    Talhao talhao;
    String nomeArquivo;
    int numTom;
    int rua;
    String linha;
    String lat;

    public ImagemProcessada getImagemProcessada() {
        return imagemProcessada;
    }

    public void setImagemProcessada(ImagemProcessada imagemProcessada) {
        this.imagemProcessada = imagemProcessada;
    }
    String longi;
    Date data;
    ImagemProcessada imagemProcessada;

    public Talhao getTalhao() {
        return talhao;
    }

    public void setTalhao(Talhao talhao) {
        this.talhao = talhao;
    }
    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public int getNumTom() {
        return numTom;
    }

    public void setNumTom(int numTom) {
        this.numTom = numTom;
    }

    public int getRua() {
        return rua;
    }

    public void setRua(int rua) {
        this.rua = rua;
    }

    public String getLinha() {
        return linha;
    }

    public void setLinha(String linha) {
        this.linha = linha;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    //SISTOM-1
    public int getColuna (){
        return "a".equalsIgnoreCase(linha) ? (rua + (rua - 1)) : rua * 2;
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
        final Tomates other = (Tomates) obj;
        if (this.numTom != other.numTom) {
            return false;
        }
        if (this.rua != other.rua) {
            return false;
        }
        if (!Objects.equals(this.linha, other.linha)) {
            return false;
        }
        
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.talhao, other.talhao)) {
            return false;
        }
        return true;
    }
    
}
