/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author The BigBang
 */
    
    import static java.lang.Math.sqrt;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Stopwatch {

//    @FunctionalInterface
    public interface Test {
        void setup();
        void test();
    }
    
    Stopwatch() {
        measurements = new ArrayList<>();
    }

    /**
     * An example of the use of this class to time the execution of simple
     * String manipulation code.
     */
    public static void main(String... arguments) {
        Stopwatch stopwatch = new Stopwatch();

        stopwatch.start();

        //do stuff
        StringBuilder messageOne = new StringBuilder();
        int numIterations = 5000;
        for (int idx = 0; idx < numIterations; ++idx) {
            messageOne.append("blah");
        }

        stopwatch.stop();
        //Note that there is no need to call a method to get the duration,
        //since toString is automatic here
        System.out.println("The reading for StringBuilder is: " + stopwatch);

        //reuse the same stopwatch to measure an alternative implementation
        //Note that there is no need to call a reset method.
        stopwatch.start();

        //do stuff again
        String messageTwo = null;
        for (int idx = 0; idx < numIterations; ++idx) {
            messageTwo = messageTwo + "blah";
        }

        stopwatch.stop();
        //perform a numeric comparsion
        if (stopwatch.toValue() > 5) {
            System.out.println("The reading is high: " + stopwatch);
        } else {
            System.out.println("The reading is low: " + stopwatch);
        }
    }

    /**
     * Start the stopwatch.
     *
     * @throws IllegalStateException if the stopwatch is already running.
     */
    public void start() {
        if (fIsRunning) {
            throw new IllegalStateException("Must stop before calling start again.");
        }
        //reset both start and stop
        fStart = System.nanoTime();
        fStop = 0;
        fIsRunning = true;
        fHasBeenUsedOnce = true;
        fMultipleRuns = false;
    }

    /**
     * Stop the stopwatch.
     *
     * @throws IllegalStateException if the stopwatch is not already running.
     */
    public void stop() {
        if (!fIsRunning) {
            throw new IllegalStateException("Cannot stop if not currently running.");
        }
        fStop = System.nanoTime();
        fIsRunning = false;
    }

    /**
     * measure the execution time of test() method in Test interface
     */
    public void measure(Test method) {
        int oldPriority = Thread.currentThread().getPriority();
        
        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
        measurements.clear();        
        for (int i = 0; i < N+M; i++) {
            method.setup();
            method.test();
            method.setup();
            fStart = System.nanoTime();
            method.test();
            fStop = System.nanoTime();
            
            measurements.add(fStop - fStart);
        }
        
        measurements.subList(0, M-1).clear();   // remove outliers
        Collections.sort(measurements);         // in order to get the median

        fMultipleRuns = true;
        fHasBeenUsedOnce = true;
        Thread.currentThread().setPriority(oldPriority);
    }

    /**
     * Express the "reading" on the stopwatch.
     *
     * <P>
     * Example: <tt>123.456 ms</tt>. The resolution of timings on most systems
     * is on the order of a few microseconds, so this style of presentation is
     * usually appropriate for reflecting the real precision of most timers.      *
     * <P>
     * Ref: https://blogs.oracle.com/dholmes/entry/inside_the_hotspot_vm_clocks
     *
     * @throws IllegalStateException if the Stopwatch has never been used, or if
     * the stopwatch is still running.
     */
    @Override
    public String toString() {
        validateIsReadable();
        StringBuilder result = new StringBuilder();
        toValue(result);
        result.append(" ms, stdev ");
        toStDev(result);
        result.append(" ms");
        return result.toString();
    }

    /**
     * Express the "reading" on the stopwatch as a numeric type, in nanoseconds.
     *
     * @throws IllegalStateException if the Stopwatch has never been used, or if
     * the stopwatch is still running.
     */
    public long toValue() {
        validateIsReadable();
        if (fMultipleRuns) {
            //for (long e: measurements)
            //    System.out.print(e + " ");
            //System.out.println();
            return measurements.get(N/2);
        }
            
        return fStop - fStart;
    }
    
    public void toValue(StringBuilder result) {
        BigDecimal value = new BigDecimal(toValue());//scale is zero
        //millis, with 3 decimals:
        value = value.divide(MILLION, 5, BigDecimal.ROUND_HALF_EVEN);
        result.append(value);
    }
    
    /**
     * Calculates standard deviation of the measurements, in nanoseconds
     * 
     */
    public double toStDev() {
        long sum = 0;
        for (Long mark : measurements) {
            sum += mark;
        }
        double mean = measurements.isEmpty()? 0: 1.0*sum/measurements.size();
        
        sum = 0;
        for (Long mark : measurements) {
            sum += (mark-mean)*(mark-mean);
        }
        //System.out.println("Mean " + mean + " StDev " + sqrt(1.0*sum/measurements.size()));
        return measurements.isEmpty()? 0: sqrt(1.0*sum/measurements.size());        
    }
    
    public void toStDev(StringBuilder result) {
        BigDecimal value = new BigDecimal(toStDev());//scale is zero
        //millis, with 3 decimals:
        value = value.divide(MILLION, 3, BigDecimal.ROUND_HALF_EVEN);
        result.append(value);        
    }

    // PRIVATE
    private List<Long> measurements;

    private long fStart;
    private long fStop;

    private boolean fIsRunning;
    private boolean fHasBeenUsedOnce;
    private boolean fMultipleRuns;

    private static final int N = 24;    // number of experiments taken
    private static final int M = 16;    // number of outliers to be removed

    /**
     * Converts from nanos to millis.
     */
    private static final BigDecimal MILLION = new BigDecimal("1000000");

    /**
     * Throws IllegalStateException if the watch has never been started, or if
     * the watch is still running.
     */
    private void validateIsReadable() {
        if (fIsRunning) {
            String message = "Cannot read a stopwatch which is still running.";
            throw new IllegalStateException(message);
        }
        if (!fHasBeenUsedOnce) {
            String message = "Cannot read a stopwatch which has never been started.";
            throw new IllegalStateException(message);
        }
    }
}

    

