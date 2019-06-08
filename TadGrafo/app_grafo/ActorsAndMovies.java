package app_grafo;

import java.util.ArrayList;

public class ActorsAndMovies {
	public String name;
	public ArrayList lst;
	
	public ActorsAndMovies(String name) {
		this.name = name;
		lst = new ArrayList();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList getLst() {
		return lst;
	}
	public void setLst(ArrayList lst) {
		this.lst = lst;
	}
	
	

}
 