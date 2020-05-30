package threads.exerciciopratico;

import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadNotifcaGerenteClientesCadastrados extends Thread {

	private static ConcurrentLinkedQueue<Cliente> clientesFila = new ConcurrentLinkedQueue<>();
	
	public void cadastrarCliente(Cliente cliente) {
		boolean cadastrou = clientesFila.add(cliente);
		if(cadastrou) {
			System.out.println("-------------------------------------------------------");
			for(int i = 0; i < 3; i ++) {
				System.out.println("Cadastrando cliente " + cliente.getNome() + " no banco de dados...");
				try {
					Thread.sleep(100);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Cliente "+ cliente.getNome() + " cadastrado com sucesso!");
			System.out.println("-------------------------------------------------------");
		}
	}
	
	public ConcurrentLinkedQueue<Cliente> getClientesFila(){
		return clientesFila;
	}
	
	@Override
	public void run() {
		//while(true) {
			synchronized (clientesFila) {
				Iterator<Cliente> iteratorClientesFila = clientesFila.iterator();
				while(iteratorClientesFila.hasNext()) {
					System.out.println("-------------------------------------------------------");
					Cliente cliente = iteratorClientesFila.next();
					for(int i = 0; i < 3; i ++) {
						System.out.println("Cadastrando cliente " + cliente.getNome() + " no banco de dados...");
						try {
							Thread.sleep(1 * 1000);
						}catch(InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println("Cliente "+ cliente.getNome() + " cadastrado com sucesso!");
					System.out.println("-------------------------------------------------------");
					iteratorClientesFila.remove();
				}
			}
		//}
	}
	
}
