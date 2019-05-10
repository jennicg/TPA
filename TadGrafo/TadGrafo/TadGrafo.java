package TadGrafo;

/*
 * Implementação: grafo simples dirigido usando matriz de adjacências
 */
public class TadGrafo {
	private int [][] mat = null;
	private String nome;
	private int quantEdges = 0;
	private int geraIDedge = 0;
	
	public TadGrafo(String nome, int quantVertices) {
		this.nome = nome;
		mat = new int [quantVertices][quantVertices];
	}
	
	public void printmat() {
		for (int i = 0; i < mat[0].length;i++) {
			for (int j = 0; j< mat[0].length;j++) {
				System.out.print(String.format("%4d", mat[i][j]));
			}
			System.out.println();
		}
	}
	
	public int numVertices() {
		return mat[0].length;
	}
	
	public int numEdges() {
		return quantEdges;
	}
	
	public int getEdge (int u, int v) {
		return mat[u][v];
	}
	
	public int[] endVertices(int e) {
		for (int i = 0; i < mat[0].length;i++) {
			for (int j = 0; j< mat[0].length;j++) {
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
		for(int i = 0; i < mat[0].length;i++)
			if(mat[v][i] == e)
				return i;
		return 0;
	}
	
	public int outDegree(int v) {
		int grau = 0;
		for(int i = 0; i < mat[0].length;i++)
			if(mat[v][i] != 0 )
				grau++;
		return grau;
	}
	
	public int inDegree(int v) {
		int grau = 0;
		for(int i = 0; i < mat[0].length;i++)
			if(mat[i][v] != 0 )
				grau++;
		return grau;
	}
	
	public int insertEdge(int u, int v) {
		if((u >=0 && u < mat[0].length) && (v >= 0 && v < mat[0].length)) {
			mat[u][v] = geraIDedge++;
			quantEdges++;
			return mat[u][v];
		}
		return -1;
	}
	
	public void removeEdge(int e) {
		for (int i = 0; i < mat[0].length;i++) {
			for (int j = 0; j< mat[0].length;j++) {
				if(mat[i][j] == e) {
					mat[i][j] = 0;
					quantEdges--;
				}
			}
	}
		
}
