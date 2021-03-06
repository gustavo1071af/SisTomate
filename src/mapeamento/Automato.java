package mapeamento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JTextField;
import mapeamento.DAO.DadosMeteorologicosDAO;
import mapeamento.DAO.LocalidadeDAO;
import mapeamento.ENUNS.Combate;
import mapeamento.ENUNS.DirecoesDoVento;
import mapeamento.beans.ImagemProcessada;
import mapeamento.beans.Localidade;
import mapeamento.beans.Talhao;
import mapeamento.beans.Tomates;
import mapeamento.beans.strategy.AbstractStrategyCombate;

/**
 *  SISTOM-4
 * @author Gustavo
 * @creationDate 10/04/2016
 */
public class Automato{

    private final Talhao talhao;
    private final int X;
    private final int Y;
    private MeuJPanel[][] matriz;
    private int risco5;
    private int risco7;
    private int risco10;
    private int surto;
    private int count;
    private int diasFavoraveis;
    private int diasAposPrimeiroSurto;
    private final DirecoesDoVento direcao;
    private final int mediaHistorica;
    private final int qtdIterecao;
    private final Date dataInicio;
    private final Localidade localidade;
    private final static int ESTADO_MAXIMO = 6;
    private final static int QTD_DIAS_MEDIA_TEMP_PARA_FAVORAVEL = 5;
    private final static int QTD_DIAS_PRECIPITACAO_PARA_FAVORAVEL = 10;
    private final static Double MEDIA_CINCO_DIAS_PARA_FAVORAVEL = 25.5;
    private final static Double SOMA_DE_PRECIPITACAO_PARA_FAVORAVEL = 30.0;
    private final static int ESTADO_MINIMO = 0;
    private final Date dataBaseadaNaMediaHistorica;
    private final Double temp;
    private final Double prec;
    private Combate combate;
    private int  combateProgresso;
    private final PainelDeSimulacao parent;


    /**
     *
     * @param talhao
     * @param umid
     * @param temp
     * @param direcao
     * @param chuva
     * @param dataInicio
     * @param mediaHistorica
     * @param qtdInter
     * @param matriz
     */
    public Automato(Talhao talhao, int umid, Double temp, DirecoesDoVento direcao, Double prec, Date dataInicio, int mediaHistorica, int qtdInter, final MeuJPanel[][] matriz, final PainelDeSimulacao parent ) {
        this.talhao = talhao;
        this.X = talhao.getQtd_TomatesPorLinhas();
        //considerando que cada rua tem 2 linhas.
        this.Y = talhao.getQtdRuas() * 2;
        this.matriz = matriz;
         this.direcao = direcao;
        this.mediaHistorica = mediaHistorica;
        this.qtdIterecao = qtdInter;
        this.dataInicio = dataInicio;
        Localidade localidade = LocalidadeDAO.get(1);
        this.localidade = localidade;
        this.temp = temp;
        this.prec = prec;
        this.combate = null;
        this.combateProgresso = 0;
        this.parent = parent;
        
        //SISTOM-11
        
        Date coletaFim = this.localidade.getColetaFim();
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(coletaFim);
        //basear nas media dos n anos passados para ter uma base do futuro.
        //A soma com 1 é para garantir que terá dados suficientes para a média, ou caso o bd não tenha algum ano completo(principalmente o ultimo(2015))
        calendar.add(Calendar.YEAR, -(this.mediaHistorica+1));
        this.dataBaseadaNaMediaHistorica = calendar.getTime();
        
        //Seta os valores iniciais 
        this.count = 0;
        this.risco5 = 0;
        this.risco7 = 0;
        this.risco10 = 0;
        //SISTOM-12
        this.surto = 0;
        diasFavoraveis = 0;
        this.diasAposPrimeiroSurto = 0;
        inicializa();
    }

