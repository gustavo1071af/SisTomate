/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mapeamento;

import ENUNS.DirecoesDoVento;
import ENUNS.SimOuNao;
import java.awt.BorderLayout;
import java.util.Date;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Gus
 */
public class Preencher_Variaveis extends javax.swing.JPanel {

    /**
     * Creates new form Preencher_Variaveis
     */
    String talhao;
    Abertura frame;
    Preencher_Variaveis(String talhao, Abertura frame) {
        this.talhao = talhao;
        this.frame = frame;
          
        initComponents();
        this.setLayout(new BorderLayout());
        this.add(titulo, BorderLayout.NORTH);
        this.add(form, BorderLayout.CENTER);
        this.add(rodape, BorderLayout.SOUTH);
        
        //SISTOM-9
        DirecoesDoVento[] direcoes = DirecoesDoVento.values();
        ComboBoxModel cbModelDirecoes = new DefaultComboBoxModel(direcoes);
        this.comboBox_direcaoDoAr.setModel(cbModelDirecoes);
        
        SimOuNao[] opcoes = SimOuNao.values();
        ComboBoxModel cbModelSimOuNao = new DefaultComboBoxModel(opcoes);
        this.comboBox_chuva.setModel(cbModelSimOuNao);
        
        String[] formats = new String[1];
        formats[0] = "dd//MM/yyyy";
        xDatePicker_data.setFormats(formats);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        groupMediaHistorica = new javax.swing.ButtonGroup();
        form = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        radio_mediaHistorica2 = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        xDatePicker_data = new org.jdesktop.swingx.JXDatePicker();
        comboBox_direcaoDoAr = new javax.swing.JComboBox<>();
        textField_inter = new javax.swing.JTextField();
        textField_Temp = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        radio_mediaHistorica1 = new javax.swing.JRadioButton();
        textField_Humid = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        comboBox_chuva = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        titulo = new javax.swing.JLabel();
        rodape = new javax.swing.JPanel();
        iniciarSimulacao = new javax.swing.JButton();

        form.setLayout(new java.awt.GridBagLayout());

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel5.setText("Media Histórica:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 119, 0, 0);
        form.add(jLabel5, gridBagConstraints);

        groupMediaHistorica.add(radio_mediaHistorica2);
        radio_mediaHistorica2.setText("10");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        form.add(radio_mediaHistorica2, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel13.setText("Anos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        form.add(jLabel13, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 2, 0, 0);
        form.add(xDatePicker_data, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 2, 0, 0);
        form.add(comboBox_direcaoDoAr, gridBagConstraints);

        textField_inter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField_interActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.ipadx = 30;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 2, 0, 0);
        form.add(textField_inter, gridBagConstraints);

        textField_Temp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField_TempActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 53;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 2, 0, 0);
        form.add(textField_Temp, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel1.setText("Umidade Rel. do Ar:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 74, 0, 0);
        form.add(jLabel1, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel10.setText("%");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 65;
        gridBagConstraints.insets = new java.awt.Insets(14, 5, 0, 0);
        form.add(jLabel10, gridBagConstraints);

        groupMediaHistorica.add(radio_mediaHistorica1);
        radio_mediaHistorica1.setText("5");
        radio_mediaHistorica1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_mediaHistorica1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 2, 0, 0);
        form.add(radio_mediaHistorica1, gridBagConstraints);

        textField_Humid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField_HumidActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 53;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 2, 0, 0);
        form.add(textField_Humid, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel7.setText("ºC");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 65;
        gridBagConstraints.insets = new java.awt.Insets(14, 5, 0, 0);
        form.add(jLabel7, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Tahoma", 2, 11)); // NOI18N
        jLabel14.setText("Dias");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.ipadx = 80;
        gridBagConstraints.ipady = 8;
        gridBagConstraints.insets = new java.awt.Insets(14, 0, 0, 0);
        form.add(jLabel14, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel4.setText("Data:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 185, 0, 0);
        form.add(jLabel4, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel8.setText("Chuva:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 172, 0, 0);
        form.add(jLabel8, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel3.setText("Direção do Vento:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 101, 0, 0);
        form.add(jLabel3, gridBagConstraints);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setText("Temperatura Média:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 84, 0, 0);
        form.add(jLabel2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(13, 2, 0, 0);
        form.add(comboBox_chuva, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel11.setText("Interações:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(14, 146, 0, 0);
        form.add(jLabel11, gridBagConstraints);

        titulo.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Preencher Fatores Determinantes para a Simulação");

        iniciarSimulacao.setText("Iniciar Simulação");
        iniciarSimulacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarSimulacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rodapeLayout = new javax.swing.GroupLayout(rodape);
        rodape.setLayout(rodapeLayout);
        rodapeLayout.setHorizontalGroup(
            rodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rodapeLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iniciarSimulacao)
                .addGap(62, 62, 62))
        );
        rodapeLayout.setVerticalGroup(
            rodapeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rodapeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iniciarSimulacao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titulo, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(77, 77, 77))
            .addComponent(rodape, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(form, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 168, Short.MAX_VALUE)
                .addComponent(rodape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textField_TempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField_TempActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField_TempActionPerformed

    private void textField_HumidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField_HumidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField_HumidActionPerformed

    private void textField_interActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField_interActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField_interActionPerformed

    private void iniciarSimulacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarSimulacaoActionPerformed
        //criar validador do form futuramente
        //SISTOM-4
        
        int umid = Integer.parseInt(textField_Humid.getText());
        int temp = Integer.parseInt(textField_Temp.getText());
        int qtdInter = Integer.parseInt(textField_inter.getText());
        
        //SISTOM-9
        DirecoesDoVento direcao = (DirecoesDoVento)comboBox_direcaoDoAr.getSelectedItem();
        SimOuNao chuva = (SimOuNao)comboBox_chuva.getSelectedItem();
        Date data = xDatePicker_data.getDate();
        int mediaHistorica = pegaTexto(groupMediaHistorica);
        PainelDeSimulacao janela = new PainelDeSimulacao(frame,talhao, umid, temp, direcao, chuva, data, mediaHistorica, qtdInter);             
        frame.mudaTela(janela);
    }//GEN-LAST:event_iniciarSimulacaoActionPerformed

    private void radio_mediaHistorica1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_mediaHistorica1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_mediaHistorica1ActionPerformed
 
    public int pegaTexto(ButtonGroup grupo) {
        Enumeration<AbstractButton> elementos = grupo.getElements();
        while (elementos.hasMoreElements()) {
            AbstractButton button = elementos.nextElement();
            if (button.isSelected()) {
                return Integer.parseInt(button.getText());
            }
        }
        return 0;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBox_chuva;
    private javax.swing.JComboBox<String> comboBox_direcaoDoAr;
    private javax.swing.JPanel form;
    private javax.swing.ButtonGroup groupMediaHistorica;
    private javax.swing.JButton iniciarSimulacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton radio_mediaHistorica1;
    private javax.swing.JRadioButton radio_mediaHistorica2;
    private javax.swing.JPanel rodape;
    private javax.swing.JTextField textField_Humid;
    private javax.swing.JTextField textField_Temp;
    private javax.swing.JTextField textField_inter;
    private javax.swing.JLabel titulo;
    private org.jdesktop.swingx.JXDatePicker xDatePicker_data;
    // End of variables declaration//GEN-END:variables
}
