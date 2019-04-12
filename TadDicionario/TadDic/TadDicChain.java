package TadDic;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.LinkedList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author jenni
 *
 */

public class TadDicChain {

	/**
	 * 
	 */

	private LinkedList [] vetBuckets;
	private double fator_carga = 0.75;
	private int quant_entradas = 0;
	private Hash_engine he = null;
	
	// VARIAVEIS GLOBAIS PARA FUNÇÃO HASH FNV
	private static final long FNV_64_INIT = 0xcbf29ce484222325L;
    private static final long FNV_64_PRIME = 0x100000001b3L;

    private static final int FNV_32_INIT = 0x811c9dc5;
    private static final int FNV_32_PRIME = 0x01000193;
   // ----------------------- fim do uso das VG
	
	public TadDicChain(int quant_entradas) {
		// TODO Auto-generated constructor stub
		int tam = (int)(quant_entradas/fator_carga);
		vetBuckets = new LinkedList [tam];
		
		for(int i = 0; i< tam; i++) {
			vetBuckets[i] = new LinkedList <DicItem>(); // DicItem
			
		he = new HashEngineDefault();
			
		}
	}
	
	public TadDicChain(Hash_engine he) {
		// TODO Auto-generated constructor stub
		int tam = (int)(quant_entradas/fator_carga);
		vetBuckets = new LinkedList [tam];
		
		for(int i = 0; i< tam; i++) {
			vetBuckets[i] = new LinkedList <DicItem>(); // DicItem
		
		if(he==null)
			he = new HashEngineDefault();
		else
			this.he = he;
			
		}
	}
	
	public TadDicChain () {
		int tam = 100;
		vetBuckets = new LinkedList[tam];
		
		for(int i=0;i <tam;i++)
			vetBuckets[i] = new LinkedList<DicItem>();
		
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
	
	private long Hash_poli(String str ){
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
	

	private long fnv_hash (String s) {
		BigInteger h = new BigInteger ("216613621");
		BigInteger a = new BigInteger ("16777619");
		
		for(int i = 0; i < s.length(); i++) {
			String bigIchar_i = String.valueOf((int)s.charAt(i));
			h = h.multiply(a).xor(new BigInteger (bigIchar_i));
			
			
		}
		
		return Math.abs(h.longValue());
		
	}

   
	private long berstein(String s) {
		long h = 0;
		int i;
		for(i = 0; i < s.length(); i++) {
			h = 33 * h + (int)s.charAt(i);
		}
		return Math.abs((int)h);
	}
	
	private long bersteinM(String s) {
		long h = 0;
		int i;
		for(i = 0; i < s.length(); i++) {
			h = (33 * h) ^(int)s.charAt(i);
		}
		return Math.abs((int)h);
		
		
	}
	
	
	
	private int buscaItem (LinkedList<DicItem> lst, Object k) {
		int pos = 0;
		while (pos < lst.size()) {
			if(((DicItem)(lst.get(pos))).getChave().equals(k))
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
	
	private void resize () {
		int novoTam = 2 * vetBuckets.length;
		LinkedList[] novoVetBuckets = new LinkedList[novoTam];
		
		for(int i = 0; i < novoTam; i++) {
			novoVetBuckets[i] = new LinkedList<DicItem>();
		}
			
			//Comecar a mudanca de entrada do old vetBuckets
			for(int i = 0; i < vetBuckets.length; i++) {
				if(vetBuckets[i] != null) {
					for (int k = 0; k < vetBuckets[i].size(); k++) {
						Object aux = (DicItem)vetBuckets[i].get(k);
						long cod_hash = ((DicItem)aux).getCod_Hash();
						int indice = (int)cod_hash % novoVetBuckets.length;
						novoVetBuckets[indice].add(aux);
				}
			}
			
		}
		vetBuckets = novoVetBuckets;
	}
	
	
	
	public void insertItem(Object k, Object e) {
		
		if(lenMaiorLst() >= (int)vetBuckets.length * 0.3) {
			resize();
		}
		Object aux = findElement(k);
		long cod_hash = hash_func(k);
		int posList =0;
		int indice = (int)cod_hash % vetBuckets.length; // utilizar o resto para não exceder o tamanho
		if (aux == null) {
			DicItem dicItem = new DicItem(k,e);
			dicItem.setCod_Hash(cod_hash);
			vetBuckets[indice].add(dicItem);
			quant_entradas++;
		}
		else {
			int pos = buscaItem(vetBuckets[indice],k);
			if (pos!= -1)
			while(posList < vetBuckets[pos].size())
				((DicItem)vetBuckets[pos].get(posList)).setValor(e);
				posList++;
		}

	}
	
	public int size() {
		return quant_entradas;
	}

	
	public Object removeElement(Object k) {
		Object aux = findElement(k);
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
	
	public Object findElement (Object k) {
		long cod_hash = hash_func(k);
		int indice = (int)cod_hash % vetBuckets.length;
		
		int posList =0;
		while(posList < vetBuckets[indice].size()) {
			if (((DicItem)(vetBuckets[indice].get(posList))).getChave().equals(k))
				return ((DicItem)vetBuckets[indice].get(posList)).getValor();
			posList++;		
		}
		
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
				entradas.add(((DicItem)(vetBuckets[i].get(posItem))).getChave());
				posItem++;
			}
		}
		return entradas;
	}
	
	public LinkedList<Object> elements(){
		int posItem = 0;
		if(isEmpty())
			return null;
		LinkedList<Object> entradas = new LinkedList<Object>();
		for(int i = 0; i < vetBuckets.length; i++) {
			posItem = 0;
			while (posItem < vetBuckets[i].size()) {
				entradas.add(((DicItem)(vetBuckets[i].get(posItem))).getValor());
				posItem++;
			}
		}
		return entradas;
	}
	
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
		
		

	public int[] getColisoes() {
			int[] colisoes = new int[vetBuckets.length];
			int pos = 0;
			while( pos< this.vetBuckets.length) {
				if(vetBuckets[pos].size() > 1) {
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
				if((DicItem)vetBuckets[i].get(posItem) != null) {
					System.out.print(((DicItem)(vetBuckets[i].get(posItem))).getChave() + ", ");
					System.out.println(((DicItem)(vetBuckets[i].get(posItem))).getValor() + ", ");
					System.out.println(i + ", " + posItem);
				}
				posItem++;
			}	 
		}		
	}

	
	
}