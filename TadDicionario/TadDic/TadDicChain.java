package TadDic;

import java.util.LinkedList;

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
	
	public TadDicChain(int quant_entradas) {
		// TODO Auto-generated constructor stub
		int tam = (int)(quant_entradas/fator_carga);
		vetBuckets = new LinkedList [tam];
		
		for(int i = 0; i< tam; i++) {
			vetBuckets[i] = new LinkedList <DicItem>(); // DicItem
			
		}
	}
	
	public TadDicChain () {
		int tam = 100;
		vetBuckets = new LinkedList[tam];
		
		for(int i=0;i <tam;i++)
			vetBuckets[i] = new LinkedList<DicItem>();
	}
	
	public int getTamVetBuckets() {
		return vetBuckets.length;
	}
	
	// Funçao para converter para hash
	private long hash_func (String txt) {
		long soma = 0;
		
		for(int i = 0; i < txt.length(); i++)
			soma = soma + (int)txt.charAt(i);
		return soma;	
	}
	
	private int buscaItem (LinkedList<DicItem> lst, String k) {
		int pos = 0;
		while (pos < lst.size()) {
			if(((DicItem)(lst.get(pos))).getChave().equals(k))
				return pos;
			pos++;
		}
		return -1;
	}
	
	
	public void insertItem(String k, Object e) {
		Object aux = findElement(k);
		long cod_hash = hash_func(k);
		int posList =0;
		int indice = (int)cod_hash % vetBuckets.length; // utilizar o resto para não exceder o tamanho
		if (aux == null) {
			vetBuckets[indice].add(new DicItem(k,e));
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

	
	public Object removeElement(String k) {
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
	
	public Object findElement (String k) {
		long cod_hash = hash_func(k);
		int indice = (int)cod_hash % vetBuckets.length;
		
		int posList =0;
		while(posList < vetBuckets[indice].size()) {
			if (((DicItem)(vetBuckets[indice].get(posList))).getChave().equalsIgnoreCase(k))
				return ((DicItem)vetBuckets[indice].get(posList)).getValor();
			posList++;		
		}
		
		return null;
		
	}
	

	public boolean isEmpty() {
		return (quant_entradas == 0);
		}
	
	
	public LinkedList<String> keys(){
		int posItem = 0;
		if(isEmpty())
			return null;
		LinkedList<String> entradas = new LinkedList<String>();		
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