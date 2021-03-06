/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mapeamento;

import mapeamento.beans.Tomates;
import java.awt.image.Raster;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;
import java.awt.image.renderable.ParameterBlock;
import java.io.File;
import java.util.List;
import javax.media.jai.InterpolationNearest;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.TiledImage;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import mapeamento.DAO.ImagemProcessadaDAO;
import mapeamento.DAO.TomatesDAO;
import mapeamento.beans.ImagemProcessada;

/**
 *
 * @author coordenador
 */
public class PDI extends Imagem {
    private Arquivo arq;
    private Tomates reg;
    private final String newline = System.lineSeparator();
    private String talhao;
    
    
    PDI () {
        arq = new Arquivo();
        reg = new Tomates();
    }
    
    PDI (Arquivo arquivo) {
        arq = arquivo;
        reg = new Tomates();
       
    }

    //pronto
    public void reduzDefinicaoLote(String[] imagens, JTextArea ap1, JProgressBar barra, JLabel label_Barra) {
        int teste;
        String path = arq.getPath();
       //configurando a barra
        barra.setMinimum(0);
        barra.setMaximum(imagens.length);
        
        
        boolean flag = (new File(path + "\\REDUZ")).mkdir();
        ap1.append("Path selecionado para salvar as imagens reduzidas: "+path + "\\REDUZ"+newline);
        ap1.setCaretPosition(ap1.getDocument().getLength());//cursoor ir para o final
        String[] arqsDiretorio = arq.abreArquivos(arq.getPath());
        for (int i = 0; i < imagens.length; i++) {
            if (verificarExistencia(arqsDiretorio, imagens[i])){
                teste = imagens[i].lastIndexOf(".");
                // TROCAR ISSO DEPOIS PARA VERIFICAR A EXTENSÃO E SÓ ACEITAR AS DE IMAGENS (JPG, ETC).
                // POSSO FAZER UMA ESCOLHA DO TIPO DE IMAGEM EM ALGUM LUGAR DO PROGRAMA, USANDO CHECKBOXES
                 if ((teste<0) || (imagens[i] == "Thumbs.db")){
                     continue;  // pula o subdiretório para onde irão as imagens
                 }
                 PlanarImage imagem = JAI.create("fileload", path + "\\" + imagens[i]);//será trocado depois a forma de escolher extensão.
                 //ap1.append("Salva a imagem reduzida em: "+path + "\\REDUZ\\" + imagens[i]+newline);
                 System.out.println(path + "\\REDUZ\\" + imagens[i]);
                 float scale = 0.3f;
                 ParameterBlock pb = new ParameterBlock();
                 pb.addSource(imagem);
                 pb.add(scale);
                 pb.add(scale);
                 pb.add(0.0F);
                 pb.add(0.0F);
                 pb.add(new InterpolationNearest());
                 PlanarImage reescalada = JAI.create("scale", pb);

                 // Salva a imagem gerada.
                 JAI.create("filestore", reescalada, path + "\\REDUZ\\"+imagens[i], "JPEG", null);
                 ap1.append("Salva a imagem reduzida em: "+path + "\\REDUZ\\" + imagens[i]+newline);
                 ap1.setCaretPosition(ap1.getDocument().getLength());//cursoor ir para o final
                 
                 //alterando a barra e o label da barra
                 barra.setValue(barra.getValue()+1);           
                 double porc = (barra.getValue()/Double.parseDouble(Integer.toString(imagens.length))*100);
                 label_Barra.setText("Redução das Imagens: "+ Math.round(porc)+"%");
              
                  
            }
            else{
                //alterando a barra e o label da barra
                 barra.setValue(barra.getValue()+1);           
                 double porc = (barra.getValue()/Double.parseDouble(Integer.toString(imagens.length))*100);
                 label_Barra.setText("Redução das Imagens: "+ Math.round(porc)+"%");
                 
                System.out.println("Não tem o arquivo: "+imagens[i]);
                 ap1.append("A IMAGEM '"+imagens[i]+"' NÃO FOI ENCONTRADA, PORTANTO NÃO SERÁ REDUZIDA!"+newline);
                 ap1.setCaretPosition(ap1.getDocument().getLength());//cursoor ir para o final
                 
            }
       }
       ap1.append("Redução das imagens terminada."+newline+"*************"+newline);
       ap1.setCaretPosition(ap1.getDocument().getLength());//cursoor ir para o final
       arq.setPath(arq.getPath()+"\\REDUZ");
       
    } 
        //função para gerar qrquivo txt SE CASO NECESSITAR DE UM LOG
    /*public void geraArquivoTxt(String[] imagens, int tpPDI,
            double tol1, double tol2, double nR, double nG, double nB) {
        int teste;
        long[][] minMax = new long[12][2];
        String path = arq.getPath();

        System.out.println(path);
        boolean flag = (new File(path + "\\CHAVES")).mkdir();
        arq.criaArquivoTxtOut(path + "\\CHAVES\\folhasChave.txt");
        int cont = 0;

        for (int i = 0; i < imagens.length; i++) {
            teste = imagens[i].lastIndexOf(".");

            // TROCAR ISSO DEPOIS PARA VERIFICAR A EXTENSÃO E SÓ ACEITAR AS DE IMAGENS (JPG, ETC).
            // POSSO FAZER UMA ESCOLHA DO TIPO DE IMAGEM EM ALGUM LUGAR DO PROGRAMA, USANDO CHECKBOXES
            if ((teste < 0) || (imagens[i] == "Thumbs.db")) {
                continue;  // pula o subdiretório para onde irão as imagens
            }

            System.out.println(imagens[i]);
            processaImagemChaveada(imagens[i], tol1, tol2, nR, nG, nB);
            arq.adicionaRegistros(reg);
        }

       
       
    }*/
    
    
    //pronto
     public void processarEArmazernar(String[] imagens, int tpPDI,
            double tol1, double tol2, double nR, double nG, double nB, JTextArea ap1, JTextArea ap2, JProgressBar barra, JLabel label_Barra) {
        int teste;
       //configurando a barra
        barra.setValue(0);
        barra.setMinimum(0);
        barra.setMaximum(imagens.length * 2);//multiplicado por 2 porque são duas coisas, processo e armazenamento de cada imagem
       
        //fazer a query chamando os tomateiros que não tem imagem processada para comparar com o nome das
        //imagens do diretório para fazer o processo 
        ap1.append("Iniciando o processamento das imagens e salvando no BD..."+newline);
        ap1.setCaretPosition(ap1.getDocument().getLength());//cursoor ir para o final
        ap1.append("Path selecionado para salvar as imagens processdas: "+arq.getPath() + "\\CHAVES"+newline);
        ap1.setCaretPosition(ap1.getDocument().getLength());//cursoor ir para o final

       
        
        int cont = 0;
        List<Tomates> tomatesSemImagemProcessadaPorTalhao = TomatesDAO.getTomatesSemImagemProcessadaPorTalhao(this.talhao);
       
           for (Tomates tomate : tomatesSemImagemProcessadaPorTalhao) {
             
         
                cont++;
                
                //alterando a barra e o label da barra
                 barra.setValue(barra.getValue()+1);
                 double porc = (barra.getValue()/Double.parseDouble(Integer.toString(imagens.length*2))*100);
                 label_Barra.setText("Processando e Armazenando: "+ Math.round(porc)+"%");
                 
                //preencher registro
               reg = tomate;
                String nomeimagem = reg.getNomeArquivo();//nome do arquivo de cada tomate achado na query acima adicionada a extensão
                if (verificarExistencia(imagens, nomeimagem)) {//verificar se o nome do arquivo existe na pasta.

                    teste = nomeimagem.lastIndexOf(".");
                    //vou fazer>>gustavo
                    // TROCAR ISSO DEPOIS PARA VERIFICAR A EXTENSÃO E SÓ ACEITAR AS DE IMAGENS (JPG, ETC).
                    // POSSO FAZER UMA ESCOLHA DO TIPO DE IMAGEM EM ALGUM LUGAR DO PROGRAMA, USANDO CHECKBOXES
                    if ((teste < 0) || (nomeimagem == "Thumbs.db")) {
                        continue;  // pula o subdiretório para onde irão as imagens

                    }

                    System.out.println(nomeimagem);
                    
                    ImagemProcessada imagemProcessadaParaSalvar = processaImagemChaveada(nomeimagem, tol1, tol2, nR, nG, nB, ap1);
                    
                    //alterando a barra e o label da barra
                    barra.setValue(barra.getValue()+1);
                    double porc2 = (barra.getValue()/Double.parseDouble(Integer.toString(imagens.length*2))*100);
                    label_Barra.setText("Processando e Armazenando: "+ Math.round(porc2)+"%");
                    adicionaRegistros(reg, imagemProcessadaParaSalvar, ap2);
                    
                    
                    //System.out.println();
                } else {//se não encontrar a imagem no diretório
                    //alterando a barra e o label da barra
                    barra.setValue(barra.getValue()+2);// porque pula o processamento e o armazenamento por não achar a imagem no diretório
                    double porc3 = (barra.getValue()/Double.parseDouble(Integer.toString(imagens.length*2))*100);
                    label_Barra.setText("Processando e Armazenando: "+ Math.round(porc3)+"%");
                    
                    System.out.println("Não achou no diretório o arquvo " + nomeimagem);
                    ap2.append("A IMAGEM '" + nomeimagem + "' NÃO FOI ENCONTRADA NO DIRETÓRIO //REDUZ, PORTANTO É IMPOSSÍVEL SER PROCESSADA!" + newline + "***********" + newline);
                    ap2.setCaretPosition(ap2.getDocument().getLength());//cursoor ir para o final

                }

            }//for

    
           
    }
     
     
     //pronto
    public boolean verificarExistencia(String[] imagens, String nomeimagem) {
        for (int i = 0; i < imagens.length; i++) {
            
            if (imagens[i].equalsIgnoreCase(nomeimagem)) {
               System.out.println(imagens[i]+" =? "+nomeimagem);
                return true;
            }
        }
        return false;
    }
    
