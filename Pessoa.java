
import java.io.*;

public class Pessoa implements Serializable{
	private String nome;
	private int identidade;
	private String nascimento;
	
	public Pessoa (String n, int i, String d){
		nome = n;
		identidade = i;
		nascimento = d;
				
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdentidade() {
		return identidade;
	}

	public void setIdentidade(int identidade) {
		this.identidade = identidade;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public String toString() {
		return "Pessoa [Nome = " + nome + ", Identidade = " + identidade
				+ ", Nascimento = " + nascimento + "]";
	}

}
