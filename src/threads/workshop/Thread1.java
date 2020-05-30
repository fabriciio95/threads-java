package threads.workshop;

public class Thread1 implements Runnable {

	@Override
	public void run() {
		//Código da rotina que será executado em paralelo
		for(int i = 0; i < 10; i++) {
			
			//Executar esse envio com um tempo de parada, ou com tempo determinado
			System.out.println("Enviando e-mail...");
			
			try {
				Thread.sleep(3000);//Determina o tempo de parada
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
