import java.util.Map;

/**
 * Created by dev on 19. 7. 5..
 */
public class ClassifyResult {
	private String id;
	private Map<String,Integer> indexTable;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, Integer> getIndexTable() {
		return indexTable;
	}

	public void setIndexTable(Map<String, Integer> indexTable) {
		this.indexTable = indexTable;
	}
}
