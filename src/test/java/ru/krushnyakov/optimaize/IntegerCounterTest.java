/**
 * 
 */
package ru.krushnyakov.optimaize;

import org.junit.Test;
import static org.junit.Assert.*;
import ru.krushnyakov.optimaize.counters.IntegerCounter;

/**
 * @author kkrushnyakov
 *
 */
public class IntegerCounterTest {

    public static final double TESTING_PRECISION_DELTA = 0.0001;

    
    @Test
    public void testMax() {
        IntegerCounter counter = new IntegerCounter();
        assertEquals(0, counter.getResult().getMax());
        assertEquals(0, counter.getResult().getMin());
        assertEquals(0, counter.getResult().getAvg(), TESTING_PRECISION_DELTA);
        
        counter.add(10);
        counter.add(20);
        assertEquals(20, counter.getResult().getMax());
        counter.add(-40);
        assertEquals(20, counter.getResult().getMax());
        counter.add(40);
        assertEquals(40, counter.getResult().getMax());
    }

    @Test
    public void testMin() {
        
        IntegerCounter counter = new IntegerCounter();
        assertEquals(0, counter.getResult().getMin());
        
        counter.add(10);
        assertEquals(0, counter.getResult().getMin());
        
        counter.add(Integer.MIN_VALUE);
        assertEquals(Integer.MIN_VALUE, counter.getResult().getMin());
        
        counter.add(-1000);
        assertEquals(Integer.MIN_VALUE, counter.getResult().getMin());
    }

    @Test
    public void testAvg() {
        
        IntegerCounter counter = new IntegerCounter();

        counter.add(Integer.MAX_VALUE);
        counter.add(Integer.MIN_VALUE);
        counter.add(2);
        
        assertEquals(1, counter.getResult().getAvg(), TESTING_PRECISION_DELTA);        
    }
    
    @Test
    public void testAvgOverflow() {
        
        IntegerCounter counter = new IntegerCounter();
        
        counter.add(Integer.MAX_VALUE);
        counter.add(Integer.MAX_VALUE);
        
        assertEquals(Integer.MAX_VALUE, counter.getResult().getAvg(), TESTING_PRECISION_DELTA);        
    }
    
    
}
