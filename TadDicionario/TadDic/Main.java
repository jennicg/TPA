package TadDic;

public class Main {

	public static void main (String[] args) {
		TadDicChain dic = new TadDicChain (256);
		System.out.println("O dicionario esta vazio ?" + dic.isEmpty());
		System.out.println ("Avengers");
		dic.insertItem("laranja", new RegDados("laranja","orange"));
		dic.insertItem("maçã", new RegDados("Maçã","apple"));
		dic.insertItem("uva", new RegDados("uva","grape"));
		dic.insertItem("morango", new RegDados("morango","strawberry"));
		dic.insertItem("moranog", new RegDados("morango","strawberry"));
		System.out.println("AS CHAVES SÃOO: " + dic.keys());
	
		System.out.println("Existe colisão ?" + dic.GetColisao());
		
		dic.insertItem("banana", new RegDados("banana","banana"));
		System.out.println(dic.size());
		
		System.out.println("O dicionario esta vazio ?" + dic.isEmpty());

		dic.imprimeLista();
		dic.removeElement("morango");
		RegDados dado = (RegDados)dic.findElement("morango");		
		if (dado!= null)
			System.out.println(dado.getWpt() + ", " + dado.getWeng());
		else
			System.out.println("A chave solicitada não existe neste dicionário");
		
		dic.imprimeLista();
		
		System.out.println("Chaves: " + dic.keys());
		System.out.println("Entradas" + dic.elements());

		dic.imprimeLista();

	}
}
