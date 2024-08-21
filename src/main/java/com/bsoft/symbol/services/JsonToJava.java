package com.bsoft.symbol.services;

import com.bsoft.symbol.area.AreaSld;
import com.bsoft.symbol.line.LineSld;
import com.bsoft.symbol.point.PointSld;
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

    public LineSld readLinesFromJson(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File jsonFile = new File(filename); // Replace with your file path
        LineSld lineSld = objectMapper.readValue(jsonFile, LineSld.class);

        return lineSld;
    }

    public PointSld readPointsFromJson(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        File jsonFile = new File(filename); // Replace with your file path
        PointSld pointSld = objectMapper.readValue(jsonFile, PointSld.class);

        return pointSld;
    }

    public AreaSld readAreasFromJson(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();


        File jsonFile = new File(filename); // Replace with your file path
        AreaSld areaSld = objectMapper.readValue(jsonFile, AreaSld.class);

        return areaSld;
    }
}
