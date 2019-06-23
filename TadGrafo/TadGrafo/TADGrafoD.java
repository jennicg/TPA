package TadGrafo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import _my_tools.ArquivoTxt;
import taddic.TADDicChain;
import taddic.TDicItem;
public class TADGrafoD {
	private int [][] mat = null;
    private String nome;
    private int quantVertices = 0;
    private int quantEdges = 0;
    private int geraIDedge = 1;
    private int geraIDvertice = 0;
    private static Integer geraID=1;
    
    TADDicChain dicLblVertex = new TADDicChain();
    TADDicChain dicLblEdge = new TADDicChain();
    
    private int primVertice = 0;
    private int ultiVertice = 0;
    //list of deleted vertex
    private LinkedList<Integer> lstEliminados = new LinkedList<Integer>();
    
    
    private LinkedList<Edge> edges = new LinkedList<Edge>();
    
    
    /*Cria um TAD Grafo dirigido a partir de um label identificador,
    utilizando o modelo de matriz de adjacências.*/
    
    public TADGrafoD(String nome){
        mat = new int[16][16];
        this.nome = nome;
    }
    
    /*Cria um TAD Grafo dirigido a partir de um label identificador e do número de máximo de 
     vértices do grafo dirigido.
     */
    public TADGrafoD(String nome, int i) {
    	
		// TODO Auto-generated constructor stub
    	mat = new int[i][i];
        this.nome = nome;
	}
    
    
    /*
     * Imprime a matriz de adjacências.
     */
	public void printmat(){
        for(int i = primVertice; i < ultiVertice; i++) {
            if(!lstEliminados.contains(i)) {
                for(int k = primVertice; k <= ultiVertice; k++) {
                    if(!lstEliminados.contains(i)) {
                        System.out.println(String.format("%d",mat[i][k]));
                    }
                }
                
            System.out.println();    
            }
        }
    }
	
	
    
	/*
	 * Imprime o grafo no formato texto mostrando as arestas e seus terminais.
	 */
    public void printgrafo() {
        ArrayList<String> al = new ArrayList<String>();
        String s, labelOrigem = "", labelDestino = "", labelEdge = "";
        
        Vertex v;
        Edge e;
        
        LinkedList<Object> lstVs = dicLblVertex.keys();
        LinkedList<Object> lstEs = dicLblEdge.keys();
        
        for(int i = primVertice; i <= ultiVertice; i++) {
            s = "";
            
            if(!lstEliminados.contains(i)) {
                for(int j = 0; j < lstVs.size(); j++) {
                    v = (Vertex)dicLblVertex.findElement(lstVs.get(j));
                    if(v.getId() == i) {
                        labelOrigem = v.getLabel();
                        break;
                    }
                }
                
                for(int k = primVertice; k <= ultiVertice; k++) {
                    if(!lstEliminados.contains(k)) {
                        for(int m = 0; m < lstVs.size(); m++) {
                            v = (Vertex)dicLblVertex.findElement(lstVs.get(m));
                            if(v.getId() == k) {
                                labelDestino = v.getLabel();
                                break;
                            }
                        }
                        
                        int idEdge = mat[i][k];
                        
                        if(idEdge != 0) {
                            for(int m = 0; m < lstEs.size(); m++) {
                                e = (Edge)dicLblEdge.findElement(lstEs.get(m));
                                if(e.getId() == idEdge) {
                                	labelEdge = e.getLabel();
                                    break;
                                }
                            }
                            
                            s = labelOrigem + "--" + labelEdge + "-->" + labelDestino;
                            al.add(s);
                        }
                    }
                }
            }
        } //for int i...
        
        //Island vertex treatment
        for(int i = 0; i < lstVs.size(); i++) {
            String lbl = (String)lstVs.get(i);
            if(degree(lbl) == 0) {
                al.add(lbl);
            }
        }
        
        Collections.sort(al);
        
        for(int n = 0; n < al.size(); n ++) {
            System.out.println(al.get(n));
        }
    }
    
    
    
    /*
     * Procura e retorna o objeto Vertex associado ao label de valor lbl.
     */
	public Vertex getVertex(String label) {
		Vertex vertex = (Vertex)dicLblVertex.findElement(label);
		if(dicLblVertex.NO_SUCH_KEY()) {
			return null;
		}
		else {
			return vertex;
		}
	}
	
	
	
