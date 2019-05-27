package taddic;

public class TDicItem {
	  public Object key;
	  public  Object getKey() {
		return key;
	}

	public void setKey(Object key) {
		this.key = key;
	}

	public Object getDado() {
		return dado;
	}

	public void setDado(Object dado) {
		this.dado = dado;
	}

	public Object dado;
	public long cach_hash;
	
	public void setCash_Hash(long cach_hash) {
		this.cach_hash = cach_hash;
	}
	
	public long getCash_Hash() {
		return cach_hash;
	}
	    
	    public TDicItem (Object key, Object dado) {
	        this.key = key;
	        this.dado = dado;
	       
	    }

}