    public void inicializa()//recebe valor inicial
    {
        int i, j, max_X, max_Y;

        max_X = this.X - 1;
        max_Y = this.Y - 1;

        for (i = 0; i < this.X; i++) {
            for (j = 0; j < this.Y; j++) {
                //SISTOM-10

                Tomates tom = matriz[i][j].getTom();
                ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                //Seta o atributo estadoComVariacao com o valor do estado inicial para somar o estado comas as variacoes
                imagemProcessada.setEstadoComVariacaoInicio();

               
            }
        }

    }
    
    /**
     *
     */
    public void simulaPropagação() {

        

    }

    /**
     *
     */
    public void imprime() {

        int i, j, max_X, max_Y;
        max_X = this.X - 1;
        max_Y = this.Y - 1;

        for (i = 0; i < this.X; i++) {
            for (j = 0; j < this.Y; j++) {
                Tomates tom = matriz[i][j].getTom();
                ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                int estado = imagemProcessada.getEstado();
                System.out.print(estado);

            }
            System.out.println();
        }

    }
	
    /**
     * @param i
     * @param j
     * @return
     */
    public int vizinhos (int i, int j){
            //VIZINHAÇA EM 2D
            int min = 0;
            int max_X = this.X-1;
            int max_Y = this.Y-1;
            int cantoEsquerdoSuperior = (i!=min && j!=min) ? matriz[i - 1][j - 1].getTom().getImagemProcessada().getEstado(): 0;
            int meioSuperior = (i!=min) ? matriz[i - 1][j].getTom().getImagemProcessada().getEstado() : 0;
            int cantoDireitoSuperior = (i!=min && j!=max_Y) ? matriz[i - 1][j + 1].getTom().getImagemProcessada().getEstado() : 0;
            int meioEsquerdo = (j!=min) ? matriz[i][j - 1].getTom().getImagemProcessada().getEstado() : 0;
            int meioDireito = (j!=max_Y) ? matriz[i][j + 1].getTom().getImagemProcessada().getEstado() : 0;
            int cantoEsquerdoInferior = (i!=max_X && j!=min) ? matriz[i + 1][j - 1].getTom().getImagemProcessada().getEstado() : 0;
            int meioInferior = (i!=max_X) ? matriz[i + 1][j].getTom().getImagemProcessada().getEstado(): 0 ;
            int cantoDireitoInferior = (i!=max_X && j!=max_Y) ? matriz[i + 1][j + 1].getTom().getImagemProcessada().getEstado() : 0;
            
            //vizinhaca é a soma dos estados
            int vizinhaca = cantoEsquerdoSuperior+meioSuperior+cantoDireitoSuperior+meioEsquerdo+meioDireito+cantoEsquerdoInferior+meioInferior+cantoDireitoInferior;
            return  vizinhaca;
	}
  
    /**
     *
     * @param celula
     * @param variacao
     * @return
     */
    private void alteraEstadoDoTomateiro(MeuJPanel celula, Double variacao) {
        Tomates tom = celula.getTom();
        ImagemProcessada imagemProcessada = tom.getImagemProcessada();
        int estado = imagemProcessada.getEstado();
        //para não estrapolar os limites dos estados, ou seja, se tiver no ultimo estado não faz nada
        if (estado >= ESTADO_MINIMO && estado < ESTADO_MAXIMO) {
            Double estadoComVariacao = imagemProcessada.getEstadoComVariacao();
            //para não estrapolar os limites dos estados, ou seja, se tiver no ultimo estado não faz nada        
            Double novoEstadoComVariacao = (estadoComVariacao + variacao) <= ESTADO_MAXIMO ? (estadoComVariacao + variacao) : ESTADO_MAXIMO;  
            imagemProcessada.setEstadoComVariacao(novoEstadoComVariacao);
            imagemProcessada.setEstado((int) Math.round(imagemProcessada.getEstadoComVariacao()));
        }
    }

