package TadGrafo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;

public class ProcessaGrafo {
	TADGrafo grafo;
	public  ProcessaGrafo(TADGrafo g) {
	 //Armazenar o grafo g
	 grafo = g;
	 
	}
	
	
/*
	public LinkedList<Vertex> dbfs(String label){
	 //Busca em largura
	 LinkedList<Vertex> retorno = new LinkedList<Vertex>();
	 return retorno;
	}
	
	public LinkedList<Vertex> dfs(Collection<? extends Vertex> label){
		LinkedList<Vertex> aux = new LinkedList<Vertex>();		
		aux.addAll(label);	
		boolean[] visitados = new boolean[ grafo.numEdges() +1];		
		LinkedList<Vertex> ordem = new LinkedList<Vertex>();	
		
		while( !aux.isEmpty()){		
			Vertex removidos = aux.pop();		
			
			if( !visitados[removidos.getId()+1]){		
				visitados[removidos.getId()+1]=true;	
				aux.add(removidos);	
				
				LinkedList<Integer> rowOfNodes = grafo.incomingEdges().get(removidos);	
				ListIterator<Integer> iterator = rowOfNodes.listIterator();		
				while(iterator.hasNext()){			
					int nodeInColumn = iterator.next();
					if( !visitados[nodeInColumn+1]){		
						aux.add(nodeInColumn);
					}
				}
			}
		}
		System.out.println("\nDepth first search visitation order\n"+ordem);
		return ordem;
	}
	*/
	/*
	public LinkedList<Vertex> breadthFirstSearch(Collection<? extends Vertex> startingNode){
		LinkedList<Vertex>nodeQueue = new LinkedList<Vertex>(); // VER DEPOIS
		nodeQueue.addAll(startingNode);	
		
		boolean[] visitedNodes = new boolean[ grafo.numEdges() +1];		
		LinkedList<Vertex> nodesInVisitedOrder = new LinkedList<Vertex>();	
		
		while( !nodeQueue.isEmpty()){		
			int nodeRemovedFromQueue = nodeQueue.remove();		
			
			if( !visitedNodes[nodeRemovedFromQueue+1]){		
				visitedNodes[nodeRemovedFromQueue+1]=true;	
				nodesInVisitedOrder.add(nodeRemovedFromQueue);	
				
				LinkedList<Integer> rowOfNodes = grafo.incomingEdges().get(removidos);		
				ListIterator<Integer> iterator = rowOfNodes.listIterator();		
				while(iterator.hasNext()){			
					int nodeInColumn = iterator.next();
					if( !visitedNodes[nodeInColumn+1]){		
						nodeQueue.add(nodeInColumn);
					}
				}
			}
		}
		System.out.println("\nBreadth first search visitation order\n"+nodesInVisitedOrder);
	}
	
	*/
}
