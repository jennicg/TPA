package app_grafo;
import taddic.TADDicChain;
import taddic.TDicItem;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Object;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import TadGrafo.TADGrafoD;
import _my_tools.ArquivoTxt;

public class ToTGF {
	
    private static Integer geraID=1;
	
	   

	 public static  void converteTxt_Tgf(String nomearq){
	        TADDicChain dicVertex = new TADDicChain();
	        LinkedList<String> arestas = new LinkedList<>();
	        //Abrindo o arquivo txt
	        ArquivoTxt open = ArquivoTxt.open("./dicionario/_my_tools/"+nomearq+".txt", "rt");
	        String linha;
	        String[] separador;
	        int quantVertex = 0;
	        while((linha = open.readline()) != null){
	            separador = linha.split("/");
	            quantVertex  = geraIDVertex();
	            dicVertex.insertItem(quantVertex, separador[0]);
	            // o movie eh a primeira pos da linha
	            TDicItem movieItem = (TDicItem)dicVertex.findElement(separador[0]);
	            int idMovie = 0;
	            if(dicVertex.NO_SUCH_KEY()){
	            	quantVertex  =geraIDVertex();
	                dicVertex.insertItem(quantVertex, separador[0]);
	                //se nao foi inserido, inserir como vertice
	                idMovie = quantVertex;
	            }else{
	                idMovie = (int)(movieItem).getKey();
	            }
	            for(int i = 1; i < separador.length; i++){
	            	//agora eh a hora de ler os atores
	                String actorName = separador[i];
	                String[] separaName = actorName.split(","); // a virgula eh o separador
	                actorName = separaName[0]+separaName[1];
	                TDicItem find_autor = (TDicItem)dicVertex.findElement(actorName);
	                int idActor = 0;
	                if(dicVertex.NO_SUCH_KEY()){
	                	quantVertex = geraIDVertex();// mesma logica, se nao achar, inserir no dic
	                    dicVertex.insertItem(quantVertex, actorName);
	                    idActor = quantVertex;
	                }else{
	                    idActor = (int)(find_autor).getKey();
	                }
	                String idTGF = idMovie+" "+idActor; 
	                arestas.add(idTGF);
	                // terminou de ler o arquivo
	            } 
	        }
	        
	        // converter TGF
	        ArquivoTxt escritaTGF = ArquivoTxt.open("./dicionario/_my_tools/"+nomearq+".tgf", "wt");
	        LinkedList[] vet = dicVertex.getVetBuckets();
	        for(int i = 0; i < dicVertex.getSizeVetBuckets(); i++){
	            for(int k = 0; k < vet[i].size(); k++){
	            	// Aqui é escrito os vertices
	                Object key = ((TDicItem)(vet[i].get(k))).getKey();
	                Object dado = ((TDicItem)(vet[i].get(k))).getDado();
	                escritaTGF.write(key.toString()+" "+dado.toString());
	                escritaTGF.write("\n");
	            }
	        }

	        escritaTGF.write("#");
	        escritaTGF.write("\n");
	        //Percorrer as listas para adicionar o idTGF
	        for(int i = 0; i < arestas.size(); i++){
	            escritaTGF.write(arestas.get(i));
	            escritaTGF.write("\n");
	        }
	        escritaTGF.close();
	    }
	 
	    
	    public static TADGrafoD carrega(String nome_arq){
	        ArquivoTxt open = ArquivoTxt.open("./dicionario/_my_tools/"+nome_arq+".tgf", "rt");
	        String linha;
	        String[] separador;
	        TADGrafoD grafo = new TADGrafoD("filmes", 300);
	        boolean achou = false;
	        while((linha = open.readline()) != null){
	            if(linha.contains("#")){
	                achou = true;
	                continue;
	            }
	            if(!achou){
	                separador = linha.split(" ");
	                String vertex = separador[1];
	                for(int i=2; i<separador.length; i++){
	                	//pegar os vertices
	                    vertex += " "+separador[i];
	                }
	                grafo.insertVertex(separador[0], vertex);
	            }else{
	            	//pegar as relações
	                separador = linha.split(" ");
	                grafo.insertEdge(separador[0], separador[1], "", "",0);
	            }
	        }
	        open.close();
	        return grafo;
	    }
    
    private static int geraIDVertex(){
        int id = geraID++;
        return id;
    }
}
        
  