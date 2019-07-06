import java.io.File;
import java.io.FileNotFoundException;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by oya on 06/07/2019.
 */
public class ScissorsFactory {


    public Scissors createScissors(String name, BlockingQueue<String> queue) throws FileNotFoundException {

        switch (name) {
            case "file":
                File rootPath = new File(".");
                System.out.println(rootPath.getAbsolutePath());
                String classPath = Logton.class.getResource("").getPath();
                String fileName = "input.log";
                return new FileScissors(classPath + fileName, queue);
//          case "json":
//              return new JsonScissors();
//          case "csv":
//              return new CsvScissors();
        }

        return null;
    }

}
