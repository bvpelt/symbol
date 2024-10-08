package com.bsoft.symbol.line;

import com.bsoft.symbol.Se_Description;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Sld_UserStyle {
    private Se_Description se_Description;
    private ArrayList<Se_FeatureTypeStyle> se_FeatureTypeStyle = new ArrayList<Se_FeatureTypeStyle>();
    private String se_Name;
}
