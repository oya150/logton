import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


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
				}finally {
//					Map<String,Integer> result = serviceMap.entrySet()
//							.stream()
//							.sorted(Map.Entry.<String,Integer>comparingByValue().reversed())
//							.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//									(oldValue, newValue) -> oldValue, LinkedHashMap::new));
				}
			}
	}

	abstract List<String> parse(String msg);


}