    /**
     * SISTOM-10
     * @param aux
     * @param i
     * @param j
     * @param direcao
     * @param variacao
     */
   public void afetaVizinhaca(MeuJPanel [][] aux, int i, int j, DirecoesDoVento direcao, Double variacao){
        int min = 0;
        int max_X = this.X-1;
        int max_Y = this.Y-1;
        if (DirecoesDoVento.SUDESTE_PARA_NOROESTE.equals(direcao) || DirecoesDoVento.LESTE_PARA_OESTE.equals(direcao) || DirecoesDoVento.SUL_PARA_NORTE.equals(direcao)){
             if(i!=min && j!=min){
                 MeuJPanel cantoEsquerdoSuperior = aux[i - 1][j - 1];
                 alteraEstadoDoTomateiro(cantoEsquerdoSuperior, variacao);
                 
                 
             }
        }
        if (DirecoesDoVento.SUDESTE_PARA_NOROESTE.equals(direcao) || DirecoesDoVento.SUDOESTE_PARA_NORDESTE.equals(direcao) || DirecoesDoVento.SUL_PARA_NORTE.equals(direcao)){
             if(i!=min){
                 MeuJPanel meioSuperior =  aux[i - 1][j];
                 alteraEstadoDoTomateiro(meioSuperior, variacao);
                 
             }
        }
        if (DirecoesDoVento.OESTE_PARA_LESTE.equals(direcao) || DirecoesDoVento.SUDOESTE_PARA_NORDESTE.equals(direcao) || DirecoesDoVento.SUL_PARA_NORTE.equals(direcao)){
             if((i!=min && j!=max_Y)){
                 MeuJPanel cantoDireitoSuperior =  aux[i - 1][j + 1];
                 alteraEstadoDoTomateiro(cantoDireitoSuperior, variacao);
                 
             }
        }
        if (DirecoesDoVento.SUDESTE_PARA_NOROESTE.equals(direcao) || DirecoesDoVento.LESTE_PARA_OESTE.equals(direcao) || DirecoesDoVento.NORDESTE_PARA_SUDOESTE.equals(direcao)){
             if(j!=min){
                 MeuJPanel meioEsquerdo = aux[i][j - 1];
                 alteraEstadoDoTomateiro(meioEsquerdo, variacao);
                 
             }
        }
        if (DirecoesDoVento.SUDOESTE_PARA_NORDESTE.equals(direcao) || DirecoesDoVento.OESTE_PARA_LESTE.equals(direcao) || DirecoesDoVento.NOROESTE_PARA_SUDESTE.equals(direcao)){
             if(j!=max_Y){
                 MeuJPanel meioDireito = aux[i][j + 1];
                 alteraEstadoDoTomateiro(meioDireito, variacao);
                 
             }
        }
        if (DirecoesDoVento.NORDESTE_PARA_SUDOESTE.equals(direcao) || DirecoesDoVento.LESTE_PARA_OESTE.equals(direcao) || DirecoesDoVento.NORTE_PARA_SUL.equals(direcao)){
             if((i!=max_X && j!=min)){
                 MeuJPanel cantoEsquerdoInferior = aux[i + 1][j - 1];
                 alteraEstadoDoTomateiro(cantoEsquerdoInferior, variacao);
                 
             }
        }
        if (DirecoesDoVento.NORDESTE_PARA_SUDOESTE.equals(direcao) || DirecoesDoVento.NORTE_PARA_SUL.equals(direcao) || DirecoesDoVento.NOROESTE_PARA_SUDESTE.equals(direcao)){
             if(i!=max_X){
                 MeuJPanel meioInferior = aux[i + 1][j];
                 alteraEstadoDoTomateiro(meioInferior, variacao);
                 
             }
        }
        if (DirecoesDoVento.NORTE_PARA_SUL.equals(direcao) || DirecoesDoVento.OESTE_PARA_LESTE.equals(direcao) || DirecoesDoVento.NOROESTE_PARA_SUDESTE.equals(direcao)){
             if((i!=max_X && j!=max_Y)){
                 MeuJPanel cantoDireitoInferior = aux[i + 1][j + 1];
                 alteraEstadoDoTomateiro(cantoDireitoInferior, variacao);
                 
             }
        }
        
   }
   
