package com.eaton.examples;

import com.eaton.utilities.MetricTableMap;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
 * Created by eaton on 10/19/2017.
 */
public class CSVFileOps {

    public static void main(String args[]) {

        // |Date|ASM_Domestic|ASM_International|Flights_Domestic|Flights_International|Passengers_Domestic|Passengers_International|RPM_Domestic|RPM_International\
        String filePath = "src\\main\\resources\\datasets\\US_Commercial_Aviation_Industry_Metrics(all_airlines_all_airports).csv";

        Map<String, Map<Date, Integer>> metricTable = null;
        try {
            metricTable = MetricTableMap.readMetricCsvFile(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(metricTable.isEmpty());

    }

}
