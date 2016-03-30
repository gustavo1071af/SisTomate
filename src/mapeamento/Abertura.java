/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
Authors:
 */

package mapeamento;

import java.awt.Image;
import static java.awt.SystemColor.menu;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Gus
 */
public class Abertura extends javax.swing.JFrame {

    /**
     * Creates new form Abertura
     */
    /*
    private PDI pdi;
   private Arquivo arq = new Arquivo(); 
   */
    private final JComboBox combo;
    private final JLabel label1;
    
    public Abertura() {
        initComponents();
       //Criando combobox de talhoes no menu
        String [] talhoes = null;
         combo = new javax.swing.JComboBox();
         
         String sql = "SELECT area_Cultivada "
                + " FROM talhao"
                + " ORDER BY 1 asc";
        Connection con = new Conn().getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs.toString());
             rs.last();
            //arredondando para cima
            int tam = rs.getRow();
            
            rs.beforeFirst();
            talhoes = new String[tam];
            int cont=0;
            while(rs.next()) {
                talhoes[cont]= rs.getString("area_Cultivada");
                cont++;
            }
              combo.setModel(new javax.swing.DefaultComboBoxModel(talhoes));
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro no SQL1:" + e.getMessage());
        }
        
        alteraLabelPrincipal();       
      
        combo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboActionPerformed(evt);
            }

            private void comboActionPerformed(ActionEvent evt) {
                
                alteraLabelPrincipal();
                //mudando para a tela principal
                setContentPane(Principal);
                getContentPane().repaint();
                validate();
                Tools.enable(true);
            }
        });
        label1 = new javax.swing.JLabel();
        label1.setText("                                            Selecione o Talhão:");
        jMenuBar1.add(label1);
        jMenuBar1.add(combo);
        
        //inserir logo
        ImageIcon img = new ImageIcon(getClass().getResource("/mapeamento/Sistomate2.jpg"));
         ImageIcon thumbnail = null;
        thumbnail = new ImageIcon(  
         img.getImage().getScaledInstance(450, 308, Image.SCALE_DEFAULT)); 
        Logo.setIcon(thumbnail);
       
        
       // Tools.enable(false);
        setTitle("SisTomate");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        this.setVisible(true);
        System.out.println();
    }

    public void alteraLabelrodape(String text){
        labelRodape.setText(text);
    }
    
    public void alteraLabelPrincipal(){
    //mudando labels da tela principal
                System.out.println(combo.getSelectedItem().toString());
                String sql2 = "SELECT *"
                + " FROM talhao"
                + " WHERE area_Cultivada = '"+ combo.getSelectedItem().toString()+"';";
                String sql3 = "SELECT count(*) as qtd_amostra"
                + " FROM Tomate t"
                + " WHERE t.idTalhao = '"+ combo.getSelectedItem().toString()+"';";
                String sql4= "SELECT count(*) as qtd_imgsNprocessadas FROM tomate t where t.idTalhao = '"+combo.getSelectedItem().toString()+"' and not exists(select * from imagem_processada ip where t.numtom = ip.Tomate_numtom  and t.rua = ip.Tomate_rua  and t.linha = ip.Tomate_linha  and t.data = ip.Tomate_data and t.idTalhao = ip.idTalhao)";
                Connection con = new Conn().getConnection();
                try {
                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt2.executeQuery(sql2);
                    Statement stmt3 = con.createStatement();
                    ResultSet rs3 = stmt3.executeQuery(sql3);
                    Statement stmt4 = con.createStatement();
                    ResultSet rs4 = stmt4.executeQuery(sql4);
                    if (rs2.next()){
                        label_Area.setText(rs2.getString("area_Cultivada"));
                        label_Colheita.setText(rs2.getString("data_Colheita"));
                        
                        label_Plantio.setText(rs2.getString("data_Plantio"));
                        if(rs3.next())
                            label_qtd_Amostras.setText(rs3.getString("qtd_amostra"));
                        if(rs4.next())
                            label_qtd_n_Processadas.setText(rs4.getString("qtd_imgsNprocessadas"));
                    }
                     con.close();
                    } catch (SQLException e) {
                    System.out.println("Erro no SQL2:" + e.getMessage());
                }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        Principal = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        label_Area = new javax.swing.JLabel();
        label_Plantio = new javax.swing.JLabel();
        label_Colheita = new javax.swing.JLabel();
        labelRodape = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        label_qtd_Amostras = new javax.swing.JLabel();
        Logo = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        label_qtd_n_Processadas = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        File = new javax.swing.JMenu();
        Carregabd = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemMap = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        Exit = new javax.swing.JMenuItem();
        Tools = new javax.swing.JMenu();
        processarImagens = new javax.swing.JMenuItem();
        simularRequeima = new javax.swing.JMenuItem();
        help = new javax.swing.JMenu();
        Sobre = new javax.swing.JMenuItem();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Área Cultivada:");

        jLabel4.setText("Data do Plantio:");

        jLabel5.setText("Data Prevista para Colheita:");

        labelRodape.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRodape.setText("Principal");

        jLabel2.setText("Qtd. de Amostras cadastradas:");

        Logo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Logo.setText(".");

        jLabel6.setText("Qtd. Imagens não Processadas:");

        javax.swing.GroupLayout PrincipalLayout = new javax.swing.GroupLayout(Principal);
        Principal.setLayout(PrincipalLayout);
        PrincipalLayout.setHorizontalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(labelRodape, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Logo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_qtd_n_Processadas))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_qtd_Amostras))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_Colheita))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_Plantio))
                    .addGroup(PrincipalLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label_Area)))
                .addContainerGap(224, Short.MAX_VALUE))
        );
        PrincipalLayout.setVerticalGroup(
            PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PrincipalLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(label_Area))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(label_Plantio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(label_Colheita))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(label_qtd_Amostras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(label_qtd_n_Processadas))
                .addGap(18, 18, 18)
                .addComponent(Logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                .addComponent(labelRodape))
        );

        jMenuBar1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        File.setText("Arquivo");

        Carregabd.setText("Listar amostras");
        Carregabd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CarregabdActionPerformed(evt);
            }
        });
        File.add(Carregabd);
        File.add(jSeparator1);

        itemMap.setText("Mapa");
        itemMap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMapActionPerformed(evt);
            }
        });
        File.add(itemMap);
        File.add(jSeparator2);

        Exit.setText("Exit");
        File.add(Exit);

        jMenuBar1.add(File);

        Tools.setText("Ferramentas");
        Tools.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ToolsActionPerformed(evt);
            }
        });

        processarImagens.setText("Processar imagens em lote ");
        processarImagens.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processarImagensActionPerformed(evt);
            }
        });
        Tools.add(processarImagens);

        simularRequeima.setText("Simular infestação de requeima");
        simularRequeima.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simularRequeimaActionPerformed(evt);
            }
        });
        Tools.add(simularRequeima);

        jMenuBar1.add(Tools);

        help.setText("Ajuda");
        help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpActionPerformed(evt);
            }
        });

        Sobre.setText("Sobre");
        Sobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SobreActionPerformed(evt);
            }
        });
        help.add(Sobre);

        jMenuBar1.add(help);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Principal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Principal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_helpActionPerformed

    private void SobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SobreActionPerformed