    /**
     * SISTOM-12
     * @param mediasTempDosDias
     * @return
     */
    public Double calcularMediaDosCincoDias (final List<Double> mediasTempDosDias){

        int size = mediasTempDosDias.size();
        Double somatorio = 0.0;
        for (Double mediaTemp : mediasTempDosDias) {
            somatorio += mediaTemp;
        }

        Double mediaDosCincoDias = somatorio / size;
        return mediaDosCincoDias;

    }
    
    /**
     * SISTOM-12
     * @param mediasPrecDosDias
     * @return
     */
    public Double calcularSomaDePrecipitacaoDosDezDias(final List<Double> mediasPrecDosDias) {
        Double somaDosDezDias = 0.0;
        for (Double mediaPrecDoDia : mediasPrecDosDias) {
            somaDosDezDias += mediaPrecDoDia;
        }

        return somaDosDezDias;

    }
   
   
    /**
     *SISTOM-11
     * @param data
     */
    public void calculaRiscos(Date data) {
        GregorianCalendar calendar = new GregorianCalendar();

        GregorianCalendar calendarDataInicio = new GregorianCalendar();
        calendarDataInicio.setTime(this.dataInicio);

        int diaDataInicio = calendar.get(GregorianCalendar.DAY_OF_MONTH);

        int mesDataInicio = calendar.get(GregorianCalendar.MONTH) + 1;

        //CALCULAR A MEDIA DAS TEMPERATURAS DOS 5 DIAS ANTERIORES A DATA
        calendar.setTime(data);
        final List<Double> mediasTempDosDias = new ArrayList<>();
        calendar.add(Calendar.DAY_OF_MONTH, -QTD_DIAS_MEDIA_TEMP_PARA_FAVORAVEL);
        for (int i = 0; i < QTD_DIAS_MEDIA_TEMP_PARA_FAVORAVEL; i++) {
            int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
            //Meses no java é um arrai de 0-11, portanto somamos +1 para ficar de acordo com a realidade
            int mes = calendar.get(GregorianCalendar.MONTH) + 1;
            //Caso for a data escolhida no fomulário(data de inicio da simulação)
            //SISTOM-14
            if (dia == diaDataInicio && mes == mesDataInicio) {
                mediasTempDosDias.add(this.temp);

            }//if
            else {
                Date coletaFim = this.localidade.getColetaFim();

                final Double mediaTempDoDiaDoAno = DadosMeteorologicosDAO.getMediaTempEntreDatasPorDiaDoMesELocalidadeBaseadoEmHyre(dataBaseadaNaMediaHistorica, coletaFim, dia, mes, localidade);

                if (mediaTempDoDiaDoAno != null) {
                    mediasTempDosDias.add(mediaTempDoDiaDoAno);

                }//if
            }

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }

        //resultados 1
        int qtdDeDiasComTempFavoraveis = mediasTempDosDias.size();
        Double mediaDasTempDosCincoDias = calcularMediaDosCincoDias(mediasTempDosDias);

        //AGORA CALCULAR A SOMA DA PRECIPITAÇÃO DOS 10 DIAS ANTERIORES A DATA
        calendar.setTime(data);

        calendar.add(Calendar.DAY_OF_MONTH, -QTD_DIAS_PRECIPITACAO_PARA_FAVORAVEL);
        final List<Double> mediasPrecDosDias = new ArrayList<>();
        for (int i = 0; i < QTD_DIAS_PRECIPITACAO_PARA_FAVORAVEL; i++) {
            int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
            //Meses no java é um arrai de 0-11, portanto somamos +1 para ficar de acordo com a realidade
            int mes = calendar.get(GregorianCalendar.MONTH) + 1;
            //Caso for a data escolhida no fomulário(data de inicio da simulação)
            //SISTOM-14
            if (dia == diaDataInicio && mes == mesDataInicio) {
                mediasPrecDosDias.add(this.prec);
            } else {
                Date coletaFim = this.localidade.getColetaFim();

                final Double mediaPrecDoDiaDoAno = DadosMeteorologicosDAO.getMediaPrecipitacaoEntreDatasPorDiaDoMesELocalidadeBaseadoEmHyre(dataBaseadaNaMediaHistorica, coletaFim, dia, mes, localidade);

                if (mediaPrecDoDiaDoAno != null) {

                    mediasPrecDosDias.add(mediaPrecDoDiaDoAno);

                }//if

            }

            calendar.add(Calendar.DAY_OF_MONTH, 1);

        }
         
          //resultados 2
        Double somaPrecDosDezDias = calcularSomaDePrecipitacaoDosDezDias(mediasPrecDosDias);
        
      // Double mediaChuva = mediasDoDiaDoAno.getValue2();
        //Modelo Hyre 1954
       if ( qtdDeDiasComTempFavoraveis >= QTD_DIAS_MEDIA_TEMP_PARA_FAVORAVEL && mediaDasTempDosCincoDias <= MEDIA_CINCO_DIAS_PARA_FAVORAVEL && somaPrecDosDezDias >= SOMA_DE_PRECIPITACAO_PARA_FAVORAVEL) {
           this.count++;
           this.diasFavoraveis++;
       } else {
           this.count = 0;
       }
       
       //SISTOM-19
       
        JTextField textDiasFavoraveisSeguidos = parent.getTextDiasFavoraveisSeguidos();
        textDiasFavoraveisSeguidos.setText(Integer.toString(this.count));
        JTextField textDiasFavoraveis = parent.getTextDiasFavoraveis();
        textDiasFavoraveis.setText(Integer.toString(this.diasFavoraveis));
        this.risco10 = this.count >= 10 ? 1 : 0;
        this.risco7 = this.count >= 7 ? 1 : 0;
        this.risco5 = this.count >= 5 ? 1 : 0;

      // return mediasDoDiaDoAno;

   }
	
