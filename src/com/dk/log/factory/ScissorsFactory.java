package com.dk.log.factory;

import com.dk.log.scissors.FileScissors;
import com.dk.log.scissors.Scissors;

import java.io.FileNotFoundException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by oya on 06/07/2019.
 */
public class ScissorsFactory {


	public Scissors createScissors(String name, String path, BlockingQueue<String> queue) throws FileNotFoundException {

		switch (name) {

			case "file":
				return new FileScissors(path, queue);

//          case "http":
//			  return new HttpScissors();

		}

		return null;
	}
}
