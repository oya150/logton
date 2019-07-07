package com.dk.log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by dev on 19. 7. 5..
 */
public class ScissorsResult{
	private String id;
	private int offset;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
}
