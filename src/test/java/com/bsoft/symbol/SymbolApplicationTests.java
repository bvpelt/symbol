package com.bsoft.symbol;


import com.bsoft.symbol.area.AreaSld;
import com.bsoft.symbol.area.Se_PolygonSymbolizer;
import com.bsoft.symbol.line.Se_FeatureTypeStyle;
import com.bsoft.symbol.line.Se_Rule;
import com.bsoft.symbol.line.Se_Stroke;
import com.bsoft.symbol.line.Se_SvgParameter;
import com.bsoft.symbol.line.Sld_NamedLayer;
import com.bsoft.symbol.line.Sld_StyledLayerDescriptor;
import com.bsoft.symbol.line.Sld_UserStyle;
import com.bsoft.symbol.line.*;
import com.bsoft.symbol.model.Graphic;
import com.bsoft.symbol.model.Line;
import com.bsoft.symbol.point.*;
import com.bsoft.symbol.repository.AreaRepository;
import com.bsoft.symbol.repository.GrapphicRepository;
import com.bsoft.symbol.repository.LineRepository;
import com.bsoft.symbol.services.JsonToJava;
import com.bsoft.symbol.services.SLDService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

@Slf4j
@SpringBootTest
class SymbolApplicationTests {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    LineRepository lineRepository;

    @Autowired
    GrapphicRepository graphicRepository;

