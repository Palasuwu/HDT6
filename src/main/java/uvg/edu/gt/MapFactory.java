package uvg.edu.gt;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapFactory {

    public static Map<String, String> createMap(String type) {
        switch (type) {
            case "HashMap":
                return new HashMap<>();
            case "TreeMap":
                return new TreeMap<>();
            case "LinkedHashMap":
                return new LinkedHashMap<>();
            default:
                throw new IllegalArgumentException("Tipo de mapa no válido: " + type);
        }
    }
}
