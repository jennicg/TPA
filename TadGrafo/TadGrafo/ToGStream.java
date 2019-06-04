package TadGrafo;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.stream.file.*;
import org.graphstream.algorithm.*;
import org.graphstream.ui.layout.*;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;

import javax.xml.crypto.Data;
public class ToGStream {
private Graph g;
	
	public ToGStream(TADGrafo g) {
		
		this.g = new SingleGraph("I can see dead pixels");
		
		Vertex[] vertex = g.vertices();
		for(Vertex pos : vertex) {
			Node no = this.g.addNode(pos.getLabel());
			no.addAttribute("ui.label", pos.getLabel());
		}
		Edge[] edges = g.edges();
		LinkedList<String[]> arestas = new LinkedList<String[]>();
		for (Edge origem : edges) {
			Vertex[] endV = g.endVertices(origem.getLabel());
				org.graphstream.graph.Edge edge = this.g.addEdge(origem.getLabel(), endV[0].getLabel(),endV[1].getLabel(),true);
				edge.addAttribute("ui.label", origem.getLabel());
				String[] labelV = {endV[0].getLabel(),endV[1].getLabel()};
				arestas.add(labelV);	
				
			
				
			
				
			}
	}
	
	public void exibe(String css) {
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
