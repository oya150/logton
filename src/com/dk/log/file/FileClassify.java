package com.dk.log.file;

import com.dk.log.Classify;

import java.util.*;
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

	FileViewer viewer;

	public FileClassify(BlockingQueue<String> queue, FileViewer viewer){
		super(queue);
		this.viewer = viewer;
	}
	@Override
	public List<String> parse(String msg) {
		List<String> apikeyList = new ArrayList<String>();

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
			viewer.setTotalApiCnt(viewer.getTotalApiCnt()+1);
			serviceMap.put(serviceId,serviceCnt);
			apiMap.put(apiKey,apiCnt);
			browserMap.put(browserName,browserCnt);

//			System.out.println("matcher ::" + matcher.group(1));
//			System.out.println("matcher ::" + matcher.group(2));
//			System.out.println("matcher ::" + matcher.group(3));
//			System.out.println("matcher ::" + matcher.group(5));
//			System.out.println("matcher ::" + matcher.group(6));
//			System.out.println("matcher ::" + matcher.group(7));

			Iterator<String> keys= serviceMap.keySet().iterator();
			while(keys.hasNext()) {
				String key = keys.next();
				int cnt = serviceMap.get(key);
//				System.out.println("table::::  "+ key + "  =   " + cnt );
			}
//			Iterator<String> skeys= apiKeyTable.keySet().iterator();
//			while(skeys.hasNext()) {
//				String key = skeys.next();
//				int cnt = apiKeyTable.get(key);
//				System.out.println("table::::  "+ key + "  =   " + cnt );
//			}
//			Iterator<String> bkeys= browserTable.keySet().iterator();
//			while(bkeys.hasNext()) {
//				String key = bkeys.next();
//				int cnt = browserTable.get(key);
//				System.out.println("table::::  "+ key + "  =   " + cnt );
//			}
		}
		return null;
	}

	@Override
	protected void orderSomthing() {
//		System.out.println("order =====================:: " + serviceMap.size());

		viewer.setServiceIdTable(orderDesc(serviceMap));
		viewer.setApiKeyTable(orderDesc(apiMap));
		viewer.setBrowserTable(orderDesc(browserMap));
		viewer.view();
//		Iterator<String> keys= serviceIdTable.keySet().iterator();
//		while(keys.hasNext()) {
//			String key = keys.next();
//			int cnt = serviceIdTable.get(key);
//			System.out.println("order::::  "+ key + "  =   " + cnt );
//		}

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
