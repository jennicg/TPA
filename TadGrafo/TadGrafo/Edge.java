package TadGrafo;

public class Edge {
	private String label;
	private int id;
	private Object dado;
	
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
	


}
