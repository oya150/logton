import java.io.*;
import java.util.concurrent.BlockingQueue;

/**
 * Created by dev on 19. 7. 5..
 */
public class FileScissors extends Scissors {

	String filePath = "";
	FileScissors(String filePath, BlockingQueue<String> queue) throws FileNotFoundException{
		super(new FileReader(filePath), 0, queue);
		this.filePath = filePath;
	}

	@Override
	protected void registResult(ScissorsResult sResult) {
		sResult.setId(filePath);
		sResult.setSkipNum(sResult.getSkipNum());
		System.out.println("registResult +  " + sResult.getSkipNum());
		System.out.println("filePath +  " + filePath);
	}
}
