/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeamento;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import mapeamento.DAO.DadosMeteorologicosDAO;
import mapeamento.DAO.LocalidadeDAO;
import mapeamento.beans.DadosMeteorologicos;
import mapeamento.beans.Localidade;


/**
 *
 * @author Gustavo
 */
//SINTON-8
public class PopulaDadosMeteoro {
 public static void main(String args[]) 
         throws BiffException, IOException {
     System.out.println("mapeamento.PopulaDadosMeteoro.main()");
     /**
      * * Carrega a planilha
      */
     
     //Deve-se configurar o path de acordo com a localização do arquivo
     Workbook workbook = Workbook.getWorkbook(new File("C:/AlferesFormatadoHyre1954.xls"));
     
      /**
      * * Escolhe a aba
      */

    Sheet sheet = workbook.getSheet(0);
     //por enquanto será apenas essa localidade
     Localidade localidade = LocalidadeDAO.get(1);
     
     int rows = sheet.getRows();
     //começa do um pois 0 é o cabeçalho
      DadosMeteorologicos novoDado = new DadosMeteorologicos();
     for (int i = 1; i < rows; i++) {
         try {
             Cell dataCell = sheet.getCell(0, i);
             Cell tempMediaCell = sheet.getCell(1, i);
             Cell umidadeCell = sheet.getCell(2, i);
             Cell tempMiniCell = sheet.getCell(3, i);
             Cell tempMaxCell = sheet.getCell(4, i);
             Cell precCell = sheet.getCell(5, i);


             String dataStr = dataCell.getContents();
             String tempMediaStr = tempMediaCell.getContents();
             String umidadeStr = umidadeCell.getContents();
             String tempMiniStr = tempMiniCell.getContents();
             String temMaxStr = tempMaxCell.getContents();
             String precStr = precCell.getContents();
             
             //convertendo a data dd/MM/yyyy para yyyy-MM-dd padrão do mysql tipo DATE
             
             java.sql.Date data = formataData(dataStr);
            
             double temperaturaMedia = Double.parseDouble(tempMediaStr);
             
             double umidade = Double.parseDouble(umidadeStr);
            
            double temperaturaMinima = Double.parseDouble(tempMiniStr);
            double temperaturaMaxima = Double.parseDouble(temMaxStr);
            double precipitacao = Double.parseDouble(precStr);
               
               Boolean chuva;
               chuva = umidade > 90;
               
               //Log:
               System.out.println(data);
               System.out.println(temperaturaMedia);
               System.out.println(umidade);
               System.out.println(chuva);
               System.out.println(temperaturaMinima);
               System.out.println(temperaturaMaxima);
               System.out.println(precipitacao);
               System.out.println("-------------");
               
              // constroi bean
            
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
             
             
             
             
         } catch (ParseException ex) {
             Logger.getLogger(PopulaDadosMeteoro.class.getName()).log(Level.SEVERE, null, ex);
         } catch (Exception ex) {
             Logger.getLogger(PopulaDadosMeteoro.class.getName()).log(Level.SEVERE, null, ex);
         }
        
     }
     
   
        

  }
   /**
 	 * Converte uma String para um objeto Date. Caso a String seja vazia ou nula, 
 	 * retorna null - para facilitar em casos onde formulários podem ter campos
 	 * de datas vazios.
 	 * @param data String no formato dd/MM/yy a ser formatada
 	 * @return Date Objeto Date ou null caso receba uma String vazia ou nula
 	 * @throws Exception Caso a String esteja no formato errado
 	 */
 	public static java.sql.Date formataData(String data) 
                throws Exception { 
 		if (data == null || data.equals(""))
 			return null;
         java.sql.Date date = null;
         try {
             DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
             date = new java.sql.Date( ((java.util.Date)formatter.parse(data)).getTime() );
         } catch (ParseException e) {            
             throw e;
         }
         return date;
 	}
    
}
