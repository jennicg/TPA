package aplicacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;

import _my_tools.ArquivoTxt;
import taddic.TADDicChain;

public class TADMatriz {
	//Armazenar valores diferentes de zero
	private int linhas;
    private int colunas;
    //private float [][] mat = null;
    TADDicChain dicA;
    
    //cria um objeto matriz do tipo tad matriz representando uma matriz de dimens�es <int linhas> x <int  colunas>.
	public TADMatriz(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		dicA = new TADDicChain();
	}
	
	
	public TADDicChain getDicA() {
		return this.dicA;
	}
	
	/*retorna o elemento n�mero real armazenado na posi��o i (linha), j (coluna) da matriz. Retorna Null se o
	elemento n�o existir (i e j forem valores que extrapolam as dimens�es da matriz).
*/
	public Float getElem(int i, int j) {
		if(i > this.quantLinhas() || j > this.quantColunas())  {
			return null;
		}
		String chave = i+","+j;
		Float dado = (Float)this.dicA.findElement(chave);
		if(dado == null) {
			return (float)0.0;
		}
		else {
			return dado;
		}
		
	}
	
	
	/*
	 armazena o elemento valor na posi��o i (linha), j (coluna) da matriz. Retorna Null se a posi��o n�o
	existir (i e j forem valores que extrapolam as dimens�es da matriz). Retorna valor, caso contr�rio.
	 */
	
	public Float setElem(int i, int j, Float valor) {
		if(i > this.quantLinhas() || j > this.quantColunas()) {
			return null;
		}
		String chave = i+","+j;
		if (valor != 0.0) {
			this.dicA.insertItem(chave, valor);
			return valor;
		}
		else {
			this.dicA.insertItem(chave, null);
			
		}
			
		return valor;

	}

	
	
	public int quantLinhas() {
		return linhas;
	}

	public int quantColunas() {
		return colunas;
	}

				
	/*
	 * soma a matriz corrente, this, com a matriz m e retorna uma terceira matriz com o resultado da soma. O
	m�todo deve usar o conceito matem�tico de soma de matrizes. Retorna Null se as matrizes n�o puderem ser somadas.
	*/

	public TADMatriz soma(TADMatriz A) {
		if (A.linhas== this.linhas && A.colunas == this.colunas) {
			TADMatriz matriz = new TADMatriz(A.linhas,this.colunas);
			for(int i = 0; i < A.linhas; i++) {
				for(int j = 0; j < A.colunas; j++) {
					String chave = i+","+j;
					Float v1 = (Float)this.dicA.findElement(chave);
					if(v1 == null) {
						v1 = (float) 0;
					}
					Float v2 = (Float)A.getDicA().findElement(chave);
					if(v2 == null) {
						v2 = (float) 0;
					}
					
					matriz.setElem(i, j, v1+v2);
					
				}
				
			}
				
					
			return matriz;
		}
		System.out.println("N�o foi poss�vel realizar a soma");
		return null;
	}
	
	
	// Fun��o subtrair para ser usada na aplica��o, similar a soma
	public TADMatriz sub(TADMatriz A) {
		if (A.linhas== this.linhas && A.colunas == this.colunas) {
			TADMatriz matriz = new TADMatriz(A.linhas,this.colunas);
			for(int i = 0; i < A.linhas; i++) {
				for(int j = 0; j < A.colunas; j++) {
					String chave = i+","+j;
					Float v1 = (Float)this.dicA.findElement(chave);
					if(v1 == null) {
						v1 = (float) 0;
					}
					Float v2 = (Float)A.getDicA().findElement(chave);
					if(v2 == null) {
						v2 = (float) 0;
					}
					
					matriz.setElem(i, j, v1-v2);
					
				}
				
			}
				
					
			return matriz;
		}
		System.out.println("N�o foi poss�vel realizar a subtra��o");
		return null;
	}
	
	/*multiplica a matriz corrente,
	this, pela matriz m de entrada e retorna uma terceira matriz com o resultado
	da multiplica��o. O m�todo deve usar o conceito matem�tico de multiplica��o
	de matrizes. Retorna Null se as matrizes n�o puderem ser multiplicadas.
*/
	
	
	 public TADMatriz multi(TADMatriz A){
	        if(this.quantColunas() != A.quantLinhas()) return null;
	        TADMatriz matriz = new TADMatriz(this.quantLinhas(), A.quantColunas());
	        for(int i=0; i<this.quantLinhas();i++)
	            for(int j=0;j<A.quantColunas();j++)
	                for(int aux =1;aux<=this.quantColunas();aux++)
	                    matriz.setElem(i, j, matriz.getElem(i, j) + (this.getElem(i, aux) * A.getElem(aux, j)));

	        return matriz;
	    }
	 
