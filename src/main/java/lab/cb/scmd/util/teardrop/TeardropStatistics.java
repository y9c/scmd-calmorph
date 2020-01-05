//--------------------------------------
//SCMDProject
//
//TeardropBackgroupnd.java 
//Since: 2004/08/16
//
// $URL$ 
// $LastChangedBy$ 
//--------------------------------------

package lab.cb.scmd.util.teardrop;

import java.util.Collection;
import java.util.Iterator;

/**
 * Teardrop描画用のデータを格納している
 *
 * @author sesejun
 */
public class TeardropStatistics {
    private int LIMIT = 1000;

    private double _max;
    private double _min;
    private double _bucketSize;
    private double _avg;
    private double _sd;
    private int[] _count;
    private int _maxcount;

    private double DIVISIONNUMOFSD = 5.0;

    public TeardropStatistics(double average, double sd, double min, double max) {
        setAvg(average);
        setSD(sd);
        setMin(min);
        setMax(max);
    }

    /**
     * @return Returns the avg.
     */
    public double getAvg() {
        return _avg;
    }

    /**
     * @param avg The avg to set.
     */
    public void setAvg(double avg) {
        this._avg = avg;
    }

    /**
     * @return Returns the max.
     */
    public double getMax() {
        return _max;
    }

    /**
     * @param max The max to set.
     */
    public void setMax(double max) {
        this._max = max;
    }

    /**
     * @return Returns the min.
     */
    public double getMin() {
        return _min;
    }

    /**
     * @param min The min to set.
     */
    public void setMin(double min) {
        this._min = min;
    }

    /**
     * @return Returns the sd.
     */
    public double getSD() {
        return _sd;
    }

    /**
     * @param sd The sd to set.
     */
    public void setSD(double sd) {
        this._sd = sd;
    }

    /**
     * @param samples
     */
    public void calcHistgram(Collection samples) {
        double diff = getMax() - getMin();
        _bucketSize = getSD() / DIVISIONNUMOFSD;
        int numOfBuckets = (int) Math.ceil(diff / _bucketSize);
        if (numOfBuckets > LIMIT) {
            // TODO 0の配列を返すのは意味がないのでは？
            _count = new int[0];
            return;
        }
        _count = new int[numOfBuckets];

        for (Object sample : samples) {
            double v = (Double) sample;
            int index = getIndex(v);
            _count[index]++;
        }
        int maxcount = 0;
        for (int value : _count) {
            if (maxcount < value)
                maxcount = value;
        }
        _maxcount = maxcount;
    }

    public int getHistgram(int n) {
        return _count[n];
    }

    public int getHistgramSize() {
        return _count.length;
    }

    public int getMaxCount() {
        return _maxcount;
    }

    public int getIndex(double value) {
        if (_bucketSize == 0.0) {
            _bucketSize = getSD() / DIVISIONNUMOFSD;
        }
        return (int) Math.floor((value - getMin()) / _bucketSize);
    }
}
