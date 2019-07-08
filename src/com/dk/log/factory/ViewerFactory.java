package com.dk.log.factory;

import com.dk.log.view.FileViewer;
import com.dk.log.view.Viewer;

import java.io.FileNotFoundException;

/**
 * Created by oya on 06/07/2019.
 */
public class ViewerFactory {


	public Viewer createViewer(String name, String outputPath) throws FileNotFoundException {

		switch (name) {

			case "file":
				return new FileViewer(outputPath);

//          case "http":
//              return new HttpScissors(outputPath);

		}

		return null;
	}

}
