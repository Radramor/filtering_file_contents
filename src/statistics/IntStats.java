package statistics;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.MathContext;

public class IntStats {
    private long count = 0;
    private BigInteger min = null, max = null, sum = BigInteger.ZERO;

    public void acceptInteger(String s) {
        BigInteger v = new BigInteger(s);
        count++;
        if (min == null || v.compareTo(min) < 0) min = v;
        if (max == null || v.compareTo(max) > 0) max = v;
        sum = sum.add(v);
    }

    public long getCount() { return count; }
    public BigInteger getMin() { return min; }
    public BigInteger getMax() { return max; }
    public BigInteger getSum() { return sum; }
    public BigDecimal getAverage() {
        return count == 0 ? BigDecimal.ZERO :
                new BigDecimal(sum).divide(BigDecimal.valueOf(count), MathContext.DECIMAL128);
    }
}
