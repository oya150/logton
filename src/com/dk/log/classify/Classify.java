package com.dk.log.classify;

import com.dk.log.view.Viewer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;


/**
 * Created by dev on 19. 7. 5..
 */
public abstract class Classify implements Runnable{

	private BlockingQueue<String> queue;
	protected Viewer viewer;

	protected Classify(BlockingQueue<String> queue, Viewer viewer) {
		this.queue = queue;
		this.viewer = viewer;
	}
	@Override
	public void run() {

			while(!Thread.currentThread().isInterrupted()) {

				String msg;
				try {
					msg = queue.take();
					parse(msg);

					if(queue.isEmpty()) {
						orderSomthing();
					}

				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}

			}
	}

	protected abstract void parse(String msg);
	protected abstract void orderSomthing();

}
