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
	private LinkedList<String> keys;
	
	public TadDicChain(int quant_entradas) {
		// TODO Auto-generated constructor stub
		int tam = (int)(quant_entradas/fator_carga);
		vetBuckets = new LinkedList [tam];
		
		for(int i = 0; i< tam; i++) {
			vetBuckets[i] = new LinkedList <RegDados>(); // DicItem
			
		}
		this.keys = new LinkedList<String>();
	}
	
	public TadDicChain () {
		int tam = 100;
		vetBuckets = new LinkedList[tam];
		
		for(int i=0;i <tam;i++)
			vetBuckets[i] = new LinkedList<RegDados>();
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
	
	public void insertItem(String k, RegDados e) {
		RegDados aux = findElement(k);
		
		if (aux == null) {
			long cod_hash = hash_func(k);
			int indice = (int)cod_hash % vetBuckets.length; // utilizar o resto para não exceder o tamanho
			
			vetBuckets[indice].add(e);
			quant_entradas++;
			this.keys.add(e.getWpt());
		}
		else
			aux.setWeng(e.getWeng());
		
	}
	
	public int size() {
		return quant_entradas;
	}

	
	public RegDados removeElement(String k) {
		RegDados aux = findElement(k);
		 long cod_hash = hash_func(k);
		 int indice = (int)cod_hash % vetBuckets.length; // utilizar o resto para não exceder o tamanho	
		 int posItem = 0;
			for(int i = 0; i < vetBuckets.length; i++) {
				posItem = 0;
				while (posItem < vetBuckets[i].size()) {
					if (findElement(k) != null) {
					 vetBuckets[indice].remove(posItem);
					 quant_entradas--;
					}	 
					posItem++;
				}
			}
			return aux;
	    }
	
	public RegDados findElement (String k) {
		long cod_hash = hash_func(k);
		int indice = (int)cod_hash % vetBuckets.length;
		
		int posList =0;
		while(posList < vetBuckets[indice].size()) {
			if (((RegDados)(vetBuckets[indice].get(posList))).getWpt().equalsIgnoreCase(k))
				return (RegDados)vetBuckets[indice].get(posList);
			posList++;		
		}
		
		return null;
		
	}
	
	public LinkedList<String> keys(){
		return this.keys;
	}

	public boolean isEmpty() {
		return (quant_entradas == 0);
		}
	
	
	public LinkedList<RegDados> entradas(){
		LinkedList<RegDados> entradas = new LinkedList<RegDados>();
		for (LinkedList<RegDados> lista_dados : this.vetBuckets) {
			if(lista_dados.size() > 0 ) {
				for (RegDados info : lista_dados) {
					entradas.add(info);
				}
			}
		}
		
		return entradas;
	}
	
	public LinkedList<Object> valor(){
		LinkedList<Object> entradas = new LinkedList<Object>();
		for (LinkedList<RegDados> lista_dados : this.vetBuckets) {
			if(lista_dados.size() > 0) {
				for(RegDados info : lista_dados) {
					entradas.add(info.getWeng());
				}
			}
		}
		
		return entradas;
	}
	
	public void imprimeLista() {
		int posItem = 0;
		for(int i = 0; i < vetBuckets.length; i++) {
			posItem = 0;
			while (posItem < vetBuckets[i].size()) {
				if((RegDados)vetBuckets[i].get(posItem) != null) {
					System.out.print(((RegDados)(vetBuckets[i].get(posItem))).getWpt() + ", ");
					System.out.println(((RegDados)(vetBuckets[i].get(posItem))).getWeng() + ", ");
					System.out.println(i + ", " + posItem);
				}
				posItem++;
			}	 
		}
		
		
		
	}
	
	
}