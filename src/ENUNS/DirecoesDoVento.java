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
public enum DirecoesDoVento {
    SUL_PARA_NORTE("s_para_n"),
    NORTE_PARA_SUL("n_para_s"),
    LESTE_PARA_OESTE("l_para_o"),
    OESTE_PARA_LESTE("o_para_l"),
    NOROESTE_PARA_SUDESTE("no_para_se"),
    SUDESTE_PARA_NOROESTE("se_para_no"),
    SUDOESTE_PARA_NORDESTE("so_para_ne"),
    NORDESTE_PARA_SUDOESTE("ne_para_so");


    public String valor;

    DirecoesDoVento(String valor) {
        this.valor = valor;
    }

    /**
     *
     * @return
     */
    public String getValor() {
        return valor;
    }
}
