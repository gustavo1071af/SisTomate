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
     Workbook workbook = Workbook.getWorkbook(new File("C:/dadosMeteoro.xls"));
     
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
             Cell chuvaCell = sheet.getCell(3, i);
             
             String dataStr = dataCell.getContents();
             String tempMediaStr = tempMediaCell.getContents();
             String umidadeStr = umidadeCell.getContents();
             String chuvaStr = chuvaCell.getContents();
             
             //convertendo a data MM/dd/yyyy para yyyy-MM-dd padrão do mysql tipo DATE
             
             java.sql.Date data = formataData(dataStr);
            // System.out.println(data);
             double temperaturaMedia = Double.parseDouble(tempMediaStr);
             // System.out.println(temperaturaMedia);
             int umidade = Integer.parseInt(umidadeStr);
            // System.out.println(umidade);
               int chuvaInt = Integer.parseInt(chuvaStr);
               Boolean chuva;
               if (chuvaInt == 0) {
                 chuva = false;
             }
               else{
                 chuva = true;
               }
               //System.out.println(chuva);
              // System.out.println("-------------");
               
              // constroi bean
            
             novoDado.setData(data);
             novoDado.setTemperaturaMedia(temperaturaMedia);
             novoDado.setUmidade(umidade);
             novoDado.setChuva(chuva);
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
             DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
             date = new java.sql.Date( ((java.util.Date)formatter.parse(data)).getTime() );
         } catch (ParseException e) {            
             throw e;
         }
         return date;
 	}
    
}
