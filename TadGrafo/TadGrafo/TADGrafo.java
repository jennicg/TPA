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
	
	public String getNome() {
		return nome;
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
		
	public Edge getEdge (String u, String v) {
		Vertex oV = (Vertex)dicLblVertex.findElement(v);
		if(dicLblVertex.NO_SUCH_KEY())
			return null;
		
		Vertex oU = (Vertex)dicLblVertex.findElement(u);
		if(dicLblVertex.NO_SUCH_KEY())
			return null;
		
		int idE = mat[oU.getId()][oV.getId()];
		if(idE == 0)
			return null;
		else {
			LinkedList<Object> lstEs = dicLblEdge.keys();
			for(int i = 0; i < lstEs.size(); i++) {
				Edge oE = (Edge)dicLblEdge.findElement(lstEs.get(i));
				if(oU.getId() == idE)
					return oE;
			}
		}
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
	
	
	public Integer outDegree(String label) {
		Vertex v = (Vertex)dicLblVertex.findElement(label);
		if(dicLblVertex.NO_SUCH_KEY())
			return null;
		else {
			int linha = v.getId();
			int grau = 0;
			for(int i = primeiroVertex; i <= ultimoVertex;i++)
				if((mat[linha][i] != 0) && !lstDeletados.contains(i))
					grau++;
			return grau;
				
		}
	}
		
	public Integer inDegree(String label) {
		Vertex v = (Vertex)dicLblVertex.findElement(label);
		if(dicLblVertex.NO_SUCH_KEY())
			return null;
		else {
			int linha = v.getId();
			int grau = 0;
			for(int i = primeiroVertex; i <= ultimoVertex;i++)
				if((mat[i][linha] != 0) && !lstDeletados.contains(i))
					grau++;
			return grau;
		}
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
	
	
	
	public Object removeEdge(String e) {
		Edge eE = (Edge)dicLblEdge.findElement(e);
		if(dicLblEdge.NO_SUCH_KEY())
			return null;
		
		int idE = eE.getId();
		
		for (int i = primeiroVertex; i <=ultimoVertex; i++ ) {
			if(!lstDeletados.contains(i))
				for(int j = primeiroVertex; j <= ultimoVertex;j++)
					if(!lstDeletados.contains(j))
						if(mat[i][j] == idE) {
							mat[i][j] = 0; // zerar faz parte da deleção
							quantEdges--;
							dicLblEdge.removeElement(e);
							return eE.getDado();
						}
		}
		return null;

	}
	
	
	public Vertex insertVertex(String label, Object o) {
		 int idV = geraIDvertex++;
        
        if(idV > ultimoVertex){
            ultimoVertex = idV;
        }
        if(idV < primeiroVertex){
            primeiroVertex = idV;
        }
        
        
        Vertex V = (Vertex)dicLblVertex.findElement(label);
        if(dicLblVertex.NO_SUCH_KEY()){
            V = new Vertex(label, o);
            V.setId(idV);
            dicLblVertex.insertItem(label,V);
            quantVertex++;
        }
        else{
            V.setDado(o);
        }
        
        return V;
    }
			
	
	
	
	/*
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
	*/
	public Object removeVertex (String label) {
		Vertex v = (Vertex)dicLblVertex.findElement(label);
		if(dicLblVertex.NO_SUCH_KEY())
			return null;
		
		int id = v.getId();
		
			if(id == primeiroVertex)
				for (int i = primeiroVertex +1; i <= ultimoVertex; i++)
					if(!lstDeletados.contains(i)) {
						primeiroVertex = i;
						break;
					}
			if(id == ultimoVertex)
				for (int i = ultimoVertex -1; i >= primeiroVertex; i--)
					if(!lstDeletados.contains(i)) {
						ultimoVertex = i;
						break;
					}
			for(int i = primeiroVertex; i<= ultimoVertex; i++ ) {
				if(mat[id][i] != 0) {
					quantEdges--;
					mat[id][i] = 0;
	
				}
				if((mat[i][id] != 0) && (mat[id][i] != mat[i][id])){
					quantEdges--;
					mat[i][id] = 0;
	
				}
				
			}
			quantVertex--;
			lstDeletados.add(id);
			
		return dicLblVertex.removeElement(label);
		
	}
	
	public boolean areAdjacent(String u, String v) {
		Vertex vU = (Vertex)dicLblVertex.findElement(u);
		if(dicLblVertex.NO_SUCH_KEY())
			return false;
		
		Vertex vV = (Vertex)dicLblVertex.findElement(v);
		if(dicLblVertex.NO_SUCH_KEY())
			return false;
		
		return mat[vU.getId()][vV.getId()] != 0;
	}

}
