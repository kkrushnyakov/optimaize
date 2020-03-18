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

public class IntegerCounter {
    
    private final static int AVERAGE_SCALE = 32;

    private BigDecimal avg = BigDecimal.ZERO;

    private BigInteger total = BigInteger.ZERO;

    private int min;

    private int max;

    public IntegerCounter() {
    }

    synchronized public IntegerCounterResult getResult() {
        return new IntegerCounterResult(min, max, avg.doubleValue());
    }

    synchronized public void add(int element) {
        if( min > element) {
            min = element;
        }
        
        if (max < element) {
            max = element;
        }
        
        this.avg = this.avg.multiply(new BigDecimal(total)).add(BigDecimal.valueOf(element)).divide(new BigDecimal(total.add(BigInteger.ONE)), AVERAGE_SCALE, RoundingMode.HALF_UP);
        this.total = this.total.add(BigInteger.ONE); 
    }

    public class IntegerCounterResult {

        private int min;
        
        private int max;

        private double avg;
        
        private IntegerCounterResult(int min, int max, double avg) {
            super();
            this.min = min;
            this.max = max;
            this.avg = avg;
        }

        public int getMax() {
            return max;
        }

        public int getMin() {
            return min;
        }

        public double getAvg() {
            return avg;
        }
        
    }
    
}
