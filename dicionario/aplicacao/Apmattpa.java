package aplicacao;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;


public class Apmattpa {

public static void main(String[] args) throws IOException {
	TADMatriz resultado = null;
	TADMatriz aux = null;
	LinkedList<String> lst = new LinkedList<String>();
	lst.add("*");
	lst.add("+");
	lst.add("t");
	lst.add("-");
	lst.add("/");
	String linha;
	//Importante substituir os caminhos ao fazer o teste
	File arquivo = new File("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\dicionario\\aplicacao\\bdmatrizes\\bdaritmat.csv");;
	@SuppressWarnings("resource")
	Scanner scanner = new Scanner(arquivo);
	while(scanner.hasNext()) {
		linha = scanner.nextLine();
		String [] dado = linha.split(",");
		for(int i=0; i < dado.length; i++) {
			if(!lst.contains(dado[i])) {
					aux = TADMatriz.carrega("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\dicionario\\aplicacao\\bdmatrizes\\"+dado[i]+".txt");
				}
			else {
				if(dado[i].equals("+")){
					i++;
					String texto = "C:\\Users\\jenny\\eclipse-workspace\\Tpa\\dicionario\\aplicacao\\bdmatrizes\\"+dado[i]+".txt";
					aux = TADMatriz.carrega(texto);
					aux = resultado.soma(aux);
	
					}
				else if(dado[i].equals("-")){
					i++;
					String texto = "C:\\Users\\jenny\\eclipse-workspace\\Tpa\\dicionario\\aplicacao\\bdmatrizes\\"+dado[i]+".txt";
					aux = TADMatriz.carrega(texto);
					aux = resultado.sub(aux);
					}
				else if(dado[i].equals("t")){
					aux = resultado.transposta();
				}
				else if(dado[i].equals("*")) {
					i++;
					if (Character.isDigit(dado[i].charAt(0))) {
						resultado.vezesK(Integer.parseInt(dado[i]));
						aux = resultado;
					}
					else {
						String texto = "C:\\Users\\jenny\\eclipse-workspace\\Tpa\\dicionario\\aplicacao\\bdmatrizes\\"+dado[i]+".txt";
						aux = TADMatriz.carrega(texto);
						aux = resultado.multi(aux);
						}
					}
			}
		}
		resultado = aux;	
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