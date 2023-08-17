package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Differ {

    public static String generate(String json1, String json2) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> jsonMap1 = objectMapper.readValue(json1, new TypeReference<>(){});
        Map<String, Object> jsonMap2 = objectMapper.readValue(json2, new TypeReference<>(){});

        Map<String, Object> sortedJSON1 = new TreeMap<>(jsonMap1);
        Map<String, Object> sortedJSON2 = new TreeMap<>(jsonMap2);



//        Map<String, Object> sortedJSON1 =
//            jsonMap1.entrySet().stream()
//                .sorted(Entry.comparingByKey())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//
//        Map<String, Object> sortedJSON2 =
//            jsonMap2.entrySet().stream()
//                .sorted(Entry.comparingByKey())
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        System.out.println(sortedJSON1.toString());
        System.out.println(sortedJSON2.toString());

        return "";
    }




}
