package taddic;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.LinkedList;



/**
 * @author jenni
 *
 */

public class TADDicChain {

	/**
	 * 
	 */

	private LinkedList [] vetBuckets;
	private double fator_carga = 0.75;
	private int quant_entradas = 0;
	private Hash_engine he = null;
	private boolean achou =  false;
	

	
	public TADDicChain(int quant_entradas) {
		// TODO Auto-generated constructor stub
		int tam = (int)(quant_entradas/fator_carga);
		vetBuckets = new LinkedList [tam];
		
		for(int i = 0; i< tam; i++) {
			vetBuckets[i] = new LinkedList <TDicItem>(); // DicItem
			
		he = new HashEngineDefault();
			
		}
	}
	
	public TADDicChain(Hash_engine he) {
		// TODO Auto-generated constructor stub
		int tam = 256;
		vetBuckets = new LinkedList [tam];
		
		for(int i = 0; i< tam; i++) {
			vetBuckets[i] = new LinkedList <TDicItem>(); // DicItem
		
		if(he==null)
			he = new HashEngineDefault();
		else
			this.he = he;
			
		}
	}
	
	public TADDicChain () {
		int tam = 100;
		vetBuckets = new LinkedList[tam];
		
		for(int i=0;i <tam;i++)
			vetBuckets[i] = new LinkedList<TDicItem>();
		
		he = new HashEngineDefault();
	}
	
	public int getTamVetBuckets() {
		return vetBuckets.length;
	}
	
	
	private long hash_func (Object k) {
		long soma = 0;
		//Converte o objeto chave k em um array de bytes
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		byte [] vetBytes = null;
		try {
			try {
				out = new ObjectOutputStream(bos);
				out.writeObject(k);
				out.flush();
				vetBytes = bos.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
				}
			}
			finally {
				try {
					bos.close();
				} catch (IOException e) {
					
				}
			
		}
		
		for (int i = 0; i < vetBytes.length; i++)
			soma = soma + (int)vetBytes[i];
		return soma;
		
	}
	


	
	
	
	private int buscaItem (LinkedList<TDicItem> lst, Object k) {
		int pos = 0;
		while (pos < lst.size()) {
			if(((TDicItem)(lst.get(pos))).getKey().equals(k))
				return pos;
			pos++;
		}
		return -1;
	}
	
	private int lenMaiorLst() {
		int maior = 0;
		for(int i = 0; i < vetBuckets.length; i++) {
			if(vetBuckets[i] != null)
				if (vetBuckets[i].size() > maior)
					maior = vetBuckets[i].size();
		}
		return maior;
	}
	
	private void redimensiona () {
		int novoTam = 2 * vetBuckets.length;
		LinkedList[] novoVetBuckets = new LinkedList[novoTam];
		
		for(int i = 0; i < novoTam; i++) {
			novoVetBuckets[i] = new LinkedList<TDicItem>();
		}
			
			//Comecar a mudanca de entrada do old vetBuckets
			for(int i = 0; i < vetBuckets.length; i++) {
				if(vetBuckets[i] != null) {
					for (int k = 0; k < vetBuckets[i].size(); k++) {
						Object aux = (TDicItem)vetBuckets[i].get(k);
						long cod_hash = ((TDicItem)aux).getCash_Hash();
						int indice = (int)cod_hash % novoVetBuckets.length;
						novoVetBuckets[indice].add(aux);
				}
			}
			
		}
		vetBuckets = novoVetBuckets;
	}
	
	
	/*
	public void insertItem(Object k, Object e) {
		
		if(lenMaiorLst() >= (int)vetBuckets.length * 0.3) {
			redimensiona();
		}
		Object aux = findElement(k);
		long cod_hash = hash_func(k);
		int posList =0;
		int indice = (int)cod_hash % vetBuckets.length; // utilizar o resto para não exceder o tamanho
		if (aux == null) {
			TDicItem dicItem = new TDicItem(k,e);
			dicItem.setCod_Hash(cod_hash);
			vetBuckets[indice].add(dicItem);
			achou = true;
			quant_entradas++;
		}
		else {
			int pos = buscaItem(vetBuckets[indice],k);
			if (pos!= -1)
			while(posList < vetBuckets[pos].size())
				((TDicItem)vetBuckets[pos].get(posList)).setValor(e);
				posList++;
				achou = true;	
		}

	}
	*/
	
