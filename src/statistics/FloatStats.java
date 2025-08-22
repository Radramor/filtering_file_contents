package statistics;

import java.math.BigDecimal;
import java.math.MathContext;

public class FloatStats {
    private long count = 0;
    private BigDecimal min = null, max = null, sum = BigDecimal.ZERO;

    public void acceptFloat(String s) {
        BigDecimal v = new BigDecimal(s);
        count++;
        if (min == null || v.compareTo(min) < 0) min = v;
        if (max == null || v.compareTo(max) > 0) max = v;
        sum = sum.add(v, MathContext.DECIMAL128);
    }

    public long getCount() { return count; }
    public BigDecimal getMin() { return min; }
    public BigDecimal getMax() { return max; }
    public BigDecimal getSum() { return sum; }
    public BigDecimal getAverage() {
        return count == 0 ? BigDecimal.ZERO :
                sum.divide(BigDecimal.valueOf(count), MathContext.DECIMAL128);
    }
}
