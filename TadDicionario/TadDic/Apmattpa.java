package TadDic;

import java.io.FileNotFoundException;
import java.io.IOException;
import _my_tools.ArquivoTxt;

public class Apmattpa {
	public static void main(String[] args) throws IOException {
		TADMatriz J = TADMatriz.carrega("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadDicionario\\TadDic\\bdmatrizes\\J.txt");
		TADMatriz A = TADMatriz.carrega("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadDicionario\\TadDic\\bdmatrizes\\A.txt");
		TADMatriz H = TADMatriz.carrega("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadDicionario\\TadDic\\bdmatrizes\\H.txt");
		TADMatriz M = TADMatriz.carrega("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadDicionario\\TadDic\\bdmatrizes\\M.txt");
		TADMatriz I = TADMatriz.carrega("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadDicionario\\TadDic\\bdmatrizes\\I.txt");
		TADMatriz Q = TADMatriz.carrega("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadDicionario\\TadDic\\bdmatrizes\\Q.txt");
		
		
		System.out.println(J.printMatriz());
		System.out.println();
		System.out.println(A.printMatriz());
		J = J.soma(A);
		System.out.println(J.printMatriz());
		
		System.out.println(J.printMatriz());
		
		System.out.println("J - H : ");
		TADMatriz J0 = H.sub(J);
		System.out.println(J0.printMatriz());
		System.out.println();

		TADMatriz J1 = M.multi(J);
		System.out.println("J * M ");
		System.out.println(J1.printMatriz());
		System.out.println();
		
		J1.vezesK(25);
		System.out.println("VEZES 25: ");
		System.out.println(J1.printMatriz());
		System.out.println();
		
		TADMatriz t = J1.transposta();
		System.out.println("TRANSPOSTA");
		System.out.println(t.printMatriz());
		System.out.println();
		
		I = I.multi(t);
		System.out.println(I.printMatriz());
		System.out.println();
		
		Q = Q.multi(I);
		System.out.println(Q.printMatriz());
		System.out.println();
		
		String resposta = Q.salva("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadDicionario\\TadDic\\bdmatrizes\\resposta.txt");
		System.out.println("Arquivo: " + resposta);
		
		System.out.println(Q.diagP(Q));
		System.out.println();
		System.out.println(Q.diagS(Q));
		
		
		
	
		
}
}
