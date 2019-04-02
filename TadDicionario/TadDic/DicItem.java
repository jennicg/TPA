package TadDic;

public class DicItem {
	  public Object chave;
	  public  Object getChave() {
		return chave;
	}

	public void setChave(Object chave) {
		this.chave = chave;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public Object valor;
	    
	    public DicItem (Object chave, Object valor) {
	        this.chave = chave;
	        this.valor = valor;
	    }

}
