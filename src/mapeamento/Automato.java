package mapeamento;

import ENUNS.DirecoesDoVento;
import ENUNS.SimOuNao;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import mapeamento.DAO.DadosMeteorologicosDAO;
import mapeamento.DAO.LocalidadeDAO;
import mapeamento.beans.ImagemProcessada;
import mapeamento.beans.Localidade;
import mapeamento.beans.Talhao;
import mapeamento.beans.Tomates;
import org.javatuples.Triplet;

/**
 *  SISTOM-4
 * @author Gustavo
 * @creationDate 10/04/2016
 */
public class Automato {

    private final Talhao talhao;
    private final int X;
    private final int Y;
    private MeuJPanel[][] matriz;
    private int risco3;
    private int risco5;
    private int risco10;
    private int count;
    private DirecoesDoVento direcao;
    private int mediaHistorica;
    private int qtdIterecao;
    private Date dataInicio;
    private Localidade localidade;
    private final static int ESTADO_MAXIMO = 6;
    private final static int ESTADO_MINIMO = 0;
    private Date dataBaseadaNaMediaHistorica;


    /**
     *
     * @param talhao
     */
    public Automato(Talhao talhao) {
        this.talhao = talhao;
        this.X = talhao.getQtd_TomatesPorLinhas();
        //considerando que cada rua tem 2 linhas.
        this.Y = talhao.getQtdRuas() * 2;
        this.matriz = new MeuJPanel[X][Y];
    }

    public void inicializa(MeuJPanel matriz[][])//recebe valor inicial
    {
        int i, j, max_X, max_Y;

        max_X = this.X - 1;
        max_Y = this.Y - 1;

        for (i = 0; i < max_X; i++) {
            for (j = 0; j < max_Y; j++) {
                //SISTOM-10

                Tomates tom = matriz[i][j].getTom();
                ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                //Seta o atributo estadoComVariacao com o valor do estado inicial para somar o estado comas as variacoes
                imagemProcessada.setEstadoComVariacaoInicio();

                this.matriz[i][j] = matriz[i][j];
            }
        }

    }

    /**
     *
     */
    public void imprime() {

        int i, j, max_X, max_Y;
        max_X = this.X - 1;
        max_Y = this.Y - 1;

        for (i = 0; i < max_X; i++) {
            for (j = 0; j < max_Y; j++) {
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
     *SISTOM-11
     * @param data
     * @return
     */
    public Triplet<Double, Double, Double> calculaRiscos(Date data){
       GregorianCalendar calendar = new GregorianCalendar();
       calendar.setTime(data);
       int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
       //Meses no java é um arrai de 0-11, portanto somamos +1 para ficar de acordo com a realidade
       int mes = calendar.get(GregorianCalendar.MONTH)+1;
       
        Date coletaFim = this.localidade.getColetaFim();
                
       Triplet<Double, Double, Double> mediasDoDiaDoAno = DadosMeteorologicosDAO.getMediasEntreDatasPorDiaDoMesELocalidade(dataBaseadaNaMediaHistorica, coletaFim, dia, mes, localidade);
       Double mediaTemp = mediasDoDiaDoAno.getValue0();
       Double mediaUmid = mediasDoDiaDoAno.getValue1();
      // Double mediaChuva = mediasDoDiaDoAno.getValue2();
        //VERIFICAR COMO SERÁ DEFINIDO O DIA FAVORAVEL A REQUEIMA
       if (mediaTemp > 24 && mediaUmid >= 90) {
           this.count++;
           //continuar aqui. colocar as regras do risco pela quantidade de dias favoráveis

       } else {
           this.count = 0;
       }
       this.risco10 = this.count >= 10 ? 1 : 0;
       this.risco5 = this.count >= 5 ? 1 : 0;
       this.risco3 = this.count >= 3 ? 1 : 0;
       
       return mediasDoDiaDoAno;

   }
	
    /**
     *
     * @param mediasDoDiaDoAno
     * @return
     */
    public MeuJPanel[][] iteracao(Triplet<Double, Double, Double> mediasDoDiaDoAno) {
        MeuJPanel[][] aux = new MeuJPanel[X][Y];
        int i, j, max_X, max_Y;
        max_X = this.X - 1;
        max_Y = this.Y - 1;

        Double media_temp = mediasDoDiaDoAno.getValue0();
        Double media_umid = mediasDoDiaDoAno.getValue1();
        Double media_chuva = mediasDoDiaDoAno.getValue2();
        Boolean chove = Math.round(media_chuva) == 1;
        //criar variavel de variacao
        for (i = 0; i < max_X; i++) {
            for (j = 0; j < max_Y; j++) {
                Tomates tom = matriz[i][j].getTom();
                ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                int estado = imagemProcessada.getEstado();
                // COLOCAR REGRAS AQUI
                aux[i][j] = matriz[i][j];
                ImagemProcessada imagemProcessadaForAux = tom.getImagemProcessada();
                switch (estado) {
                    case 0: {
                        //afetaVizinhaca
                        break;
                    }
                    case 1: {
                        //afetaVizinhaca
                        break;
                    }
                    case 2: {
                        //afetaVizinhaca
                        break;
                    }
                    case 3: {
                        //afetaVizinhaca
                        break;
                    }
                    case 4: {
                        //afetaVizinhaca
                        break;
                    }
                    case 5: {
                        //afetaVizinhaca
                        break;
                    }
                    case 6: {
                        //afetaVizinhaca
                        break;
                    }
                    default: {
                        break;
                    }

                }

            }
        }
        return aux;
    }

    /**
     *
     * @param umid
     * @param temp
     * @param direcao
     * @param chuva
     * @param dataInicio
     * @param mediaHistorica
     * @param qtdInter
     */
    public void simulaPropagação(int umid, int temp, DirecoesDoVento direcao, SimOuNao chuva, Date dataInicio, int mediaHistorica, int qtdInter) {

        int i;
        
        //SISTOM-11
        
        this.direcao = direcao;
        this.mediaHistorica = mediaHistorica;
        this.qtdIterecao = qtdInter;
        this.dataInicio = dataInicio;

        //temporario(talhao deve ter a localidade, mudar modelo depois)
        Localidade localidade = LocalidadeDAO.get(1);
        this.localidade = localidade;
        
        Date coletaFim = this.localidade.getColetaFim();
        
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(coletaFim);
        //basear nas media dos n anos passados para ter uma base do futuro.
        //A soma com 1 é para garantir que terá dados suficientes para a média, ou caso o bd não tenha algum ano completo(principalmente o ultimo)
        calendar.add(Calendar.YEAR, -(this.mediaHistorica+1));
        this.dataBaseadaNaMediaHistorica = calendar.getTime();
        
        //Seta os valores iniciais 
        this.count = 0;
        this.risco3 = 0;
        this.risco5 = 0;
        this.risco10 = 0;

        for (i = 0; i < this.qtdIterecao; i++) {
            /*  Date data = new Date();
                   data.setDate(dataInicio.getDate() +1);*/
            Date data;
            if (i > 0) {
                calendar.setTime(dataInicio);
                calendar.add(Calendar.DAY_OF_MONTH, i);
                data = calendar.getTime();
            }//if
            else {
                data = this.dataInicio;
            }//else
            Triplet<Double, Double, Double> mediasDoDiaDoAno = calculaRiscos(data);

            System.out.println("Iteração: " + (i + 1));
            imprime();
            matriz = iteracao(mediasDoDiaDoAno);
            //repinda()
        }

    }

  
	
}
