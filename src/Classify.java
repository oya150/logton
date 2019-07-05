import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.concurrent.BlockingQueue;


/**
 * Created by dev on 19. 7. 5..
 */
public abstract class Classify implements Runnable{

	private BlockingQueue<String> queue;

	Classify(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {

			while(!Thread.currentThread().isInterrupted()) {
				String msg = "";
				try {
					msg = queue.take();
					System.out.println("take :::" + msg);
					parse(msg);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
	}

	abstract List<String> parse(String msg);
}
