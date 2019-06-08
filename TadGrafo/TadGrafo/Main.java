package TadGrafo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

import app_grafo.ToTGF;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		TADGrafo g = new TADGrafo("oi");
		g.insertVertex("A", "1");
		g.insertVertex("B", "2");
		g.insertVertex("C", "3");
		g.insertVertex("D", "4");
		g.insertVertex("E", "5");
		g.insertVertex("F", "6");
		g.insertVertex("G", "7");
		g.insertVertex("H", "8");

	
		g.insertEdge("A", "B", "O CAMINHO", 3);
		//g.insertEdge("B", "A", "SEXTOU", 3); DANDO ERRO PQ A LIB � DE GRAPH N�O DIRIGIDO!
		g.insertEdge("A", "C", "AINDA NAO TA TUDO CERTO", 666);
		g.insertEdge("A", "D", "� LONGO", 123);
		g.insertEdge("C", "E", "ODIOOOO", 321);
		g.insertEdge("A", "F", "MAS A DERROTA", 321);
		g.insertEdge("F", "G", "� CERTA", 321);
		g.insertEdge("E", "H", "DEU POR HOJE", 321);
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
		System.out.println("Grafo em busca de n�vel: ");
		for(int i= 0; i< resultado_bfs.size(); i++) {
			System.out.print(resultado_bfs.get(i).getLabel() + "||");
		}
		/*
		ToGStream show = new ToGStream(g);
		String css = "graph { fill-color: red; }";
		String teste = null;
		show.exibe(teste);
		*/
		
        
        LinkedList vertices = new LinkedList();
        LinkedList arestas = new LinkedList();
        ToTGF conv = new ToTGF("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadGrafo\\movies.txt");
        conv.converte("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\TadGrafo\\movies.txt");
        
        vertices=conv.filmesEAtores();
        for(int i=0; i<vertices.size();i++){
            System.out.println("Pos: "+i+": "+vertices.get(i));
            
        }
        
        arestas= conv.relacionamentos();
        for(int i=0; i< arestas.size();i++){
            System.out.println("Rela��o "+i+": "+arestas.get(i));
            
        }
        conv.write();
        
        TADGrafo grafo = conv.carrega("C:\\Users\\jenny\\eclipse-workspace\\Tpa\\saidafinal.tgf");
        ToGStream show = new ToGStream(grafo);
		String css = "graph { fill-color: red; }";
		String teste = null;
		show.exibe(teste);

	
	}
}
		
		

	




