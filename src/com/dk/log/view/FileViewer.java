package com.dk.log.view;

import com.dk.log.Logton;
import com.dk.log.view.constants.ViewConstants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by oya on 06/07/2019.
 */
public class FileViewer extends Viewer {

	String outputPath;
	int totalApiCnt;
	Map<String,Integer> serviceIdTable;
	Map<String,Integer> apiKeyTable;
	Map<String,Integer> browserTable;

	public FileViewer(String outputPath) {
		this.outputPath = outputPath;
	}

	@Override
	public void view(){
		String fileViewConfigFromProp = Logton.applicationProperties.getProperty("fileView");
		String[] configs = fileViewConfigFromProp.split("\\|");
		StringBuffer result = new StringBuffer();

		for(String config : configs) {
			String[] fileViewConfig = config.split(",");
			String dpType = fileViewConfig[0];
			String dpField = fileViewConfig[1];
			String dpFieldName = "";

			Map<String,Integer> table = new HashMap<>();

			if(ViewConstants.DP_FILED.APIKEY.name().equals(dpField)) {

				table = apiKeyTable;
				dpFieldName = ViewConstants.DP_FILED.APIKEY.getFieldName();

			} else if (ViewConstants.DP_FILED.SERVICEID.name().equals(dpField)) {

				table = serviceIdTable;
				dpFieldName = ViewConstants.DP_FILED.SERVICEID.getFieldName();

			} else if (ViewConstants.DP_FILED.BROWSER.name().equals(dpField)) {

				table = browserTable;
				dpFieldName = ViewConstants.DP_FILED.BROWSER.getFieldName();

			}

			if(ViewConstants.DP_TYPE_TOP.equals(dpType)) {

				Map.Entry<String,Integer> entry = table.entrySet().iterator().next();
				String apiKey = entry.getKey();
				String title = String.format("최다호출 %s", dpFieldName);
				result.append(title);
				result.append(System.lineSeparator());
				result.append(apiKey);
				result.append(System.lineSeparator());
				result.append(System.lineSeparator());

			} else if (ViewConstants.DP_TYPE_RANK.equals(dpType)) {

				int rankRange = Integer.parseInt(fileViewConfig[2]);
				String title = String.format("상위 %d개의 API %s와 각각의 요청 수", rankRange, dpFieldName);
				result.append(title);
				result.append(System.lineSeparator());
				table.entrySet().stream()
								.limit(rankRange)
								.forEach(entry -> {
									result.append(entry.getKey());
									result.append(" : ");
									result.append(entry.getValue());
									result.append(System.lineSeparator());
								});

			} else if (ViewConstants.DP_TYPE_PERCENT.equals(dpType)) {

				String title = String.format("%s별 사용 비율", dpFieldName);
				result.append(System.lineSeparator());
				result.append(title);
				result.append(System.lineSeparator());
				table.entrySet().stream()
								.forEach(entry -> {
									result.append(entry.getKey());
									result.append(" : ");
									result.append(entry.getValue() * 100 / getTotalApiCnt() + "%");
									result.append(System.lineSeparator());
								});

			}
		}

		File file = new File(outputPath);
		if (file.exists()) {
			file.delete();
		}

		FileWriter writer = null;
		BufferedWriter bWriter = null;

		try {
			file.createNewFile();
			writer = new FileWriter(file, true);
			bWriter = new BufferedWriter(writer);
			bWriter.write(result.toString());
			bWriter.newLine();
			bWriter.flush();

		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bWriter != null) bWriter.close();
				if(writer != null) writer.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void setServiceIdTable(Map<String, Integer> serviceIdTable) {
		this.serviceIdTable = serviceIdTable;
	}

	public void setApiKeyTable(Map<String, Integer> apiKeyTable) {
		this.apiKeyTable = apiKeyTable;
	}

	public void setBrowserTable(Map<String, Integer> browserTable) {
		this.browserTable = browserTable;
	}

	public int getTotalApiCnt() {
		return totalApiCnt;
	}

	public void setTotalApiCnt(int totalApiCnt) {
		this.totalApiCnt = totalApiCnt;
	}

}