    public String[] verificaNecessidade(JTextArea ap1, String talhao) {
        String[] listaAuxiliar = null;
        this.talhao = talhao;
        ///fazer conexao com o banco 
        //processando cada registro encontrado na query
        List<String> nomeArquivoDeTomatesSemImagemProcessadaPorTalhao = TomatesDAO.getNomeArquivoDeTomatesSemImagemProcessadaPorTalhao(talhao, ap1);
        int numLinha = nomeArquivoDeTomatesSemImagemProcessadaPorTalhao.size();
        if (numLinha > 0) {//verificar se existe registros

            ap1.append("Iniciando o processo..." + newline);
            ap1.append("Foram encontrados " + numLinha + " registros que ainda não tiveram as imagens processadas processados." + newline);
            listaAuxiliar = nomeArquivoDeTomatesSemImagemProcessadaPorTalhao.toArray(new String[numLinha]);
        }

        return listaAuxiliar;

    }
    
//pronto
    public ImagemProcessada processaImagemChaveada (String imagem, 
            double tol1, double tol2, double nR, double nG, double nB, JTextArea ap1) {
        int teste;
        String path = arq.getPath();
   
        System.out.println("Path no processamento: " +arq.getPath());
        
        boolean flag = (new File(path + "\\CHAVES")).mkdir();
       
        teste = imagem.lastIndexOf(".");
        // TROCAR ISSO DEPOIS PARA VERIFICAR A EXTENSÃO E SÓ ACEITAR AS DE IMAGENS (JPG, ETC).
        // POSSO FAZER UMA ESCOLHA DO TIPO DE IMAGEM EM ALGUM LUGAR DO PROGRAMA, USANDO CHECKBOXES
        if ((teste < 0) || (imagem == "Thumb.db")) {
            return null;  // pula o subdiretório para onde irão as imagens
        }
        ap1.append("Processando a imagem: " +imagem+"..."+newline);
        ap1.setCaretPosition(ap1.getDocument().getLength());//cursoor ir para o final
        System.out.println(imagem);
         ap1.append("Carregando a imagem reduzida em: "+ path + "\\" + imagem+newline);
        ap1.setCaretPosition(ap1.getDocument().getLength());//cursoor ir para o final
        PlanarImage imgP = JAI.create("fileload", path + "\\" + imagem);
        int largura = imgP.getWidth();
        int altura = imgP.getHeight();
        SampleModel sm = imgP.getSampleModel();
        int nbandas = sm.getNumBands();
        Raster rasterLeitura = imgP.getData();
        WritableRaster rasterEscrita = rasterLeitura.createCompatibleWritableRaster();
        int[] pixels = new int[nbandas * largura * altura];
        rasterLeitura.getPixels(0, 0, largura, altura, pixels);

        int deslocamento, totR = 0, totG = 0, totP = 0, tot = 0;
        int rMD = 0, gMD = 0, bMD = 0;

        for (int a = 0; a < altura; a++) {
            for (int l = 0; l < largura; l++) {
                //deslocamento = 1;
                deslocamento = a * largura * nbandas + l * nbandas;
                rMD = rMD + pixels[deslocamento + 0];
                gMD = gMD + pixels[deslocamento + 1];
                bMD = bMD + pixels[deslocamento + 2];
                tot++;

                // vou destacar os pixels em tons de verde, tornando-os verde puro
                if ((pixels[deslocamento + 0] >= 25 * (1 - nR)) && (pixels[deslocamento + 0] <= 120 * (1 + nR))
                        && (pixels[deslocamento + 1] >= 60 * (1 - nG)) && (pixels[deslocamento + 1] <= 160 * (1 + nG))
                        && (pixels[deslocamento + 2] >= 25 * (1 - nB)) && (pixels[deslocamento + 2] <= 100 * (1 + nB))
                        && (pixels[deslocamento + 1] >= pixels[deslocamento + 0] * (1 - tol2))
                        && (pixels[deslocamento + 1] >= pixels[deslocamento + 2] * (1 - tol2))
                        && ((pixels[deslocamento + 0] + pixels[deslocamento + 2]) * tol1 <= pixels[deslocamento + 1])) {
                    pixels[deslocamento + 0] = 0;
                    pixels[deslocamento + 1] = 255;
                    pixels[deslocamento + 2] = 0;
                    totG++;
                } else // vou destacar os pixels em tons de amarelo, tornando-os vermelhos puro
                if ((pixels[deslocamento + 0] >= 135 * (1 - nR)) && (pixels[deslocamento + 0] <= 180 * (1 + nR))
                        && (pixels[deslocamento + 1] >= 130 * (1 - nG)) && (pixels[deslocamento + 1] <= 160 * (1 + nG))
                        && (pixels[deslocamento + 2] >= 40 * (1 - nB)) && (pixels[deslocamento + 2] <= 60 * (1 + nB))) {
                    pixels[deslocamento + 0] = 255;
                    pixels[deslocamento + 1] = 0;
                    pixels[deslocamento + 2] = 0;
                    totR++;
                } else //PRECISO DESCOBRIR AS PROPORÇÕES IDEIAS PARA MUDANÇA DE COR!!!!!
                // vou destacar os pixels em tons de marrom, tornando-os vermelho puro
                if ((pixels[deslocamento + 0] >= 10 * (1 - nR)) && (pixels[deslocamento + 0] <= 120 * (1 + nR))
                        && (pixels[deslocamento + 1] >= 25 * (1 - nG)) && (pixels[deslocamento + 1] <= 110 * (1 + nG))
                        && (pixels[deslocamento + 2] >= 10 * (1 - nB)) && (pixels[deslocamento + 2] <= 95 * (1 + nB))) {
                    pixels[deslocamento + 0] = 255;
                    pixels[deslocamento + 1] = 0;
                    pixels[deslocamento + 2] = 0;
                    totR++;
                } // vou destacar os pixels do fundo, tornando-os pretos
                else {
                    pixels[deslocamento + 0] = 0;
                    pixels[deslocamento + 1] = 0;
                    pixels[deslocamento + 2] = 0;
                    totP++;
                }
            }
        }

        rasterEscrita.setPixels(0, 0, largura, altura, pixels);
        TiledImage ti = new TiledImage(imgP, 1, 1);
        ti.setData(rasterEscrita);
        
        
        JAI.create("filestore", ti, path + "\\CHAVES\\COR_" + imagem, "JPEG", null);
        
        ap1.append("Salva a imagem processada em:" +path + "\\CHAVES\\COR_" + imagem+newline);
        ap1.setCaretPosition(ap1.getDocument().getLength());//cursoor ir para o final
        //SISTOM-3
        ImagemProcessada imagemProcessada = new ImagemProcessada();
        imagemProcessada.setVermelhos(totR);
        imagemProcessada.setVerdes(totG);
        imagemProcessada.setPretos(totP);
        imagemProcessada.setNomeArquivo("COR_"+imagem);
        setGradKey((double) totR / totG * 100);
        imagemProcessada.setEstado(getGradKey());
        imagemProcessada.setTomate(reg);
        ap1.append("Finalizando o processamento e gerando o resultado."+newline+"***********"+newline);
        ap1.setCaretPosition(ap1.getDocument().getLength());//cursoor ir para o final
        System.out.println(totR + " " + totG + " " + totP + " "
                + (double) totR / totG * 100 + " " + getGradKey() + "%");
        
        return imagemProcessada; 
 
    }

    private void adicionaRegistros(Tomates reg, ImagemProcessada imagemProcessadaParaSalvar, JTextArea ap2) {
        
        System.out.println("Adicionando o registro: "+reg.getNomeArquivo()+", "+imagemProcessadaParaSalvar.getVermelhos()+", "+imagemProcessadaParaSalvar.getVerdes()+", "+imagemProcessadaParaSalvar.getPretos()+", "+imagemProcessadaParaSalvar.getEstado()+", "+reg.getNumTom()+", "+reg.getRua()+", "+reg.getLinha()+", "+reg.getData());
        ap2.append(">> Adicionando o registro no banco de dados da imagem: "+reg.getNomeArquivo()+" chaveada com o Estado: "+imagemProcessadaParaSalvar.getEstado()+"."+newline);
        Boolean saveImagemProcessada = ImagemProcessadaDAO.saveImagemProcessada(imagemProcessadaParaSalvar);
        ap2.setCaretPosition(ap2.getDocument().getLength());//cursoor ir para o final
       
    }
    
}
