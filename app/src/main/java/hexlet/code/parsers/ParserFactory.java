package hexlet.code.parsers;

public class ParserFactory {
    public static Parser createParser(String parseType) {
        return switch (parseType) {
            case ("json") -> new ParserJSON();
            case ("yml") -> new ParserYAML();
            default -> throw new RuntimeException("Undefined type - " + parseType);
        };
    }
}
