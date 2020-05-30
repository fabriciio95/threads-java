package threads.exerciciopratico;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Cliente {

	private Integer idCliente;
	private String nome;
	private String email;
	private ConcurrentLinkedQueue<Produto> produtos = new ConcurrentLinkedQueue<>();
	
	
	
	public Cliente() {
	}

	public Cliente(Integer idCliente, String nome, String email) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.email = email;
	}
	
	public Cliente(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}


	public ConcurrentLinkedQueue<Produto> getProdutos() {
		return produtos;
	}
	
	public void addProduto(Produto produto) {
		produtos.add(produto);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCliente == null) ? 0 : idCliente.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (idCliente == null) {
			if (other.idCliente != null)
				return false;
		} else if (!idCliente.equals(other.idCliente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", email=" + email + "]";
	}
	
}
