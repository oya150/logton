package com.dk.log;

import java.util.List;
import java.util.concurrent.BlockingQueue;


/**
 * Created by dev on 19. 7. 5..
 */
public abstract class Classify implements Runnable{

	private BlockingQueue<String> queue;

	protected Classify(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	@Override
	public void run() {

			while(!Thread.currentThread().isInterrupted()) {
				String msg = "";
				try {
					msg = queue.take();
//					System.out.println(msg);
					parse(msg);

					if(queue.isEmpty()) {
						orderSomthing();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
				}
			}
	}

	protected abstract List<String> parse(String msg);
	protected abstract void orderSomthing();

}
