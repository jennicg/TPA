package TadGrafo;

public class Edge {
	private String label;
	private int id;
	private Object dado;
	private Vertex destination;
	private Vertex source;
	int peso;
	
	public Edge (String label, Object dado) {
		this.label = label;
		this.dado = dado;
		
	}
	public String getLabel() {
		return label;
		
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Vertex getDestination() {
        return destination;
    }
	
	public void setDestination(Vertex destination) {
		this.destination = destination;
	}
	
	public Vertex getSource() {
        return source;
    }
	
	public void setSource(Vertex source) {
		this.source = source;
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
	
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}

}
