package com.eaton.utilities;

import java.util.Arrays;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.rank.Percentile;

/**
 * Created by yitgeng on 10/19/2017.
 */
public class StatisticsUtils {

    private static Percentile percentile = new Percentile(50);

    /**
     * calculate sum
     *
     * @param values array
     * @return a copy of the original array, truncated or padded with zeros to obtain the specified length
     */
    public static double getSum(double[] values) {

        if (values == null) {
            return 0.0;
        }
        return StatUtils.sum(values);
    }


    /**
     * calculate median
     *
     * @param values array
     * @return a copy of the original array, truncated or padded with zeros to obtain the specified length
     */
    public static double getMedian(double[] values) {

        if (values == null) {
            return 0.0;
        }
        return percentile.evaluate(values);
    }

    /**
     * calculate Median Absolute Deviation
     *
     * @param values array
     * @return Median Absolute Deviation
     */
    public static double getMAD(double[] values) {

        double median = StatisticsUtils.getMedian(values);

        double[] deviations = new double[values.length];
        for (int i = 0; i < values.length; i++) {
            deviations[i] = Math.abs(values[i] - median);
        }
        return StatisticsUtils.getMedian(deviations);
    }

}
