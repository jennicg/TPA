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
		
		
		J = J.soma(J,A);
		J.printMatriz();
		
		
		System.out.println();
		
		
		H = H.sub(J,H);
		H.printMatriz();
		System.out.println();
		
		
		M = M.multi(H,M);
		M.printMatriz();
		System.out.println();
		
		M.vezesK(M, 25);
		M.printMatriz();
		System.out.println();
		
		TADMatriz t = M.transposta();
		System.out.println("TRANSPOSTA");
		t.printMatriz();
		System.out.println();
		
		I = I.multi(t,I);
		I.printMatriz();
		System.out.println();
		
		Q = Q.multi(I,Q);
		Q.printMatriz();
		System.out.println();
		
		Q.diagP(Q);
		//Q.diagS(Q);
		
		String resultado = Q.salva("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadDicionario\\TadDic\\bdmatrizes\\resultado.txt");
		
		
		
		
		
		
		
		//String o = A.salva("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadDicionario\\TadDic\\bdmatrizes\\teste.txt");
		//A.salva("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadDicionario\\TadDic\\bdmatrizes\\teste.txt",A);
		
		//ArquivoTxt arq = ArquivoTxt.open("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadDicionario\\TadDic\\bdmatrizes\\A.txt","rt");
		
		
		/*
		A.setElem(0,0,(float) 3);
		A.setElem(0,1,(float) 2);
		A.setElem(0,2,(float) 3);
		A.setElem(1,0,(float) 3);
		A.setElem(1,1,(float) 2);
		A.setElem(1,2,(float) 1);
		A.printMatriz();
		*/
		//A.salva("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadDicionario\\TadDic\\oi.txt",A);
		/*
		System.out.println();
		System.out.println(A.getElem(1,1));
		System.out.println();
		System.out.println(A.quantLinhas());
		System.out.println();
		System.out.println(A.quantColunas());
		System.out.println();
		
		TADMatriz S= new TADMatriz(3,3);
		
		S = S.soma(A,A);
		S.printMatriz();
		System.out.println();
		
		
		S.vezesK(S,2);
		S.printMatriz();
		
		TADMatriz B = new TADMatriz(3,2);
		B.setElem(0,0,(float) 3);
		B.setElem(0,1,(float) 2);
		B.setElem(1,0,(float) 3);
		B.setElem(1,1,(float) 3);
		B.setElem(2,0,(float) 2);
		B.setElem(2,1,(float) 1);
		System.out.println();
		B.printMatriz();
		
		System.out.println();
		S.multi(A,B);
		S.printMatriz();
		*/
				
}
}
