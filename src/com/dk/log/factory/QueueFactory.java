package com.dk.log.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by oya on 07/07/2019.
 */
public class QueueFactory {
	Map<String,BlockingQueue<String>> queueMap = new HashMap<>();
	final int QUEUE_SIZE = 5;

	public BlockingQueue<String> createQueue(String name) {

		if(queueMap.containsKey(name)) {

			return queueMap.get(name);

		} else {

			BlockingQueue<String> queue = new ArrayBlockingQueue<String>(QUEUE_SIZE);
			queueMap.put(name, queue);

			return queue;

		}
	}
}
