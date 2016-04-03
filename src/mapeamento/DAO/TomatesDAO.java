/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeamento.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;
import mapeamento.beans.Talhao;
import mapeamento.beans.Tomates;

/**
 *SISTOM-3
 * @author Gustavo
 */
public class TomatesDAO {
    
    private static String newline = System.lineSeparator();
    
    /**
     *
     * @param area_Cultivada
     * @return
     */
    public static int getQtdDeTomatesPorTalhao(String area_Cultivada){
        String sql = "SELECT count(*) as qtd_amostra"
                + " FROM Tomate t"
                + " WHERE t.idTalhao = '"+ area_Cultivada+"';";
        
        Connection con = new Conn().getConnection();
        int retornoValor = 0;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()){
                
                retornoValor = rs.getInt("qtd_amostra");
                       
            }
            con.close();
            } catch (SQLException e) {
            System.out.println("Erro na Função getQtdDeTomatesPorTalhao() em TomatesDAO: " + e.getMessage());
            } 
        return retornoValor;
     }
        
    /**
     *
     * @param area_Cultivada
     * @return
     */
    public static List<Tomates> getTomatesSemImagemProcessadaPorTalhao(String area_Cultivada) {
        String sql = "SELECT * "
                + "FROM tomate t "
                + "where idTalhao = '" + area_Cultivada + "' "
                + "and not exists("
                + "select * "
                + "from imagem_processada ip "
                + "where t.numtom = ip.Tomate_numtom "
                + "and t.rua = ip.Tomate_rua "
                + "and t.linha = ip.Tomate_linha "
                + "and t.data = ip.Tomate_data "
                + "and t.idTalhao = ip.idTalhao)";

        Connection con = new Conn().getConnection();
        final List<Tomates> tomates = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Talhao talhao = TalhaoDAO.get(area_Cultivada);
            while (rs.next()) {
                Tomates tomate = new Tomates();

                tomate.setTalhao(talhao);
                tomate.setNomeArquivo(rs.getString("nomearquivo"));
                tomate.setNumTom(rs.getInt("numtom"));
                tomate.setRua(rs.getInt("rua"));
                tomate.setLinha(rs.getString("linha"));
                tomate.setData(rs.getDate("data"));
                tomate.setLat(rs.getString("lat"));
                tomate.setLongi(rs.getString("longi"));
                tomates.add(tomate);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro na Função getTomatesSemImagemProcessadaPorTalhao() em TomatesDAO: " + e.getMessage());
        }
        return tomates;
    }
    
    /**
     *
     * @param area_Cultivada
     * @param ap1
     * @return
     */
    public static List<String> getNomeArquivoDeTomatesSemImagemProcessadaPorTalhao(String area_Cultivada, JTextArea ap1) {
        String sql = "SELECT nomearquivo "
                + "FROM tomate t "
                + "where t.idTalhao = '" + area_Cultivada + "' "
                + "and not exists("
                + "select * "
                + "from imagem_processada ip "
                + "where t.numtom = ip.Tomate_numtom  "
                + "and t.rua = ip.Tomate_rua  "
                + "and t.linha = ip.Tomate_linha  "
                + "and t.data = ip.Tomate_data "
                + "and t.idTalhao = ip.idTalhao)";
        
        Connection con = new Conn().getConnection();
        final List<String> nomearquivos = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {                
                nomearquivos.add(rs.getString("nomearquivo"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro na Função getNomeArquivoDeTomatesSemImagemProcessadaPorTalhao() em TomatesDAO: " + e.getMessage());
             //depois mudar cor das mensagens de erro com highlights
             // ap1 da tela de processamento 
             ap1.append("Falha no banco de dados ao verificar a necessidade do processamento de imagens..."+newline);
             ap1.setCaretPosition(ap1.getDocument().getLength());//cursoor ir para o final
        }
        return nomearquivos;
    }
    
}
