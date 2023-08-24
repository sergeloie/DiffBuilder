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

    @Option(names = {"-f", "--format"},
            defaultValue = "stylish",
            paramLabel = "format",
            description = "output format [default: ${DEFAULT-VALUE}]")
    String format;

    @Override
    public String call() throws Exception {
        String filePath1 = file1.getAbsolutePath();
        String filePath2 = file2.getAbsolutePath();
        File newFile1 = new File(filePath1);
        File newFile2 = new File(filePath2);
        System.out.println(Differ.generate(newFile1, newFile2, format));
        return Differ.generate(newFile1, newFile2, format);
    }

    public static void main(String[] args) {

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
