package taddic;

import java.math.BigInteger;

public class Hash_Algoritmos {
	public long fnv_hash (String s) {
		BigInteger h = new BigInteger ("216613621");
		BigInteger a = new BigInteger ("16777619");
		
		for(int i = 0; i < s.length(); i++) {
			String bigIchar_i = String.valueOf((int)s.charAt(i));
			h = h.multiply(a).xor(new BigInteger (bigIchar_i));
			
			
		}
		
		return Math.abs(h.longValue());
		
	}

   
	public long berstein(String s) {
		long h = 0;
		int i;
		for(i = 0; i < s.length(); i++) {
			h = 33 * h + (int)s.charAt(i);
		}
		return Math.abs((int)h);
	}
	
	public long bersteinM(String s) {
		long h = 0;
		int i;
		for(i = 0; i < s.length(); i++) {
			h = (33 * h) ^(int)s.charAt(i);
		}
		return Math.abs((int)h);
		
		
	}
	

}
