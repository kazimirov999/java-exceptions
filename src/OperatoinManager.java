
import java.util.*;

public class OperatoinManager {

    private String url;
    private int size;
    private UUID uuid;
    public Map<UUID, String> urlMap ;

    public OperatoinManager(int size, Map<UUID, String> urlMap) {
        this.size = size;
        this.urlMap = urlMap;
    }

    protected UUID add(String string) throws OutOfLengthException {
            uuid = UUID.randomUUID();

            if (urlMap.size()== size){
                throw new OutOfLengthException("Outside the length of the maximum value of the elements, you must remove the link!");
            }
            else {
                urlMap.put(uuid, string);
                System.out.println("Id of  this link is: " + uuid);
            }

        return uuid;
    }

    protected UUID checkByUrl(String string) throws ValueNotFoundExcetpion {
        if(urlMap.containsValue(string)){
        for (Object o : urlMap.keySet()) {
            if (urlMap.get(o).equals(string)) {
                System.out.println(o);
            }
        }
        }
        else {
            throw new ValueNotFoundExcetpion("URL is not found in the map");
        }


        return uuid;
    }

    protected String getById(UUID uuid) throws ValueNotFoundExcetpion {
        String url = urlMap.get(uuid);
        if (urlMap.get(uuid) == null){
            throw new ValueNotFoundExcetpion("ID is not found in the map");
        }

        System.out.println("Link is: " + url );
        return url;
    }

    protected String deleteById(UUID uuid) throws ValueNotFoundExcetpion {

        url = urlMap.get(uuid) + " deleted";
        if ( urlMap.remove(uuid) == null) {

            throw new ValueNotFoundExcetpion("ID is not found in the map");
        }

         else{

            System.out.println(url);
        }

        return url;

    }

    protected Map clean() {
        Map oldMap = Map.copyOf(urlMap);
        urlMap.clear();
        System.out.println("Map is clean, keys of the old map is: " + oldMap.keySet());

        return oldMap;
    }

}
