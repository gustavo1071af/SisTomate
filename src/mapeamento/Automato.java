package mapeamento;

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
        private Tomates [][] matriz;

    /**
     *
     * @param talhao
     */
    public Automato(Talhao talhao) {
        this.talhao = talhao;
        this.X = talhao.getQtd_TomatesPorLinhas();
        //considerando que cada rua tem 2 linhas.
        this.Y = talhao.getQtdRuas() * 2;
        this.matriz = new Tomates [X][Y];
    }

	
	public void inicializa(Tomates matriz[][])//recebe valor inicial
	{
		int i, j, max_X, max_Y;
                
		max_X = this.X-1;
                max_Y = this.Y-1;
     
		for(i=0;i < max_X; i++){
			for(j=0; j < max_Y; j++){
				//matriz[i][j] = (int)(Math.random()*1.5);
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
                            ImagemProcessada imagemProcessada = matriz[i][j].getImagemProcessada();
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
            int cantoEsquerdoSuperior = (i!=min && j!=min) ? matriz[i - 1][j - 1].getImagemProcessada().getEstado(): 0;
            int meioSuperior = (i!=min) ? matriz[i - 1][j].getImagemProcessada().getEstado() : 0;
            int cantoDireitoSuperior = (i!=min && j!=max_Y) ? matriz[i - 1][j + 1].getImagemProcessada().getEstado() : 0;
            int meioEsquerdo = (j!=min) ? matriz[i][j - 1].getImagemProcessada().getEstado() : 0;
            int meioDireito = (j!=max_Y) ? matriz[i][j + 1].getImagemProcessada().getEstado() : 0;
            int cantoEsquerdoInferior = (i!=max_X && j!=min) ? matriz[i + 1][j - 1].getImagemProcessada().getEstado() : 0;
            int meioInferior = (i!=max_X) ? matriz[i + 1][j].getImagemProcessada().getEstado(): 0 ;
            int cantoDireitoInferior = (i!=max_X && j!=max_Y) ? matriz[i + 1][j + 1].getImagemProcessada().getEstado() : 0;
            
            //vizinhaca é a soma dos estados
            int vizinhaca = cantoEsquerdoSuperior+meioSuperior+cantoDireitoSuperior+meioEsquerdo+meioDireito+cantoEsquerdoInferior+meioInferior+cantoDireitoInferior;
            return  vizinhaca;
	}
	
    /**
     *
     * @return
     */
    public Tomates [][] iteracao(){
		Tomates [][] aux = new Tomates [X][Y];
		int i, j, max_X, max_Y;
                max_X = this.X-1;
                max_Y = this.Y-1;
		
		for (i = 0; i < max_X; i++){
			for (j = 0; j < max_Y; j++){
                            ImagemProcessada imagemProcessada = matriz[i][j].getImagemProcessada();
                            int estado = imagemProcessada.getEstado();
                               // COLOCAR REGRAS AQUI
                               aux[i][j] = matriz[i][j];
                               ImagemProcessada imagemProcessadaForAux= aux[i][j].getImagemProcessada();
				if( estado == 1){
					if((vizinhos(i,j) < 2) || (vizinhos(i,j) > 3)){
                                           
                                            imagemProcessadaForAux.setEstado(2);
					}
					else{
						imagemProcessadaForAux.setEstado(1);
					}
				}
				else{
					if( vizinhos(i,j) == 3 ){
						imagemProcessadaForAux.setEstado(1);
					}
					else{
						imagemProcessadaForAux.setEstado(2);
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
		}
	
	}
	
}
