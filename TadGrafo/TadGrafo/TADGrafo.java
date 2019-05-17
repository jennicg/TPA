package TadGrafo;
import java.util.LinkedList;
import TadDic.TADDicChain;
/*
 * Implementação: grafo simples dirigido usando matriz de adjacências
 */

import TadDic.TADDicChain;

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
	TADDicChain dicLblVertex = new TADDicChain();
	TADDicChain dicLblEdge = new TADDicChain();

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
	
	public int[] vertices() {
		int[] lst = new int[this.mat[0].length];
		for (int i = primeiroVertex; i <= ultimoVertex; i++) {
			if(!lstDeletados.contains(i)) {
				lst[i] = mat[i][0];
			}
		}
		return lst;
	}

	public int[] edges() {
		int [] lst = new int[quantEdges];
		int pos = 0;
		for (int i = primeiroVertex;  i <= ultimoVertex;  i++) {
			if(!lstDeletados.contains(i)) {
				for (int j = primeiroVertex; j<= ultimoVertex;j++) {
					if(!lstDeletados.contains(j))
					lst[pos] = mat[i][j];
					pos++;
				}
			}
		}
		return lst;
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
	
	public int[] outgoingEdges(int v) {
		LinkedList<Integer> lst = new LinkedList<Integer>();
		if(!lstDeletados.contains(v)) {
			for (int i = primeiroVertex; i <= this.ultimoVertex; i++) {
				if (mat[v][i] > 0) {
					lst.add(i);
				}
			}
		}
		
		if (lst.size()> 0) {
			int[] array = new int[lst.size()];
			for(int i = 0; i < lst.size(); i++) array[i] = lst.get(i);
			return array;
		}
		else {
			return null;
		}
	}
		
	public int[] incomingEdges(int v) {
		LinkedList<Integer> lst = new LinkedList<Integer>();
		for (int i = primeiroVertex; i<ultimoVertex; i++) {
			if (mat[i][v] > 0) {
				lst.add(i);
			}
		}
		if (lst.size()> 0) {
			int[] array = new int[lst.size()];
			for(int i = 0; i < lst.size(); i++) array[i] = lst.get(i);
			return array;
		}
		else {
			return null;
		}
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
	
	/*
	public Vertex insertVertex(String label, Object o) {
		int id;
		if(lstDeletados.size() > 0)
			id = lstDeletados.removeFirst();
		else
			id = geraIDvertex++;
		if(id > ultimoVertex)
			ultimoVertex = id;
		if (id < primeiroVertex)
			primeiroVertex = id;
		
		Vertex v = (Vertex)dicLblVertex.findElement(label);
		//Inclusão de um vértice novo
		if(dicLblVertex.NO_SUCH_KEY()) {
			v = new Vertex(label,o);
			v.setId(id);
			dicLblVertex.insertItem(label, v);
			quantVertex++;
		}
		else
			v.setDado(o);
		
		return v;
			
	}
	*/
	public int insertVertex() {
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
				for (int i = primeiroVertex +1; i <= ultimoVertex; i++)
					if(!lstDeletados.contains(i)) {
						primeiroVertex = i;
						break;
					}
			if(v == ultimoVertex)
				for (int i = ultimoVertex -1; i >= primeiroVertex; i--)
					if(!lstDeletados.contains(i)) {
						ultimoVertex = i;
						break;
					}
			for(int i = primeiroVertex; i<= ultimoVertex; i++ ) {
				if(mat[v][i] != 0) {
					quantEdges--;
					mat[v][i] = 0;
	
				}
				if((mat[i][v] != 0) && (mat[v][i] != mat[i][v])){
					quantEdges--;
					mat[i][v] = 0;
	
				}
				
			}
			quantVertex--;
			return v;
		}
		else
			return null;
		
	}
	
	public boolean areAdjacent(int u, int v) {
		if(vertexOk(u) && vertexOk(v))
			return mat[u][v] != 0;
		else
			return false;
	}

}
