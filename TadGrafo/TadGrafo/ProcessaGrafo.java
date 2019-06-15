package TadGrafo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

public class ProcessaGrafo {
	TADGrafo grafo;
	private Vertex[] verticesGrafo;
	private Edge[] edgesGrafo;
	
	

	
	
	
	public  ProcessaGrafo(TADGrafo g) {
	 //Armazenar o grafo g
	 grafo = g;
	 
	}
	
	public static void floydWarshall(TADGrafo grafo, int [][] weights) {
		int numVertices = grafo.numVertices();
        double[][] dist = new double[numVertices][numVertices];
        for (double[] row : dist)
            Arrays.fill(row, Double.POSITIVE_INFINITY);
 
        for (int[] w : weights)
            dist[w[0] - 1][w[1] - 1] = w[2];
 
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
                        u = next[u - 1][v - 1];
                        caminho += " -> " + u;
                    } while (u != v);
                    System.out.println(caminho);
                }
            }
        }
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
				LinkedList<Vertex> VertexSaida = grafo.inAdjacentVertices(aux.getLabel());        
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
			LinkedList<Vertex> VertexSaida = this.grafo.inAdjacentVertices(aux.getLabel());
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

	
	
	
	
	
	public void printmat(int[][] passedMatrix){
        for(int i = 0; i < passedMatrix.length; i++) {
                for(int k = 0; k < passedMatrix.length; k++) {
                        System.out.println(String.format("%d",passedMatrix[i][k]));
             
                }
                
            System.out.println();    
      }

    }
	
	
}