	public void insertItem(Object chave, Object valor) {
        if(lenMaiorLst() >= (int)getSizeVetBuckets() * 0.30) {
            
            redimensiona();
           
        }
        
      
        long cod_hash = hash_func(chave);
		int indice = (int)cod_hash % vetBuckets.length; 
        
        if(NO_SUCH_KEY()) {
            TDicItem dicItem = new TDicItem(chave, valor);
            dicItem.setCash_Hash(cod_hash);
            vetBuckets[indice].add(dicItem);
            quant_entradas++;
        }
        else {
            int pos = buscaItem(vetBuckets[indice], chave);
            if(pos != -1) {
                ((TDicItem)(vetBuckets[indice].get(pos))).setDado(valor);
            }
        } 
    }
	
	public int size() {
		return quant_entradas;
	}

	
	public Object removeElement(Object k) {
		Object aux = findElement(k);
		if(NO_SUCH_KEY()) {
			achou = false;
			return null;
		}
		 long cod_hash = hash_func(k);
		 int indice = (int)cod_hash % vetBuckets.length; // utilizar o resto para não exceder o tamanho	
			if (aux == null)
				return null;
			else {
				int pos = buscaItem(vetBuckets[indice],k);
				vetBuckets[indice].remove(pos);
				quant_entradas--;	
			}
			return aux;
	    }
	
	/*
	public Object findElement (Object k) {
		long cod_hash = hash_func(k);
		int indice = (int)cod_hash % vetBuckets.length;
		
		int posList =0;
		while(posList < vetBuckets[indice].size()) {
			if (((TDicItem)(vetBuckets[indice].get(posList))).getChave().equals(k))
				return ((TDicItem)vetBuckets[indice].get(posList)).getValor();
			posList++;		
		}
		
		return null;
		
	}
	*/
	
	 public Object findElement(Object chave) {
		 long cod_hash = hash_func(chave);
		int indice = (int)cod_hash % vetBuckets.length;
       
       int posList = 0;
       while(posList < vetBuckets[indice].size()) {
           if(((TDicItem)vetBuckets[indice].get(posList)).getKey().equals(chave)) {
               achou = true;
               return ((TDicItem)(vetBuckets[indice].get(posList))).getDado();
           }
               
           posList++;
       }
       
       achou = false;
       return null;
   }
	

	public boolean isEmpty() {
		return (quant_entradas == 0);
		}
	
	 
	public LinkedList<Object> keys(){
		int posItem = 0;
		if(isEmpty())
			return null;
		LinkedList<Object> entradas = new LinkedList<Object>();		
		for(int i = 0; i < vetBuckets.length; i++) {
			posItem = 0;
			while (posItem < vetBuckets[i].size()) {
				entradas.add(((TDicItem)(vetBuckets[i].get(posItem))).getKey());
				posItem++;
			}
		}
		return entradas;
	}
	
	public boolean NO_SUCH_KEY() {
		return !achou;
	}
	
	public int getSizeVetBuckets() {
		return vetBuckets.length;
	}
		public long Hash_poli(String str ){
	    long total=0;
	    double mathIt=0;
	    int size = str.length();
	    for(int j = 0 ; j< size ; j++){
	        double coef =  (double) str.charAt(j); 
	        double base = (int) Math.pow(31, (size-j))*coef;
	        mathIt = mathIt + base  ;      

	        }
	    total =(int) mathIt %vetBuckets.length;
	    return total; 

	}
	
