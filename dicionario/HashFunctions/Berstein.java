package HashFunctions;

public class Berstein {
	public long berstein(String s) {
		long h = 0;
		int i;
		for(i = 0; i < s.length(); i++) {
			h = 33 * h + (int)s.charAt(i);
		}
		return Math.abs((int)h);
	}

}
