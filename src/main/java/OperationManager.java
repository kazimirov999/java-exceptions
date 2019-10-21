import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class OperationManager<K,V>  {
    
    private int size;
    public Map<K, V> linkMap;
    protected static int amountUrl;
    private static final long serialVersionUID = 1L;
    protected List<Instant> saveTime = new ArrayList<>();
    protected List<Instant> readTime = new ArrayList<>();

    public OperationManager(int size, Map<K, V> linkMap) {
        this.size = size;
        this.linkMap = linkMap;
    }

    public K add(V link) throws OutOfLengthException {
        K key = generateKey(link);
        if (linkMap.size() == size) {
            throw new OutOfLengthException("Outside the length of the maximum value of the elements, you must remove the link!");
        } else {
            amountUrl++;
            linkMap.put(key, link);
            System.out.println("Id of  this link is: " + key);
        }

        return key;
    }

    public K check(V link) throws ValueNotFoundExcetpion {
        K key = null;
        if (linkMap.containsValue(link)) {
            for (Object o : linkMap.keySet()) {
                if (linkMap.get(o).equals(link)) {
                    System.out.println(key= (K) o);
                }
            }
        } else {
            throw new ValueNotFoundExcetpion("URL is not found in the map");
        }


        return key;
    }

    public V get(K key) throws ValueNotFoundExcetpion {
        V link = linkMap.get(key);
        if (linkMap.get(key) == null) {
            throw new ValueNotFoundExcetpion("ID is not found in the map");
        }

        System.out.println("Link is: " + link);
        return link;
    }

    public V delete(K key) throws ValueNotFoundExcetpion {

        V link = linkMap.get(key);
        if (linkMap.remove(key) == null) {

            throw new ValueNotFoundExcetpion("ID is not found in the map");
        } else {
            amountUrl--;
            System.out.println(link + " deleted");
        }

        return link;

    }
    private K generateKey(V link){
        K key = null;
        if ( link instanceof Url){
            key = (K) UUID.randomUUID();
        }
        else if (link instanceof Urn) {
            Random random = new Random();
             Long keyToLong = random.nextLong();
             key = (K) keyToLong;
        }
        return key;
    }

    protected Map clean() {
        Map oldMap = new HashMap();
        linkMap.clear();
        System.out.println("Map is clean, keys of the old map is: " + oldMap.keySet());

        return oldMap;
    }

    protected Instant findingTime() {
        Instant instant;
        ZoneId zoneId = ZoneId.of("Europe/Kiev");
        instant = (ZonedDateTime.now(zoneId).toInstant());
        return instant;
    }

    protected void exitTheProgram() {
        System.out.println("The application is closed and data is saved.");
        System.exit(0);
    }
}

