package base;

import java.nio.file.Path;
import java.util.List;

public record Config(
        Path outputDir,
        String prefix,
        boolean append,
        StatMode statMode,
        List<String> inputFiles
) {
    public enum StatMode { SHORT, FULL }
}
