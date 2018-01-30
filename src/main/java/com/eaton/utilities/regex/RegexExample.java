package com.eaton.utilities.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by yitgeng on 1/26/2018.
 */
public class RegexExample {

    public static void main(String[] args) {
        String testStr1 = "3        T20:14:41.37\tSHAHAHAHA3\tIAF.get\t0\t11\tcorr_id_=YpiVh&ri=QqU&node_id=m0N0Dg9&GUID=16116b5144&REQUEST_ID=1611bd144!IAFService!10.166.110.77!r12345678[]";
        String testStr2 = "2      T20:14:41.37\tValid\tcom.ebay.\t0\t16\tTokenleted";
        String testStr3 = "1    T20:14:41.37\thand\t(30,RaptorSe)\t0\t17\t";

        List<String> testStrs = new ArrayList<String>();
        testStrs.add(testStr1);
        testStrs.add(testStr2);
        testStrs.add(testStr3);

//        String ragex1 = " {2,}";
        String regexEventHead = "^[0-9]{1,} {2,}T";
        String regexMultiSpace = " {2,}";
        String regexMultiTab = "\t{2,}";

        Pattern pattern = Pattern.compile(regexEventHead);

        for (int i = 0; i < testStrs.size(); i++) {
            String str = testStrs.get(i);
            Matcher matcher = pattern.matcher(str);
            // String transfered = testStrs.get(i).replaceAll(ragex2, "\t");
            if (matcher.find() && (str.indexOf("ri=") != -1) && (str.indexOf("corr_id_=") != -1) && (str.indexOf("node_id=") != -1)) {
//                System.out.println(str.indexOf("ri="));
//                System.out.println(str.indexOf("corr_id_="));
//                System.out.println(str.indexOf("node_id="));

                String transLogs = str.replaceAll(regexMultiSpace, "\t").replaceAll(regexMultiTab, "\t");
                System.out.println();
            }


        }

    }

}



