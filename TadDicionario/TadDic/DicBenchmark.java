package TadDic;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import _my_tools.*;

class RegMD {
	private String cpf;
	private String nome;
	private String end;
	private String cel;
	
	public RegMD(String cpf, String nome, String end, String cel) {
		this.cpf = cpf;
		this.nome = nome;
		this.end = end;
		this.cel = cel;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getCel() {
		return cel;
	}
	public void setCel(String cel) {
		this.cel = cel;
	}
	
	public boolean equals(RegMD r) {
		return (cpf == r.getCpf()) && (nome == r.getNome()) && (end == r.getEnd()) && (cel == r.getCel());
	}
} /* fim RegMD */

public class DicBenchmark {
	public static void main(String[] args) {
		int TAM_TESTE = 50000;
		TADDicChain dicA = new TADDicChain();
		FileReader arqIn = new FileReader("C:\\Users\\jenny\\OneDrive\\Documentos\\Ed\\maladireta.csv");
		
		HashMap<Object, Object> hm = new HashMap<Object, Object>();
		
		/* Iniciando o benchmark da classe TADDicChain.  */
		System.out.println("Iniciando o benchmark da classe TADDicChain:");
		
		/* Povoando o dicion�rio e HashMap Java. */
		System.out.println("Preenchendo o dicion�rio com " + TAM_TESTE + " entradas a partir do conte�do de maladireta.csv.");
		String linha = arqIn.readline();
		while(linha != null) {
			String[] lst = linha.split(";");
			RegMD dado = new RegMD(lst[0],lst[1],lst[2],lst[3]);
			RegMD dadoHM = new RegMD(lst[0],lst[1],lst[2],lst[3]);
			
			dicA.insertItem(dado.getCpf(),dado);
			hm.put(dadoHM.getCpf(),dadoHM);
			
			linha = arqIn.readline();			
		}
		arqIn.close();
		
		System.out.println("preenchimento conclu�do com sucesso!");
		
		if(dicA.size() == TAM_TESTE)
			System.out.println("Confirmado, dicion�rio A cont�m " + TAM_TESTE + " entradas.");
		else {
			System.out.println("Problemas!, dicion�rio N�O foi povoado corretamente.");
			System.exit(0);
		}
		
		/* Transferindo elementos entre dicion�rios. */
		System.out.println("\nTestando a transfer�ncia de entradas entre dicion�rios..");
		TADDicChain dicB = new TADDicChain();
		
		System.out.println("  Antes da transfer�ncia: quantidade de entradas do dicion�rio A: " + dicA.size());
		System.out.println("  Antes da transfer�ncia: quantidade de entradas do dicion�rio B: " + dicB.size());
		System.out.println("  Antes da transfer�ncia: tamanho do vetor de buckets do dicion�rio A: " + dicA.getSizeVetBuckets());
		System.out.println("  Antes da transfer�ncia: tamanho do vetor de buckets do dicion�rio B: " + dicB.getSizeVetBuckets());
		
		LinkedList<Object> lstKs = dicA.keys();
		
		int i = 0;
		while(dicA.size() > 0) {
			RegMD dado = (RegMD)dicA.removeElement(lstKs.get(i));
			if(dicA.NO_SUCH_KEY()) {
				System.out.println("**Problemas!\nFalha na remo��o da chave " +  lstKs.get(i).toString() + " do dicion�rio, abortando o benchmark.");
				System.exit(0);
			}
			dicB.insertItem(lstKs.get(i),dado);	
			i = i + 1;
		}
		
		if((dicA.isEmpty()) && (dicB.size() == TAM_TESTE)) {
			System.out.println(" >Entradas transferidos com sucesso!");
			System.out.println("  Ap�s a transfer�ncia: quantidade de entradas do dicion�rio A: " + dicA.size());
			System.out.println("  Ap�s a transfer�ncia: quantidade de entradas do dicion�rio B: " + dicB.size());
			System.out.println("  Ap�s a transfer�ncia: tamanho do vetor de buckets do dicion�rio A: " + dicA.getSizeVetBuckets());
			System.out.println("  Ap�s a transfer�ncia: tamanho do vetor de buckets do dicion�rio B: " + dicB.getSizeVetBuckets());
		}
		else {
			System.out.print("**Problemas!\nTransfr�ncia de entradas entre dicion�rios falhou!.");
			System.exit(0);
		}	
		
		/* Testando a clonagem e a igualdade. */
		System.out.print("\nClonando o dicion�rio B..");
		TADDicChain dicC = dicB.clone();
		
		if(dicC.equals(dicB)) {
			System.out.println("clonagem concluida com �xito!");			
			System.out.println("  Ap�s a clonagem: quantidade de entradas do dicion�rio (C)lone: " + dicC.size());
			System.out.println("  Ap�s a clonagem: tamanho do vetor de buckets do dicion�rio (C)lone: " + dicC.getSizeVetBuckets());			
		}
		else
			System.out.println("**Erro! problema no processo de clonagem ou na verifica��o de igualdade.");
		
		System.out.print("\nTestando a integridade chave/conte�do..");
		
        /* Obtem um conjunto de entradas do HashMap (chaves e dados). */
        Set set = hm.entrySet();
      
        /* Constr�i um iterator para percorrer o conjunto. */
        Iterator it = set.iterator();
      
        /* Para cada chave e dado extra�do do HashMap, verifica se a chave obt�m o memso dado do TADDicChain. */
        int n = 0;
        while(it.hasNext()) {
           Map.Entry me = (Map.Entry)it.next();
          
           String k = (String)me.getKey();
           RegMD dadoHM = (RegMD)me.getValue();          
           RegMD dadoTAD = (RegMD)dicC.findElement(k);          
           
		   if(dicC.NO_SUCH_KEY() || !dadoTAD.equals(dadoHM)) {
			   System.out.println("**Problemas!\nFalha na rela��o chave/dado " +  k + "/" + dadoHM.getNome() + " do dicion�rio, abortando o benchmark.");
			   System.exit(0);
		   }		   
		   n++;
        } /* while(it.. */
       
        System.out.println("teste conclu�do com sucesso.");
        System.out.println(n + " rela��es chaves/conte�dos verificadas corretamente!");
		
		System.out.println("\nBenchmark conclu�do!");
		
		System.out.println("\nOs seguinte m�todos foram testados:");
		System.out.println(" .insertItem(k,e);");
		System.out.println(" .findElement(k);");
		System.out.println(" .removeElement(k);");
		System.out.println(" .redimensiona();");
		System.out.println(" .isEmpty();");
		System.out.println(" .size();");
		System.out.println(" .NO_SUCH_KEY();");
		System.out.println(" .keys();");
		System.out.println(" .elements();");
		System.out.println(" .clone();");
		System.out.println(" .equals();");		
	} // fim main
} // fim classe DicBenchmark