	public TADDicChain clone() {
		TADDicChain dicClone = new TADDicChain(he);
		
		for(int i = 0; i < vetBuckets.length; i++) {
			for(int k = 0; k < vetBuckets[i].size();k++) {
				Object chave = ((TDicItem)vetBuckets[i].get(k)).getKey();
				Object dado = ((TDicItem)vetBuckets[i].get(k)).getDado();
				dicClone.insertItem(chave, dado);
				
			}
		}
		return dicClone;
	}
	
	public boolean equals(TADDicChain outroDic) {
		if(this.size() == outroDic.size()) {
			for(int i = 0; i <vetBuckets.length;i++) {
				for(int k = 0; k < vetBuckets[i].size();k++) {
					Object chave = ((TDicItem)vetBuckets[i].get(k)).getKey();
					Object dado = ((TDicItem)vetBuckets[i].get(k)).getDado();
					
					Object outroDado = outroDic.findElement(chave);
					if(dado!= outroDado)
						return false;
				}
			}
		}
		return true;
		
	}
	
	public LinkedList<Object> elements(){
		int posItem = 0;
		if(isEmpty())
			return null;
		LinkedList<Object> entradas = new LinkedList<Object>();
		for(int i = 0; i < vetBuckets.length; i++) {
			posItem = 0;
			while (posItem < vetBuckets[i].size()) {
				entradas.add(((TDicItem)(vetBuckets[i].get(posItem))).getDado());
				posItem++;
			}
		}
		return entradas;
	}
	
	public LinkedList<Object> getItens(){
		int posItem = 0;
		if(isEmpty())
			return null;
		LinkedList<Object> itens = new LinkedList<Object>();
		for(int i = 0; i < vetBuckets.length; i++) {
			posItem = 0;
			while (posItem < vetBuckets[i].size()) {
				Object chave = ((TDicItem)vetBuckets[i].get(posItem)).getKey();
				if(findElement(chave) != null)
					itens.add(((TDicItem)(vetBuckets[i].get(posItem))).getDado());
				posItem++;
			}
		}
		return itens;
	}
	/*
	
	public void exibeDiagrama (int[] colisoes) {
		colisoes =getColisoes();
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int pos = 0;
		while (pos < colisoes.length) {
			
		dataset.addValue(colisoes[pos], String.valueOf(colisoes[pos]), String.valueOf(pos));
		
		pos++;
		}
		JFreeChart criaGrafico = ChartFactory.createBarChart("Grafico Dicionario", "pos Vetor Buckets", "colisoes", dataset);
		
		try {
			System.out.println("Criando o grafico...");
			OutputStream png = new FileOutputStream("GraficoTeste.png");
			ChartUtilities.writeChartAsPNG(png,criaGrafico,500,400);
			png.close();
		} catch (IOException io) {
			System.out.println("Deu ruim..."+ io.getMessage());

		
		System.out.println("GRAFICO CRIADO!...");

	}
		}
		*/
		
		

	public int[] getColisoes() {
			int[] colisoes = new int[vetBuckets.length];
			int pos = 0;
			while( pos< this.vetBuckets.length) {
				if(vetBuckets[pos].size() >= 1) {
					colisoes[pos] = vetBuckets[pos].size()-1;
				}
				else {
					colisoes[pos] = vetBuckets[pos].size();
				}
					pos++;
			}
			return colisoes;
		}

	
	public void imprimeLista() {
		int posItem = 0;
		for(int i = 0; i < vetBuckets.length; i++) {
			posItem = 0;
			while (posItem < vetBuckets[i].size()) {
				if((TDicItem)vetBuckets[i].get(posItem) != null) {
					System.out.print(((TDicItem)(vetBuckets[i].get(posItem))).getKey() + ", ");
					System.out.println(((TDicItem)(vetBuckets[i].get(posItem))).getDado() + ", ");
					System.out.println(i + ", " + posItem);
				}
				posItem++;
			}	 
		}		
	}

	
	
}