package com.company;

import exceptions.CommandNotFoundException;
import exceptions.ElementNotFoundException;
import exceptions.IllegalArgumentException;
import exceptions.SizeOutOfBoundsException;

import java.util.Map;
import java.util.Scanner;
import java.util.UUID;



public class UrlOperation{
    public static int MAX_URL_SIZE = 10;
    private final int size;
    private final Map<UUID,String> urlList;

    public UrlOperation(int size, Map<UUID,String> urls){
        this.size = size;
        this.urlList = urls;
    }

    public UUID add(String url){
        UUID uuid = UUID.randomUUID();
        if(urlList.size() == size){
            throw new SizeOutOfBoundsException("Max size for map is: " + MAX_URL_SIZE);
        }else {
            urlList.put(uuid, url);
            return uuid;
        }
    }

    public String get(UUID uuid) throws ElementNotFoundException {
        String url = urlList.get(uuid);
        if(url == null){
            throw new ElementNotFoundException("Key is not found in map!");
        }
        return url;
    }

    public UUID check(String url) throws ElementNotFoundException{

        if(urlList.containsValue(url)){
            for(UUID uuid:urlList.keySet()){
                if(url.equals(urlList.get(uuid))){
                    return uuid;
                }
            }
        }else{
            throw new ElementNotFoundException("Value is not found in map!");
        }
        return null;
    }

    public String delete(UUID uuid)throws ElementNotFoundException{
        String result = "Key: " + uuid + ", value: " + urlList.get(uuid)+".";
        if(urlList.remove(uuid)== null){
            throw new ElementNotFoundException("Key is not found!");
        }
        return result;
    }

    public Map cleanAll(){
        Map map = Map.copyOf(urlList);
        urlList.clear();
        return map;
    }

    private String checkArgument(String value,Operation operation) {
        if(operation == Operation.DELETE || operation == Operation.GET){
            try{
                UUID.fromString(value);
            }catch(java.lang.IllegalArgumentException e){
                throw new IllegalArgumentException("Not correct value.");
            }
        }else{
            long count = value.chars().filter(x->x==' ').count();
            if(count != 0){
                throw new IllegalArgumentException("Not correct value.");
            }
        }
        return value;
    }

    public Object launchOperation (String value)throws CommandNotFoundException {
        Object result;
        try {
            value = value.trim();
            Operation operation = Operation.valueOf(value);
            Scanner scanner = new Scanner(System.in);
            String argument;
            switch (operation) {
                case ADD: {
                    argument = checkArgument(scanner.nextLine(), operation);
                    result = add(argument);
                    break;
                }

                case GET: {
                    argument = checkArgument(scanner.nextLine(), operation);
                    result = get(UUID.fromString(argument));
                    break;
                }

                case CHECK: {
                    argument = checkArgument(scanner.nextLine(), operation);
                    result = check(argument);
                    break;
                }
                case DELETE: {
                    argument = checkArgument(scanner.nextLine(), operation);
                    result = delete(UUID.fromString(argument));
                    break;
                }
                case CLEAN_ALL: {
                    result = cleanAll();
                    break;
                }

                default: {
                    result = null;
                }
            }
        }catch(java.lang.IllegalArgumentException e){
            throw new CommandNotFoundException("Not found a command.",e);
        }
        return result;
    }
}
