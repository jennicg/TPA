package TadDic;

public class EstudanteBsi {
	private String nome;
	private String mat;
	private int idade;
	
	public EstudanteBsi (String nome, String mat, int idade) {
		this.nome = nome;
		this.mat = mat;
		this.idade = idade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMat() {
		return mat;
	}

	public void setMat(String mat) {
		this.mat = mat;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	

}
