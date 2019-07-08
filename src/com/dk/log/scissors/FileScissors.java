package com.dk.log.scissors;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;

/**
 * Created by dev on 19. 7. 5..
 */
public class FileScissors extends Scissors {

	String filePath = "";
	public FileScissors(String filePath, BlockingQueue<String> queue) throws FileNotFoundException{
		super(new FileReader(filePath), 0, queue);
		this.filePath = filePath;
	}

	@Override
	protected void registResult(ScissorsResult sResult) {
		sResult.setId(filePath);
		sResult.setOffset(sResult.getOffset());
	}

}
