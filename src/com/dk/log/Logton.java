package com.dk.log;

import com.dk.log.classify.Classify;
import com.dk.log.factory.ClassifyFactory;
import com.dk.log.factory.QueueFactory;
import com.dk.log.factory.ScissorsFactory;
import com.dk.log.factory.ViewerFactory;
import com.dk.log.scissors.Scissors;
import com.dk.log.view.Viewer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Logton {
	private static final int THREAD_CNT = 2;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_CNT);
	public static Properties applicationProperties = new Properties();
	private static final String LOG_TYPE_FILE = "file";

	public static void main(String[] args){

		try {

			setupProperties();
			String dirPath = System.getProperty("user.dir") + "/";
			String intputFile = "input.log";
			String outputFile = "output.log";
			String intputPath = dirPath + intputFile;
			String outputPath = dirPath + outputFile;

			QueueFactory queueFactory = new QueueFactory();
			BlockingQueue<String> queue = queueFactory.createQueue(intputPath);

			ScissorsFactory scissorsFactory = new ScissorsFactory();
			Scissors scissors = scissorsFactory.createScissors(LOG_TYPE_FILE, intputPath,queue);
			threadPool.execute(scissors);

			ViewerFactory viewerFactory = new ViewerFactory();
			Viewer viewer = viewerFactory.createViewer(LOG_TYPE_FILE, outputPath);

			ClassifyFactory classifyFactory = new ClassifyFactory();
			Classify classify = classifyFactory.createClassify(LOG_TYPE_FILE, queue, viewer);
			threadPool.execute(classify);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static void setupProperties() throws IOException {
		applicationProperties.load(Logton.class.getResourceAsStream("/com/dk/log/config/application.properties"));
	}
}
