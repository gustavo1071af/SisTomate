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
import mapeamento.beans.Localidade;

/**
 *SISTOM-8
 * @author Gustavo
 */
public class LocalidadeDAO {
    
    /**
     *
     * @param id
     * @return
     */
    public static Localidade get(int id){
         String sql = "SELECT * "
                 + "FROM localidade l where l.id = '" + id + "' ";
                
         Connection con = new Conn().getConnection();
          Localidade localidade = new Localidade();
         try {
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
           
            
            if (rs.next()){
                localidade.setId(rs.getInt("id"));
              localidade.setEstacao(rs.getString("estacao"));
              localidade.setLatitude(rs.getString("latitude"));
              localidade.setLongitude(rs.getString("longitude"));
              localidade.setAltitude(rs.getString("altitude"));
              localidade.setColetaInicio(rs.getDate("coleta_inicio"));
              localidade.setColetaFim(rs.getDate("coleta_fim"));
                       
            }
            con.close();
            } catch (SQLException e) {
            System.out.println("Erro na Função get() em LocalidadeDAO: " + e.getMessage());
            } 
       return localidade;
   }
     
 
   
    
}
