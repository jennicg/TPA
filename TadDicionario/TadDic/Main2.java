package TadDic;

public class Main2 {
	public static void main (String[] args) {
		TadDicChain dic = new TadDicChain (256);
		System.out.println("O dicionario esta vazio ?" + dic.isEmpty());
		System.out.println ("Avengers");
		dic.insertItem("jennifer", new EstudanteBsi("jennifer","20162",20));
		dic.insertItem("refinnej", new EstudanteBsi("gabrielle","1602",86));
		dic.insertItem("eduarda", new EstudanteBsi("eduarda","2210",19));
		dic.insertItem("Larissa", new EstudanteBsi("Larissa","2812",17));
		dic.insertItem("Yasmim", new EstudanteBsi("Yasmim","0803",22));

		System.out.println("COLIS�ES " + dic.getColisoes());
		



		dic.insertItem("jennifer", new EstudanteBsi("jennifer","2304",21));
		System.out.println("AS CHAVES S�O: " + dic.keys());
		System.out.println(dic.size());
		
		System.out.println("O dicionario esta vazio ?" + dic.isEmpty());

		dic.imprimeLista();
		dic.removeElement("eduarda");
		EstudanteBsi dado = (EstudanteBsi)dic.findElement("jennifer");		
		if (dado!= null)
			System.out.println(dado.getNome() + ", " + dado.getIdade());
		else
			System.out.println("A chave solicitada n�o existe neste dicion�rio");
		
		dic.imprimeLista();
		
		System.out.println("Chaves: " + dic.keys());
		System.out.println("Entradas" + dic.elements());

		dic.imprimeLista();

	}
}
