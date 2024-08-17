package com.bsoft.symbol.lijn;

public class Sld_NamedLayer {
    Se_Description Se_DescriptionObject;
    Sld_UserStyle Sld_UserStyleObject;
    private String se_Name;


    // Getter Methods

    public Se_Description getSe_Description() {
        return Se_DescriptionObject;
    }

    public Sld_UserStyle getSld_UserStyle() {
        return Sld_UserStyleObject;
    }

    public String getSe_Name() {
        return se_Name;
    }

    // Setter Methods

    public void setSe_Description(Se_Description se_DescriptionObject) {
        this.Se_DescriptionObject = se_DescriptionObject;
    }

    public void setSld_UserStyle(Sld_UserStyle sld_UserStyleObject) {
        this.Sld_UserStyleObject = sld_UserStyleObject;
    }

    public void setSe_Name(String se_Name) {
        this.se_Name = se_Name;
    }
}