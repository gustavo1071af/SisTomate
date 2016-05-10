package mapeamento;

import ENUNS.DirecoesDoVento;
import mapeamento.beans.ImagemProcessada;
import mapeamento.beans.Talhao;
import mapeamento.beans.Tomates;

/**
 *  SISTOM-4
 * @author Gustavo
 * @creationDate 10/04/2016
 */
public class Automato {

        private final Talhao talhao;
	private final int X;
        private final int Y;
        private MeuJPanel [][] matriz;

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
                
		max_X = this.X-1;
                max_Y = this.Y-1;
     
		for(i=0;i < max_X; i++){
			for(j=0; j < max_Y; j++){
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
    public void imprime(){
		
		int i, j, max_X, max_Y;
                max_X = this.X-1;
                max_Y = this.Y-1;
		
		for(i=0; i <max_X; i++){
			for(j=0; j < max_Y; j++){
                            Tomates tom = matriz[i][j].getTom();
                            ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                            int estado = imagemProcessada.getEstado();
                            System.out.print(estado);
				
				
			}
			System.out.println();
		}
		
	}
	
    /**
     *
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
                 Tomates tom = cantoEsquerdoSuperior.getTom();
                 ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                  Double estadoComVariacao = imagemProcessada.getEstadoComVariacao();
                 imagemProcessada.setEstadoComVariacao(estadoComVariacao+variacao);
                 imagemProcessada.setEstado((int) Math.round(imagemProcessada.getEstadoComVariacao()));
                 
             }
        }
        if (DirecoesDoVento.SUDESTE_PARA_NOROESTE.equals(direcao) || DirecoesDoVento.SUDOESTE_PARA_NORDESTE.equals(direcao) || DirecoesDoVento.SUL_PARA_NORTE.equals(direcao)){
             if(i!=min){
                 MeuJPanel meioSuperior =  aux[i - 1][j];
                 Tomates tom = meioSuperior.getTom();
                 ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                 Double estadoComVariacao = imagemProcessada.getEstadoComVariacao();
                 imagemProcessada.setEstadoComVariacao(estadoComVariacao+variacao);
                 imagemProcessada.setEstado((int) Math.round(imagemProcessada.getEstadoComVariacao()));
                 
             }
        }
        if (DirecoesDoVento.OESTE_PARA_LESTE.equals(direcao) || DirecoesDoVento.SUDOESTE_PARA_NORDESTE.equals(direcao) || DirecoesDoVento.SUL_PARA_NORTE.equals(direcao)){
             if((i!=min && j!=max_Y)){
                 MeuJPanel cantoDireitoSuperior =  aux[i - 1][j + 1];
                 Tomates tom = cantoDireitoSuperior.getTom();
                 ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                 Double estadoComVariacao = imagemProcessada.getEstadoComVariacao();
                 imagemProcessada.setEstadoComVariacao(estadoComVariacao+variacao);
                 imagemProcessada.setEstado((int) Math.round(imagemProcessada.getEstadoComVariacao()));
                 
             }
        }
        if (DirecoesDoVento.SUDESTE_PARA_NOROESTE.equals(direcao) || DirecoesDoVento.LESTE_PARA_OESTE.equals(direcao) || DirecoesDoVento.NORDESTE_PARA_SUDOESTE.equals(direcao)){
             if(j!=min){
                 MeuJPanel meioEsquerdo = aux[i][j - 1];
                 Tomates tom = meioEsquerdo.getTom();
                 ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                 Double estadoComVariacao = imagemProcessada.getEstadoComVariacao();
                 imagemProcessada.setEstadoComVariacao(estadoComVariacao+variacao);
                 imagemProcessada.setEstado((int) Math.round(imagemProcessada.getEstadoComVariacao()));
                 
             }
        }
        if (DirecoesDoVento.SUDOESTE_PARA_NORDESTE.equals(direcao) || DirecoesDoVento.OESTE_PARA_LESTE.equals(direcao) || DirecoesDoVento.NOROESTE_PARA_SUDESTE.equals(direcao)){
             if(j!=max_Y){
                 MeuJPanel meioDireito = aux[i][j + 1];
                 Tomates tom = meioDireito.getTom();
                 ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                  Double estadoComVariacao = imagemProcessada.getEstadoComVariacao();
                 imagemProcessada.setEstadoComVariacao(estadoComVariacao+variacao);
                 imagemProcessada.setEstado((int) Math.round(imagemProcessada.getEstadoComVariacao()));
                 
             }
        }
        if (DirecoesDoVento.NORDESTE_PARA_SUDOESTE.equals(direcao) || DirecoesDoVento.LESTE_PARA_OESTE.equals(direcao) || DirecoesDoVento.NORTE_PARA_SUL.equals(direcao)){
             if((i!=max_X && j!=min)){
                 MeuJPanel cantoEsquerdoInferior = aux[i + 1][j - 1];
                 Tomates tom = cantoEsquerdoInferior.getTom();
                 ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                 Double estadoComVariacao = imagemProcessada.getEstadoComVariacao();
                 imagemProcessada.setEstadoComVariacao(estadoComVariacao+variacao);
                 imagemProcessada.setEstado( (int) Math.round( imagemProcessada.getEstadoComVariacao() ) );
                 
             }
        }
        if (DirecoesDoVento.NORDESTE_PARA_SUDOESTE.equals(direcao) || DirecoesDoVento.NORTE_PARA_SUL.equals(direcao) || DirecoesDoVento.NOROESTE_PARA_SUDESTE.equals(direcao)){
             if(i!=max_X){
                 MeuJPanel meioInferior = aux[i + 1][j];
                 Tomates tom = meioInferior.getTom();
                 ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                 Double estadoComVariacao = imagemProcessada.getEstadoComVariacao();
                 imagemProcessada.setEstadoComVariacao(estadoComVariacao+variacao);
                 imagemProcessada.setEstado( (int) Math.round( imagemProcessada.getEstadoComVariacao() ) );
                 
             }
        }
        if (DirecoesDoVento.NORTE_PARA_SUL.equals(direcao) || DirecoesDoVento.OESTE_PARA_LESTE.equals(direcao) || DirecoesDoVento.NOROESTE_PARA_SUDESTE.equals(direcao)){
             if((i!=max_X && j!=max_Y)){
                 MeuJPanel cantoDireitoInferior = aux[i + 1][j + 1];
                 Tomates tom = cantoDireitoInferior.getTom();
                 ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                 Double estadoComVariacao = imagemProcessada.getEstadoComVariacao();
                 imagemProcessada.setEstadoComVariacao(estadoComVariacao+variacao);
                 imagemProcessada.setEstado( (int) Math.round( imagemProcessada.getEstadoComVariacao() ) );
                 
             }
        }
        
   }
	
    /**
     *
     * @return
     */
    public MeuJPanel [][] iteracao(){
		MeuJPanel [][] aux = new MeuJPanel[X][Y];
		int i, j, max_X, max_Y;
                max_X = this.X-1;
                max_Y = this.Y-1;
		
		for (i = 0; i < max_X; i++){
			for (j = 0; j < max_Y; j++){
                            Tomates tom = matriz[i][j].getTom();
                            ImagemProcessada imagemProcessada = tom.getImagemProcessada();
                            int estado = imagemProcessada.getEstado();
                               // COLOCAR REGRAS AQUI
                               aux[i][j] = matriz[i][j];
                               ImagemProcessada imagemProcessadaForAux= tom.getImagemProcessada();
				if( estado == 1){
					if((vizinhos(i,j) < 2) || (vizinhos(i,j) > 3)){
                                           
                                            //AFETAVIZINHOS()
					}
					else{
						//AFETAVIZINHOS()
					}
				}
				else{
					if( vizinhos(i,j) == 3 ){
						//AFETAVIZINHOS()
					}
					else{
						//AFETAVIZINHOS()
					}
				}
			}
		}
		return aux;
	}
	
    /**
     *
     * @param quant
     */
    public void simulaPropagação(int quant){
		
		int i;
		
		for(i=0; i< quant; i++){
                    System.out.println("Iteração: "+ (i+1));
			imprime();
			matriz = iteracao();
                        //repinda()
		}
	
	}
	
}
