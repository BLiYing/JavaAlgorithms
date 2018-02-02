package model.fourcapter;

/**
 * 1.4 算法分析
 * Created by admin on 2018-1-30.
 */

public class Stopwatch {
    private final  long start;
    public Stopwatch(){
        start = System.currentTimeMillis();
    }

    public double elapsedTime(){
        long now = System.currentTimeMillis();
        return (now - start)/1000.0;
    }
}