	/*
	 * Utilizado na classe ProcessaGrafo
	 */
	public Vertex[] vertices_aux() {
		Vertex[] v = new Vertex[numVertices()];
		LinkedList<Object> vertex = dicLblVertex.elements();
		for (int i = 0; i < vertex.size(); i++) {
			v[i] = (Vertex)vertex.get(i);
		}
		return v;
	}
	
	
	/*
	 * Retorna uma lista com os objetos aresta pertencentes ao grafo.
	 */
	public LinkedList<Vertex> vertices() {
		LinkedList<Vertex> v = new LinkedList<Vertex>();
		LinkedList<Object> vertex = dicLblVertex.elements();
		for (int i = 0; i < vertex.size(); i++) {
			Vertex aux = (Vertex)vertex.get(i);
			v.add(aux);
		}
		return v;
	}
	

	/*
	 * Retorna uma lista com os objetos aresta pertencentes ao grafo.
	 */
	public LinkedList<Edge> edges() {
		LinkedList<Edge> e = new LinkedList<Edge>();
		LinkedList<Object> edge = dicLblEdge.elements();
		for (int i = 0; i < edge.size(); i++) {
			Edge aux = (Edge)edge.get(i);
			e.add(aux);
		}
		return e;
	}
	
	
	
	/*
	 * Utilizado na classe ProcessaGrafo
	 */
	public Edge[] edges_aux() {
		Edge [] e = new Edge[numEdges()];
		int pos = 0;
		LinkedList<Object> edges = dicLblEdge.elements();
		for(int i = 0; i < edges.size(); i++) {
			Edge ed = (Edge)edges.get(i);
			if(!validaEdge(e,ed.getLabel())) {
				e[pos] = ed;
				pos++;
				
			}
				
			
		}
		return e;
	}
	
	


	/*
	 * Retorna o total de vértices que compõe o grafo.
	 */
    public int numVertices(){
        return quantVertices;
    }
    
    
    /*
     * Retorna o total de arestas que compõe o grafo.
     */
    public int numEdges(){
        return quantEdges;
    }
    
    /*
     * Retorna o texto identificador do grafo.
     */
    public String getNome() {
        return nome;
    }
    
    /*
     * Define/altera o label identificador do grafo.
     */
    public void setNome(String nome) {
    	this.nome = nome;
    }
    
    public boolean valido(int v){
        return((v >= primVertice) && (v<=ultiVertice) && !(lstEliminados.contains(v)));
    }
    
    /*
     * Procura e retorna o objeto Edge associado ao label lbl.
     */
    public Edge getEdge(String lbl) {
    	Edge e = (Edge)dicLblEdge.findElement(lbl);
    	if(dicLblEdge.NO_SUCH_KEY()) {
            return null;
        }
    	
    	return e;
    }
    
    /*
     * Procura e retorna o objeto Edge associada a aresta que possui os vértices u e v como terminais.
     */
    public Edge getEdge(String origem, String destino) {
        Vertex vDestino = (Vertex)dicLblVertex.findElement(destino);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        Vertex vOrigem = (Vertex)dicLblVertex.findElement(origem);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }

        int idEdge = mat[vOrigem.getId()][vDestino.getId()];

        
        if(idEdge == 0) {
            return null;
        }
        else {
        	

        	
        	for(Edge e : this.edges) {
        		if(e.getId() == idEdge) {
        			return e;
        		}

        		
        		
        	}
        	/*
            LinkedList<Object> lstEdgeKeys = dicLblEdge.keys();
          
            for(int i = 0; i < lstEdgeKeys.size(); i++) {
                Edge e = (Edge)dicLblEdge.findElement(lstEdgeKeys.get(i));
               
                if(vDestino.getId() == idEdge) {
                    return e;
                   
                }
            }
        }
        return null;
        */
    }
        return null;
    }
    
   
    
    public Edge intToEdge (int id) {
    	LinkedList<Object> lst = dicLblEdge.elements();
    	for(int i = 0; i< lst.size();i++) {
    		Edge e = (Edge)lst.get(i);
    		if(id == e.getId())
    			return e;
    	}
    	return null;
    }
    
    
    
    /*
     * Encontra e retorna os objetos Vertex que formam os terminais da aresta de label labelE.
     */
    public Vertex[] endVertices(String labelE){ 
    	Edge oE = (Edge)dicLblEdge.findElement(labelE);
    	if(dicLblEdge.NO_SUCH_KEY())
    		return null;
    	int idE = oE.getId();
        for(int i = primVertice;i<=ultiVertice;i++){
            if(valido(i)){
                for(int k=primVertice;k<=ultiVertice;k++){
                    if(mat[i][k] == idE){
                        Vertex[] v = new Vertex[2];
                        v[0] = intToVertex(i);
                        v[1] = intToVertex(k);
                        return v;
                    }
                }
            }
        }
        
        return null;
    }
    
    /*
     *Encontra e retorna o objeto Vertex que é o destino da aresta de label e saindo do vértice de label v.
     */
    public Vertex opposite(String v, String e){
        Vertex objV = (Vertex)dicLblVertex.findElement(v);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        Edge objE = (Edge)dicLblEdge.findElement(e);
        if(dicLblEdge.NO_SUCH_KEY()) {
            return null;
        }
        
        for(int i = primVertice; i <= ultiVertice; i++) {
            if((!lstEliminados.contains(i)) && (mat[objV.getId()][i] == objE.getId())) {
                LinkedList<Object> lstVs = dicLblVertex.keys();
                
                for(int m = 0; m< lstVs.size(); m++) {
                    Vertex oU = (Vertex)dicLblVertex.findElement(lstVs.get(m));
                    if(oU.getId() == i) {
                        return oU;
                    }
                }
            }
        }
        
        return null;
    }
    
    // v, linha, i, coluna: todos as arestas saindo de v.
    /*
     * Calcula e retorna a quantidade de arestas que saem do vértice de label lbl.
     */
    public Integer outDegree(String label){
        Vertex v = (Vertex)dicLblVertex.findElement(label);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        else {
            int line = v.getId();
            int grade = 0;
            
            for(int i = primVertice; i <= ultiVertice; i++) {
                if((mat[line][i] != 0) && !lstEliminados.contains(i)) {
                    grade++;
                }
            }
            
            return grade;
        }
    }
    
    // v, coluna, i, linha: todos as arestas entrando de v.
    /*
     * Calcula e retorna a quantidade de arestas que chegam até o vértice de label lbl.
     */
    public Integer inDegree(String label) {
        Vertex v = (Vertex)dicLblVertex.findElement(label);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        else {
            int line = v.getId();
            int grade = 0;
            
            for(int i = primVertice; i <= ultiVertice; i++) {
                if((mat[i][line] != 0) && !lstEliminados.contains(i)) {
                    grade++;
                }
            }
            
            return grade;
        }
    }
    
    
    public Vertex insereVertex(String label, Object o){
        int idV = geraIDvertice++;
        
        if(idV > ultiVertice){
            ultiVertice = idV;
        }
        if(idV < primVertice){
            primVertice = idV;
        }
        
        
        Vertex v = (Vertex)dicLblVertex.findElement(label);
        if(dicLblVertex.NO_SUCH_KEY()){
            v = new Vertex(label, o);
            v.setId(idV);
            dicLblVertex.insertItem(label, v);
            quantVertices++;
        }
        else{
            v.setDado(o);
        }
        
        return v;
    }
    

	
    /*
     * Acrescenta ao grafo uma aresta de label lbl armazenando um objeto de dados (da aplicação) o
     */
    public Edge insertEdge(String origem, String destino, String label, Object o, int peso) {
        Vertex vOrigem = (Vertex)dicLblVertex.findElement(origem);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        Vertex vDestino = (Vertex)dicLblVertex.findElement(destino);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        Edge e = (Edge)dicLblEdge.findElement(label);
        
        //Inclusion of a new arch
        if(dicLblEdge.NO_SUCH_KEY()) {
            e = new Edge(label, o);
            e.setId(geraIDedge++);
            e.setSource(vOrigem);
            e.setDestination(vDestino);
            e.setPeso(peso);
            dicLblEdge.insertItem(label, e);
            mat[vOrigem.getId()][vDestino.getId()] = e.getId();
            quantEdges++;
            
             
            
            edges.add(e);
            
       
            
            
        } //Update of a existent arch
        else {
            e.setDado(o);
        }
        return e; 
    }
    
    
    public Edge insertEdge(String origem, String destino, String label, Object o) {
        Vertex vOrigem = (Vertex)dicLblVertex.findElement(origem);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        Vertex vDestino = (Vertex)dicLblVertex.findElement(destino);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        Edge e = (Edge)dicLblEdge.findElement(label);
        
        //Inclusion of a new arch
        if(dicLblEdge.NO_SUCH_KEY()) {
            e = new Edge(label, o);
            e.setId(geraIDedge++);
            e.setSource(vOrigem);
            e.setDestination(vDestino);
            dicLblEdge.insertItem(label, e);
            mat[vOrigem.getId()][vDestino.getId()] = e.getId();
            quantEdges++;
            
             
            
            edges.add(e);
            
       
            
            
        } //Update of a existent arch
        else {
            e.setDado(o);
        }
        return e; 
    }
    
 
     
    /*
     * Remove do grafo a aresta de label lbl.
     */
    public Object removeEdge(String edge){
        Edge e  = (Edge)dicLblEdge.findElement(edge);
        if(dicLblEdge.NO_SUCH_KEY()) {
            return null;
        }
        
        int idE = e.getId();
        
        for(int i = primVertice; i <= ultiVertice; i++) {
            if(!lstEliminados.contains(i)) {
                for(int j = primVertice; j <= ultiVertice; j++) {
                    if(mat[i][j] == idE) {
                        mat[i][j] = 0;
                        quantEdges--;
                        dicLblEdge.removeElement(edge);
                        return e.getDado();
                    } 
                } 
            }      
        }
        
        /* Anomalia: o arco de label existe mas o seu id não se encontra */
        return null;
    }
    
    
    /*
     * Remove do grafo o vértice de label lbl.
     */
    
    public Object removeVertex(String label) {
        Vertex v = (Vertex)dicLblVertex.findElement(label);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return null;
        }
        
        int idV = v.getId();
            
        if(idV == primVertice) {
            for(int i = primVertice+1; i <= ultiVertice; i++) {
                if(!lstEliminados.contains(i)) {
                    primVertice = i;
                    break;
                }
            }
        }

        if(idV == ultiVertice) {
            for(int i = ultiVertice-1; i <= primVertice; i--) {
                if(!lstEliminados.contains(i)) {
                    ultiVertice = i;
                    break;
                }
            }
        }
        
        for(int i = primVertice; i <= ultiVertice; i++) {
            //Fill removed vertex line with 0's that means the vertex does not exist
            if(mat[idV][i] != 0) {
                quantEdges--;
                mat[idV][i] = 0;
            }
            
            //Fill removed vertex column with 0's that means the vertex does not exist
            //Also prevent from decrementing quantEdges already decremented
            if((mat[i][idV] !=0) && (mat[idV][i] != mat[i][idV])) {
                quantEdges--;
                mat[i][idV] = 0;
            }
        }
        
        quantVertices--;
        lstEliminados.add(idV);
        return dicLblVertex.removeElement(label);   
    }
    
    
   
    /*
     * Verifica se dois vértices estão conectados por uma aresta.
     */
    public boolean areAdjacent(String origem, String destino){
        Vertex vOrigem = (Vertex)dicLblVertex.findElement(origem);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return false;
        }
        
        Vertex vDestino = (Vertex)dicLblVertex.findElement(destino);
        if(dicLblVertex.NO_SUCH_KEY()) {
            return false;
        }
        
        return mat[vOrigem.getId()][vDestino.getId()] != 0;
    }
    /*
    public LinkedList<Edge> outGoingEdges(String label) {
		LinkedList<Edge> lst = new LinkedList<Edge>();
		Vertex v = (Vertex)this.dicLblVertex.findElement(label);
		int id = v.getId();
		if(!this.lstEliminados.contains(v.getId())) {
			for (int i = primVertice; i <= ultiVertice; i++) {
				int auxEdge = this.mat[id][i];
				
					lst.add(intToEdge(auxEdge));
				
			}
		}
		return lst;
	}
	
	public LinkedList<Edge> incomingEdges(String label) {
		
		LinkedList<Edge> lst = new LinkedList<Edge>();
    	if(dicLblVertex.NO_SUCH_KEY())
    		return null;
    	Vertex v = (Vertex)this.dicLblVertex.findElement(label);
		int id = v.getId();
		for (int i =primVertice; i<= ultiVertice; i++) {
			int auxEdge = this.mat[i][id];
			
				lst.add(intToEdge(auxEdge));
			
		}
		return lst;
	}
    */
    private int geraIDVertex() {
        int id;
        
        if(lstEliminados.size() == 0) {
            id = geraIDvertice++;
        }
        else {
            id = lstEliminados.get(0);
            lstEliminados.remove();
        }
        
        if(id < primVertice)
            primVertice = id;
        
        if(id > ultiVertice)
            ultiVertice = id;
        
        return id;
    }
    
    
    /*
     * Acrescenta ao grafo um vértice de label lbl armazenando um objeto de dados (da aplicação) o.
     */
    public Vertex insertVertex(String label, Object dado) {
        int idVertex = geraIDVertex();
        
        if(idVertex > ultiVertice) { 
            ultiVertice = idVertex;
        }
        
        if(idVertex < primVertice) {
            primVertice = idVertex;
        }
        
        Vertex v = (Vertex)dicLblVertex.findElement(label);
        
        //Including a new vertex
        if(dicLblVertex.NO_SUCH_KEY()) {
            v = new Vertex(label, dado);
            v.setId(idVertex);
            dicLblVertex.insertItem(label, v);
            quantVertices++;
        }
        else { //updating a existent vertex
            v.setDado(dado);
        }
        
        return v;
    }
    
    /*
     * Calcula e retorna a quantidade de arestas conectadas ao vértice de label label.
     */
    public Integer degree(String label) {
        Integer in = inDegree(label);
        Integer out = outDegree(label);
        
        if((in == null) || (out == null)) {
            return null;
        }
        else {
            return in + out;
        }
    }