	/*
	 * carrega uma matriz a partir de um arquivo texto de nome nome_arq. Retorna uma matriz do
	tipo TADMatriz preenchida com o conte�do arquivo. No arquivo, a matriz est� 
	representada da seguinte forma: elementos de cada linha da matriz separados 
	por espa�o, cada linha do arquivo texto � uma linha da matriz.
	 */
	
	public static TADMatriz carrega(String nomeArq) throws FileNotFoundException {
		File f1 = new File (nomeArq);
		Scanner s = new Scanner(f1);
		String linha = "";
		LinkedList<String> lst = new LinkedList<String>();
		int tamLinha = 0;
		int tamCol = 0;
		while(s.hasNextLine()) {
			tamLinha++;
			linha = s.nextLine();
			String[] vet = linha.split(" ");
			for (int i = 0; i < vet.length; i++) {
				if(!vet[i].isEmpty()) {
					lst.addLast(vet[i]);
	
					
				}
				}
			if(tamLinha ==1) {
				tamCol = lst.size();
			}
			
			}
	
		TADMatriz matriz = new TADMatriz(tamLinha,tamCol);
		int posLst = 0;
		for(int i = 0; i< tamLinha; i++) {
			for(int j = 0; j< tamCol; j++) {
				matriz.setElem(i,j,(Float.parseFloat(lst.get(posLst))));
				posLst++;
				
			}
		}
	
		return matriz;
	}
	
	
	/*
	 * salva uma matriz em um arquivo texto de nome nome_arq. 
	 * Retorna o nome do arquivo usado parasalvar a matriz. No arquivo,
	 *  a matriz deve estar representada da seguinte forma: elementos 
	 *  de cada linha da matriz separados por espa�o, cada linha de
	 *  texto � uma linha da matriz.
	  */
	public String salva(String nomeArq) throws IOException {
		String result = nomeArq;
		File resultado = new File(result);	
		
		FileWriter fw = new FileWriter(resultado);
		PrintWriter gravarArq = new PrintWriter(fw);
		
		for(int i = 0; i < this.quantLinhas(); i++) {
			for(int j = 0; j < this.quantColunas();j++) {
				gravarArq.print(this.getElem(i, j) + " ");
			}
			gravarArq.println();
			
		}
		fw.close();
		return result;

	
	}
	

	//Diagonal Principal
	public  LinkedList<Float> diagP(TADMatriz A) {
		LinkedList<Float> lst = new LinkedList<Float>();
		if (A.quantLinhas() == A.quantColunas()) {
			for(int i = 0; i < A.quantLinhas(); i++) {
				
				lst.add(A.getElem(i,i));
				//System.out.println(lst.size());
			}
				
			
		}
		return lst;
	}	
	
	
	//Diagonal Secund�ria		
	public LinkedList<Float> diagS(TADMatriz A) {
		LinkedList<Float> lst = new LinkedList<Float>();
		if (A.quantLinhas() == A.quantColunas()) {
			int i = A.quantColunas();
			for(int j = 0; j< A.quantLinhas();j++) {
				lst.add(A.getElem(i,j));
				i -= 1;
			}
		
	}
		return lst;
	}

	
	/*
	 * Altera a matriz corrente, this, multiplicando os seus elementos por k.
	*/
	public void vezesK(float k) {
		//n�o trato de k = 0, pois o setElem j� faz isso
		for(int i = 0; i < this.linhas; i++) {
			for(int j = 0; j < this.colunas; j++) {
				String chave = i+","+j;
				Float dado = (Float)this.dicA.findElement(chave);
				if(dado == null) {
					dado = (float)0;
				}
				this.setElem(i, j, dado*k);
				
			}
		}
		}
	
	// printar matriz como string, utilizada para testes
	public String printMatrizTeste() {
			String deposito = "";
			for(int i = 0; i < this.quantLinhas(); i++) {
				for(int j = 0; j < this.quantColunas(); j++) {
					deposito += String.valueOf(this.getElem(i,j)) + "";
					deposito += '\n';
					
				}
				
			}
			return deposito;

	}
	
	public void printMatriz() {
		for(int i=1; i <= this.linhas;i++){
            for(int j=1; j <= this.colunas;j++){
                System.out.print(this.getElem(i, j)+" | ");
            }
            System.out.println("");
		}
	}
	
	/*
	 * retorna uma nova matriz com a transposta da matriz corrente, this.
	 * linha vira coluna, coluna vira linha
	 */
	
	public TADMatriz transposta() {
		TADMatriz matriz = new TADMatriz(this.colunas,this.linhas);
	    for (int i=0;i<this.colunas;i++)   
	        for (int j=0;j<this.linhas;j++)    
	        	matriz.setElem(i,j,(getElem(j,i)));  
	    return  matriz;
	}
	
		

}
