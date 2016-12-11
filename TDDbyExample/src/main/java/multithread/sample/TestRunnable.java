package multithread.sample;

//public class TestRunnable implements Runnable{
public class TestRunnable extends Thread{

	public boolean isComplete = false;
	//public boolean isRunning = true;
	public void run() {
		System.out.println(Thread.currentThread().getName()+ " started:");
		try {
			int i=0;
			while (!interrupted()) {
				System.out.print(".");
				Thread.sleep(200);
				i++;
				if (i>20) {
					isComplete = true;
					break;
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("\n subthread:terminated!");
			isComplete = false;
			return;
		}
		System.out.print("\nsubthread:completed!");
	}
	
	public static void main(String[] args) throws InterruptedException{
		TestRunnable tr = new TestRunnable();
		tr.setDaemon(true);
		Thread thread = new Thread(tr,"subthread");
		thread.start();
		for (int i=0; i<10; i++){
			if (tr.isComplete) break;
			Thread.sleep(100);
		}
		if (tr.isComplete)
			System.out.println("\nmain thread: complete!\n");
		else {
			System.out.print("\nmain thread: timeout!\n");
			tr.interrupt();
			//tr.isRunning = false;
		}
		//return;
	}	
}