    @Autowired
    AreaRepository areaRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void genPointSLD() {
        SLDService sldService = new SLDService();

        try {
            JSONObject json = sldService.convertXML("Puntsymbolen_v1.0.1.sld");
            String jsonString = json.toString(4);
            log.info(jsonString);

            String outputFileName = "/tmp/Puntsymbolen_v1.0.1.json";

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            writer.write(jsonString);
            writer.flush(); // Force flushing the buffer
            writer.close();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void genLineSLD() {
        SLDService sldService = new SLDService();

        try {
            JSONObject json = sldService.convertXML("Lijnsymbolen_v1.0.1.sld");
            String jsonString = json.toString(4);
            log.info(jsonString);

            String outputFileName = "/tmp/Lijnsymbolen_v1.0.1.json";

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            writer.write(jsonString);
            writer.flush(); // Force flushing the buffer
            writer.close();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void genVlakSLD() {
        SLDService sldService = new SLDService();

        try {
            JSONObject json = sldService.convertXML("Vlaksymbolen_v1.1.0.sld");
            String jsonString = json.toString(4);
            log.info(jsonString);

            String outputFileName = "/tmp/Vlaksymbolen_v1.1.0.json";

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            writer.write(jsonString);
            writer.flush(); // Force flushing the buffer
            writer.close();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void readPointSLD() {
        JsonToJava json = new JsonToJava(objectMapper);

        try {
            graphicRepository.deleteAll();
            PointSld pointSld = json.readPointsFromJson("src/main/resources/Puntsymbolen_v1.0.1.json");

            com.bsoft.symbol.point.Sld_StyledLayerDescriptor sld_StyledLayerDescriptor = pointSld.getSld_StyledLayerDescriptor();
            com.bsoft.symbol.point.Sld_NamedLayer sld_NamedLayer = sld_StyledLayerDescriptor.getSld_NamedLayer();
            com.bsoft.symbol.point.Sld_UserStyle sld_UserStyle = sld_NamedLayer.getSld_UserStyle();
            ArrayList<com.bsoft.symbol.point.Se_FeatureTypeStyle> se_featureTypeStyle = sld_UserStyle.getSe_FeatureTypeStyle();

            se_featureTypeStyle.forEach(featureType -> {
                ArrayList<com.bsoft.symbol.point.Se_Rule> se_Rule = featureType.getSe_Rule();
                se_Rule.forEach(rule -> {
                    Graphic graphic = new Graphic();
                    String name = rule.getSe_Name();
                    graphic.setName(name);
                    log.info("Rule name: {}", name);
                    Se_PointSymbolizer se_PointSymbolizer = rule.getSe_PointSymbolizer();
                    Se_Graphic se_Graphic = se_PointSymbolizer.getSe_Graphic();
                    int size = se_Graphic.getSe_Size();
                    int rotation = se_Graphic.getSe_Rotation();
                    graphic.setSize(size);
                    graphic.setRotation(rotation);
                    Se_Mark se_Mark = se_Graphic.getSe_Mark();
                    Se_Fill se_Fill = se_Mark.getSe_Fill();
                    ArrayList<com.bsoft.symbol.point.Se_SvgParameter> se_svgParameter_Fill = se_Fill.getSe_SvgParameter();
                    se_svgParameter_Fill.forEach(parameter -> {
                        switch (parameter.getName()) {
                            case "fill":
                                graphic.setFill(parameter.getContent());
                                break;
                            case "fill-opacity":
                                graphic.setFillopacity(parameter.getContent());
                                break;
                            default:
                                log.error("Unknown fill parameter: {}", parameter.getName());
                                break;
                        }
                    });
                    com.bsoft.symbol.point.Se_Stroke se_Stroke = se_Mark.getSe_Stroke();
                    ArrayList<com.bsoft.symbol.point.Se_SvgParameter> se_svgParameter_Stroke = se_Stroke.getSe_SvgParameter();
                    se_svgParameter_Stroke.forEach(parameter -> {
                        switch (parameter.getName()) {
                            case "stroke":
                                graphic.setStroke(parameter.getContent());
                                break;
                            case "stroke-opacity":
                                graphic.setStrokeopacity(Integer.parseInt(parameter.getContent()));
                                break;
                            case "stroke-width":
                                graphic.setStrokewidth(Integer.parseInt(parameter.getContent()));
                                break;
                            default:
                                log.error("Unknown stroke parameter: {}", parameter.getName());
                                break;
                        }
                    });
                    graphicRepository.save(graphic);
                });
            });

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    void readLineSLD() {
        JsonToJava json = new JsonToJava(objectMapper);

        try {
            lineRepository.deleteAll();
            LineSld lineSld = json.readLinesFromJson("src/main/resources/Lijnsymbolen_v1.0.1.json");
            ArrayList<Line> lines = new ArrayList<Line>();

            Sld_StyledLayerDescriptor styledLayerDescriptor = lineSld.getSld_StyledLayerDescriptor();
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


    @Test
    void readAreaSld() {
        JsonToJava json = new JsonToJava(objectMapper);

        try {
            areaRepository.deleteAll();
            AreaSld areaSld = json.readAreasFromJson("src/main/resources/Vlaksymbolen_v1.1.0.json");

            com.bsoft.symbol.area.Sld_StyledLayerDescriptor styledLayerDescriptor = areaSld.getSld_StyledLayerDescriptor();
            com.bsoft.symbol.area.Sld_NamedLayer sld_NamedLayer = styledLayerDescriptor.getSld_NamedLayer();
            com.bsoft.symbol.area.Sld_UserStyle sld_UserStyle = sld_NamedLayer.getSld_UserStyle();
            ArrayList<com.bsoft.symbol.area.Se_FeatureTypeStyle> se_FeatureTypeStyle = sld_UserStyle.getSe_FeatureTypeStyle();
            se_FeatureTypeStyle.forEach(featureType -> {
                ArrayList<com.bsoft.symbol.area.Se_Rule> se_Rule = featureType.getSe_Rule();
                se_Rule.forEach(rule -> {
                    Se_PolygonSymbolizer se_PolygonSymbolizer = rule.getSe_PolygonSymbolizer();
                    com.bsoft.symbol.area.Se_Fill se_Fill = se_PolygonSymbolizer.getSe_Fill();
                    ArrayList<com.bsoft.symbol.area.Se_SvgParameter> se_SvgParameters_Fill = se_Fill.getSe_SvgParameter();
                    se_SvgParameters_Fill.forEach(fill_parameter -> {
                        String name = fill_parameter.getName();
                    });

                    com.bsoft.symbol.area.Se_Stroke se_Stroke = se_PolygonSymbolizer.getSe_Stroke();
                    ArrayList<com.bsoft.symbol.area.Se_SvgParameter> se_SvgParameter_Stroke = se_Stroke.getSe_SvgParameter();
                    se_SvgParameter_Stroke.forEach(stroke_parameter -> {
                        String name = stroke_parameter.getName();
                    });
                });

            });

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
