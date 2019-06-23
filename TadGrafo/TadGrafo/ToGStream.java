package TadGrafo;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import  java.lang.Object;
import java.util.LinkedList;


public class ToGStream extends java.lang.Object{
private Graph g;
private boolean dirigido;
private boolean vertexVisivel;
private boolean edgeVisivel;
	
	public ToGStream(TADGrafoD g, boolean dirigido) {
		
		this.g = new SingleGraph("I can see dead pixels");
		
		Vertex[] vertex = g.vertices_aux();
		for(Vertex pos : vertex) {
			Node no = this.g.addNode(pos.getLabel());
			no.addAttribute("ui.label", pos.getLabel());
		}
		Edge[] edges = g.edges_aux();
		LinkedList<String[]> arestas = new LinkedList<String[]>();
		for (Edge origem : edges) {
			Vertex[] endV = g.endVertices(origem.getLabel());
			if(dirigido) {
				org.graphstream.graph.Edge edge = this.g.addEdge(origem.getLabel(), endV[0].getLabel(),endV[1].getLabel(),true);
				edge.addAttribute("ui.label", origem.getLabel());
				String[] labelV = {endV[0].getLabel(),endV[1].getLabel()};
				arestas.add(labelV);	
				
			}
			else {
				org.graphstream.graph.Edge edge = this.g.addEdge(origem.getLabel(), endV[0].getLabel(),endV[1].getLabel());
				edge.addAttribute("ui.label", origem.getLabel());
				String[] labelV = {endV[0].getLabel(),endV[1].getLabel()};
				arestas.add(labelV);	
			}
						
		
			}
	}
	
	
	
	public ToGStream(TADGrafoD g,boolean vertexVisivel, boolean edgeVisivel, boolean dirigido) {
		
		this.g = new SingleGraph("I can see dead pixels");
		
		if(vertexVisivel) {
			Vertex[] vertex = g.vertices_aux();
			for(Vertex pos : vertex) {
				Node no = this.g.addNode(pos.getLabel());
				no.addAttribute("ui.label", pos.getLabel());
			}
			if(edgeVisivel) {
				Edge[] edges = g.edges_aux();
				LinkedList<String[]> arestas = new LinkedList<String[]>();
				for (Edge origem : edges) {
					Vertex[] endV = g.endVertices(origem.getLabel());
					if(dirigido) {
						org.graphstream.graph.Edge edge = this.g.addEdge(origem.getLabel(), endV[0].getLabel(),endV[1].getLabel(),true);
						edge.addAttribute("ui.label", origem.getLabel());
						String[] labelV = {endV[0].getLabel(),endV[1].getLabel()};
						arestas.add(labelV);	
						
					}
					else {
						org.graphstream.graph.Edge edge = this.g.addEdge(origem.getLabel(), endV[0].getLabel(),endV[1].getLabel());
						edge.addAttribute("ui.label", origem.getLabel());
						String[] labelV = {endV[0].getLabel(),endV[1].getLabel()};
						arestas.add(labelV);	
					}
								
				
					}
				
				
			}
			
		}
		
	}
	
	
	
	public void exibe(java.lang.String css) {
		if(css == null)
			css = "";
	    System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	    g.setAttribute("stylesheet", "node { size: 30px; fill-color: yellow, orange; fill-mode: gradient-horizontal; text-size: 15px;} edge { z-index: 0; fill-color: #333; size: 3px; text-size: 10px; text-color: red; }");
	    g.addAttribute("ui.quality");
	    g.addAttribute("ui.antialias");
		g.addAttribute("ui.stylesheet", css);
		g.display();
	}
	
	
	

}
