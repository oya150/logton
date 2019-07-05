import java.io.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Logton {
	private static final int THREAD_CNT = 3;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_CNT);
	private static BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);

	public static void main(String[] args) {
		File rootPath = new File(".");
		System.out.println(rootPath.getAbsolutePath());
		String classPath = Logton.class.getResource("").getPath();
		String fileName = "input.log";
		try {
			Scissors scissors = new FileScissors(classPath + fileName, queue);
			threadPool.execute(scissors);
			Classify classify = new FileClassify(queue);
			threadPool.execute(classify);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
