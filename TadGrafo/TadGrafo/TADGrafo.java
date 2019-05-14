package TadGrafo;

import java.util.LinkedList;
/*
 * Implementação: grafo simples dirigido usando matriz de adjacências
 */

public class TADGrafo {
	private int [][] mat = null;
	private String nome;
	private int quantVertex = 0;
	private int quantEdges = 0;
	private int geraIDedge = 1;
	private int geraIDvertex = 0;
	private int primeiroVertex = 0;
	private int ultimoVertex = 0;
	LinkedList<Integer> lstDeletados = new LinkedList<Integer>();
	
	
	public TADGrafo(String nome) {
		this.nome = nome;
		mat = new int [16][16];
	}
	
	public void printmat() {
		for (int i = primeiroVertex; i <= ultimoVertex;i++) {
			if(!lstDeletados.contains(i)) {
				for (int j = primeiroVertex; j<= ultimoVertex;j++) {
					if(!lstDeletados.contains(j))
							System.out.print(String.format("%4d", mat[i][j]));					
			}
				System.out.println();
				
			}
			
		}
	}
	
	public int numVertices() {
		return quantVertex;
	}
	
	public int numEdges() {
		return quantEdges;
	}
	
	private boolean vertexOk (int v) {
		if(( v>= primeiroVertex) && (v <= ultimoVertex) && !lstDeletados.contains(v))
			return true;
		return false;
			}
	
	public Integer getEdge (int u, int v) {
		if (vertexOk(v) && vertexOk(u))
			return mat[u][v];
		return null;	
	}
	
	public int[] endVertices(int e) {
		for (int i = primeiroVertex; i < ultimoVertex;i++) {
			for (int j = primeiroVertex; j< ultimoVertex;j++) {
				if(mat[i][j] == e) {
					int[]v = new int[2];
					v[0]= i;
					v[1]= j;
					return v;
				}
			}
			
		}
		return null;
	}
	
	public int opposite (int v, int e) {
		for(int i = primeiroVertex; i < ultimoVertex;i++)
			if(mat[v][i] == e)
				return i;
		return 0;
	}
	
	public int outDegree(int v) {
		int grau = 0;
		for(int i = primeiroVertex; i < ultimoVertex;i++)
			if(mat[v][i] != 0 )
				grau++;
		return grau;
	}
	
	public int inDegree(int v) {
		int grau = 0;
		for(int i = primeiroVertex; i < ultimoVertex;i++)
			if(mat[i][v] != 0 )
				grau++;
		return grau;
	}
	
	public int insertEdge(int u, int v) {
		if(vertexOk(u) && vertexOk(v)) {
			mat[u][v] = geraIDedge++;
			quantEdges++;
			return mat[u][v];
		}
		return -1;
	}
	
	public void removeEdge(int e) {
		for (int i = primeiroVertex; i < ultimoVertex;i++) {
			for (int j = primeiroVertex; j<ultimoVertex;j++) {
				if(mat[i][j] == e) {
					mat[i][j] = 0;
					quantEdges--;
				}
			}
	}

	}
	
	public Integer insertVertex() {
		int id;
		if(lstDeletados.size() > 0)
			id = lstDeletados.removeFirst();
		else
			id = geraIDvertex++;
		if(id > ultimoVertex)
			ultimoVertex = id;
		if (id < primeiroVertex)
			primeiroVertex = id;
		
		quantVertex++;
		return id;
			
	}
	
	public Integer removeVertex (int v) {
		if(vertexOk(v)) {
			lstDeletados.add(v);
			if(v == primeiroVertex)
				for (int i = primeiroVertex; i <= ultimoVertex; i++)
					if(!lstDeletados.contains(i)) {
						primeiroVertex = i;
						break;
					}
			if(v == ultimoVertex)
				for (int i = primeiroVertex; i <= ultimoVertex; i++)
					if(!lstDeletados.contains(i)) {
						ultimoVertex = i;
						break;
					}
			quantVertex--;
			return v;
		}
		else
			return null;
		
	}

}
