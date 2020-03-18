/**
 * 
 */
package ru.krushnyakov.optimaize.counters;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

/**
 * @author kkrushnyakov
 *
 */

public class DoubleCounter {

    private static final int AVERAGE_SCALE = 32;

    private BigDecimal avg  = BigDecimal.ZERO;

    private BigInteger total = BigInteger.ZERO;

    private double min;

    private double max;

    public DoubleCounter() {
    }

    synchronized public DoubleCounterResult getResult() {
        return new DoubleCounterResult(min, max, avg.doubleValue());
    }

    synchronized public void add(double element) {
        
        if(Double.isInfinite(element)) {
            throw new IllegalArgumentException("Element must be finite!");
        }
        
        if(Double.isNaN(element)) {
            throw new IllegalArgumentException("Element must not be a NaN!");
        }
        
        if( min > element) {
            min = element;
        }
        
        if (max < element) {
            max = element;
        }
        
        this.avg = this.avg.multiply(new BigDecimal(total)).add(BigDecimal.valueOf(element)).divide(new BigDecimal(total.add(BigInteger.ONE)), AVERAGE_SCALE, RoundingMode.HALF_UP);
        
        
        total = total.add(BigInteger.ONE);
        
    }

    
    public static class DoubleCounterResult {

        private double min;
        
        private double max;
        
        private double avg;

        private DoubleCounterResult(double min, double max, double avg) {
            this.min = min;
            this.max = max;
            this.avg = avg;
        }

        public double getMin() {
            return min;
        }

        public double getMax() {
            return max;
        }

        public double getAvg() {
            return avg;
        }

    }

}
