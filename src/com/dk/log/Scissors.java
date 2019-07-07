package com.dk.log;

import java.io.*;
import java.util.concurrent.BlockingQueue;

/**
 * Created by dev on 19. 7. 5..
 */
public abstract class Scissors implements Runnable{
	Reader reader;
	int skipLineNum = 0;
	private BlockingQueue<String> queue;
	protected Scissors(Reader reader, int skipLineNum, BlockingQueue<String> queue) {
		this.reader = reader;
		this.skipLineNum = skipLineNum;
		this.queue = queue;
	}
	@Override
	public void run() {

		BufferedReader br = null;
		try {
			br = new BufferedReader(reader);

			String logLine;
			for(int i=0; i<skipLineNum;i++) {
				br.readLine();
			}

			while(!Thread.currentThread().isInterrupted()) {
				logLine = br.readLine();
				if(logLine == null) {
					break;
				} else {
					queue.put(logLine);
//					System.out.println("0000 :: " + logLine);
					skipLineNum++;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
			ScissorsResult sResult = new ScissorsResult();
			sResult.setSkipNum(skipLineNum);
			registResult(sResult);
		} catch (InterruptedException e) {
			e.printStackTrace();
			ScissorsResult sResult = new ScissorsResult();
			sResult.setSkipNum(skipLineNum);
			registResult(sResult);
			Thread.currentThread().interrupt();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	protected abstract void registResult(ScissorsResult sResult);
}