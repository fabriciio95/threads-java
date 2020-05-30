package threads.exerciciopratico;

import java.util.ArrayList;
import java.util.List;

public class Produto {
	
	private Integer idProduto;
	private String nome;
	private Double valorProduto;
	private List<Cliente> clientes = new ArrayList<>();
	
	public Produto() {
	}

	public Produto(Integer idProduto, String nome, Double valorProduto) {
		super();
		this.idProduto = idProduto;
		this.nome = nome;
		this.valorProduto = valorProduto;
	}

	public Integer getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorProduto() {
		return valorProduto;
	}

	public void setValorProduto(Double valorProduto) {
		this.valorProduto = valorProduto;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProduto == null) ? 0 : idProduto.hashCode());
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
		Produto other = (Produto) obj;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", nome=" + nome + ", valorProduto=" + valorProduto + ", clientes="
				+ clientes + "]";
	}
}
