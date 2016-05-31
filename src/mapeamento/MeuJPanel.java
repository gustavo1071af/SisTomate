/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapeamento;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import mapeamento.beans.ImagemProcessada;
import mapeamento.beans.Tomates;

/**
 *
 * @author Gus
 */
public class MeuJPanel extends JPanel{

    Tomates tom;

    public Tomates getTom() {
        return tom;
    }

    public void setTom(Tomates tom) {
        this.tom = tom;
    }
    
    @Override
    public void paintComponent(Graphics g) {
        //escolher cor de acordo com o estado
        if (tom != null) {
            ImagemProcessada imagemProcessada = tom.getImagemProcessada();
            int estado = imagemProcessada.getEstado();
            switch (estado) {
                case 1: {
                    g.setColor(new Color(0, 128, 0));
                    break;
                }//Green
                case 2: {
                    g.setColor(new Color(144, 238, 144));
                    break;
                }//LightGreen
                case 3: {
                    g.setColor(new Color(255, 255, 0));
                    break;
                }//Yellow
                case 4: {
                    g.setColor(new Color(255, 165, 0));
                    break;
                }//Orange
                case 5: {
                    g.setColor(new Color(255, 140, 0));
                    break;
                }//DarkOrange
                case 6: {
                    g.setColor(new Color(255, 69, 0));
                    break;
                }//OrangeRed
                default: {
                    g.setColor(new Color(0, 128, 0));
                    break;
                }//Green

            }//switch
        }//if
        else{//Foi necessário para a classe Mapa.java que possui o bean sem tom
             g.setColor(new Color(0, 128, 0));
        }//else
        //Fazer Retângulo
         g.fillRect(0, 0, 500, 500);
    }
    
}
