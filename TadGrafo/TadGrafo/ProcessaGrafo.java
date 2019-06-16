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
	TADGrafo grafo;

	


	
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
	
	private List<Vertex> grafo1 = new ArrayList<Vertex>();
	List<Vertex> menorCaminho = new ArrayList<Vertex>();
	
	public void setVertices(List<Vertex> vertices) {

		this.grafo1.addAll(vertices);
	}

	public void adicionarVertice(Vertex novoVertice) {

		this.grafo1.add(novoVertice);
	}

	public List<Vertex> getVertices() {

		return this.grafo1;
	}

	// Variavel que recebe os vertices pertencentes ao menor caminho
	Vertex verticeCaminho;

	// Variavel que guarda o vertice que esta sendo visitado
	Vertex atual;

	// Variavel que marca o vizinho do vertice atualmente visitado
	Vertex vizinho;

	// Lista dos vertices que ainda nao foram visitados
	List<Vertex> naoVisitados = new ArrayList<Vertex>();

	// Algoritmo de Dijkstra
	public List<Vertex> encontrarMenorCaminhoDijkstra(TADGrafo grafo, Vertex v1,
			Vertex v2) {

		// Adiciona a origem na lista do menor caminho
		menorCaminho.add(v1);

		// Colocando a distancias iniciais
		for (int i = 0; i < grafo.numVertices(); i++) {

			// Vertice atual tem distancia zero, e todos os outros,
			// 9999("infinita")
			if (((Vertex) grafo.dicLblVertex.elements().get(i)).getLabel()
					.equals(v1.getLabel())) {

				((Vertex)grafo.dicLblVertex.elements().get(i)).setDistancia(0);

			} else {

				((Vertex)grafo.dicLblVertex.elements().get(i)).setDistancia(9999);

			}
			// Insere o vertice na lista de vertices nao visitados
			this.naoVisitados.add(((Vertex)grafo.dicLblVertex.elements().get(i)));
		}

		Collections.sort(naoVisitados);

		// O algoritmo continua ate que todos os vertices sejam visitados
		while (!this.naoVisitados.isEmpty()) {

			// Toma-se sempre o vertice com menor distancia, que eh o primeiro
			// da
			// lista

			atual = this.naoVisitados.get(0);
			System.out.println("Pegou esse vertice:  " + atual);
			/*
			 * Para cada vizinho (cada aresta), calcula-se a sua possivel
			 * distancia, somando a distancia do vertice atual com a da aresta
			 * correspondente. Se essa distancia for menor que a distancia do
			 * vizinho, esta eh atualizada.
			 */
			LinkedList<Vertex> VertexSaida = this.grafo.inAdjacentVertices(atual.getLabel());
			for (int i = 0; i < VertexSaida.size(); i++) {
				System.out.println("Oi" + this.grafo.inAdjacentVertices(atual.getLabel()));

				vizinho = VertexSaida.get(i);
				System.out.println("Olhando o vizinho de " + atual + ": "
						+ vizinho);
				if (!vizinho.verificarVisita()) {

					// Comparando a distância do vizinho com a possível
					// distância
					if (vizinho.getDistancia() > (atual.getDistancia() + VertexSaida.get(i).getDistancia())) {

						vizinho.setDistancia(atual.getDistancia()
								+ VertexSaida.get(i).getDistancia());
						vizinho.setPai(atual);

						/*
						 * Se o vizinho eh o vertice procurado, e foi feita uma
						 * mudanca na distancia, a lista com o menor caminho
						 * anterior eh apagada, pois existe um caminho menor
						 * vertices pais, ateh o vertice origem.
						 */
						if (vizinho == v2) {
							//menorCaminho.clear();
							verticeCaminho = vizinho;
							menorCaminho.add(vizinho);
							while (verticeCaminho.getPai() != null) {

								menorCaminho.add(verticeCaminho.getPai());
								verticeCaminho = verticeCaminho.getPai();

							}
							// Ordena a lista do menor caminho, para que ele
							// seja exibido da origem ao destino.
							Collections.sort(menorCaminho);

						}
					}
				}

			}
			// Marca o vertice atual como visitado e o retira da lista de nao
			// visitados
			atual.visitar();
			this.naoVisitados.remove(atual);
			/*
			 * Ordena a lista, para que o vertice com menor distancia fique na
			 * primeira posicao
			 */

			Collections.sort(naoVisitados);
			System.out.println("Nao foram visitados ainda:"+naoVisitados);

		}

		return menorCaminho;
	}

	  

	
	
}
