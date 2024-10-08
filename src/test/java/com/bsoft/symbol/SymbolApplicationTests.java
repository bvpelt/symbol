package com.bsoft.symbol;


import com.bsoft.symbol.area.*;
import com.bsoft.symbol.line.Se_FeatureTypeStyle;
import com.bsoft.symbol.line.Se_Rule;
import com.bsoft.symbol.line.Se_Stroke;
import com.bsoft.symbol.line.Sld_NamedLayer;
import com.bsoft.symbol.line.Sld_StyledLayerDescriptor;
import com.bsoft.symbol.line.Sld_UserStyle;
import com.bsoft.symbol.line.*;
import com.bsoft.symbol.model.*;
import com.bsoft.symbol.norm.NormSld;
import com.bsoft.symbol.point.Se_Fill;
import com.bsoft.symbol.point.Se_Graphic;
import com.bsoft.symbol.point.*;
import com.bsoft.symbol.repository.*;
import com.bsoft.symbol.services.JsonToJava;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@Slf4j
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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

    @Autowired
    NormRepository normRepository;

    @Autowired
    SymbolRepository symbolRepository;

    @Test
    void contextLoads() {
    }

/*
    @Test
    @Order(1)
    void genPointSLD() {
        SLDService sldService = new SLDService();

        try {
            JSONObject json = sldService.convertXML("Puntsymbolen_v1.0.1.sld");
            String jsonString = json.toString(4);
            log.trace(jsonString);

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
    @Order(2)
    void genLineSLD() {
        SLDService sldService = new SLDService();

        try {
            JSONObject json = sldService.convertXML("Lijnsymbolen_v1.0.1.sld");
            String jsonString = json.toString(4);
            log.trace(jsonString);

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
    @Order(3)
    void genVlakSLD() {
        SLDService sldService = new SLDService();

        try {
            JSONObject json = sldService.convertXML("Vlaksymbolen_v1.1.0.sld");
            String jsonString = json.toString(4);
            log.trace(jsonString);

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
    @Order(4)
    void genNormwaardeSLD() {
        SLDService sldService = new SLDService();

        try {
            JSONObject json = sldService.convertXML("Normwaarden_v1.0.1.sld");
            String jsonString = json.toString(4);
            log.trace(jsonString);

            String outputFileName = "/tmp/Normwaarden_v1.0.1.json";

            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName));
            writer.write(jsonString);
            writer.flush(); // Force flushing the buffer
            writer.close();

        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
*/

    @Test
    @Order(5)
    void readPointSLD() {
        JsonToJava json = new JsonToJava(objectMapper);
        ArrayList<Graphic> graphics = new ArrayList<>();

        try {
            graphicRepository.deleteAll();
            symbolRepository.deleteAll();
            PointSld pointSld = json.readPointsFromJson("src/main/resources/Puntsymbolen_v1.0.1.json");

            com.bsoft.symbol.point.Sld_StyledLayerDescriptor sld_StyledLayerDescriptor = pointSld.getSld_StyledLayerDescriptor();
            com.bsoft.symbol.point.Sld_NamedLayer sld_NamedLayer = sld_StyledLayerDescriptor.getSld_NamedLayer();
            com.bsoft.symbol.point.Sld_UserStyle sld_UserStyle = sld_NamedLayer.getSld_UserStyle();
            String type = sld_UserStyle.getSe_Name();
            ArrayList<com.bsoft.symbol.point.Se_FeatureTypeStyle> se_featureTypeStyle = sld_UserStyle.getSe_FeatureTypeStyle();

            se_featureTypeStyle.forEach(featureType -> {
                ArrayList<com.bsoft.symbol.point.Se_Rule> se_Rule = featureType.getSe_Rule();
                se_Rule.forEach(rule -> {
                    Graphic graphic = new Graphic();
                    graphic.setType(type);
                    Symbol symbol = new Symbol();
                    symbol.setType(type);
                    String name = rule.getSe_Name();
                    graphic.setName(name);
                    symbol.setName(name);
                    log.trace("Rule name: {}", name);
                    Se_PointSymbolizer se_PointSymbolizer = rule.getSe_PointSymbolizer();
                    Se_Graphic se_Graphic = se_PointSymbolizer.getSe_Graphic();
                    int size = se_Graphic.getSe_Size();
                    int rotation = se_Graphic.getSe_Rotation();
                    graphic.setSize(size);
                    symbol.setSize(size);
                    graphic.setRotation(rotation);
                    symbol.setRotation(rotation);
                    Se_Mark se_Mark = se_Graphic.getSe_Mark();
                    graphic.setWelknownname(se_Mark.getSe_WellKnownName());
                    symbol.setWelknownname(se_Mark.getSe_WellKnownName());
                    Se_Fill se_Fill = se_Mark.getSe_Fill();
                    ArrayList<Se_SvgParameter> se_svgParameter_Fill = se_Fill.getSe_SvgParameter();
                    se_svgParameter_Fill.forEach(parameter -> {
                        switch (parameter.getName()) {
                            case "fill":
                                graphic.setFill(parameter.getContent());
                                symbol.setFill(parameter.getContent());
                                break;
                            case "fill-opacity":
                                graphic.setFillopacity(Float.parseFloat(parameter.getContent()));
                                symbol.setFillopacity(Float.parseFloat(parameter.getContent()));
                                break;
                            default:
                                log.error("Unknown fill parameter: {}", parameter.getName());
                                break;
                        }
                    });
                    com.bsoft.symbol.point.Se_Stroke se_Stroke = se_Mark.getSe_Stroke();
                    ArrayList<Se_SvgParameter> se_svgParameter_Stroke = se_Stroke.getSe_SvgParameter();
                    se_svgParameter_Stroke.forEach(parameter -> {
                        switch (parameter.getName()) {
                            case "stroke":
                                graphic.setStroke(parameter.getContent());
                                symbol.setStroke(parameter.getContent());
                                break;
                            case "stroke-opacity":
                                graphic.setStrokeopacity(Float.parseFloat(parameter.getContent()));
                                symbol.setStrokeopacity(Float.parseFloat(parameter.getContent()));
                                break;
                            case "stroke-width":
                                graphic.setStrokewidth(Float.parseFloat(parameter.getContent()));
                                symbol.setStrokewidth(Float.parseFloat(parameter.getContent()));
                                break;
                            default:
                                log.error("Unknown stroke parameter: {}", parameter.getName());
                                break;
                        }
                    });
                    graphics.add(graphic);
                    graphicRepository.save(graphic);
                    symbolRepository.save(symbol);
                });
            });
            log.info("Converted {} graphics", graphics.size());

            log.trace("Graphics:\n{}", graphics);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @Order(6)
    void readLineSLD() {
        JsonToJava json = new JsonToJava(objectMapper);

        try {
            lineRepository.deleteAll();
            LineSld lineSld = json.readLinesFromJson("src/main/resources/Lijnsymbolen_v1.0.1.json");
            ArrayList<Line> lines = new ArrayList<>();

            Sld_StyledLayerDescriptor styledLayerDescriptor = lineSld.getSld_StyledLayerDescriptor();
            Sld_NamedLayer sldNamedLayer = styledLayerDescriptor.getSld_NamedLayer();
            Sld_UserStyle sldUserStyle = sldNamedLayer.getSld_UserStyle();
            String type = sldUserStyle.getSe_Name();
            log.trace("Processing {}", sldUserStyle.getSe_Name());
            ArrayList<Se_FeatureTypeStyle> seFeatureTypeStyles = sldUserStyle.getSe_FeatureTypeStyle();
            seFeatureTypeStyles.forEach(featuretype -> {
                log.trace("Processing {}", featuretype.getSe_Description().getSe_Title());
                ArrayList<Se_Rule> seRules = featuretype.getSe_Rule();
                seRules.forEach(rule -> {
                    log.trace("Processing {}", rule.getSe_Name());
                    Line line = new Line();
                    line.setType(type);
                    Symbol symbol = new Symbol();
                    symbol.setType(type);
                    line.setName(rule.getSe_Name());
                    symbol.setName(rule.getSe_Name());
                    Se_LineSymbolyzer seLineSymbolyzer = rule.getSe_LineSymbolizer();
                    Se_Stroke seStroke = seLineSymbolyzer.getSe_Stroke();
                    ArrayList<Se_SvgParameter> seSvgParameter = seStroke.getSe_SvgParameter();

                    seSvgParameter.forEach(parameter -> {
                        switch (parameter.getName()) {
                            case "stroke":
                                line.setStroke(parameter.getContent());
                                symbol.setStroke(parameter.getContent());
                                break;
                            case "stroke-opacity":
                                line.setOpacity(Float.parseFloat(parameter.getContent()));
                                symbol.setStrokeopacity(Float.parseFloat(parameter.getContent()));
                                break;
                            case "stroke-width":
                                line.setWidth(Float.parseFloat(parameter.getContent()));
                                symbol.setStrokewidth(Float.parseFloat(parameter.getContent()));
                                break;
                            case "stroke-linecap":
                                line.setLinecap(parameter.getContent());
                                symbol.setStokelinecap(parameter.getContent());
                                break;
                            case "stroke-dasharray":
                                line.setDasharray(parameter.getContent());
                                symbol.setStrokedasharray(parameter.getContent());
                                break;
                            default:
                                log.error("Unexpected parameter name: {}", parameter.getName());
                                break;
                        }
                    });
                    lines.add(line);
                    lineRepository.save(line);
                    symbolRepository.save(symbol);
                });
            });
            log.info("Converted {} line symbols", lines.size());

            log.trace("Line symbols:\n{}", lines);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }


    @Test
    @Order(7)
    void readAreaSld() {
        JsonToJava json = new JsonToJava(objectMapper);
        ArrayList<Area> areas = new ArrayList<>();
        try {
            areaRepository.deleteAll();
            AreaSld areaSld = json.readAreasFromJson("src/main/resources/Vlaksymbolen_v1.1.0.json");

            com.bsoft.symbol.area.Sld_StyledLayerDescriptor styledLayerDescriptor = areaSld.getSld_StyledLayerDescriptor();
            com.bsoft.symbol.area.Sld_NamedLayer sld_NamedLayer = styledLayerDescriptor.getSld_NamedLayer();
            com.bsoft.symbol.area.Sld_UserStyle sld_UserStyle = sld_NamedLayer.getSld_UserStyle();
            String type = sld_UserStyle.getSe_Name();
            ArrayList<com.bsoft.symbol.area.Se_FeatureTypeStyle> se_FeatureTypeStyle = sld_UserStyle.getSe_FeatureTypeStyle();
            se_FeatureTypeStyle.forEach(featureType -> {
                ArrayList<com.bsoft.symbol.area.Se_Rule> se_Rule = featureType.getSe_Rule();
                se_Rule.forEach(rule -> {
                    Area area = new Area();
                    area.setType(type);
                    Symbol symbol = new Symbol();
                    symbol.setType(type);
                    Se_PolygonSymbolizer se_PolygonSymbolizer = rule.getSe_PolygonSymbolizer();
                    String name = se_PolygonSymbolizer.getSe_Name();
                    log.info("Processing area: {}", name);
                    area.setName(name);
                    symbol.setName(name);
                    com.bsoft.symbol.area.Se_Fill se_Fill = se_PolygonSymbolizer.getSe_Fill();
                    ArrayList<Se_SvgParameter> se_SvgParameters_Fill = se_Fill.getSe_SvgParameter();
                    se_SvgParameters_Fill.forEach(fill_parameter -> {
                        switch (fill_parameter.getName()) {
                            case "fill":
                                area.setFill(fill_parameter.getContent());
                                symbol.setFill(fill_parameter.getContent());
                                break;
                            case "fill-opacity":
                                area.setFillopacity(Float.parseFloat(fill_parameter.getContent()));
                                symbol.setFillopacity(Float.parseFloat(fill_parameter.getContent()));
                                break;
                            case "stroke-linejoin":
                                area.setStrokelinejoinfill(fill_parameter.getContent());
                                symbol.setStrokelinejoinfill(fill_parameter.getContent());
                                break;
                            default:
                                log.error("Unexpected fill parameter: {}", fill_parameter.getName());
                                break;
                        }
                    });

                    Se_GraphicFill se_graphicFill = se_Fill.getSe_GraphicFill();
                    if (se_graphicFill != null) {
                        com.bsoft.symbol.area.Se_Graphic se_Graphic = se_graphicFill.getSe_Graphic();
                        Se_ExternalGraphic se_ExternalGraphic = se_Graphic.getSe_ExternalGraphic();
                        Se_OnlineResource se_OnlineResource = se_ExternalGraphic.getSe_OnlineResource();
                        String xlinkhref = se_OnlineResource.getXlink_href();

                        area.setSymbol(xlinkhref.replace("./symbols/", ""));
                        symbol.setSymbol(xlinkhref.replace("./symbols/", ""));
                    }

                    com.bsoft.symbol.area.Se_Stroke se_Stroke = se_PolygonSymbolizer.getSe_Stroke();
                    ArrayList<Se_SvgParameter> se_SvgParameter_Stroke = se_Stroke.getSe_SvgParameter();
                    se_SvgParameter_Stroke.forEach(stroke_parameter -> {
                        switch (stroke_parameter.getName()) {
                            case "stroke":
                                area.setStroke(stroke_parameter.getContent());
                                symbol.setStroke(stroke_parameter.getContent());
                                break;
                            case "stroke-opacity":
                                area.setStrokeopacity(Float.parseFloat(stroke_parameter.getContent()));
                                symbol.setStrokeopacity(Float.parseFloat(stroke_parameter.getContent()));
                                break;
                            case "stroke-width":
                                area.setStrokewidth(Float.parseFloat(stroke_parameter.getContent()));
                                symbol.setStrokewidth(Float.parseFloat(stroke_parameter.getContent()));
                                break;
                            case "stroke-linejoin":
                                area.setStrokelinejoinstroke(stroke_parameter.getContent());
                                symbol.setStrokelinejoinstroke(stroke_parameter.getContent());
                                break;
                            case "stroke-dasharray":
                                area.setStrokedasharray(stroke_parameter.getContent());
                                symbol.setStrokedasharray(stroke_parameter.getContent());
                                break;
                            default:
                                log.error("Unexpected stroke parameter: {}", stroke_parameter.getName());
                                break;
                        }
                        areas.add(area);
                        areaRepository.save(area);
                        symbolRepository.save(symbol);
                    });
                });

            });
            log.info("Converted {} areas", areas.size());

            log.trace("Areas:\n{}", areas);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Test
    @Order(8)
    void readNormSld() {
        JsonToJava json = new JsonToJava(objectMapper);
        ArrayList<Norm> normen = new ArrayList<>();
        try {
            normRepository.deleteAll();
            NormSld normSld = json.readNormFromJson("src/main/resources/Normwaarden_v1.0.1.json");

            com.bsoft.symbol.norm.Sld_StyledLayerDescriptor styledLayerDescriptor = normSld.getSld_StyledLayerDescriptor();
            com.bsoft.symbol.norm.Sld_NamedLayer sld_NamedLayer = styledLayerDescriptor.getSld_NamedLayer();
            com.bsoft.symbol.norm.Sld_UserStyle sld_UserStyle = sld_NamedLayer.getSld_UserStyle();
            String type = sld_UserStyle.getSe_Name();
            ArrayList<com.bsoft.symbol.norm.Se_FeatureTypeStyle> se_FeatureTypeStyle = sld_UserStyle.getSe_FeatureTypeStyle();
            se_FeatureTypeStyle.forEach(featureType -> {
                ArrayList<com.bsoft.symbol.norm.Se_Rule> se_Rule = featureType.getSe_Rule();
                se_Rule.forEach(rule -> {
                    Norm norm = new Norm();
                    norm.setType(type);
                    Symbol symbol = new Symbol();
                    symbol.setType(type);
                    com.bsoft.symbol.norm.Se_PolygonSymbolizer se_PolygonSymbolizer = rule.getSe_PolygonSymbolizer();
                    String name = se_PolygonSymbolizer.getSe_Name();
                    log.info("Processing norm: {}", name);
                    norm.setName(name);
                    symbol.setName(name);
                    com.bsoft.symbol.norm.Se_Fill se_Fill = se_PolygonSymbolizer.getSe_Fill();
                    ArrayList<Se_SvgParameter> se_SvgParameters_Fill = se_Fill.getSe_SvgParameter();
                    se_SvgParameters_Fill.forEach(fill_parameter -> {
                        switch (fill_parameter.getName()) {
                            case "fill":
                                norm.setFill(fill_parameter.getContent());
                                symbol.setFill(fill_parameter.getContent());
                                break;
                            case "fill-opacity":
                                norm.setFillopacity(Float.parseFloat(fill_parameter.getContent()));
                                symbol.setFillopacity(Float.parseFloat(fill_parameter.getContent()));
                                break;
                            default:
                                log.error("Unexpected fill parameter: {}", fill_parameter.getName());
                                break;
                        }
                    });

                    com.bsoft.symbol.norm.Se_Stroke se_Stroke = se_PolygonSymbolizer.getSe_Stroke();
                    ArrayList<Se_SvgParameter> se_SvgParameter_Stroke = se_Stroke.getSe_SvgParameter();
                    se_SvgParameter_Stroke.forEach(stroke_parameter -> {
                        switch (stroke_parameter.getName()) {
                            case "stroke":
                                norm.setStroke(stroke_parameter.getContent());
                                symbol.setStroke(stroke_parameter.getContent());
                                break;
                            case "stroke-opacity":
                                norm.setStrokeopacity(Float.parseFloat(stroke_parameter.getContent()));
                                symbol.setStrokeopacity(Float.parseFloat(stroke_parameter.getContent()));
                                break;
                            case "stroke-width":
                                norm.setStrokewidth(Float.parseFloat(stroke_parameter.getContent()));
                                symbol.setStrokewidth(Float.parseFloat(stroke_parameter.getContent()));
                                break;
                            case "stroke-linejoin":
                                norm.setStrokelinejoinstroke(stroke_parameter.getContent());
                                symbol.setStrokelinejoinstroke(stroke_parameter.getContent());
                                break;
                            default:
                                log.error("Unexpected stroke parameter: {}", stroke_parameter.getName());
                                break;
                        }
                        normen.add(norm);
                        normRepository.save(norm);
                        symbolRepository.save(symbol);
                    });
                });

            });
            log.info("Converted {} norm", normen.size());

            log.trace("Normen:\n{}", normen);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
