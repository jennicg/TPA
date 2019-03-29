package TadDic;

import java.io.OutputStream;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	  

	static List<String> listaPalavras;
	
	public static void lerOsAtletas() throws FileNotFoundException {

        FileReader arq = new FileReader("C://Users//jenny//Documents//test.csv");

        Scanner bufferedScanner = new Scanner(new BufferedReader(arq));
        try {
            while (bufferedScanner.hasNextLine()) {
                String linhaCurrente = bufferedScanner.nextLine();
                Scanner linhaScanner = new Scanner(linhaCurrente);
                linhaScanner.useDelimiter(",");
                listaPalavras.add((linhaScanner.next()));
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
	
	public static void main (String[] args) {

		TadDicChain dic = new TadDicChain (256);
		System.out.println("O dicionario esta vazio ?" + dic.isEmpty());
		System.out.println ("Avengers");
		dic.insertItem("laranja", new RegDados("laranja","orange"));
		dic.insertItem("maçã", new RegDados("Maçã","apple"));
		dic.insertItem("uva", new RegDados("uva","grape"));
		dic.insertItem("morango", new RegDados("morango","strawberry"));
		dic.insertItem("moranog", new RegDados("morango","strawberry"));
		System.out.println("AS CHAVES SÃOO: " + dic.keys());
	
		System.out.println("Existe colisão ?" + dic.getColisoes());
		
		dic.insertItem("banana", new RegDados("banana","banana"));
		System.out.println(dic.size());
		
		System.out.println("O dicionario esta vazio ?" + dic.isEmpty());

		dic.imprimeLista();
		dic.removeElement("morango");
		RegDados dado = (RegDados)dic.findElement("morango");		
		if (dado!= null)
			System.out.println(dado.getWpt() + ", " + dado.getWeng());
		else
			System.out.println("A chave solicitada não existe neste dicionário");
		
		dic.imprimeLista();
		
		System.out.println("Chaves: " + dic.keys());
		System.out.println("Entradas" + dic.elements());

		dic.imprimeLista();

	}
}