/*
 * MÉTODOS ESPECÍFICOS PARA GRAFOS DIRIGIDOS
 */
    
    /*
     * Descobre e retorna o objeto vértice onde entra a aresta de label labelE.
     */
    public Vertex destination (String labelE) {
    	Vertex [] vet = endVertices(labelE);
    	if(vet!= null)
    		return vet[1];
    	else
    		return null;
    }
    
    /*
     * Descobre e retorna o objeto vértice de onde sai a aresta de label lbl.
     */
    public Vertex origin (String labelE) {
    	Vertex [] vet = endVertices(labelE);
    	if(vet!= null)
    		return vet[0];
    	else
    		return null;
    }
    
    
    /*
     * Descobre e retorna todas as arestas que chegam ao vértice de label lbl.
     */
    public LinkedList<Edge> inIncidentEdges (String labelV){
    	Vertex v = (Vertex)dicLblVertex.findElement(labelV);
    	if(dicLblVertex.NO_SUCH_KEY())
    		return null;
    	LinkedList<Edge> lst = new LinkedList <Edge>();
    	int id = v.getId();
    	
    	for(int k = primVertice; k<= ultiVertice; k++)
    		if((!lstEliminados.contains(k) && (mat[id][k] != 0)))
    			lst.add(intToEdge(mat[id][k]));
    	return lst;
    }
    
    
    
    /*
     * Descobre e retorna todas as arestas que saem do vértice de label lbl.
     */
    public LinkedList<Edge> outIncidentEdges (String labelV){
    	Vertex v = (Vertex)dicLblVertex.findElement(labelV);
    	if(dicLblVertex.NO_SUCH_KEY())
    		return null;
    	LinkedList<Edge> lst = new LinkedList <Edge>();
    	int id = v.getId();
    	
    	for(int i = primVertice; i<= ultiVertice; i++)
    		if((!lstEliminados.contains(i) && (mat[i][id] != 0)))
    			lst.add(intToEdge(mat[i][id]));
    	return lst;
    }
    
    
    /*
     * Descobre e retorna os vértices origem das arestas que entram no vértice de label lbl.
     */
    public LinkedList<Vertex> inAdjacenteVertices(String labelV){
    	Vertex v = (Vertex)dicLblVertex.findElement(labelV);
    	if(dicLblVertex.NO_SUCH_KEY())
    		return null;
    	LinkedList<Vertex> lst = new LinkedList<Vertex>();
    	int id = v.getId();
    	for(int k = primVertice; k<= ultiVertice; k++)
    		if(!lstEliminados.contains(k) && (mat[k][id] != 0))
    			lst.add(intToVertex(k));
    	return lst;
    }
    
    
    
    /*
     * Descobre e retorna os vértices destino das arestas que saem no vértice de label lbl.
     */
    public LinkedList<Vertex> outAdjacenteVertices(String labelV){
    	Vertex v = (Vertex)dicLblVertex.findElement(labelV);
    	if(dicLblVertex.NO_SUCH_KEY()) {
    		return null;
    	}
    	LinkedList<Vertex> lst = new LinkedList<Vertex>();
    	int id = v.getId();
    	for(int k = primVertice; k<= ultiVertice; k++)
    		if(!lstEliminados.contains(k) && (mat[id][k] != 0))
    			lst.add(intToVertex(k));
    	return lst;
    }
    
    public Vertex intToVertex(int id) {
    	LinkedList<Object> lst = dicLblVertex.elements();
    	for(int i = 0; i <lst.size(); i++) {
    		Vertex v = (Vertex)lst.get(i);
    		if(id == v.getId())
    			return v;
    	}
    	return null;
    }
    
   
    
    /*
     * Descobre e retorna todas as arestas conectadas ao vértice de label labelV.
     */
    public LinkedList<Edge> incidentEdges(String labelV){
    	LinkedList<Edge> lst = inIncidentEdges(labelV);
    	lst.addAll(outIncidentEdges(labelV));
    	return lst;
    }
    
    
    /*
     * Descobre e retorna os vértices adjacentes ao vértice de label labelV.
     */
    public LinkedList<Vertex> adjacentVertices(String labelV){
    	LinkedList<Vertex> lst = inAdjacenteVertices(labelV);
    	lst.addAll(outAdjacenteVertices(labelV));
    	return lst;
    	
    }
    //PRIVATE
	   
	private boolean validaEdge(Edge[] vetor, String str) {
		for (Edge i : vetor) {
			if(i != null && i.getLabel().equals(str)) {
				return true;
			}
		}
		return false;
	}
	
	
