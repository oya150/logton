import java.io.*;
import java.util.concurrent.BlockingQueue;

/**
 * Created by dev on 19. 7. 5..
 */
public class FileScissors extends Scissors {

	FileScissors(String filePath, BlockingQueue<String> queue) throws FileNotFoundException{
		super(new FileReader(filePath), 0, queue);
	}

	@Override
	protected void registResult(ScissorsResult sResult) {
		System.out.println("registResult +  " + sResult.getSkipNum());
	}
}
