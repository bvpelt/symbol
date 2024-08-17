package com.bsoft.symbol.lijn;

import java.util.ArrayList;

public class Sld_UserStyle {
    Se_Description Se_DescriptionObject;
    ArrayList< Object > se_FeatureTypeStyle = new ArrayList < Object > ();
    private String se_Name;


    // Getter Methods

    public Se_Description getSe_Description() {
        return Se_DescriptionObject;
    }

    public String getSe_Name() {
        return se_Name;
    }

    // Setter Methods

    public void setSe_Description(Se_Description se_DescriptionObject) {
        this.Se_DescriptionObject = se_DescriptionObject;
    }

    public void setSe_Name(String se_Name) {
        this.se_Name = se_Name;
    }
}