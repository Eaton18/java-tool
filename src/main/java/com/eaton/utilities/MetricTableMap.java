package com.eaton.utilities;

import com.csvreader.CsvReader;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by eaton on 10/19/2017.
 *
 */
public class MetricTableMap {

    /**
     * Read @filePath csv file
     * Return the metric value read from csv file in a HashMap
     * File format must: |DateTime|metric1|metric2|...|
     *
     * @param   filePath csv file location
     * @throws IOException file not found
     * @return  metricTable metric value
     */
    public static Map<String, Map<Date, Integer>> readMetricCsvFile(String filePath) throws IOException, ParseException {

        System.out.println("Run readCsvFile()");

        Map<String, Map<Date, Integer>> metricTable = new HashMap<String, Map<Date, Integer>>();

        // Read csv file
        CsvReader csvReader = new CsvReader(filePath);

        csvReader.readHeaders();

        int metricCount = csvReader.getHeaderCount();
        String[] metricNames = csvReader.getHeaders();

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");

        while (csvReader.readRecord()){

            for (int i = 1; i < metricCount; i++) {

                if(metricTable.get(metricNames[i]) == null){
                    Map<Date, Integer> metricPoint = new LinkedHashMap<Date, Integer>();
                    Date datetime = df.parse(csvReader.get(0));
                    int value = Integer.parseInt(csvReader.get(i));
                    metricPoint.put(datetime, value);
                    metricTable.put(metricNames[i], metricPoint);
                } else {
                    Map<Date, Integer> metricPoint = metricTable.get(metricNames[i]);
                    Date datetime = df.parse(csvReader.get(0));
                    int value = Integer.parseInt(csvReader.get(i));
                    metricPoint.put(datetime, value);
                }

            }

        }

        csvReader.close();

        return metricTable;
    }

}
