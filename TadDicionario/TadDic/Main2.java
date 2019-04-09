package TadDic;


public class Main2 {
	public static void main (String[] args) {
	
		TadDicChain dic = new TadDicChain (5);
		System.out.println("O dicionario esta vazio ?" + dic.isEmpty());
		System.out.println ("Avengers");
		dic.insertItem("jennifer", new EstudanteBsi("jennifer","20162",20));
		dic.insertItem("refinnej", new EstudanteBsi("gabrielle","1602",86));
		dic.insertItem("eduarda", new EstudanteBsi("eduarda","2210",19));
		dic.insertItem("Larissa", new EstudanteBsi("Larissa","2812",17));
		dic.insertItem("Yasmim", new EstudanteBsi("Yasmim","0803",22));

		

		dic.imprimeLista();

	}
}
