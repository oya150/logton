package com.dk.log.classify;

import com.dk.log.view.FileViewer;
import com.dk.log.view.Viewer;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by dev on 19. 7. 5..
 */
public class FileClassify extends Classify {
	static ConcurrentHashMap<String, Integer> serviceMap = new ConcurrentHashMap<>();
	static ConcurrentHashMap<String, Integer> apiMap = new ConcurrentHashMap<>();
	static ConcurrentHashMap<String, Integer> browserMap = new ConcurrentHashMap<>();
	FileViewer fileView;

	public FileClassify(BlockingQueue<String> queue, Viewer viewer){
		super(queue, viewer);
		fileView =(FileViewer) viewer;
	}

	@Override
	public void parse(String msg) {
		if(msg == null) {
			return;
		}
		String regex = "\\[(200)\\]\\[https?:\\/\\/\\w+\\.\\w+\\.\\w+\\/(\\w+)\\/(\\w+)\\?(\\w+\\=(\\w+)\\&)?\\w\\=(\\w+)\\]\\[(\\w+)\\]\\[[0-9- :]+\\]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(msg);

		while(matcher.find()) {

			String serviceId = matcher.group(3);
			String apiKey = matcher.group(5);
			String browserName = matcher.group(7);

			int serviceCnt = 1;
			if(serviceMap.get(serviceId) != null) {
				serviceCnt = serviceMap.get(serviceId) + 1;
			}

			int apiCnt = 1;
			if(apiMap.get(apiKey) != null) {
				apiCnt = apiMap.get(apiKey) + 1;
			}

			int browserCnt = 1;
			if(browserMap.get(browserName) != null) {
				browserCnt = browserMap.get(browserName) + 1;
			}

			fileView.setTotalApiCnt(fileView.getTotalApiCnt()+1);

			serviceMap.put(serviceId,serviceCnt);
			apiMap.put(apiKey,apiCnt);
			browserMap.put(browserName,browserCnt);

		}
	}

	@Override
	protected void orderSomthing() {
		fileView.setServiceIdTable(orderDesc(serviceMap));
		fileView.setApiKeyTable(orderDesc(apiMap));
		fileView.setBrowserTable(orderDesc(browserMap));
		fileView.view();
	}

	private Map<String, Integer> orderDesc(Map<String,Integer> map) {
		Map<String,Integer> resultMap = map.entrySet()
											.stream()
											.sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
											.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
													(oldValue, newValue) -> oldValue, LinkedHashMap::new));
		return resultMap;
	}

}
