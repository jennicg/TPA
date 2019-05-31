package TadGrafo;

import java.util.LinkedList;

public class Main {

	public static void main(String[] args) {
		TADGrafo g = new TADGrafo("oi");
		g.insertVertex("A", "1");
		g.insertVertex("B", "2");
		g.insertVertex("C", "3");
		g.insertVertex("D", "4");
		g.insertVertex("E", "5");
		g.insertVertex("F", "6");
		g.insertVertex("G", "7");
		g.insertVertex("H", "8");

	
		g.insertEdge("A", "B", "o caminho", 3);
		//g.insertEdge("B", "A", "SEXTOU", 3);
		g.insertEdge("A", "C", "é longo", 666);
		g.insertEdge("A", "D", "mas a derrota", 123);
		g.insertEdge("C", "E", "é certa", 321);
		g.insertEdge("A", "F", "morte", 321);
		g.insertEdge("F", "G", "help", 321);
		g.insertEdge("E", "H", "SD", 321);
		System.out.println("Grafo: ");
		g.printgrafo();
		
		ProcessaGrafo md = new ProcessaGrafo(g);
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
		ToGStream show = new ToGStream(g);
		String css = "graph { fill-color: red; }";
		String teste = null;
		show.exibe(teste);
	
	}
}
		
		

	




