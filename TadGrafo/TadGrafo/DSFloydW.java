package TadGrafo;

import java.util.LinkedList;

import taddic.TADDicChain;

public class DSFloydW extends DataSet{
	private int [][] mat_custos;
	private TADDicChain dic_vertex_label_int = null;
	
	public DSFloydW (int [][] mat_custos) {
		this.mat_custos = mat_custos;
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
