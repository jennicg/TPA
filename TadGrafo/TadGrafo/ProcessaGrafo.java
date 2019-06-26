package TadGrafo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

public class ProcessaGrafo {
	TADGrafoD grafo;

	
	public  ProcessaGrafo(TADGrafoD grafo) {
	 //Armazenar o grafo g
	 this.grafo = grafo;
	 
	}
	
	
	
	
	public LinkedList<Vertex> dfs(String labelV){
		LinkedList<Vertex> retorno = new LinkedList<Vertex>();
		LinkedList<Vertex> pilha = new LinkedList<Vertex>();
		pilha.add(grafo.getVertex(labelV));
                
		if(pilha.equals(null)) {
			return null;
		}
		else {
			while(pilha.size() != 0) { //repetir o processo
				Vertex aux = pilha.pollLast();// tirar o ultimo da pilha
				LinkedList<Vertex> VertexSaida = grafo.outAdjacenteVertices(aux.getLabel());        
	            LinkedList<Vertex> VertexSaida2 = new LinkedList<Vertex>();
	                        for(int i = VertexSaida.size(); i > 0; i--){
	                            VertexSaida2.add(VertexSaida.get(i-1));
	                        }
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
			LinkedList<Vertex> VertexSaida = this.grafo.outAdjacenteVertices(aux.getLabel());
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
	
	
	public void floyd_warshall() {
		int numVertices = this.grafo.numVertices();
		double [][] dist = new double [numVertices][numVertices];
        for (double[] row : dist)
            Arrays.fill(row, Double.POSITIVE_INFINITY);
        
        
        int linha = 0;
        
        for( Vertex origem : this.grafo.vertices_aux() ) {
        	int coluna = 0;
        	for(Vertex destino : this.grafo.vertices_aux()) {
        		if(origem.getId() != destino.getId()) {
        			Edge e = this.grafo.getEdge(origem.getLabel(), destino.getLabel());
            		if( e != null) {
            		
            			dist[linha][coluna] = e.getPeso();
            			
            		}
            		else {
            			dist[linha][coluna] = Integer.MAX_VALUE;
            		}
            		
        			
        		}
        		else {
        			dist[linha][coluna] =  0;
				}
        	
        	coluna++;
        	}
        	linha++;
        }
        
        int[][] next = new int[numVertices][numVertices];
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++)
                if (i != j)
                    next[i][j] = j + 1;
        }
 
        for (int k = 0; k < numVertices; k++)
            for (int i = 0; i < numVertices; i++)
                for (int j = 0; j < numVertices; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
 
        printResult(dist, next);
        
        }
	
    static void printResult(double[][] dist, int[][] next) {
        System.out.println("par     dist    caminho");
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++) {
                if (i != j) {
                    int u = i + 1;
                    int v = j + 1;
                    String caminho = String.format("%d -> %d    %2d     %s", u, v,
                            (int) dist[i][j], u);
                    do {
                        u = next[u -1 ][v-1 ];
                        caminho += " -> " + u;
                    } while (u != v);
                    System.out.println(caminho);
                }
            }
        }
    }

	public int[] dijkstra(Vertex v1) {
		Vertex[] verticesGrafo = this.grafo.vertices_aux();
		int[]peso = new int[verticesGrafo.length];
		String[] caminho = new String[peso.length];
		LinkedList<Integer> vizinhos = new LinkedList<Integer>();
		for (int i = 0; i < peso.length; i++) {
			peso[i] = Integer.MAX_VALUE;
			vizinhos.add(i);
		}
		peso[v1.getId()] = 0;
		int posEdge = v1.getId();
		caminho[posEdge] = v1.getLabel();
	
		while(vizinhos.size() != 0) {					
			vizinhos.remove((Integer)posEdge);
			int[] dist = peso.clone();
			String[] atualLocal = caminho.clone();
			LinkedList<Vertex> aux = grafo.adjacentVertices(verticesGrafo[posEdge].getLabel());					
			for (Vertex v : aux) {					
				if(grafo.getEdge(verticesGrafo[posEdge].getLabel(), v.getLabel()) != null && vizinhos.contains((Integer)(v.getId()))) {				
					int novoPeso = grafo.getEdge(verticesGrafo[posEdge].getLabel(), v.getLabel()).getPeso()+ peso[posEdge];					
					int pesoAtual = peso[(v.getId())];				
					if(pesoAtual > novoPeso) {
						dist[v.getId()] = novoPeso;
						atualLocal[v.getId()] = caminho[posEdge] +'-' + v.getLabel();					
					}
				}
			}
			peso = dist;
			caminho = atualLocal;
			if(vizinhos.size() != 0) {
				posEdge = verificaMenorValor(peso,vizinhos);		
			}
		}
		for(String v : caminho) {
			System.out.println(v);
		}
		return peso;		
	}
	
	private int verificaMenorValor(int[] peso, LinkedList<Integer> vizinhos) {
		int menor = Integer.MAX_VALUE;
		int id = -1;
		for(int i = 0; i < peso.length; i++) {
			if( peso[i] < menor && vizinhos.contains((Integer)i)) {
				menor = peso[i];
				id = i;
			}
		}
		return id;
	}
	
	
	public int[] belman_ford(Vertex v1) {
		Vertex[] verticesGrafo = this.grafo.vertices_aux();
		int[] peso = new int[verticesGrafo.length];
		for(int i = 0; i < peso.length; i++) {
			peso[i] = Integer.MAX_VALUE;
		}
		int id = v1.getId();
		peso[id] = 0;
		for(int i = 0; i < (verticesGrafo.length - 1); i++) {
			int[] pesoAtual = peso.clone();
			for(int j = 0; j < pesoAtual.length; j++) {
				if(pesoAtual[j] != Integer.MAX_VALUE) {
					Vertex vertex = verticesGrafo[j];
					int posVertex = vertex.getId();
					LinkedList<Vertex> connections = grafo.adjacentVertices(vertex.getLabel());
					for(Vertex v : connections) {
						Edge e = this.grafo.getEdge(vertex.getLabel(), v.getLabel());
						if (e != null) {
							int pesoTotal = pesoAtual[posVertex]+e.getPeso();
							if(pesoAtual[(v.getId())] > pesoTotal) {
								pesoAtual[v.getId()] = pesoTotal;
							}
						}
					}
				}
			}
			if(Arrays.equals(pesoAtual, peso)) {
				peso = pesoAtual;
				break;
			}
			else {
				peso = pesoAtual;
			}
		}
		return peso;
	}
}
