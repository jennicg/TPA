package TadDic;

import java.util.*;
import java.io.*;

public class Main {
	  static List<String> lstPalavras;

	    public static void main(String[] args) throws FileNotFoundException {

	    	lstPalavras = new ArrayList<>();
	        lerOsAtletas();

	        System.out.println("Tamanho da lista " + lstPalavras.size());

	        
	    }

	    public static void lerOsAtletas() throws FileNotFoundException {

	        FileReader arq = new FileReader("C:\\Users\\jenny\\OneDrive\\Documentos\\Ed\\eng-pt.txt");

	        Scanner bufferedScanner = new Scanner(new BufferedReader(arq));
	        try {
	            while (bufferedScanner.hasNextLine()) {
	                String linhaCurrente = bufferedScanner.nextLine();
	                Scanner linhaScanner = new Scanner(linhaCurrente);
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