private static int geraIDVertexaux(){
	       int id = geraID++;
	       return id;
	   }

	 public static  void converteTxt_Tgf(String nomearq){
	        TADDicChain dicVertex = new TADDicChain();
	        LinkedList<String> arestas = new LinkedList<>();
	        //Abrindo o arquivo txt
	        ArquivoTxt open = ArquivoTxt.open("./dicionario/_my_tools/"+nomearq+".txt", "rt");
	        String linha;
	        String[] separador;
	        int quantVertex = 0;
	        while((linha = open.readline()) != null){
	            separador = linha.split("/");
	            quantVertex  = geraIDVertexaux();
	            dicVertex.insertItem(quantVertex, separador[0]);
	            // o movie eh a primeira pos da linha
	            TDicItem movieItem = (TDicItem)dicVertex.findElement(separador[0]);
	            int idMovie = 0;
	            if(dicVertex.NO_SUCH_KEY()){
	            	quantVertex  =geraIDVertexaux();
	                dicVertex.insertItem(quantVertex, separador[0]);
	                //se nao foi inserido, inserir como vertice
	                idMovie = quantVertex;
	            }else{
	                idMovie = (int)(movieItem).getKey();
	            }
	            for(int i = 1; i < separador.length; i++){
	            	//agora eh a hora de ler os atores
	                String actorName = separador[i];
	                String[] separaName = actorName.split(","); // a virgula eh o separador
	                actorName = separaName[0]+separaName[1];
	                TDicItem find_autor = (TDicItem)dicVertex.findElement(actorName);
	                int idActor = 0;
	                if(dicVertex.NO_SUCH_KEY()){
	                	quantVertex = geraIDVertexaux();// mesma logica, se nao achar, inserir no dic
	                    dicVertex.insertItem(quantVertex, actorName);
	                    idActor = quantVertex;
	                }else{
	                    idActor = (int)(find_autor).getKey();
	                }
	                String idTGF = idMovie+" "+idActor; 
	                arestas.add(idTGF);
	                // terminou de ler o arquivo
	            } 
	        }
	        
	        // converter TGF
	        ArquivoTxt escritaTGF = ArquivoTxt.open("./dicionario/_my_tools/"+nomearq+".tgf", "wt");
	        LinkedList[] vet = dicVertex.getVetBuckets();
	        for(int i = 0; i < dicVertex.getSizeVetBuckets(); i++){
	            for(int k = 0; k < vet[i].size(); k++){
	            	// Aqui é escrito os vertices
	                Object key = ((TDicItem)(vet[i].get(k))).getKey();
	                Object dado = ((TDicItem)(vet[i].get(k))).getDado();
	                escritaTGF.write(key.toString()+" "+dado.toString());
	                escritaTGF.write("\n");
	            }
	        }

	        escritaTGF.write("#");
	        escritaTGF.write("\n");
	        //Percorrer as listas para adicionar o idTGF
	        for(int i = 0; i < arestas.size(); i++){
	            escritaTGF.write(arestas.get(i));
	            escritaTGF.write("\n");
	        }
	        escritaTGF.close();
	    }
	 
	    
	 	/*
	 	 * Função estática que retorna um tad grafo dirigido a partir dos 
	 	 * dados armazenados no arquivo de nome nome_arq_tgf.
	 	 */
	    public static TADGrafoD carregaTGF(String nome_arq, int quant_vertices){
	        ArquivoTxt open = ArquivoTxt.open(nome_arq, "rt");
	        String linha;
	        String[] separador;
	        TADGrafoD grafo = new TADGrafoD("filmes", quant_vertices);
	        boolean achou = false;
	      
	        while((linha = open.readline()) != null ){
	            if(linha.contains("#")){
	                achou = true;
	                continue;
	            }
	            if(!achou){
	                separador = linha.split(" ");
	                String vertex = separador[1];
	                for(int i=2; i<separador.length; i++){
	                	//pegar os vertices
	                    vertex += " "+separador[i];
	                }
	                grafo.insertVertex(separador[0], vertex);
	            }else{
	            	//pegar as relações
	                separador = linha.split(" ");
	                grafo.insertEdge(separador[0], separador[1], "", "");
	            }
	            
	        }
	        open.close();
	        return grafo;
	    }
	    
	    
	    /*
	     *Verifica se dois tad grafos são iguais.
	     */
	   public boolean equals​(TADGrafoD OutroG) {
			if(this.numEdges() == OutroG.numEdges() && this.numVertices() == OutroG.numVertices()) {
				for(int i = primVertice; i<= ultiVertice;i++) {
					for(int k = primVertice; k < ultiVertice;k++) {
						 Vertex chave = intToVertex(k);
						 Object outroVertex = OutroG.dicLblVertex.findElement(chave.getLabel());
						 Edge e = intToEdge(k);
						 Object outroEdge = OutroG.dicLblEdge.findElement(e.getLabel());
						 if(chave.getDado() != outroVertex) {
							 return false;
						 }
						 if(e.getDado() != outroEdge) {
							 return false;
						 }
						 
					}
				}
			}
			return true;
			
		}
   
   
/*
Clona o tad grafo corrente em um novo grafo com a 
mesma estrutura e mesmo conteúdo.
 */
   public TADGrafoD clone() {
	   TADGrafoD grafoClone = new TADGrafoD("oi");
	   for(int i = primVertice; i <= ultiVertice; i++) {
		   for(int k = primVertice; k <= ultiVertice; k++) {
			   if(!lstEliminados.contains(k) && (mat[i][k] != 0)) {
				   Vertex chave = intToVertex(k);
				   grafoClone.insertVertex(chave.getLabel(), chave.getDado());
				   Edge e = intToEdge(k);
				   grafoClone.insertEdge(e.getSource().getLabel(), e.getDestination().getLabel(), e.getLabel(), e.getDado());
	   }
		   }
	   }
	return grafoClone;
	   
	   
   }
   
	 

           
}