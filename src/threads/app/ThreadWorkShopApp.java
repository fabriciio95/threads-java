package threads.app;

import javax.swing.JOptionPane;

import threads.workshop.Thread1;
import threads.workshop.Thread2;
import threads.workshop.Thread3;

public class ThreadWorkShopApp {

	public static void main(String[] args) throws InterruptedException{
		Thread1 thread1 = new Thread1(); //Thread1 implementa Runnable
		Thread t1 = new Thread(thread1);
		t1.start();//Liga a thread que fica processando paralelamente	
		Thread2 thread2 = new Thread2(); //Thread2 implementa Runnable
		Thread t2 = new Thread(thread2);
		t2.start();
		Thread3 t3 = new Thread3(); // //Thread3 extends Thread
		t3.start();
		Runnable run = new Runnable() { //Usando classe anônima
			public void run() {
				for(int i = 0; i < 10; i++) {
					System.out.println("THRED 3...");
					try {
						Thread.sleep(3 * 1000);
					}catch(InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread3 = new Thread(run);
		thread3.start();
		
		new Thread(() -> { // Implementando o método run da interface funcional Runnable através de lambda passada no construtor da classe Thread
			for(int i = 0; i < 10; i ++) {
				System.out.println("THERED 4...");
				try {
					Thread.sleep(3 * 1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();;
		
		Runnable run2 = () -> { //Implementando o método run da interface Runnable através de uma expressão lambda
			for(int i = 0; i < 10; i ++) {
				System.out.println("THREAD 9...");
				try{
					Thread.sleep(3 * 1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread thread = new Thread(run2);
		thread.start();
		
		//Fim do código em paralelo
		//Código do sistema do usuário chegou ao fim, mas o processamento continua em parelelo
		System.out.println("CHEGOU AO FIM DO CÓDIGO DE TESTE DE THREAD");
		//Fluxo do sistema, cadastro de venda, uma emissão de nota fiscal ou algo do tipo
		JOptionPane.showMessageDialog(null, "Sistema continua executando para o usuário");
		
	}

}
