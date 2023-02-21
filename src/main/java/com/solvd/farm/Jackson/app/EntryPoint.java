package com.solvd.farm.Jackson.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.farm.Jackson.binary.Farm;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EntryPoint {
    private final static Logger log = LogManager.getLogger(EntryPoint.class);

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JavaType type = objectMapper.getTypeFactory().constructCollectionType(List.class, Farm.class);
//            TypeReference<List<User>> type = new TypeReference<List<User>>() {}; - can be used as an option
            log.info("reading users from file");
            //Todo: change path to file
            List<Farm> farms = objectMapper.readValue(new File("src/main/resources/jackson/example.json"), type);
            log.info(farms.toString());
            log.info("writing farms to file");
            writeToJson(farms, objectMapper);
        } catch (JsonProcessingException e) {
            log.error("Error while parsing json file", e);
        } catch (IOException e) {
            log.error("Error while reading file", e);
        }
    }

    private static void writeToJson(List<Farm> farms, ObjectMapper objectMapper) throws IOException {
        File result = new File("src/main/resources/jackson/resultExample.json");
        if(!result.exists()) {
            result.createNewFile();
        }
        objectMapper.writeValue(result, farms);
    }
}