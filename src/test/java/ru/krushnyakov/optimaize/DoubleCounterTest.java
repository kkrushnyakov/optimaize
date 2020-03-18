/**
 * 
 */
package ru.krushnyakov.optimaize;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ru.krushnyakov.optimaize.counters.DoubleCounter;

/**
 * @author kkrushnyakov
 *
 */
public class DoubleCounterTest {

    public static final double TESTING_PRECISION_DELTA = 0.0001;
    
    @Rule
    public ExpectedException throwable = ExpectedException.none();
    
    @Test
    public void testMax() {
        DoubleCounter counter = new DoubleCounter();
        assertEquals(0, counter.getResult().getMax(), TESTING_PRECISION_DELTA);
        counter.add(10);
        counter.add(20);
        assertEquals(20, counter.getResult().getMax(), TESTING_PRECISION_DELTA);
        counter.add(-40);
        assertEquals(20, counter.getResult().getMax(), TESTING_PRECISION_DELTA);
        counter.add(40);
        assertEquals(40, counter.getResult().getMax(), TESTING_PRECISION_DELTA);
        counter.add(Double.MAX_VALUE);
        assertEquals(Double.MAX_VALUE, counter.getResult().getMax(), TESTING_PRECISION_DELTA);
    }

    @Test
    public void testMin() {
        DoubleCounter counter = new DoubleCounter();
        assertEquals(0, counter.getResult().getMin(), TESTING_PRECISION_DELTA);
        counter.add(10.5);
        assertEquals(0, counter.getResult().getMin(), TESTING_PRECISION_DELTA);
        counter.add(-1000);
        assertEquals(-1000, counter.getResult().getMin(), TESTING_PRECISION_DELTA);
    }

    @Test
    public void testAvg() {
        DoubleCounter counter = new DoubleCounter();
        System.out.print(1e300 + 3 + -1e200);
        counter.add(1e300);
        counter.add(3);
        counter.add(-1e300);
        assertEquals(1, counter.getResult().getAvg(), TESTING_PRECISION_DELTA);
    }
    
    @Test
    public void testNaN() {
        
        DoubleCounter counter = new DoubleCounter();
        
        throwable.expect(IllegalArgumentException.class);

        counter.add(Double.NaN);
    }
    
    @Test
    public void testInfinite() {
        
        DoubleCounter counter = new DoubleCounter();

        throwable.expect(IllegalArgumentException.class);

        counter.add(Double.NEGATIVE_INFINITY);
    }
    
}
