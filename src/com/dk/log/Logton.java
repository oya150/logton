package com.dk.log;

import com.dk.log.*;
import com.dk.log.file.FileClassify;
import com.dk.log.file.FileViewer;

import java.io.*;
import java.util.concurrent.*;

public class Logton {
	private static final int THREAD_CNT = 3;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_CNT);

//	private static final ConcurrentMap<String, > queue = new ConcurrentHashMap();

	public static void main(String[] args) {

		try {
			File rootPath = new File(".");
			System.out.println(rootPath.getAbsolutePath());
			String classPath = Logton.class.getResource("").getPath();
			String fileName = "input.log";
			String path = classPath + fileName;

			QueueFactory queueFactory = new QueueFactory();
			BlockingQueue<String> queue = queueFactory.createQueue(path);

			ScissorsFactory scissorsFactory = new ScissorsFactory();
			Scissors scissors = scissorsFactory.createScissors("file", path,queue);
			threadPool.execute(scissors);
			FileViewer viewer = new FileViewer("output.log");
			Classify classify = new FileClassify(queue, viewer);
			threadPool.execute(classify);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
