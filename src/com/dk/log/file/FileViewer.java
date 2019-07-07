package com.dk.log.file;

import com.dk.log.Viewer;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

/**
 * Created by oya on 06/07/2019.
 */
public class FileViewer extends Viewer {
    String outFilePath;
    Map<String,Integer> serviceTable;
    Map<String,Integer> apiTable;
    Map<String,Integer> browserTable;

    public FileViewer(String outFilePath) {
        this.outFilePath = outFilePath;
    }

    public void setServiceTable(Map<String, Integer> serviceTable) {
        this.serviceTable = serviceTable;
    }

    public void setApiTable(Map<String, Integer> apiTable) {
        this.apiTable = apiTable;
    }

    public void setBrowserTable(Map<String, Integer> browserTable) {
        this.browserTable = browserTable;
    }


    @Override
    protected void view() {
        System.out.println("apiTable.size() :: " + apiTable.size());
        Map.Entry<String,Integer> entry = apiTable.entrySet().iterator().next();
        String key = entry.getKey();
        int value = entry.getValue();

                System.out.println("key :: " + key);
                System.out.println("value :: " + value);
//        Optional<Map<String,Integer>> apiKey = apiTable.entrySet().stream().findFirst();
//        int cnt = apiTable.get(apiTable.entrySet().stream().findFirst());
//        System.out.println("view :: " + cnt);
    }
}
