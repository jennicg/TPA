package TadGrafo;

public class Main {

	public static void main(String[] args) {
		// Teste 
		TADGrafo g = new TADGrafo("teste");
		g.insertVertex("A",null);
		g.insertVertex("B",2);
		g.insertVertex("C",3);
		g.insertVertex("D",4);
		//g.insertVertex("E");
		/*
		g.insertEdge(0, 1);
		g.insertEdge(0, 2);
		g.insertEdge(1, 0);
		g.insertEdge(1, 2);
		g.insertEdge(2, 0);
		g.insertEdge(2, 1);
		g.insertEdge(2, 3);
		g.insertEdge(3, 2);
		*/
		g.printmat();
		System.out.println(g.numVertices());
		System.out.println(g.numEdges());
		System.out.println();
		
		g.removeVertex("A");
		
		g.printmat();
		System.out.println(g.numVertices());
		System.out.println(g.numEdges());
		System.out.println();
		
		g.insertVertex("A",null);
		g.printmat();
		System.out.println(g.numVertices());
		System.out.println(g.numEdges());
		
		TADGrafo tadGrafoD = new TADGrafo("IAE MAN");
        tadGrafoD.insertVertex("A", 1);
        tadGrafoD.insertVertex("B", 2);
        tadGrafoD.insertVertex("C", 3);
        tadGrafoD.insertEdge("A", "B", "ab", 12);
        tadGrafoD.insertEdge("B", "C", "bc", 23);
        tadGrafoD.insertEdge("C", "C", "cc", 33);
        
        tadGrafoD.printmat();
        
        System.out.println("");
        
        tadGrafoD.printgrafo();
	

	}

}
