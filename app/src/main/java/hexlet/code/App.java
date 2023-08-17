package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.nio.file.Files;
import java.nio.file.Paths;

// some exports omitted for the sake of brevity

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

//        System.out.println(file1.toPath());
//        System.out.println(file2.toPath());
//
//        System.out.println(file1.getAbsolutePath());
//        System.out.println(file2.getAbsolutePath());

        Path path1 = Paths.get(file1.getAbsolutePath());
        Path path2 = Paths.get(file2.getAbsolutePath());

        String contentOfJSONFile1 = Files.readString(path1);
        String contentOfJSONFile2 = Files.readString(path2);

//        System.out.println(contentOfJSONFile1);
//        System.out.println(contentOfJSONFile2);

        Differ.generate(contentOfJSONFile1, contentOfJSONFile2);
        // The business logic of the command goes here...
        // In this case, code for generation of ASCII art graphics
        // (omitted for the sake of brevity).
        return "";
    }

    public static void main(String[] args) throws Exception{

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
