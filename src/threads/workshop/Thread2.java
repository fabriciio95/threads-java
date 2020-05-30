package threads.workshop;

public class Thread2 implements Runnable{

	@Override
	public void run() {

		for(int i = 0; i < 10; i++) {
			System.out.println("Enviando nota fiscal...");
		
			try {
				Thread.sleep(3 * 1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}
