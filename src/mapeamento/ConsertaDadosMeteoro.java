/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeamento;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import mapeamento.DAO.DadosMeteorologicosDAO;
import mapeamento.DAO.LocalidadeDAO;
import mapeamento.beans.DadosMeteorologicos;
import mapeamento.beans.Localidade;
import org.javatuples.Quintet;

/**
 *
 * @author Gustavo
 */
//SISTOM-18
public class ConsertaDadosMeteoro {

    public static void main(String args[]) throws Exception {
        System.out.println("mapeamento.ConsertaDadosMeteoro.main()");
        // Date dataInicio = new Date(2001, 6, 30 );
        // Date dataFim = new Date(2002, 4, 31 );

        Boolean salvar = false;

        GregorianCalendar calendarDataInicio = new GregorianCalendar();
        calendarDataInicio.set(2001, 6, 1);
        //calendarDataInicio.setTime(dataInicio);

        GregorianCalendar calendarDataFim = new GregorianCalendar();
        calendarDataFim.set(2002, 3, 30);
        //calendarDataFim.setTime(dataFim);
        long timeInMillisDataFim = calendarDataFim.getTimeInMillis();
        long timeInMillisDataInicio = calendarDataInicio.getTimeInMillis();

        Localidade localidade = LocalidadeDAO.get(1);

        while (timeInMillisDataInicio <= timeInMillisDataFim) {
            int diaDataInicio = calendarDataInicio.get(GregorianCalendar.DAY_OF_MONTH);

            int mesDataInicio = calendarDataInicio.get(GregorianCalendar.MONTH) + 1;
            int anoDataInicio = calendarDataInicio.get(GregorianCalendar.YEAR);

            //System.out.println(diaDataInicio+"/"+mesDataInicio+"/"+anoDataInicio);
            String dataStr = (diaDataInicio + "/" + mesDataInicio + "/" + anoDataInicio);;
            Date coletaFim = localidade.getColetaFim();
            Date coletaInicio = localidade.getColetaInicio();

            Quintet quintet = DadosMeteorologicosDAO.getTodasAsMediasEntreDatasPorDiaDoMesELocalidade(coletaInicio, coletaFim, diaDataInicio, mesDataInicio, localidade);

            DadosMeteorologicos novoDado = new DadosMeteorologicos();
            double temperaturaMedia = (double) quintet.getValue0();

            double umidade = (double) quintet.getValue1();

            double temperaturaMinima = (double) quintet.getValue2();
            double temperaturaMaxima = (double) quintet.getValue3();
            double precipitacao = (double) quintet.getValue4();

            Boolean chuva;
            chuva = umidade > 90;

            java.sql.Date data = formataData(dataStr);

            //Log:
            /*System.out.println(data);
               System.out.println(temperaturaMedia);
               System.out.println(umidade);
               System.out.println(chuva);
               System.out.println(temperaturaMinima);
               System.out.println(temperaturaMaxima);
               System.out.println(precipitacao);
               System.out.println("-------------");
             */
            // constroi bean
            if (salvar) {
                novoDado.setData(data);
                novoDado.setTemperaturaMedia(temperaturaMedia);
                novoDado.setUmidade(umidade);
                novoDado.setChuva(chuva);
                novoDado.setTemperaturaMinima(temperaturaMinima);
                novoDado.setTemperaturaMaxima(temperaturaMaxima);
                novoDado.setPrecipitacao(precipitacao);
                novoDado.setLocalidade(localidade);

                //salvaBean
                DadosMeteorologicosDAO.saveDadosMeteorologicos(novoDado);
            }

            calendarDataInicio.add(Calendar.DAY_OF_MONTH, 1);
            timeInMillisDataInicio = calendarDataInicio.getTimeInMillis();

        }//while

    }

    /**
     * Converte uma String para um objeto Date. Caso a String seja vazia ou
     * nula, retorna null - para facilitar em casos onde formulários podem ter
     * campos de datas vazios.
     *
     * @param data String no formato dd/MM/yy a ser formatada
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula
     * @throws Exception Caso a String esteja no formato errado
     */
    public static java.sql.Date formataData(String data)
            throws Exception {
        if (data == null || data.equals("")) {
            return null;
        }
        java.sql.Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
            date = new java.sql.Date(((java.util.Date) formatter.parse(data)).getTime());
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }

}
