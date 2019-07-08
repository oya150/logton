package com.dk.log.scissors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.BlockingQueue;

/**
 * Created by dev on 19. 7. 5..
 */
public abstract class Scissors implements Runnable{
	Reader reader;
	int offset = 0;
	private BlockingQueue<String> queue;

	protected Scissors(Reader reader, int offset, BlockingQueue<String> queue) {
		this.reader = reader;
		this.offset = offset;
		this.queue = queue;
	}

	@Override
	public void run() {
		BufferedReader br = null;

		try {
			br = new BufferedReader(reader);

			String logLine;
			br.skip(offset);

			while(!Thread.currentThread().isInterrupted()) {

				logLine = br.readLine();
				if(logLine == null) {

					break;

				} else {

					queue.put(logLine);
					offset++;

				}

			}

		} catch (IOException e) {
			e.printStackTrace();
//			ScissorsResult sResult = new ScissorsResult();
//			sResult.setOffset(offset);
//			registResult(sResult);
		} catch (InterruptedException e) {
			e.printStackTrace();
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
