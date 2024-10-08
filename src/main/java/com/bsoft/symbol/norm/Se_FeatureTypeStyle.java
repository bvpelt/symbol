package com.bsoft.symbol.norm;

import com.bsoft.symbol.Se_Description;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Se_FeatureTypeStyle {
    private Se_Description se_Description;
    private ArrayList<Se_Rule> se_Rule = new ArrayList<Se_Rule>();
    private String se_Name;
}
