import exeption.OutOfBoundsUrlsException;
import exeption.ValueNotFoundException;

import java.util.Map;
import java.util.UUID;

public class OperationsManager {

    private final int size;
    private final Map<UUID, String> urls;

    public OperationsManager(int size, Map<UUID, String> urls) {
        this.size = size;
        this.urls = urls;
    }

    public  UUID add(String url){
        UUID uuid = UUID.randomUUID();

        if(urls.size() == size){
            throw new OutOfBoundsUrlsException("Max size for map of urls is " + size);
        }else {
            urls.put(uuid, url);
            return uuid;
        }
    }

    public  UUID check(String url) {
        if(urls.containsValue(url)){
            return getKey(urls, url);
        }
        else{
            throw new ValueNotFoundException("URL is not found in map");
        }
    }

    public  String getUrlById(UUID uuid) {

        String url = urls.get(uuid);
        if(url == null){
            throw new ValueNotFoundException("UUID is not found in map");
        }
        return url;
    }

    public String deleteById(UUID uuid){

        String result = uuid + " " + urls.get(uuid);

        if(urls.remove(uuid) == null){
            throw new ValueNotFoundException("UUID is not found in map");
        }
        else{
            return result;
        }

    }
    public Map cleanAll(){

        Map oldMap = Map.copyOf(urls);
        urls.clear();

        return oldMap;
    }


    private  <K, V> K getKey(Map<K, V> map, V value) {
        return map.keySet()
                .stream()
                .filter(key -> value.equals(map.get(key)))
                .findFirst().get();
    }

}
