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
	
	public Vertex[] vertices() {
		Vertex[] lst = new Vertex[quantVertex];
		LinkedList<Object> v = dicLblVertex.elements();
		for (int i = 0; i < v.size(); i++) {
			lst[i] = (Vertex)v.get(i);
			}
		
		return lst;
	}

	public Edge[] edges() {
		Edge [] lst = new Edge[quantEdges];
		LinkedList<Object> e = dicLblEdge.elements();
		int pos = 0;
		for (int i = 0;  i < e.size();  i++) {
			Edge aux = (Edge)e.get(i);
			if(!VerificaEdge(lst,aux.getLabel()))
				lst[pos] = aux;
			pos++;
			
			
		}
		return lst;
	}
	
	private boolean VerificaEdge(Edge[] lst, String label) {
		for (Edge aux : lst) {
			if(aux != null && aux.getLabel().equals(label)) {
				return true;
			}
		}
		return false;
		
		
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
	
	/*
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
	*/
	
	public Vertex[] endVertices(String e) {
		Vertex[] v = new Vertex[2];
		Vertex oV = (Vertex)dicLblVertex.findElement(e);
		int id = oV.getId();
		for (int i = primeiroVertex; i < ultimoVertex;i++) {
			for (int j = primeiroVertex; j< ultimoVertex;j++) {
				if(mat[i][j]== id && !lstDeletados.contains(i) && !lstDeletados.contains(j))
					v[0] = findElementV(i);
					v[1] = findElementV(j);
		
				}
			
			}
		
			return v;
		}
		
	
	private Vertex findElementV(int index) {
		Vertex v = null;
		for(Object obj : this.dicLblVertex.elements()) {
			if(((Vertex)obj).getId() == index) {
				v = (Vertex)obj;
			}
		}
		return v;
	}
		
	
	
	public Vertex opposite (String v, String e) {
		Vertex oV = (Vertex)dicLblVertex.findElement(v);
		if(dicLblVertex.NO_SUCH_KEY())
			return null;
		Edge oE = (Edge)dicLblEdge.findElement(v);
		if(dicLblEdge.NO_SUCH_KEY())
			return null;
		for(int i = primeiroVertex; i <= ultimoVertex;i++)
			if(!lstDeletados.contains(i) && (mat[oV.getId()][i] == oE.getId())) {
				LinkedList<Object> lstVs = dicLblVertex.keys();
				
				for(int j = 0 ; j< lstVs.size(); j++) {
					Vertex oU = (Vertex)dicLblVertex.findElement(lstVs.get(j));
					if(oU.getId() ==i)
						return oU;
				}
						
			}
		return null;
		
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
	
	/*
	public Edge[] outgoingEdges(int v) {
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
	*/
	public Edge[] outGoingEdges(String v) {
		LinkedList<Integer> lst = new LinkedList<Integer>();
		Vertex vertex = (Vertex)dicLblVertex.findElement(v);
		if(!this.lstDeletados.contains(vertex.getId())) {
			for (int i = primeiroVertex; i <= ultimoVertex; i++) {
				int aux = mat[vertex.getId()][i];
				if ( aux != 0  ) {
					lst.add(aux);
				}
			}
		}
		if (lst.size()> 0) {
			Edge[] e = new Edge[lst.size()];
			for(int i = 0; i < lst.size(); i++) e[i] = (Edge)dicLblEdge.findElement(lst.get(i));
			return e;
		}
		else {
			return null;
		}
	}
	
	public Edge[] incomingEdges(String v) {
		LinkedList<Integer> lst = new LinkedList<Integer>();
		Vertex vertex = (Vertex)dicLblVertex.findElement(v);
		for (int i = primeiroVertex; i<ultimoVertex; i++) {
			int aux = this.mat[i][vertex.getId()];
			if ( aux != 0) {
				lst.add(aux);
			}
		}
		if (lst.size() > 0) {
			Edge[] e = new Edge[lst.size()];
			for(int i = 0; i < lst.size(); i++) e[i] = (Edge)dicLblEdge.findElement(lst.get(i));
			return e;
		}
		else {
			return null;
		}
	}
	
	public Edge insertEdge(String vu, String vv, String label, Object o) {
	        Edge e = (Edge)dicLblEdge.findElement(label);
	        Vertex vA = (Vertex)dicLblVertex.findElement(vu);
	        Vertex vB = (Vertex)dicLblVertex.findElement(vv);
	        if(!lstDeletados.contains(vA.getId()) || !lstDeletados.contains(vB.getId()))
	        		if(dicLblEdge.NO_SUCH_KEY()){
	    	            e = new Edge(label, o);
	    	            dicLblEdge.insertItem(label,e);
	    	            quantEdges++;
	    	        }
	    	        else{
	    	            e.setDado(o);
	    	        }
	        return e;
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
