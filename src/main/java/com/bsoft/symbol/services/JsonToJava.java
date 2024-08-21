package com.bsoft.symbol.services;

import com.bsoft.symbol.line.LineSld;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class JsonToJava {

    @Autowired
    ObjectMapper objectMapper;

    public JsonToJava(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public LineSld readUsersFromJson(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        // ClassPathResource resource = new ClassPathResource(filename);
        // String inputFile = resource.getFilename();

        //File jsonFile = new File("src/main/resources/Lijnsymbolen_v1.0.1.json"); // Replace with your file path
        File jsonFile = new File(filename); // Replace with your file path
        LineSld lineSld = objectMapper.readValue(jsonFile, LineSld.class);

        return lineSld;
    }
}
