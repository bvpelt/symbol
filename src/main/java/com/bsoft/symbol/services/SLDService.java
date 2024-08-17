package com.bsoft.symbol.services;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class SLDService {

    public JSONObject convertXML(String filename) throws IOException {
        JSONObject json = null;

        json = XML.toJSONObject(readFileContent(filename));

        return json;
    }

    private String readFileContent(String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource(filename);
        return IOUtils.toString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}
