package TadGrafo;

public class Edge {
	private String label;
	private int id;
	private Object dado;
	
	int peso;
	
	
	//Construtor da classe.
	public Edge (String label, Object dado) {
		this.label = label;
		this.dado = dado;
		
	}
	
	//Retorna o label da aresta.
	public String getLabel() {
		return label;
		
	}
	
	//Compara dois objetos aresta.
	public boolean equals​(Edge e){
		if (this.label == e.getLabel() && this.getId() == e.getId()
				&& this.getDado() == e.getDado())
			return true;
		return false;
	}
	
	//Define/redefine o label da aresta.
	public void setLabel(String label) {
		this.label = label;
	}
	
	
	//Retorna o id da aresta.
	public int getId() {
		return id;
	}
	
	//Define o id do arco/aresta.
	public void setId(int id) {
		this.id = id;
	}
	
	//Retorna o dado armazenado pelo objeto aresta (Edge).
	public Object getDado() {
		return dado;
	}
	
	//Define/redefine o campo dado.
	public void setDado(Object dado) {
		this.dado = dado;
	}
	
	
	//Utilizada como auxiliar no ProcessaGrafo
	public int getPeso() {
		return peso;
	}
	
	//Utilizada como auxiliar no ProcessaGrafo
	public void setPeso(int peso) {
		this.peso = peso;
	}

}
