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
	public long cod_hash;
	
	public void setCod_Hash(long cod_hash) {
		this.cod_hash = cod_hash;
	}
	
	public long getCod_Hash() {
		return cod_hash;
	}
	    
	    public DicItem (Object chave, Object valor) {
	        this.chave = chave;
	        this.valor = valor;
	       
	    }

}
