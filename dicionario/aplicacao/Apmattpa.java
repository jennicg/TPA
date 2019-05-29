package aplicacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


public class Apmattpa {
	
	public static void main(String[] args) throws FileNotFoundException, IOException{
	       String arq = "C:\\Users\\jenny\\eclipse-workspace\\Tpa\\dicionario\\aplicacao\\bdmatrizes\\bdaritmat.csv"; 
	       String matTxt;
	       TADMatriz matrizAux =null;
	       @SuppressWarnings("resource")
	       		BufferedReader bufferReader = new BufferedReader(new FileReader(arq));
	       String linha = bufferReader.readLine(); 
	        matTxt = linha+".txt";
	        TADMatriz resultado = TADMatriz.carrega("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\dicionario\\aplicacao\\bdmatrizes\\"+matTxt);
	        linha = bufferReader.readLine();
	        while( linha != null)   {
	            String dado[] = linha.split(",");
	            switch (dado[0]){
	            case "*":
                    if(dado[1].matches("[0-9]+")){  
                        Float vezes = Float.parseFloat(dado[1]);
                        resultado.vezesK(vezes);
                    }
                    else{
                        matTxt = dado[1]+".txt";
                        matrizAux = TADMatriz.carrega("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\dicionario\\aplicacao\\bdmatrizes\\"+matTxt);
                        resultado = resultado.multi(matrizAux);
                    }
                    break;
	                case "-":
	                    matTxt = dado[1]+".txt";
                        matrizAux = TADMatriz.carrega("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\dicionario\\aplicacao\\bdmatrizes\\"+matTxt);
	                    matrizAux.vezesK(-1f);
	                    resultado = resultado.soma(matrizAux);
	                    break;
	                case "+":
	                    matTxt = dado[1]+".txt";
                        matrizAux = TADMatriz.carrega("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\dicionario\\aplicacao\\bdmatrizes\\"+matTxt);
	                    resultado = resultado.soma(matrizAux);
	                    break;
	                case "t":
	                	resultado = resultado.transposta();
	                    break;
	            }
	            
	            linha = bufferReader.readLine();
	        }
	     
	       
	        String resposta = resultado.salva("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\dicionario\\aplicacao\\bdmatrizes\\resposta.txt");
			System.out.println("Arquivo está localizado no diretório:  " + resposta);
			System.out.println();
			System.out.println("Diagonal Principal: ");
			System.out.println(resultado.diagP(resultado));
			System.out.println();
			System.out.println("Diagonal Secundária: ");
			System.out.println(resultado.diagS(resultado));
	        
	    }
	    
	


}
	