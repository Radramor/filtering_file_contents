package base;

import cmd.ArgParser;
import statistics.FloatStats;
import statistics.IntStats;
import statistics.StringStats;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.EnumSet;

import static java.nio.file.StandardOpenOption.*;

public class Main {

    public static void main(String[] args) {
        Config cfg;
        try {
            cfg = ArgParser.parse(args);
        } catch (IllegalArgumentException iae) {
            System.err.println("Ошибка аргументов: " + iae.getMessage());
            ArgParser.printHelp();
            return;
        }

        IntStats intStats = new IntStats();
        FloatStats floatStats = new FloatStats();
        StringStats stringStats = new StringStats();

        BufferedWriter intOut = null;
        BufferedWriter floatOut = null;
        BufferedWriter stringOut = null;

        try {
            for (String in : cfg.inputFiles()) {
                Path inPath = Paths.get(in);
                if (!Files.exists(inPath)) {
                    System.err.println("Файл не найден: " + inPath);
                    continue;
                }

                try (BufferedReader br = Files.newBufferedReader(inPath, StandardCharsets.UTF_8)) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String trimmed = line.trim();
                        Classifier.Type t = Classifier.classify(trimmed);
                        switch (t) {
                            case INTEGER -> {
                                intStats.acceptInteger(trimmed);
                                if (intOut == null) intOut = openWriter(cfg, "integers.txt");
                                intOut.write(line);
                                intOut.newLine();
                            }
                            case FLOAT -> {
                                floatStats.acceptFloat(trimmed);
                                if (floatOut == null) floatOut = openWriter(cfg, "floats.txt");
                                floatOut.write(line);
                                floatOut.newLine();
                            }
                            case STRING -> {
                                stringStats.acceptString(trimmed);
                                if (stringOut == null) stringOut = openWriter(cfg, "strings.txt");
                                stringOut.write(line);
                                stringOut.newLine();
                            }
                        }
                    }
                }

            }
        } catch (IOException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } finally {
            close(intOut);
            close(floatOut);
            close(stringOut);
        }

        if (cfg.statMode() != null) {
            StatsPrinter.printAll(cfg, intStats, floatStats, stringStats);
        }
    }

    private static BufferedWriter openWriter(Config cfg, String baseName) throws IOException {
        Path dir = cfg.outputDir();
        if (dir != null) {
            Files.createDirectories(dir);
        }
        String fileName = (cfg.prefix() == null ? "" : cfg.prefix()) + baseName;
        Path out = (dir == null ? Paths.get(fileName) : dir.resolve(fileName));

        EnumSet<StandardOpenOption> opts = EnumSet.of(CREATE, WRITE);
        if (cfg.append()) {
            opts.add(APPEND);
        } else {
            opts.add(TRUNCATE_EXISTING);
        }
        return Files.newBufferedWriter(out, StandardCharsets.UTF_8, opts.toArray(new OpenOption[0]));
    }

    private static void close(Closeable c) {
        if (c != null) try { c.close(); } catch (IOException ignored) {}
    }
}
