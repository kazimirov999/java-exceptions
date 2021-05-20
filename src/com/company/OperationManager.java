package com.company;

import exceptions.ElementNotFoundException;
import exceptions.SizeOutOfBoundsException;

import java.io.*;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;


public class OperationManager <K, V> implements Serializable {
    static final long serialVersionUID = 42L;
    private static int size;
    private List<Instant> saveDate;
    private List<Instant> readDate;
    private Map<K, V> urlList;
    public static int MAX_URL_SIZE = 10;


    public OperationManager(int size, List<Instant> saveDate, List<Instant> readDate,
                            Map<K, V> urlList) {
        this.size = size;
        this.saveDate = saveDate;
        this.readDate = readDate;
        this.urlList = urlList;
    }

    private K createKey(V v) {
        if (v instanceof Url) {
            return (K) UUID.randomUUID();
        } else if (v instanceof Urn) {
            Random ran = new Random();
            return (K) ((Long) ran.nextLong());
        }
        return null;
    }

    public K add(V value) {
        K key = createKey(value);
        if (urlList.size() == MAX_URL_SIZE) {
            throw new SizeOutOfBoundsException("Max size for map is: " + MAX_URL_SIZE);
        } else {
            size++;
            urlList.put(key, value);
            return key;
        }
    }

    public V get(K key) throws ElementNotFoundException {
        V value = urlList.get(key);
        if (value == null) {
            throw new ElementNotFoundException("Key is not found in map!");
        }
        return value;
    }


    public K check(V value) throws ElementNotFoundException {

        if (urlList.containsValue(value)) {
            for (K key : urlList.keySet()) {
                if (value.equals(urlList.get(key))) {
                    return key;
                }
            }
        } else {
            throw new ElementNotFoundException("Value is not found in map!");
        }
        return null;
    }

    public String delete(K key) throws ElementNotFoundException {
        if (urlList.remove(key) == null) {
            throw new ElementNotFoundException("Key is not found!");
        } else {
            String result = "Key: " + key + ", value: " + urlList.get(key) + ".";
            size--;
            return result;
        }
    }

    public Map cleanAll() {
        Map map = Map.copyOf(urlList);
        urlList.clear();
        size = 0;
        return map;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        saveDate.add(Clock.system(ZoneId.of("Europe/Kiev")).instant());
        out.writeObject(saveDate);
        out.writeObject(readDate);

        out.writeObject(urlList);
        out.writeInt(size);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        readDate = (ArrayList<Instant>) in.readObject();
        saveDate = (ArrayList<Instant>) in.readObject();
        urlList = (HashMap<K, V>) in.readObject();
        size = in.readInt();

        readDate.add(Clock.system(ZoneId.of("Europe/Kiev")).instant());
    }
}

    /*public V typingToUrl(String s){
        java.net.URL newUrl = null;
        try {
            newUrl = new java.net.URL(s);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Not correct URL, try again.");
        }
        return new V(newUrl.getProtocol(),newUrl.getAuthority()
                    ,newUrl.getPath(),newUrl.getQuery(),newUrl.getRef());
    }

*/

