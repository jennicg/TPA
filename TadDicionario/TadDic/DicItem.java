package TadDic;

public class DicItem {
	  public String chave;
	  public String getChave() {
		return chave;
	}

	public void setChave(String chave) {
		this.chave = chave;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public Object valor;
	    
	    public DicItem (String chave, Object valor) {
	        this.chave = chave;
	        this.valor = valor;
	    }

}
