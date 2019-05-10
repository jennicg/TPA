package HashFunctions;

import TadDic.Hash_engine;

public class BersteinModificado extends Hash_engine{
	public long bersteinM(String s) {
		long h = 0;
		int i;
		for(i = 0; i < s.length(); i++) {
			h = (33 * h) ^(int)s.charAt(i);
		}
		return Math.abs((int)h);
		
		
	}

	@Override
	public long hash_func(Object k) {
		// TODO Auto-generated method stub
		return 0;
	}

}
