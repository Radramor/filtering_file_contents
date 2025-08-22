package base;

public final class Classifier {

    public enum Type { INTEGER, FLOAT, STRING }

    public static Type classify(String s) {
        if (s == null || s.isBlank()) return Type.STRING;

        String trimmed = s.strip();

        try {
            Integer.parseInt(trimmed);
            return Type.INTEGER;
        } catch (NumberFormatException ignored) {}

        try {
            Float.parseFloat(trimmed);
            return Type.FLOAT;
        } catch (NumberFormatException ignored) {}

        return Type.STRING;
    }

}
