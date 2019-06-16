package TadGrafo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import _my_tools.ArquivoTxt;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import app_grafo.ToTGF;

public class Main {


	public static void main(String[] args) throws FileNotFoundException, IOException {
		/*
		TADGrafo g = new TADGrafo("oi");
		g.insertVertex("1", "1");
		g.insertVertex("2", "2");
		g.insertVertex("3", "3");
		g.insertVertex("4", "4");
		System.out.println();
		
		g.insertEdge("1", "2", "1-2", "1->2",3);
		g.insertEdge("2", "3", "2-3", "2->3",2);
		g.insertEdge("3", "4", "3-4", "3->4",1);
		g.insertEdge("4", "1", "4-1", "4->1",2);
		g.insertEdge("1", "4", "1-4", "1->4",7);
		g.insertEdge("2", "1", "2-1", "2->1",8);
		g.insertEdge("3", "1", "3-1", "3->1",4);
		
		TADGrafo g1 = new TADGrafo("oi");
		g1.insertVertex("A", "1");
		g1.insertVertex("B", "2");
		g1.insertVertex("C", "3");
		g1.insertVertex("D", "4");
		g1.insertVertex("E", "5");
		g1.insertVertex("F", "6");
		g1.insertVertex("G", "7");


	
		g1.insertEdge("A", "B", "A-B", 3,4);
		g1.insertEdge("A", "C", "A-C", 666,3);
		g1.insertEdge("A", "E", "A-E", 123,7);
		
		g1.insertEdge("B", "D", "B-D", 321,5);
		
		g1.insertEdge("C", "D", "C-D", 321,11);
		g1.insertEdge("C", "E", "C-E", 321,8);
		g1.insertEdge("C", "B", "C-B", 321,6);
		
		g1.insertEdge("D", "F", "D-F", 321,2);
		
		
		g1.insertEdge("E", "D", "E-D", 321,2);
		
		g1.insertEdge("F", "G", "F-G", 321,3);
		
		g1.insertEdge("G", "G", "G-D", 321,10);
		
		
	
		
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
        
        
        */

		TADGrafo g2 = new TADGrafo("oiiii");
		g2.insertVertex("A", "1");
		g2.insertVertex("B", "2");
		g2.insertVertex("C", "3");
		g2.insertVertex("D", "4");
		g2.insertVertex("E", "5");
		g2.insertVertex("F", "6");


		g2.insertEdge("A", "B", "A-B", 3,7);
		g2.insertEdge("A", "C", "A-C", 666,9);
		g2.insertEdge("A", "F", "A-F", 123,14);
		
		g2.insertEdge("B", "C", "B-C", 321,10);
		g2.insertEdge("B", "D", "B-D", 321,15);
		
		g2.insertEdge("C", "D", "C-D", 321,11);
		g2.insertEdge("C", "F", "C-F", 321,2);
		
		
		g2.insertEdge("D", "E", "D-E", 321,5);
		
		
		g2.insertEdge("E", "F", "E-F", 321,9);
		
		Vertex A = g2.getVertex("A");
		//System.out.println("AQUI: " + A);
		Vertex F = g2.getVertex("F");
		//System.out.println("AQUI: " + F.getDado());
		
        ProcessaGrafo dj = new ProcessaGrafo(g2);
        List<Vertex> resultado = new ArrayList<Vertex>();
        resultado = dj.encontrarMenorCaminhoDijkstra(g2,A,F);
        System.out.println("Esse é o menor caminho feito pelo algoritmo:"
				+ resultado);
     
        
	}
}
		
		

	




