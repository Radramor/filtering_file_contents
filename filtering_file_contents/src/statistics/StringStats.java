package statistics;

public class StringStats {
    private long count = 0;
    private Integer minLen = null, maxLen = null;

    public void acceptString(String s) {
        count++;
        int len = s.length();
        if (minLen == null || len < minLen) minLen = len;
        if (maxLen == null || len > maxLen) maxLen = len;
    }

    public long getCount() { return count; }
    public Integer getMinLen() { return minLen; }
    public Integer getMaxLen() { return maxLen; }
}
