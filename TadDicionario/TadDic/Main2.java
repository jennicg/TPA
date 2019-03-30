package TadDic;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.jfree.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;


public class Main2 {
	public static void main (String[] args) {
	
		TadDicChain dic = new TadDicChain (256);
		System.out.println("O dicionario esta vazio ?" + dic.isEmpty());
		System.out.println ("Avengers");
		dic.insertItem("jennifer", new EstudanteBsi("jennifer","20162",20));
		dic.insertItem("refinnej", new EstudanteBsi("gabrielle","1602",86));
		dic.insertItem("eduarda", new EstudanteBsi("eduarda","2210",19));
		dic.insertItem("Larissa", new EstudanteBsi("Larissa","2812",17));
		dic.insertItem("Yasmim", new EstudanteBsi("Yasmim","0803",22));

		System.out.println("COLISÕES " + dic.getColisoes());
		int [] colisoes = dic.getColisoes();
		
		dic.exibeDiagrama(colisoes);
		



		dic.insertItem("jennifer", new EstudanteBsi("jennifer","2304",21));
		System.out.println("AS CHAVES SÃO: " + dic.keys());
		System.out.println(dic.size());
		
		System.out.println("O dicionario esta vazio ?" + dic.isEmpty());

		dic.imprimeLista();
		dic.removeElement("eduarda");
		EstudanteBsi dado = (EstudanteBsi)dic.findElement("jennifer");		
		if (dado!= null)
			System.out.println(dado.getNome() + ", " + dado.getIdade());
		else
			System.out.println("A chave solicitada não existe neste dicionário");
		
		dic.imprimeLista();
		
		System.out.println("Chaves: " + dic.keys());
		System.out.println("Entradas" + dic.elements());

		dic.imprimeLista();

	}
}
