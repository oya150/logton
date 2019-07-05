import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by dev on 19. 7. 5..
 */
public class FileClassify extends Classify {

	FileClassify(BlockingQueue<String> queue){
		super(queue);
	}

	@Override
	List<String> parse(String msg) {
		List<String> statusList = new ArrayList<String>();
		List<String> apikeyList = new ArrayList<String>();

//		String[] msgArr = msg.split("]");
		String regex = "\\[(\\d+)\\]\\[https?:\\/\\/\\w+\\.\\w+\\.\\w+\\/(\\w+)\\/(\\w+)\\?(\\w+\\=(\\w+)\\&)?\\w\\=(\\w+)\\]\\[(\\w+)\\]\\[[0-9- :]+\\]";
		Pattern pattern = Pattern.compile(regex);
		//  \[(\d+)\]\[https?:\/\/\w+\.\w+\.\w+\/(\w+)\/(\w+)\?(\w+\=(\w+)\&)?\w\=(\w+)\]\[(\w+)\]\[[0-9- :]+\]
		Matcher matcher = pattern.matcher(msg);
		while(matcher.find()) {
			System.out.println("matcher ::" + matcher.group(1));
			System.out.println("matcher ::" + matcher.group(2));
			System.out.println("matcher ::" + matcher.group(3));
			System.out.println("matcher ::" + matcher.group(5));
			System.out.println("matcher ::" + matcher.group(6));
		}

		return null;
	}

}
