package com.comand;

import com.exception.OutOfBoundsUrlsException;
import com.exception.ValueNotFoundException;
import com.utils.Keys;

import java.io.*;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.*;

public class OperationsManager<K, V> implements Serializable{

    private static final long serialVersionUID = 1129128812188L;

    private static final String zoneId = "Europe/Kiev";

    private int size;
    private Map<K, V> values;
    private List<Instant> dateSave;
    private List<Instant> dateRead;
    private static int amountValues;

    public OperationsManager(int size, Map<K, V> values, List<Instant> dateRead, List<Instant> dateSave) {
        this.size = size;
        this.values = values;
        this.dateRead = dateRead;
        this.dateSave = dateSave;
    }


    public  K add(V value){
        K key = Keys.generateKey();
        if(values.size() == size){

            throw new OutOfBoundsUrlsException("Max size for map of com.values is " + size);

        }else {

            values.put(key, value);
            amountValues++;
            return key;

        }
    }


    public  K check(V value) {

        if(values.containsValue(value)){
            return getKey(values, value);
        }
        else{
            throw new ValueNotFoundException("Value is not found in map");
        }

    }

    public  V getUrlById(K key) {

        V value = values.get(key);

        if(value == null){
            throw new ValueNotFoundException("Key is not found in map");
        }

        return value;
    }

    public String deleteById(K key){

        if(values.containsKey(key)){
            String result = key + " " + values.remove(key);
            amountValues--;
            return result;
        }
        else{
            throw new ValueNotFoundException("Key is not found in map");
        }

    }

    public Map<K, V> cleanAll(){

        Map<K, V> oldMap = Map.copyOf(values);
        values.clear();
        amountValues = 0;

        return oldMap;
    }



    private K getKey(Map<K, V> map, V value) {

        return map.keySet()
                .stream()
                .filter(key -> value.equals(map.get(key)))
                .findFirst().get();

    }

    private void writeObject(ObjectOutputStream out) throws IOException {

        dateSave.add(Clock.system(ZoneId.of(zoneId)).instant());

        out.writeObject(dateSave);
        out.writeObject(dateRead);
        out.writeObject(values);
        out.writeInt(amountValues);
        out.writeInt(size);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {

        dateSave = (ArrayList<Instant>) in.readObject();
        dateRead = (ArrayList<Instant>) in.readObject();
        values = (HashMap<K, V>) in.readObject();
        amountValues = in.readInt();
        size = in.readInt();

        dateRead.add(Clock.system(ZoneId.of(zoneId)).instant());
    }
}
