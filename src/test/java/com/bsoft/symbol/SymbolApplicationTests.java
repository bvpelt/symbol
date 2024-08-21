package com.bsoft.symbol;


import com.bsoft.symbol.line.*;
import com.bsoft.symbol.model.Line;
import com.bsoft.symbol.repository.LineRepository;
import com.bsoft.symbol.services.JsonToJava;
import com.bsoft.symbol.services.SLDService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@Slf4j
@SpringBootTest
class SymbolApplicationTests {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    LineRepository lineRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void genPointSLD() {
        SLDService sldService = new SLDService();

        try {
            JSONObject json = sldService.convertXML("Puntsymbolen_v1.0.1.sld");
            log.info(json.toString(4));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void genLineSLD() {
        SLDService sldService = new SLDService();

        try {
            JSONObject json = sldService.convertXML("Lijnsymbolen_v1.0.1.sld");
            log.info(json.toString(4));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void genVlakSLD() {
        SLDService sldService = new SLDService();

        try {
            JSONObject json = sldService.convertXML("Vlaksymbolen_v1.0.1.sld");
            log.info(json.toString(4));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    /*
    @Test
    void readPointSLD() {
        JsonToJava json = new JsonToJava(objectMapper);

        try {
            LijnSld lijnSld = json.readUsersFromJson("src/main/resources/Puntsymbolen_v1.0.1.json");
            ArrayList<Symbol> symbols = new ArrayList<Symbol>();

            Sld_StyledLayerDescriptor styledLayerDescriptor = lijnSld.getSld_StyledLayerDescriptor();
            Sld_NamedLayer sldNamedLayer = styledLayerDescriptor.getSld_NamedLayer();
            Sld_UserStyle sldUserStyle = sldNamedLayer.getSld_UserStyle();
            log.info("Processing {}", sldUserStyle.getSe_Name());
            ArrayList<Se_FeatureTypeStyle> seFeatureTypeStyles = sldUserStyle.getSe_FeatureTypeStyle();
            seFeatureTypeStyles.forEach(featuretype -> {
                log.info("Processing {}", featuretype.getSe_Description().getSe_Title());
                ArrayList<Se_Rule> seRules = featuretype.getSe_Rule();
                seRules.forEach(rule -> {
                    log.info("Processing {}", rule.getSe_Name());
                    Symbol symbol = new Symbol();
                    symbol.setName(rule.getSe_Name());
                    Se_LineSymbolyzer seLineSymbolyzer = rule.getSe_LineSymbolizer();
                    Se_Stroke seStroke = seLineSymbolyzer.getSe_Stroke();
                    ArrayList<Se_SvgParameter> seSvgParameter = seStroke.getSe_SvgParameter();

                    seSvgParameter.forEach(parameter -> {
                        switch (parameter.getName()) {
                            case "stroke":
                                symbol.setStroke(parameter.getContent());
                                break;
                            case "stroke-opacity":
                                symbol.setOpacity(Integer.parseInt(parameter.getContent()));
                                break;
                            case "stroke-width":
                                symbol.setWidth(Integer.parseInt(parameter.getContent()));
                                break;
                            case "stroke-linecap":
                                symbol.setLinecap(parameter.getContent());
                                break;
                            case "stroke-dasharray":
                                symbol.setDasharray(parameter.getContent());
                                break;
                            default:
                                log.error("Unexpected parameter name: {}", parameter.getName());
                                break;
                        }
                    });
                    symbols.add(symbol);
                    //symbolRepository.save(symbol);
                });
            });
            log.info("Converted {} symbols", symbols.size());

            log.info("Symbols:\n{}", symbols);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

     */

    @Test
    void readLineSLD() {
        JsonToJava json = new JsonToJava(objectMapper);

        try {
            LijnSld lijnSld = json.readUsersFromJson("src/main/resources/Lijnsymbolen_v1.0.1.json");
            ArrayList<Line> lines = new ArrayList<Line>();

            Sld_StyledLayerDescriptor styledLayerDescriptor = lijnSld.getSld_StyledLayerDescriptor();
            Sld_NamedLayer sldNamedLayer = styledLayerDescriptor.getSld_NamedLayer();
            Sld_UserStyle sldUserStyle = sldNamedLayer.getSld_UserStyle();
            log.info("Processing {}", sldUserStyle.getSe_Name());
            ArrayList<Se_FeatureTypeStyle> seFeatureTypeStyles = sldUserStyle.getSe_FeatureTypeStyle();
            seFeatureTypeStyles.forEach(featuretype -> {
                log.info("Processing {}", featuretype.getSe_Description().getSe_Title());
                ArrayList<Se_Rule> seRules = featuretype.getSe_Rule();
                seRules.forEach(rule -> {
                    log.info("Processing {}", rule.getSe_Name());
                    Line line = new Line();
                    line.setName(rule.getSe_Name());
                    Se_LineSymbolyzer seLineSymbolyzer = rule.getSe_LineSymbolizer();
                    Se_Stroke seStroke = seLineSymbolyzer.getSe_Stroke();
                    ArrayList<Se_SvgParameter> seSvgParameter = seStroke.getSe_SvgParameter();

                    seSvgParameter.forEach(parameter -> {
                        switch (parameter.getName()) {
                            case "stroke":
                                line.setStroke(parameter.getContent());
                                break;
                            case "stroke-opacity":
                                line.setOpacity(Integer.parseInt(parameter.getContent()));
                                break;
                            case "stroke-width":
                                line.setWidth(Integer.parseInt(parameter.getContent()));
                                break;
                            case "stroke-linecap":
                                line.setLinecap(parameter.getContent());
                                break;
							case "stroke-dasharray":
                                line.setDasharray(parameter.getContent());
								break;
                            default:
                                log.error("Unexpected parameter name: {}", parameter.getName());
								break;
                        }
                    });
                    lines.add(line);
                    lineRepository.save(line);
                });
            });
			log.info("Converted {} line symbols", lines.size());

			log.info("Line symbols:\n{}", lines);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
