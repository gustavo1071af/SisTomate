package mapeamento.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mapeamento.beans.Talhao;

/**
 * SISTOM-3
 * @author Gustavo
 */
public class TalhaoDAO {
    
   static String formatoDataPadrao = "yyyy-MM-dd";
    
   public static List<Talhao> getAll(){
        String sql = "SELECT * "
                + " FROM talhao"
                + " ORDER BY area_Cultivada asc";
        Connection con = new Conn().getConnection();
        final List<Talhao> talhoes = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()) {
                Talhao talhao = new Talhao();
                talhao.setAreaCultivada(rs.getString("area_Cultivada"));
                Date dataColheita = new SimpleDateFormat(formatoDataPadrao).parse(rs.getString("data_Colheita"));  
               // Date dataColheita = new Date().parse(rs.getString("data_Colheita"));
                talhao.setDataColheira(dataColheita);
                //Date dataPlantio = new Date(rs.getString("data_Plantio"));
                Date dataPlantio = new SimpleDateFormat(formatoDataPadrao).parse(rs.getString("data_Plantio"));  
                talhao.setDataPlantio(dataPlantio);
                int qtd_Ruas = Integer.parseInt(rs.getString("qtd_Ruas"));
                        
                talhao.setQtdRuas(qtd_Ruas);
                int qtd_TomatesPorLinhas = Integer.parseInt(rs.getString("qtd_TomatesPorLinhas"));
                talhao.setQtd_TomatesPorLinhas(qtd_TomatesPorLinhas);
                
                talhoes.add(talhao);
            }
             
            con.close();
            } catch (SQLException e) {
            System.out.println("Erro na Função getAll() em TalhaoDAO: " + e.getMessage());
            } catch (ParseException ex) {
           Logger.getLogger(TalhaoDAO.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("Erro na Função getAll() date" + ex.getMessage());
       }
        return talhoes;
   }
   
    public static Talhao get(String area_Cultivada){
        String sql = "SELECT * "
                + " FROM talhao"
                + " WHERE area_Cultivada = '"+area_Cultivada+"';";
        Talhao talhao = new Talhao();
        Connection con = new Conn().getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()){
                talhao.setAreaCultivada(rs.getString("area_Cultivada"));
                Date dataColheita = new SimpleDateFormat(formatoDataPadrao).parse(rs.getString("data_Colheita"));
                // Date dataColheita = new Date().parse(rs.getString("data_Colheita"));
                talhao.setDataColheira(dataColheita);
                //Date dataPlantio = new Date(rs.getString("data_Plantio"));
                Date dataPlantio = new SimpleDateFormat(formatoDataPadrao).parse(rs.getString("data_Plantio"));
                talhao.setDataPlantio(dataPlantio);
                int qtd_Ruas = rs.getInt("qtd_Ruas");
                talhao.setQtdRuas(qtd_Ruas);
                int qtd_TomatesPorLinhas = rs.getInt("qtd_TomatesPorLinhas");
                talhao.setQtd_TomatesPorLinhas(qtd_TomatesPorLinhas);
            }
            con.close();
            } catch (SQLException e) {
            System.out.println("Erro na Função get() em TalhaoDAO: " + e.getMessage());
            } catch (ParseException ex) {
           Logger.getLogger(TalhaoDAO.class.getName()).log(Level.SEVERE, null, ex);
           System.out.println("Erro na Função get() date" + ex.getMessage());
       }
        return talhao;
   }
    
   
    
  
    
}
