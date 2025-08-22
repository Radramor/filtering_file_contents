package cmd;

import base.Config;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ArgParser {
    public static Config parse(String[] args) {
        Path outDir = null;
        String prefix = null;
        boolean append = false;
        Config.StatMode mode = Config.StatMode.SHORT;

        List<String> inputs = new ArrayList<>();
        for (int i = 0; i < args.length; i++) {
            String a = args[i];
            switch (a) {
                case "-o" -> outDir = Path.of(args[++i]);
                case "-p" -> prefix = args[++i];
                case "-a" -> append = true;
                case "-s" -> mode = Config.StatMode.SHORT;
                case "-f" -> mode = Config.StatMode.FULL;
                default -> inputs.add(a);
            }
        }
        if (inputs.isEmpty()) throw new IllegalArgumentException("Нет входных файлов");
        return new Config(outDir, prefix, append, mode, List.copyOf(inputs));
    }

    public static void printHelp() {
        System.out.println("Использование: java -cp out base.Main [опции] <файлы>");
    }
}
