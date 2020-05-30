package threads.workshop;


public class Thread3 extends Thread {

	@Override
	public void run() {
		for(int i = 0; i < 10; i ++) {
			System.out.println("THRED 5....");
			try {
				Thread.sleep(3 * 1000);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
