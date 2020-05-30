package threads.exerciciopratico;

import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JOptionPane;

public class Utils {

	public static Integer gerarIdCliente(ConcurrentLinkedQueue<Cliente> clientes) {
		return clientes.size() + 1;
	}
	
	public static Integer gerarIdProduto(ConcurrentLinkedQueue<Produto> produtos) {
		return produtos.size() + 1;
	}
	
	public static Cliente recuperarClientePorIdOuPorNome(Integer idCliente,String nomeCliente, ConcurrentLinkedQueue<Cliente> clientes, Boolean nomeOuId) {
		Cliente cliente = null;
		if(nomeOuId) {
			for (Cliente cli : clientes) {
				if(cli.getIdCliente().equals(idCliente)) {
					cliente = cli;
				}
			}
		} else {
			for (Cliente cli : clientes) {
				if(cli.getNome().equals(nomeCliente)) {
					cliente = cli;
				}
			}
		}
		return cliente;
	}
	
	public static Double getValorTotalPedido(ConcurrentLinkedQueue<Produto> produtos) {
		Double valorTotal = 0.0;
		for(Produto prod : produtos) {
			valorTotal += prod.getValorProduto();
		}
		return valorTotal;
	}
	
	public static Boolean pagamento(Cliente cliente) {
		Double valorTotalPedido = Utils.getValorTotalPedido(cliente.getProdutos());
		System.out.println("-------------------------------------------------------");
		System.out.println("Fazendo integração com o banco Itaú...");
		JOptionPane.showMessageDialog(null, "Processando Pagamento...");
		for(int i = 0; i < 5; i++) {
			System.out.println("Confirmando pagamento...");
			try {
				Thread.sleep(300);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println(String.format("Pagamento de %.2f confirmado!!!", valorTotalPedido));
		System.out.println("-------------------------------------------------------");
		return true;
	}
}
