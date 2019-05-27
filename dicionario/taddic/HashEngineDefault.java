package taddic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class HashEngineDefault extends Hash_engine {
	public long hash_func (Object k) {
		long soma = 0;
		//Converte o objeto chave k em um array de bytes
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		byte [] vetBytes = null;
		try {
			try {
				out = new ObjectOutputStream(bos);
				out.writeObject(k);
				out.flush();
				vetBytes = bos.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
				}
			}
			finally {
				try {
					bos.close();
				} catch (IOException e) {
					
				}
			
		}
		
		for (int i = 0; i < vetBytes.length; i++)
			soma = soma + (int)vetBytes[i];
		return soma;
		
	}

}
