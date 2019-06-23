package TadGrafo;


public class Vertex  {
	private String label;
	private int id;
	private Object dado;

	//Construtor da classe.
	public Vertex (String label, Object dado) {
		this.label = label;
		this.dado = dado;
		
	}
	
	//Retorna o label do vertice.
	public String getLabel() {
		return label;
	}
	
	//Define/redefine o label do vertice.
	public void setLabel(String label) {
		this.label = label;
	}
	
	//Retorna o id do vertice.
	public int getId() {
		return id;
	}
	
	//Define o id do vertice.
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	//Retorna o dado armazenado pelo objeto vertice (Vertex).
	public Object getDado() {
		return dado;
	}
	
	//Define/redefine o campo dado.
	public void setDado(Object dado) {
		this.dado = dado;
	}

	
//Compara dois objetos vertices.
public boolean equals​(Vertex v){
	if (this.label == v.getLabel() && this.getId() == v.getId()
			&& this.getDado() == v.getDado())
		return true;
	return false;
}



}
