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
import java.util.Iterator;

import javax.xml.crypto.Data;
public class ToGStream {
	private static final String NODE_ID = "Node.nodeId";
	@SuppressWarnings("unused")
	private FileInputStream fpIn;
	@SuppressWarnings("unused")
	private FileOutputStream fpOut;
	private BufferedReader reader;
	private PrintWriter writer;
	private Graph gr;
	private String sep;

	
	public ToGStream(TADGrafo graph ) throws IdAlreadyInUseException, ElementNotFoundException, EdgeRejectedException, IOException {
		//Converte um grafo do tipo TADGrafo para GraphStream
		String line ="";
		Integer edgeCount = 0;
		while ((line = reader.readLine()) != null) {
			if (line.startsWith("#")) continue;
			line = line.trim();
			String[] edgePoints = line.split(sep);
//			System.out.print(edgePoints[0] + " : " ) ;
//			System.out.println(edgePoints[1]);
			if (edgePoints.length < 2) continue;
			gr.addNode(edgePoints[0]);
			gr.addNode(edgePoints[1]);
			gr.addEdge((edgeCount++).toString(), edgePoints[0] , edgePoints[1]);
		}
		
		this.writer.println(gr.getNodeCount() + " " + gr.getEdgeCount());
		
		Integer nodeCount = 0;
		Iterator<Node> nIt = gr.getNodeIterator();
		ArrayList<Node> arrNodes = new ArrayList<>();
		while (nIt.hasNext()) {
			Node nx = nIt.next();
			arrNodes.add(nx);
			nx.setAttribute(NODE_ID, ++nodeCount);
		}
	
		arrNodes.stream()
			.forEach(p -> {
				System.out.println("" + p.getAttribute(NODE_ID));
				writer.flush();
			});
		writer.flush();
		writer.close();
		
		
	}
	
	public void exibe(String css) {
		//Exibe o grafo com a lib definido pela String
		 Graph graph = gr;
		 Viewer viewer = graph.display();
		 View view = viewer.getDefaultView();
	}

}
