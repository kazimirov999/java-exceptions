package com.comand;

import com.exception.OutOfBoundsUrlsException;
import com.exception.ValueNotFoundException;
import com.link.Link;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class OperationsManager implements Serializable {

    private static final long serialVersionUID = 42324L;
    private int size;
    private Map<String, Link> links;
    private List<Instant> dateSave;
    private List<Instant> dateRead;


    public OperationsManager(int size, Map<String, Link> links, List<Instant> dateRead, List<Instant> dateSave) {
        this.size = size;
        this.links = links;
        this.dateRead = dateRead;
        this.dateSave = dateSave;
    }


    public String add(Link argument, String key) {
        if (links.size() == size) {
            throw new OutOfBoundsUrlsException("Max size for map of com.links is " + size);
        } else {
            links.put(key, argument);
            return key;
        }
    }


    public String check(Link link) {
        return links.keySet()
                .stream()
                .filter(key -> link.equals(links.get(key)))
                .findFirst()
                .orElseThrow(() -> new ValueNotFoundException("Value is not found in map"));
    }

    public Link getUrlById(String key) {

        Link link = links.get(key);

        if (link == null) {
            throw new ValueNotFoundException("Key is not found in map");
        }

        return link;
    }

    public String deleteById(String key) {

        if (links.containsKey(key)) {
            return key + " " + links.remove(key);
        } else {
            throw new ValueNotFoundException("Key is not found in map");
        }

    }

    public Map<String, Link> cleanAll() {
        Map<String, Link> oldMap = Map.copyOf(links);
        links.clear();

        return oldMap;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        dateSave.add(Instant.now());
        out.writeObject(size);
        out.writeObject(links);
        out.writeObject(dateRead);
        out.writeObject(dateSave);

    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        size = (int) in.readObject();
        links = (Map<String, Link>) in.readObject();
        dateRead = (List<Instant>) in.readObject();
        dateSave = (List<Instant>) in.readObject();
        dateRead.add(Instant.now());
    }
}
