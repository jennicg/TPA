package TadGrafo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vertex implements Comparable<Vertex> {
	private String label;
	private int id;
	private Object dado;
	private int distancia;
    private boolean visitado = false;
    private Vertex pai;
    private List<Edge> arestas = new ArrayList<Edge>();
    private List<Vertex> vizinhos = new ArrayList<Vertex>();

	public Vertex (String label, Object dado) {
		this.label = label;
		this.dado = dado;
		
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Object getDado() {
		return dado;
	}
	public void setDado(Object dado) {
		this.dado = dado;
	}
	
	public void visitar (){
        
        this.visitado = true;
}

public boolean verificarVisita(){
        
        return visitado;
}

public void setDistancia(int distancia){
        
        this.distancia = distancia;
}

public int getDistancia(){
        
        return this.distancia;
}

public void setPai(Vertex pai){
        
        this.pai = pai;
}

public Vertex getPai(){
        
        return this.pai;
}

public void setVizinhos(List<Vertex> vizinhos) {
        
        this.vizinhos.addAll(vizinhos);
                        
}

public List<Vertex> getVizinhos(){
        
        return this.vizinhos;
}

public void setArestas(List <Edge> arestas){
        
        this.arestas.addAll(arestas);
        
}

public List<Edge> getArestas() {
        
        return arestas;
}

public int compareTo(Vertex vertice) {
          if(this.getDistancia() < vertice.getDistancia()) return -1;
  else if(this.getDistancia() == vertice.getDistancia()) return 0;
  
  return 1;

        
}

@Override
public boolean equals(Object obj) {
        if(obj instanceof Vertex){
                Vertex vRef = (Vertex) obj;
                if(this.getLabel().equals(vRef.getLabel())) return true;
        }
        return false;
}

@Override
public String toString() {
        String s = " ";
        s+= this.getLabel();
        return s;
}

}
