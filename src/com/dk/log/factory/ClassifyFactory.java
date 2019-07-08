package com.dk.log.factory;

import com.dk.log.classify.Classify;
import com.dk.log.classify.FileClassify;
import com.dk.log.view.Viewer;

import java.io.FileNotFoundException;
import java.util.concurrent.BlockingQueue;

/**
 * Created by oya on 06/07/2019.
 */
public class ClassifyFactory {


	public Classify createClassify(String name, BlockingQueue<String> queue, Viewer viewer) throws FileNotFoundException {

		switch (name) {

			case "file":
				return new FileClassify(queue, viewer);
//          case "http":
//              return new HttpScissors();

		}

		return null;
	}
}
