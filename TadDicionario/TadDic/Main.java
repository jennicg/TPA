package TadDic;

import java.util.*;
import java.io.*;

public class Main {
	  static List<String> lstPalavras;

	    public static void main(String[] args) throws FileNotFoundException {

	    	lstPalavras = new ArrayList<>();
	    	lerDicionario();

	        System.out.println("Tamanho da lista " + lstPalavras.size());
	        for (String i : lstPalavras) {
	            System.out.println("linha.: " + i);
	        }
	        
	    }

	    public static void lerDicionario() throws FileNotFoundException {

	        FileReader arq = new FileReader("C:\\Users\\jenny\\OneDrive\\Documentos\\Ed\\eng-pt.txt");

	        Scanner bufferedScanner = new Scanner(new BufferedReader(arq));
	        try {
	            while (bufferedScanner.hasNextLine()) {
	                String linhaAtual = bufferedScanner.nextLine();
	                Scanner linhaScanner = new Scanner(linhaAtual);
	                linhaScanner.useDelimiter(",");
	                lstPalavras.add((linhaScanner.next()));
	                linhaScanner.close();
	            }
	        } catch (Exception anException) {
	            System.out.println("Erro: " + anException);
	        } finally {
	            try {
	                bufferedScanner.close();
	            } catch (Exception anException) {
	                System.out.println("Error: " + anException);
	            }
	        }
	    }
	
	  

	
}