    /**
     *
     * @param matrizPainel
     * @return
     */
    public MeuJPanel[][] iteracao(final MeuJPanel[][] matrizPainel) {
        MeuJPanel[][] aux = new MeuJPanel[X][Y];
         this.matriz = matrizPainel;
        //aux=this.matriz;
       
        int i, j, max_X, max_Y;
        //codigo abaixo consegue clonar a original
        //SISTOM-13
        for (i = 0; i < this.X; i++) {
            for (j = 0; j < this.Y; j++) {
                aux[i][j] = new MeuJPanel();
                Tomates novoTomate = matriz[i][j].getTom().clone();
                aux[i][j].setTom(novoTomate);
            }//for
        }//for 
        max_X = this.X - 1;
        max_Y = this.Y - 1;
        Double variacao, variacaoCombate;
        String verificador = Integer.toString(surto + 1);
        surto = count/10.0 == Double.parseDouble(verificador) ? surto+1 : surto;
        if (surto >= 1) {
            diasAposPrimeiroSurto++;        
        }
        
        //criar variavel de variacao
        for (i = 0; i < this.X; i++) {
            for (j = 0; j < this.Y; j++) {
                Tomates tom = matriz[i][j].getTom();
                ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                int estado = imagemProcessada.getEstado();
                variacao = 0.0;
                variacaoCombate = 0.0;
                
                // COLOCAR REGRAS AQUI
                switch (estado) {
                    case 0: {
                       // if ( (surto == 1 && diasAposPrimeiroSurto >= 7) || (surto > 1) ) {
                       if ( surto > 2 ) {
                            if (risco10 > 0) {
                                variacao = 0.0;
                                break;
                            }//if 
                            else if (risco7 > 0) {
                                variacao = 0.0;
                                break;
                            }//elseif
                            else if (risco5 > 0 && surto >= 3) {
                                variacao = 0.0;
                                break;
                            }//elseif
                        }//if
                        
                        
                        break;      
                         
                       
                    }
                    case 1: {
                        //if ( (surto == 1 && diasAposPrimeiroSurto >= 7) || (surto > 1) ) {
                        if ( surto > 1 ){
                            if (risco10 > 0) {
                                variacao = 0.1; 
                                break;
                            }//if 
                            else if(risco7 > 0){
                                variacao = 0.1;
                                break;
                            }//elseif
                            else if(risco5 > 0 && surto >= 3){
                                variacao = 0.1;
                                break;
                            }//elseif
                        }//if
                        
                        break; 
                    }
                    case 2: {
                        //if ((surto == 1 && diasAposPrimeiroSurto >= 7) || (surto > 1)) {
                         if ( surto > 1 ) {
                            if (risco10 > 0) {
                                variacao = 0.8;
                                break;
                            }//if 
                            else if (risco7 > 0) {
                                variacao = 0.5;
                                break;
                            }//elseif
                            else if (risco5 > 0 && surto >= 3) {
                                variacao = 0.4;
                                break;
                            }//elseif
                        }
                        break;
                    }
                    case 3: {
                       // if ((surto == 1 && diasAposPrimeiroSurto >= 7) || (surto > 1)) {
                        if ( surto >= 1 ) {
                            if (risco10 > 0) {
                                variacao = 1.4;
                                break;
                            }//if 
                            else if (risco7 > 0) {
                                variacao = 1.0;
                                break;
                            }//elseif
                            else if (risco5 > 0 && surto >= 3) {
                                variacao = 0.6;
                                break;
                            }//elseif
                        }//if
                             break;
                    }
                    case 4: {
                        //if ((surto == 1 && diasAposPrimeiroSurto >= 7) || (surto > 1)) {
                        if ( surto >= 1 ) {
                            if (risco10 > 0) {
                                variacao = 1.6;
                                break;
                            }//if 
                            else if (risco7 > 0) {
                                variacao = 1.1;
                                break;
                            }//elseif
                            else if (risco5 > 0 && surto >= 3) {
                                variacao = 0.6;
                                break;
                            }//elseif
                        }//if
                        break;
                    }
                    case 5: {
                        //if ((surto == 1 && diasAposPrimeiroSurto >= 7) || (surto > 1)) {
                        if ( surto >= 1 ) {
                            if (risco10 > 0) {
                                variacao = 1.8;
                                break;
                            }//if 
                            else if (risco7 > 0) {
                                variacao = 1.2;
                                break;
                            }//elseif
                            else if (risco5 > 0 && surto >= 3) {
                                variacao = 0.8;
                                break;
                            }//elseif
                        }//if
                        break;
                    }
                    case 6: {
                        //if ((surto == 1 && diasAposPrimeiroSurto >= 7) || (surto > 1)) {
                        if ( surto >= 1 ) {
                            if (risco10 > 0) {
                                variacao = 2.0;
                                break;
                            }//if 
                            else if (risco7 > 0) {
                                variacao = 1.4;
                                break;
                            }//elseif
                            else if (risco5 > 0 && surto >= 3) {
                                variacao = 0.8;
                                break;
                            }//elseif
                        }
                        
                        break;
                    }
                    default: {
                        break;
                    }

                }//switch
                
                afetaVizinhaca(aux, i, j, direcao, variacao);
                
                //SISTOM-15
                if (combateEmProgresso()) {
                    aplicaCombate(aux, i, j);
                }//if
            }//for j
        }//for i
        JTextField textDiasRestantes = this.parent.getTextDiasRestantes();
        JTextField textCombateCorrente = this.parent.getTextCombateCorrente();
        //SISTOM-15
        //A cada iteracao, diminui o tempo do combate
         if (combateEmProgresso()) {
            this.combateProgresso--;
            //SISTOM-19
            
            textDiasRestantes.setText(Integer.toString(this.combateProgresso));
         }//if
         else{
             //SISTOM-19
             
             //this.combate = null;
             textDiasRestantes.setText("-");
             textCombateCorrente.setText("-");
         }//else
        
        //Isso foi mudado para a classe Painel de Simulação na Thread
        /*for (i = 0; i < this.X; i++) {
            for (j = 0; j < this.Y; j++) {
                this.matriz[i][j].setTom(aux[i][j].getTom());
            }//for
        }//for */
        return aux;
    }
    
