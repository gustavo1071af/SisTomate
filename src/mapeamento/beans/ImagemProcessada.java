/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapeamento.beans;

import java.util.Objects;

/**
 *SISTOM-3
 * @author Gus
 */
public class ImagemProcessada {

    String nomearquivo;
    int vermelhos;
    int verdes;
    int pretos;
    int estado;
    Tomates tomate;

    public String getNomeArquivo() {
        return nomearquivo;
    }

    public void setNomeArquivo(String nomearquivo) {
        this.nomearquivo = nomearquivo;
    }

    public int getVermelhos() {
        return vermelhos;
    }

    public void setVermelhos(int vermelhos) {
        this.vermelhos = vermelhos;
    }

    public int getVerdes() {
        return verdes;
    }

    public void setVerdes(int verdes) {
        this.verdes = verdes;
    }

    public int getPretos() {
        return pretos;
    }

    public void setPretos(int pretos) {
        this.pretos = pretos;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }
    
    public Tomates getTomate() {
        return tomate;
    }

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

    
    
}