Help janela = new Help();
        this.setContentPane(janela);
        this.getContentPane().repaint();
        this.validate();
        // TODO add your handling code here:
    }//GEN-LAST:event_SobreActionPerformed

    private void itemMapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMapActionPerformed
        // TODO add your handling code here
  
        Mapa janela = new Mapa(combo.getSelectedItem().toString());
        this.setContentPane(janela);
        this.getContentPane().repaint();
        this.validate();
        Tools.enable(true);
    }//GEN-LAST:event_itemMapActionPerformed

    private void CarregabdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CarregabdActionPerformed
        // TODO add your handling code here:
        CarregaBD janela = new CarregaBD(combo.getSelectedItem().toString());
        this.setContentPane(janela);
        this.getContentPane().repaint();
        this.validate();
        Tools.enable(true);
    }//GEN-LAST:event_CarregabdActionPerformed

    private void ToolsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ToolsActionPerformed

    }//GEN-LAST:event_ToolsActionPerformed

    private void processarImagensActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processarImagensActionPerformed
        // TODO add your handling code here:
        //fazer mensagens da situação do processamento futuramente->contrução
        
        Processamento janela = new Processamento(combo.getSelectedItem().toString());
        this.setContentPane(janela);
        this.getContentPane().repaint();
        this.validate();
        
       /* Forma antiga
        pdi= new PDI(arq);
        
        if(pdi.verificaNecessidade()){
        arq.escolheDir();
       // habilitaMenusPasta();
        //labelPath.setText(" "+arq.getPath());
        //seta as imagens que estão no diretório
        pdi.setImagens(arq.abreArquivos(arq.getPath()));
        //diminuir as imagens
        pdi.reduzDefinicaoLote(pdi.getImagens());
        //processar e guardar no banco.
        pdi.processarEArmazernar(pdi.getImagens(),11,0.6,0.05,2.0,0.0,0.0);}
        else{
            System.out.println("Não há imagens a ser processada!");
        }*/
    }//GEN-LAST:event_processarImagensActionPerformed

    private void simularRequeimaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simularRequeimaActionPerformed
        // TODO add your handling code here:
         Preencher_Variaveis janela = new Preencher_Variaveis(combo.getSelectedItem().toString());
        this.setContentPane(janela);
        this.getContentPane().repaint();
        this.validate();
        Tools.enable(true);
    }//GEN-LAST:event_simularRequeimaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Abertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Abertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Abertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Abertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Abertura().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Carregabd;
    private javax.swing.JMenuItem Exit;
    private javax.swing.JMenu File;
    private javax.swing.JLabel Logo;
    private javax.swing.JPanel Principal;
    private javax.swing.JMenuItem Sobre;
    private javax.swing.JMenu Tools;
    private javax.swing.JMenu help;
    private javax.swing.JMenuItem itemMap;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JLabel labelRodape;
    private javax.swing.JLabel label_Area;
    private javax.swing.JLabel label_Colheita;
    private javax.swing.JLabel label_Plantio;
    private javax.swing.JLabel label_qtd_Amostras;
    private javax.swing.JLabel label_qtd_n_Processadas;
    private javax.swing.JMenuItem processarImagens;
    private javax.swing.JMenuItem simularRequeima;
    // End of variables declaration//GEN-END:variables
}
