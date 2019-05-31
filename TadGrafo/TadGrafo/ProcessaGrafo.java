package TadGrafo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

public class ProcessaGrafo {
	TADGrafo grafo;
	public  ProcessaGrafo(TADGrafo g) {
	 //Armazenar o grafo g
	 grafo = g;
	 
	}
	
	public LinkedList<Vertex> dfs(String labelV){
		LinkedList<Vertex> retorno = new LinkedList<Vertex>();
		LinkedList<Vertex> pilha = new LinkedList<Vertex>();
		pilha.add(grafo.getVertex(labelV));

                //LinkedList<Vertex> pilha2 = new LinkedList<Vertex>();
                //pilha2.addLast(pilha.pollLast());

		while(pilha.size() != 0) { //repetir o processo
			Vertex aux = pilha.pollLast();// tirar o ultimo da pilha


			LinkedList<Vertex> VertexSaida = grafo.outAdjacentVertices(aux.getLabel());
                        
            LinkedList<Vertex> VertexSaida2 = new LinkedList<Vertex>();
                        
                        for(int i = VertexSaida.size(); i > 0; i--){
                            VertexSaida2.add(VertexSaida.get(i-1));
                        }
                        
			//Colocar na pilha os adjacentes
			if(VertexSaida2.size() != 0) {
				if(!retorno.contains(aux)) {
					retorno.add(aux);
					}
				for(Vertex v : VertexSaida2) {

					if(!retorno.contains(v)) {// para não haver repetições
						pilha.add(v);
                                               
                                                
					}
				}
                                
			}
			else{
				retorno.add(aux);
			}
		}
		return retorno;
	}
	

	public LinkedList<Vertex> bfs(String labelV){
		//A lógica é bem parecida, porém é utilizado fila
		LinkedList<Vertex> retorno = new LinkedList<Vertex>();
		LinkedList<Vertex> fila = new LinkedList<Vertex>();

		fila.add(grafo.getVertex(labelV));
		
		while(fila.size() != 0) {
			Vertex aux = fila.pop(); //colocar na fila os adjacentes e tirar o primeiro
			// ficar repetindo o processo com o próximo da fila
			LinkedList<Vertex> VertexSaida = this.grafo.outAdjacentVertices(aux.getLabel());
			if(VertexSaida.size() != 0) {
				if(!retorno.contains(aux)) {
					retorno.add(aux);
					}
				for(Vertex v : VertexSaida) {
					if(!retorno.contains(v)) {
						fila.add(v);
					}
				}
			}
			else{
				retorno.add(aux);
			}
		}
		
		return retorno;
	}
	
	
}
