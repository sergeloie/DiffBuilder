package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.concurrent.Callable;

@Command(name = "gendiff", version = "gendiff 1.0", mixinStandardHelpOptions = true)
public class App implements Callable {

    @Parameters(paramLabel = "filepath1", description = "path to first file")
    File file1;

    @Parameters(paramLabel = "filepath2", description = "path to second file")
    File file2;

    @Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
    private String format = "stylish";

    @Override
    public String call() throws Exception {

        return Differ.generate(file1, file2);
    }

    public static void main(String[] args) throws Exception {

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
