import java.util.HashMap;
import java.util.Map;

public class Database {
    public static Map<String, User> users = new HashMap<>();
    public static Map<Integer, Book> books = new HashMap<>();
    public static int bookIdCounter = 1;
}
