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
import java.util.Date;
import java.util.List;
import mapeamento.beans.DadosMeteorologicos;
import mapeamento.beans.Localidade;
import org.javatuples.Triplet;

/**
 * SISTOM-8
 *
 * @author Gustavo
 */
public class DadosMeteorologicosDAO {

    private static String formatoDataPadrao = "yyyy-MM-dd";
    /**
     *
     * @param idLocalidade
     * @return
     */
    public static List<DadosMeteorologicos> getDadosMeteorologicosPorLocalidade(int idLocalidade) {
        Localidade localidade = LocalidadeDAO.get(idLocalidade);
        String sql = "SELECT * "
                + "FROM dados_meteorologicos d where d.localidade = '" + idLocalidade + "' "
                + "ORDER BY data ";

        Connection con = new Conn().getConnection();
        List<DadosMeteorologicos> result = new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                DadosMeteorologicos dadosMeteorologicos = new DadosMeteorologicos();
                dadosMeteorologicos.setData(rs.getDate("data"));
                dadosMeteorologicos.setTemperaturaMedia(rs.getDouble("temp_media"));
                dadosMeteorologicos.setUmidade(rs.getInt("umidade"));
                dadosMeteorologicos.setChuva(rs.getBoolean("flag_chuva"));
                dadosMeteorologicos.setLocalidade(localidade);
                dadosMeteorologicos.setId(rs.getInt("id"));

                result.add(dadosMeteorologicos);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro na Função get() em LocalidadeDAO: " + e.getMessage());
        }
        return result;
    }
    
    /**
     *
     * @param dataInicio
     * @param dataFim
     * @param dia
     * @param mes
     * @param localidade
     * @return
     */
    public static Triplet<Double, Double, Double>  getMediasEntreDatasPorDiaDoMesELocalidade(Date dataInicio, Date dataFim, int dia, int mes, Localidade localidade) 
    {
        int idLocalidade = localidade.getId();
        //Preparar as datas para consulta do formato que estava para yyyy-MM-dd(padrao do mysql)
        long dataInicioTime = dataInicio.getTime();
        java.sql.Date dataInicioSql = new java.sql.Date(dataInicioTime);
        
        long dataFimTime = dataFim.getTime();
        java.sql.Date dataFimSql = new java.sql.Date(dataFimTime);
        
        String sql = "SELECT AVG(temp_media) AS media_temp, AVG(umidade) AS media_umid, avg(flag_chuva) as media_chuva "
                + "FROM dados_meteorologicos d where d.localidade = '" + idLocalidade + "' "
                + "AND (data BETWEEN '"+dataInicioSql+"' AND '"+dataFimSql+"') "
                + "AND EXTRACT(DAY FROM data) = '"+dia+"' And EXTRACT(MONTH FROM data) = '"+mes+"' "
                + "ORDER BY data ";

        Connection con = new Conn().getConnection();
        Triplet<Double, Double, Double> result = null;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                double media_temp = rs.getDouble("media_temp");
                double media_umid = rs.getDouble("media_umid");
                double media_chuva = rs.getDouble("media_chuva");
               
                Triplet<Double, Double, Double> triplet = new Triplet<Double, Double, Double>(media_temp, media_umid, media_chuva);

                result= triplet;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro na Função getMediasEntreDatasPorDiaDoMesELocalidade() em DadosMeteorologicosDAO: " + e.getMessage());
        }
        return result;
    }

    public static Boolean saveDadosMeteorologicos(DadosMeteorologicos dadosMeteorologicos) {
        Boolean result = null;
        Localidade localidade = dadosMeteorologicos.getLocalidade();
        String sql = "INSERT INTO dados_meteorologicos (data, temp_media, umidade, flag_chuva, localidade) VALUES ("
                + "'" + dadosMeteorologicos.getData() + "', "
                + "'" + dadosMeteorologicos.getTemperaturaMedia() + "', "
                + "'" + dadosMeteorologicos.getUmidade() + "', "
                + "" + dadosMeteorologicos.getChuva() + ", "
                + "'" + localidade.getId() + "');";
        
        //System.out.println(sql);

        Connection con = new Conn().getConnection();
        try {
            Statement stmt = con.createStatement();
            result = stmt.execute(sql);

            con.close();
        } catch (SQLException e) {
            System.out.println("Erro na Função saveDadosMeteorologicos() em DadosMeteorologicosDAO: " + e.getMessage());
        }

        return result;
    }

}
