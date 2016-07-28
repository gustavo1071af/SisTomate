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
import org.javatuples.Quintet;
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
                dadosMeteorologicos.setUmidade(rs.getDouble("umidade"));
                dadosMeteorologicos.setChuva(rs.getBoolean("flag_chuva"));
                dadosMeteorologicos.setChuva(rs.getBoolean("flag_chuva"));
                //SISTOM-12
                
                dadosMeteorologicos.setTemperaturaMinima(rs.getDouble("temp_min"));
                dadosMeteorologicos.setTemperaturaMaxima(rs.getDouble("temp_max"));
                dadosMeteorologicos.setPrecipitacao(rs.getDouble("precipitacao"));
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
     *SISTOM-11
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
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                double media_temp = rs.getDouble("media_temp");
                double media_umid = rs.getDouble("media_umid");
                double media_chuva = rs.getDouble("media_chuva");
               
                Triplet<Double, Double, Double> triplet = new Triplet<>(media_temp, media_umid, media_chuva);

                result= triplet;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro na Função getMediasEntreDatasPorDiaDoMesELocalidade() em DadosMeteorologicosDAO: " + e.getMessage());
        }
        return result;
    }
    
    /**
     *SISTOM-12
     * @param dataInicio
     * @param dataFim
     * @param dia
     * @param mes
     * @param localidade
     * @return
     */
    public static Double  getMediaTempEntreDatasPorDiaDoMesELocalidadeBaseadoEmHyre(Date dataInicio, Date dataFim, int dia, int mes, Localidade localidade) 
    {
        int idLocalidade = localidade.getId();
        //Preparar as datas para consulta do formato que estava para yyyy-MM-dd(padrao do mysql)
        long dataInicioTime = dataInicio.getTime();
        java.sql.Date dataInicioSql = new java.sql.Date(dataInicioTime);
        
        long dataFimTime = dataFim.getTime();
        java.sql.Date dataFimSql = new java.sql.Date(dataFimTime);
        
        String sql = "SELECT AVG(temp_media) AS media_temp "
                + "FROM dados_meteorologicos d where d.localidade = '" + idLocalidade + "' "
                + "AND (data BETWEEN '"+dataInicioSql+"' AND '"+dataFimSql+"') "
                + "AND EXTRACT(DAY FROM data) = '"+dia+"' And EXTRACT(MONTH FROM data) = '"+mes+"' "
                + "AND temp_mini >= '7.2' "
                + "ORDER BY data ";

        Connection con = new Conn().getConnection();
        Double result = null;
        try {
            Statement stmt = con.createStatement();
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                double media_temp = rs.getDouble("media_temp");
               
                result= media_temp;
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro na Função getMediasEntreDatasPorDiaDoMesELocalidade() em DadosMeteorologicosDAO: " + e.getMessage());
        }
        return result;
    }
    
    /**
     * SISTOM-18
     * @param dataInicio
     * @param dataFim
     * @param dia
     * @param mes
     * @param localidade
     * @return
     */
    public static Quintet getTodasAsMediasEntreDatasPorDiaDoMesELocalidade(Date dataInicio, Date dataFim, int dia, int mes, Localidade localidade) 
    {
        int idLocalidade = localidade.getId();
        
        Quintet result = null;
        //Preparar as datas para consulta do formato que estava para yyyy-MM-dd(padrao do mysql)
        long dataInicioTime = dataInicio.getTime();
        java.sql.Date dataInicioSql = new java.sql.Date(dataInicioTime);
        
        long dataFimTime = dataFim.getTime();
        java.sql.Date dataFimSql = new java.sql.Date(dataFimTime);
        
        String sql = "SELECT AVG(temp_media) AS media_temp, AVG(umidade) AS media_umid, AVG(temp_mini) AS media_tempMini, "
                + "AVG(temp_max) AS media_tempMax, AVG(precipitacao) AS media_prec  "
                + "FROM dados_meteorologicos d where d.localidade = '" + idLocalidade + "' "
                + "AND (data BETWEEN '"+dataInicioSql+"' AND '"+dataFimSql+"') "
                + "AND EXTRACT(DAY FROM data) = '"+dia+"' And EXTRACT(MONTH FROM data) = '"+mes+"' "
                + "ORDER BY data ";

        Connection con = new Conn().getConnection();
        try {
            Statement stmt = con.createStatement();
            //System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                double media_temp = rs.getDouble("media_temp");
                double media_umid = rs.getDouble("media_umid");
                double media_tempMini = rs.getDouble("media_tempMini");
                double media_tempMax = rs.getDouble("media_tempMax");
                double media_prec = rs.getDouble("media_prec");
                result = new Quintet(media_temp, media_umid, media_tempMini, media_tempMax, media_prec);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Erro na Função getTodasAsMediasEntreDatasPorDiaDoMesELocalidade() em DadosMeteorologicosDAO: " + e.getMessage());
        }
        return result;
    }
    
    /**
     *SISTOM-12
     * @param dataInicio
     * @param dataFim
     * @param dia
     * @param mes
     * @param localidade
     * @return
     */
    public static Double  getMediaPrecipitacaoEntreDatasPorDiaDoMesELocalidadeBaseadoEmHyre(Date dataInicio, Date dataFim, int dia, int mes, Localidade localidade) 
    {
        int idLocalidade = localidade.getId();
        //Preparar as datas para consulta do formato que estava para yyyy-MM-dd(padrao do mysql)
        long dataInicioTime = dataInicio.getTime();
        java.sql.Date dataInicioSql = new java.sql.Date(dataInicioTime);
        
        long dataFimTime = dataFim.getTime();
        java.sql.Date dataFimSql = new java.sql.Date(dataFimTime);
        
        String sql = "SELECT AVG(precipitacao) AS media_prec "
                + "FROM dados_meteorologicos d where d.localidade = '" + idLocalidade + "' "
                + "AND (data BETWEEN '"+dataInicioSql+"' AND '"+dataFimSql+"') "
                + "AND EXTRACT(DAY FROM data) = '"+dia+"' And EXTRACT(MONTH FROM data) = '"+mes+"' "
                + "AND precipitacao > 0 "
                + "ORDER BY data ";

        Connection con = new Conn().getConnection();
        Double result = null;
        try {
            Statement stmt = con.createStatement();
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                double media_prec = rs.getDouble("media_prec");
               
                result= media_prec;
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
        String sql = "INSERT INTO dados_meteorologicos (data, temp_media, umidade, flag_chuva, temp_mini, temp_max, precipitacao, localidade) "
                + "VALUES ("
                + "'" + dadosMeteorologicos.getData() + "', "
                + "'" + dadosMeteorologicos.getTemperaturaMedia() + "', "
                + "'" + dadosMeteorologicos.getUmidade() + "', "
                + "" + dadosMeteorologicos.getChuva() + ", "
                //SISTOM-12
                + "'" + dadosMeteorologicos.getTemperaturaMinima() + "', "
                + "'" + dadosMeteorologicos.getTemperaturaMaxima() + "', "
                + "'" + dadosMeteorologicos.getPrecipitacao() + "', "
                + "'" + localidade.getId() + "');";
        
        System.out.println(sql);

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
