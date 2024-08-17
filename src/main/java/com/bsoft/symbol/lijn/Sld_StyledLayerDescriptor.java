package com.bsoft.symbol.lijn;


public class Sld_StyledLayerDescriptor {
    private String xmlns_se;
    Sld_NamedLayer Sld_NamedLayerObject;
    private String xmlns_ogc;
    private String xmlns_sld;
    private String xmlns_xlink;
    private String xmlns_dso;
    private String xsi_schemaLocation;
    private String xmlns_xsi;
    private String version;


    // Getter Methods

    public String getXmlns_se() {
        return xmlns_se;
    }

    public Sld_NamedLayer getSld_NamedLayer() {
        return Sld_NamedLayerObject;
    }

    public String getXmlns_ogc() {
        return xmlns_ogc;
    }

    public String getXmlns_sld() {
        return xmlns_sld;
    }

    public String getXmlns_xlink() {
        return xmlns_xlink;
    }

    public String getXmlns_dso() {
        return xmlns_dso;
    }

    public String getXsi_schemaLocation() {
        return xsi_schemaLocation;
    }

    public String getXmlns_xsi() {
        return xmlns_xsi;
    }

    public String getVersion() {
        return version;
    }

    // Setter Methods

    public void setXmlns_se(String xmlns_se) {
        this.xmlns_se = xmlns_se;
    }

    public void setSld_NamedLayer(Sld_NamedLayer sld_NamedLayerObject) {
        this.Sld_NamedLayerObject = sld_NamedLayerObject;
    }

    public void setXmlns_ogc(String xmlns_ogc) {
        this.xmlns_ogc = xmlns_ogc;
    }

    public void setXmlns_sld(String xmlns_sld) {
        this.xmlns_sld = xmlns_sld;
    }

    public void setXmlns_xlink(String xmlns_xlink) {
        this.xmlns_xlink = xmlns_xlink;
    }

    public void setXmlns_dso(String xmlns_dso) {
        this.xmlns_dso = xmlns_dso;
    }

    public void setXsi_schemaLocation(String xsi_schemaLocation) {
        this.xsi_schemaLocation = xsi_schemaLocation;
    }

    public void setXmlns_xsi(String xmlns_xsi) {
        this.xmlns_xsi = xmlns_xsi;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}