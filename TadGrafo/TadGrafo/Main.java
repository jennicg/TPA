package TadGrafo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import _my_tools.ArquivoTxt;

import app_grafo.ToTGF;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		TADGrafo g = new TADGrafo("oi");
		g.insertVertex("1", "1");
		g.insertVertex("2", "2");
		g.insertVertex("3", "3");
		g.insertVertex("4", "4");
		System.out.println();
		
		g.insertEdge("1", "2", "1-2", "1->2");
		g.insertEdge("2", "3", "2-3", "2->3");
		g.insertEdge("3", "4", "3-4", "3->4");
		g.insertEdge("4", "1", "4-1", "4->1");
		g.insertEdge("1", "4", "1-4", "1->4");
		g.insertEdge("2", "1", "2-1", "2->1");
		g.insertEdge("3", "1", "3-1", "3->1");
		
		
		
		TADGrafo g1 = new TADGrafo("oi");
		g1.insertVertex("A", "1");
		g1.insertVertex("B", "2");
		g1.insertVertex("C", "3");
		g1.insertVertex("D", "4");
		g1.insertVertex("E", "5");
		g1.insertVertex("F", "6");
		g1.insertVertex("G", "7");
		g1.insertVertex("H", "8");

	
		g1.insertEdge("A", "B", "O CAMINHO", 3);
		//g.insertEdge("B", "A", "SEXTOU", 3); DANDO ERRO PQ A LIB É DE GRAPH NÃO DIRIGIDO!
		g1.insertEdge("A", "C", "AINDA NAO TA TUDO CERTO", 666);
		g1.insertEdge("A", "D", "É LONGO", 123);
		g1.insertEdge("C", "E", "ODIOOOO", 321);
		g1.insertEdge("A", "F", "MAS A DERROTA", 321);
		g1.insertEdge("F", "G", "É CERTA", 321);
		g1.insertEdge("E", "H", "DEU POR HOJE", 321);
		
		System.out.println("Grafo: ");
		g.printgrafo();
		
		
		ProcessaGrafo md = new ProcessaGrafo(g1);
		LinkedList<Vertex> resultado_dfs = new LinkedList<Vertex>();
		LinkedList<Vertex> resultado_bfs = new LinkedList<Vertex>();
		resultado_dfs = md.dfs("A");
		resultado_bfs = md.bfs("A");
		System.out.println("Grafo em busca de prfundidade: ");
		for(int i= 0; i< resultado_dfs.size(); i++) {
			System.out.print(resultado_dfs.get(i).getLabel() + "||");
		}
		System.out.println();
		System.out.println("Grafo em busca de nível: ");
		for(int i= 0; i< resultado_bfs.size(); i++) {
			System.out.print(resultado_bfs.get(i).getLabel() + "||");
		}
		System.out.println();
		
		
		ToGStream show = new ToGStream(g);
		String css = "graph { fill-color: red; }";
		String teste = null;
		show.exibe(teste);
		
   
        ToTGF.converteTxt_Tgf("movies2");
        TADGrafo grafo = ToTGF.carrega("movies2");
        grafo.printgrafo();
        
        ToGStream show1 = new ToGStream(grafo);
        String teste1 = null;
        show.exibe(teste1);
        
        
        //-------------------------------------
		
		
		int [][] pesos = { {1,2,3}, {1,4,7}, {2,1,8}, {2,3,2} , {3,1,5}, {3,4,1},
				{4,1,2}};
        ProcessaGrafo.floydWarshall(g,pesos);
		
       
	
		
	}
}
		
		

	




