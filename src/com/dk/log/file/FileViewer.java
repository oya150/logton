package com.dk.log.file;

import com.dk.log.Viewer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by oya on 06/07/2019.
 */
public class FileViewer extends Viewer {
    private static final String DP_TYPE_TOP = "TOP";
    private static final String DP_TYPE_RANK = "RANK";
    private static final String DP_TYPE_PERCENT = "PERCENT";
    private static final String DP_FIELD_APIKEY = "APIKEY";
    private static final String DP_FIELD_SERVICEID = "SERVICEID";
    private static final String DP_FIELD_BROWSER = "BROWSER";


    String outputPath;
    int totalApiCnt;
    Map<String,Integer> serviceIdTable;
    Map<String,Integer> apiKeyTable;
    Map<String,Integer> browserTable;



    public FileViewer(String outputPath) {
        this.outputPath = outputPath;
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

    @Override
    protected void view(){
        System.out.println("apiKeyTable.size() :: " + apiKeyTable.size());
        String[] configs = "TOP,APIKEY|RANK,SERVICEID,3|PERCENT,BROWSER".split("\\|");
        StringBuffer result = new StringBuffer();

        for(String config : configs) {
            String[] fileViewConfig = config.split(",");
            String dpType = fileViewConfig[0];
            String dpField = fileViewConfig[1];
            String dpFieldName = "";

            Map<String,Integer> table = new HashMap<>();
            if(DP_FIELD_APIKEY.equals(dpField)) {
                table = apiKeyTable;
                dpFieldName = "APIKEY";
            } else if (DP_FIELD_SERVICEID.equals(dpField)) {
                table = serviceIdTable;
                dpFieldName = "Service ID";
            } else if (DP_FIELD_BROWSER.equals(dpField)) {
                table = browserTable;
                dpFieldName = "웹브라우저";
            }

            if(DP_TYPE_TOP.equals(dpType)) {
                Map.Entry<String,Integer> entry = table.entrySet().iterator().next();
                String apiKey = entry.getKey();
                String title = "최다호출 ";
                result.append(title);
                result.append(dpFieldName);
                result.append(System.lineSeparator());
                result.append(apiKey);
            } else if (DP_TYPE_RANK.equals(dpType)) {
                int rankRange = Integer.parseInt(fileViewConfig[2]);

            } else if (DP_TYPE_PERCENT.equals(dpType)) {

            }
        }

         Iterator<String> serviceKesy = serviceIdTable.keySet().iterator();
         String fRankService = serviceKesy.next();
         int fRankCnt = serviceIdTable.get(fRankService);
         String sRankService = serviceKesy.next();
         int sRankCnt = serviceIdTable.get(sRankService);
         String tRankService = serviceKesy.next();
         int tRankCnt = serviceIdTable.get(tRankService);
        System.out.println("totalCoutn ::  " + getTotalApiCnt());

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

            System.out.println("DONE");
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


//        Optional<Map<String,Integer>> apiKey = apiKeyTable.entrySet().stream().findFirst();
//        int cnt = apiKeyTable.get(apiKeyTable.entrySet().stream().findFirst());
//        System.out.println("view :: " + cnt);
    }
}
