/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapeamento;

import javax.swing.JPanel;
import javax.swing.JTextPane;

/**
 *
 * @author Gus
 */
public class PainelEscolhaCombate extends JPanel{

    private JTextPane text;

    public PainelEscolhaCombate() {
        JTextPane jTextPane = new JTextPane();
        jTextPane.setText("---");
        this.text = jTextPane;
    }
    
    

    /**
     * @return the text
     */
    public JTextPane getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(JTextPane text) {
        this.text = text;
    }

  
    
}
