import java.io.*;
import java.util.concurrent.*;

public class Logton {
	private static final int THREAD_CNT = 3;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_CNT);
	private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
//	private static final ConcurrentMap<String, > vehicleQueue = new ConcurrentHashMap();

	public static void main(String[] args) {

		try {
			ScissorsFactory scissorsFactory = new ScissorsFactory();
			Scissors scissors = scissorsFactory.createScissors("file", queue);
			threadPool.execute(scissors);
			Classify classify = new FileClassify(queue);
			threadPool.execute(classify);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
