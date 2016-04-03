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
import mapeamento.beans.ImagemProcessada;
import mapeamento.beans.Talhao;
import mapeamento.beans.Tomates;

/**
 *SISTOM-3
 * @author Gustavo
 */
public class ImagemProcessadaDAO {
    
    /**
     *
     * @param area_Cultivada
     * @return
     */
    public static int getQtdDeImagensProcessadasPorTalhao(String area_Cultivada){
         String sql = "SELECT count(*) as qtd_imgsprocessadas "
                 + "FROM tomate t where t.idTalhao = '" + area_Cultivada + "' "
                 + "and not exists("
                 + "select * "
                 + "from imagem_processada ip where t.numtom = ip.Tomate_numtom "
                 + "and t.rua = ip.Tomate_rua  "
                 + "and t.linha = ip.Tomate_linha  "
                 + "and t.data = ip.Tomate_data "
                 + "and t.idTalhao = ip.idTalhao)";

         Connection con = new Conn().getConnection();
         int retornoValor = 0;
         try {
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
            
            if (rs.next()){
                
                retornoValor = rs.getInt("qtd_imgsprocessadas");
                       
            }
            con.close();
            } catch (SQLException e) {
            System.out.println("Erro na Função getQtdDeImagensProcessadasPorTalhao() em ImagemProcessadaDAO: " + e.getMessage());
            } 
        return retornoValor;
   }
     
    /**
     *
     * @param imagemProcessadaParaSalvar
     */
    public static Boolean saveImagemProcessada(ImagemProcessada imagemProcessadaParaSalvar) {
        Boolean result = null;
        Tomates tomate = imagemProcessadaParaSalvar.getTomate();
        Talhao talhao = tomate.getTalhao();
        String sql = "INSERT INTO imagem_processada (nomearquivo, vermelhos, verdes, pretos, estado, Tomate_numtom, Tomate_rua, Tomate_linha, Tomate_data, idTalhao) VALUES ("
                + imagemProcessadaParaSalvar.getNomeArquivo() + "', "
                + imagemProcessadaParaSalvar.getVermelhos() + ", "
                + imagemProcessadaParaSalvar.getVerdes() + ", "
                + imagemProcessadaParaSalvar.getPretos() + ", "
                + imagemProcessadaParaSalvar.getEstado() + ", "
                + tomate.getNumTom() + ", "
                + tomate.getRua() + ", "
                + "'" + tomate.getLinha() + "', "
                + "'" + tomate.getData() + "', "
                + "'" + talhao.getAreaCultivada() + "');";

        Connection con = new Conn().getConnection();
        try {
            Statement stmt = con.createStatement();
            result = stmt.execute(sql);

            con.close();
        } catch (SQLException e) {
            System.out.println("Erro na Função saveImagemProcessada() em ImagemProcessadaDAO: " + e.getMessage());
        }

        return result;
    }
    
}
