package TadDic;

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

public class TADMatriz {
	//Armazenar valores diferentes de zero
	private int linhas;
    private int colunas;
    private float [][] mat = null;
    
    //cria um objeto matriz do tipo tad matriz representando uma matriz de dimensões <int linhas> x <int  colunas>.
	public TADMatriz(int linhas, int colunas) {
		this.linhas = linhas;
		this.colunas = colunas;
		mat = new float[linhas][colunas];
	}
	
	/*retorna o elemento número real armazenado na posição i (linha), j (coluna) da matriz. Retorna Null se o
	elemento não existir (i e j forem valores que extrapolam as dimensões da matriz).
*/
	public Float getElem(int i, int j) {
		if (i < this.quantLinhas() && j < this.quantColunas() && i >=0 && j >=0)
				return mat[i][j];
		return null;

	}
	
	public void printMatriz() {
        
        float[][] c = this.mat;
        
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[i].length; j++) {
                System.out.printf(c[i][j] + " ");
            }
            System.out.println("");
        }
    }
	
	/*
	 armazena o elemento valor na posição i (linha), j (coluna) da matriz. Retorna Null se a posição não
	existir (i e j forem valores que extrapolam as dimensões da matriz). Retorna valor, caso contrário.
	 */
	public Float setElem(int i, int j, Float valor) {
		if (getElem(i,j) != null) {
			mat[i][j] = valor;
			return valor;
		}
		else
			return null;		
		
	}
	
	public int quantLinhas() {
		return linhas;
	}

	public int quantColunas() {
		return colunas;
	}

				
	/*
	 * soma a matriz corrente, this, com a matriz m e retorna uma terceira matriz com o resultado da soma. O
	método deve usar o conceito matemático de soma de matrizes. Retorna Null se as matrizes não puderem ser somadas.
	*/
	public TADMatriz soma(TADMatriz A, TADMatriz B) {
		if (A.linhas== B.linhas && A.colunas == B.colunas) {
			TADMatriz matriz = new TADMatriz(A.linhas,B.colunas);
			for(int i = 0; i < A.linhas; i++)
				for(int j = 0; j < A.colunas; j++)
					matriz.setElem(i,j,(A.getElem(i,j)+B.getElem(i,j))); 
			return matriz;
		}
		return null;
	}
	
	
	public TADMatriz sub(TADMatriz A, TADMatriz B) {
		if (A.quantLinhas()== B.quantLinhas() && A.quantColunas() == B.quantColunas()) {
			TADMatriz matriz = new TADMatriz(A.linhas,B.colunas);
			for(int i = 0; i < A.linhas; i++)
				for(int j = 0; j < A.colunas; j++)
					matriz.setElem(i,j,(A.getElem(i,j)-B.getElem(i,j))); 
			return matriz;
		}
		return null;
	}
	
	/*
	 * Altera a matriz corrente, this, multiplicando os seus elementos por k.
	*/
	public void vezesK(TADMatriz A, float k) {
		for(int i = 0; i < A.linhas; i++)
			for(int j = 0; j < A.colunas; j++)
				A.setElem(i,j,(A.getElem(i,j)*k)); 
			
		
	}
	
	/*multiplica a matriz corrente,
	this, pela matriz m de entrada e retorna uma terceira matriz com o resultado
	da multiplicação. O método deve usar o conceito matemático de multiplicação
	de matrizes. Retorna Null se as matrizes não puderem ser multiplicadas.
*/
	public TADMatriz multi(TADMatriz A, TADMatriz B) {
		if (A.colunas == B.linhas ) {
			//System.out.println("matriz A");
			//A.printMatriz();
			//System.out.println("MATRIZ B");
			//B.printMatriz();
			TADMatriz matriz = new TADMatriz(A.linhas,B.colunas);
			for(int i = 0; i < matriz.linhas; i++) {
				for(int j = 0; j < matriz.colunas; j++) {
					float aux = 0;
					for(int a = 0; a< A.colunas; a++) {
						aux += A.getElem(i,a) * B.getElem(a,j);
						//System.out.println(aux);
					}
					matriz.setElem(i,j,aux);
				}
			}
			return matriz;
				
		}
		System.out.println("Não foi possível realizar a multiplicação");
		return null;
	}
	
	/*
	 * retorna uma nova matriz com a transposta da matriz corrente, this.
	 */
	public TADMatriz transposta() {
		TADMatriz matriz = new TADMatriz(this.colunas,this.linhas);
		for(int linha=0;linha<this.quantLinhas();linha++){
			for(int coluna=0;coluna<mat[linha].length;coluna++){
				if(coluna>linha)
					matriz.setElem(linha,coluna,(getElem(coluna,linha)));	
			}
		}
		return matriz;
		
	}
	
	/*
	 * carrega uma matriz a partir de um arquivo texto de nome nome_arq. Retorna uma matriz do
	tipo TADMatriz preenchida com o conteúdo arquivo. No arquivo, a matriz está 
	representada da seguinte forma: elementos de cada linha da matriz separados 
	por espaço, cada linha do arquivo texto é uma linha da matriz.
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
				//System.out.println(vet[i]);
				if(!vet[i].isEmpty()) {
					lst.addLast(vet[i]);
	
					
				}
				}
			if(tamLinha ==1) {
				tamCol = lst.size();
			}
			
			}
	
		for (int i = 0; i < lst.size(); i++) {	
			
			
				//System.out.println(lst.size());
		
				//System.out.println(lst.get(i));
			
		}
		//System.out.println(tamLinha);
		//System.out.println(tamCol);
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
	
	public String salva(String nomeArq) throws IOException {
		String result = nomeArq;
		File resultado = new File(result);	
		
		FileWriter fw = new FileWriter(resultado);
		PrintWriter gravarArq = new PrintWriter(fw);
		
		for(int i = 0; i < this.quantLinhas(); i++) {
			for(int j = 0; j < this.quantColunas();j++) {
				gravarArq.print(this.mat[i][j] + " ");
			}
			gravarArq.println();
			
		}
		fw.close();
		return result;
		
			
		
	
	}
	
	//Diagonal Principal
	public  void diagP(TADMatriz A) {
		LinkedList<Float> lst = new LinkedList<Float>();
		if (A.quantLinhas() == A.quantColunas()) {
			for(int i = 0; i < A.quantLinhas(); i++) {
				
				lst.add(A.getElem(i,i));
				System.out.println(lst.size());
			}
				
			
			
		}
		for(int i = 0; i< lst.size(); i++)
			System.out.println(lst.get(i));
	}	
	
	
	//Diagonal Secundária		
	public void diagS(TADMatriz A) {
		LinkedList<Float> lst = new LinkedList<Float>();
		if (A.quantLinhas() == A.quantColunas()) {
			int i = A.quantColunas();
			for(int j = 0; j< A.quantLinhas();j++) {
				lst.add(A.getElem(i,j));
				i -= 1;
			}
		
	}
		for(int i = 0; i< lst.size(); i++)
		System.out.println(lst.get(i));
	}
}
