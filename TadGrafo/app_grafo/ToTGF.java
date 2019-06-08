package app_grafo;
import taddic.TADDicChain;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Object;
import java.util.LinkedList;
import java.util.Scanner;

import TadGrafo.TADGrafo;
import _my_tools.ArquivoTxt;

public class ToTGF {
	
    private String nome;
    public  TADDicChain dicKeys = new TADDicChain();
    public TADDicChain dicElements = new TADDicChain();
    private ActorsAndMovies IDMovies;
    private int geraID =0;
    
    
    public ToTGF(String nome){
        this.nome=nome;
    }
    
 
    public LinkedList filmesEAtores(){
        return dicKeys.keys();
    }
    
    public LinkedList relacionamentos(){
        return dicElements.elements();
    }
    
    
    public static TADGrafo carrega(String nome_arq){
        ArquivoTxt arqIn = ArquivoTxt.open(nome_arq, "rt");
        String linha;
        String[] array_split;
        TADGrafo g = new TADGrafo("filmes", 300);
        boolean troca = false;
        while((linha = arqIn.readline()) != null){
            if(linha.contains("#")){
                troca = true;
            }
            if(!troca){
                array_split = linha.split(" ");
                System.out.println("OII" + array_split[1]);
                g.insertVertex("", array_split[1]);
            }else{
                array_split = linha.split("");
                System.out.println("iae" + array_split[0]);
                System.out.println("XAUU" + array_split[1]);
                if(array_split[1] != "") {
                	 g.insertEdge(array_split[0], array_split[1], "", "");
                	
                }
               
            }
        }
        arqIn.close();
        return g;
    }
    
    
    public void write() throws IOException{
        BufferedWriter bufferWriter = new BufferedWriter(new FileWriter("convert.tgf", true));
        String str;
        LinkedList<Object> keys = new LinkedList<Object>();
        keys = dicKeys.keys();
        
        for (Object object : keys) {
            str = dicKeys.findElement(object).toString()+" "+ object+"\n";
            bufferWriter.write(str);
        }
        
        bufferWriter.write("# \n");
        keys = dicElements.keys();
        
        for (Object elem : keys) {
            ArrayList<Integer> lista = (ArrayList<Integer>) dicElements.findElement(elem);
            for (Integer i : lista) {
                str = dicKeys.findElement(elem).toString()+" "+ i.toString()+"\n";
                bufferWriter.write(str);
            }  
        }
        System.out.println("ESCRITO!");
        bufferWriter.close();

    }
    
    public void converte(String nomearq) throws FileNotFoundException, IOException{
        File arq = new File (nomearq);
        Scanner scanner = new Scanner(arq);
        String linha;
        IDMovies = new ActorsAndMovies(null);
        
        while(scanner.hasNextLine()) {  
            linha = scanner.nextLine(); 
            String[] lista = linha.split("/");    
            for (int i = 0; i < lista.length; i++){ 
                Integer intAux = (Integer) dicKeys.findElement(lista[i]);
                if(dicKeys.NO_SUCH_KEY()){
                    dicKeys.insertItem(lista[i], geraIDVertex()); 
                }
                if (!lista[i].isEmpty()){
                    if(isNumerico(lista[i])){  // Caso seja ator
                        IDMovies.getLst().add(dicKeys.findElement(lista[i])); 
                    }
                    else{ //Caso seja filme 
                        
                        if(!IDMovies.getLst().isEmpty()){
                            dicElements.insertItem(IDMovies.getName(), IDMovies.getLst()); 
                            IDMovies = new ActorsAndMovies(null);
                        }    
                        IDMovies.setName(lista[i]); //Renomeia a lista de relacionamentos para o novo filme encontrado;      
            
                    }    
                         // Adiciona o relacionamento filme_atores no dicionario de relacionamentos                       
                }
                else {
                    System.out.println("Vazio");
                }          
            }            
        }
        dicElements.insertItem(IDMovies.getName(), IDMovies.getLst());  
       scanner.close();
        
    }

    
    
    private int geraIDVertex(){
        int id = geraID++;
        return id;
    }
    
        
  //retorna false caso a string possua algum caracter numérico.
    public boolean isNumerico(String s){
        String o = "a a (a) 2";
        
        for (int i = 0; i < s.length(); i++) {
          if (Character.isDigit(s.charAt(i))==true)
          {
              return false;
          }
        }
        
        return true;
    }

}
