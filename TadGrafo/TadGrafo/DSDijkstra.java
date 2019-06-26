package TadGrafo;

import java.util.LinkedList;

public class DSDijkstra extends DataSet {
	
	private int vet_custos[];
	private String vet_antecessores[];
	
	public DSDijkstra(int vet_custos[], String vet_antecessores[]) {
		this.vet_antecessores = vet_antecessores;
		this.vet_custos = vet_custos;
		
	}
	
	@Override
	public LinkedList<Vertex> caminho(String origem, String destino){
		return null;
		
	}
	
	@Override
	public int custo (String origem, String destino) {
		return 0;
	}
	

}
