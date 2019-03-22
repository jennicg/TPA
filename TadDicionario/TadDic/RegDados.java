package TadDic;

public class RegDados {
	
	private String portw;
	private Object englishw;
	
	public RegDados (String portw, Object englishw) {
		this.portw = portw;
		this.englishw = englishw;	
	}
	
	public Object getWeng() {
		return englishw;
	}
	
	public String getWpt() {
		return portw;
	}
	
	public void setWeng(Object englishw) {
		this.englishw =  englishw;
	}
	
	public void setWpt(String portw) {
		this.portw =  portw;
	}
	
	
}
