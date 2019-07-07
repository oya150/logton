package com.dk.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by dev on 19. 7. 5..
 */
public class ScissorsResult{
	private String id;
	private int skipNum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSkipNum() {
		return skipNum;
	}

	public void setSkipNum(int skipNum) {
		this.skipNum = skipNum;
	}
}