    /**
     * //SISTOM-15
     * @param aux
     * @param i
     * @param j
     */
    public void aplicaCombate(MeuJPanel [][] aux, int i, int j){
        Double variacaoCombate = 0.0;
        if (this.combate != null) {
            AbstractStrategyCombate strategy = this.combate.getStrategy();
            Double resultado = strategy.getResultado();
            variacaoCombate = resultado;
        }
        Tomates tom = aux[i][j].getTom();
        ImagemProcessada imagemProcessada = tom.getImagemProcessada();
        Double estadoComVariacao = imagemProcessada.getEstadoComVariacao();
        Double calc = estadoComVariacao - variacaoCombate;

        Double novoEstadoComVariacao = calc >= ESTADO_MINIMO ? calc : ESTADO_MINIMO;
        imagemProcessada.setEstadoComVariacao(novoEstadoComVariacao);
        imagemProcessada.setEstado((int) Math.round(imagemProcessada.getEstadoComVariacao()));
    }
    
      private void repinta() {
        int i, j;
      
         for (i = 0; i < this.X; i++) {
            for (j = 0; j < this.Y; j++) {
                matriz[i][j].repaint();
            }//for
         }//for
    }

   /*
      @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                int i;

                //SISTOM-11
                this.surto = 0;

                Date coletaFim = this.localidade.getColetaFim();

                GregorianCalendar calendar = new GregorianCalendar();
                calendar.setTime(coletaFim);
                //basear nas media dos n anos passados para ter uma base do futuro.
                //A soma com 1 é para garantir que terá dados suficientes para a média, ou caso o bd não tenha algum ano completo(principalmente o ultimo)
                calendar.add(Calendar.YEAR, -(this.mediaHistorica + 1));
                this.dataBaseadaNaMediaHistorica = calendar.getTime();

                //Seta os valores iniciais 
                this.count = 0;
                this.risco5 = 0;
                this.risco7 = 0;
                this.risco10 = 0;
                //SISTOM-12
                this.surto = 0;
                this.diasAposPrimeiroSurto = 0;

                for (i = 0; i < this.qtdIterecao; i++) {
                   
                   //Date data = new Date();
                   //data.setDate(dataInicio.getDate() +1);
                    Date data;
                    if (i > 0) {
                        calendar.setTime(dataInicio);
                        calendar.add(Calendar.DAY_OF_MONTH, i);
                        data = calendar.getTime();
                    }//if
                    else {
                        data = this.dataInicio;
                    }//else
                    calculaRiscos(data);

                    System.out.println("Iteração: " + (i + 1));
                    Thread.sleep(2000);
                    matriz = iteracao();
                    imprime();
                    repinta();
                    
                }//for
                Thread.currentThread().interrupt();
            } catch (InterruptedException e1) {
                System.out.println("(" + Thread.currentThread().getId() + " - " + Thread.currentThread().getName() + ") foi interrompida enquanto estava em sleep!");
                Thread.currentThread().interrupt(); //cancelando a thread corrente
                //e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        System.out.println("(" + Thread.currentThread().getId() + " - " + Thread.currentThread().getName() + ") será finalizada!");
    }*/

    /**
     * @return the combate
     */
    public Combate getCombate() {
        return combate;
    }

    /**
     * @param combate the combate to set
     */
    public void setCombate(Combate combate) {
        this.combate = combate;
        atualizaCombateProgresso();
       
    }
    
    public void atualizaCombateProgresso(){
        if (this.combate != null) {
            AbstractStrategyCombate strategy = combate.getStrategy();
            int duracao = strategy.getDuracao();
            this.combateProgresso = duracao;
        }
    }
    
    /**
     *
     * @return
     */
    public Boolean combateEmProgresso(){
        return (this.combate!=null && this.combateProgresso > 0);
    }

  

}
