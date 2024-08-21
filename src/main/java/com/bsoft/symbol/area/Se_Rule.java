package com.bsoft.symbol.area;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Se_Rule {
    private Se_Description se_Description;
    private Se_PolygonSymbolizer se_PolygonSymbolizer;
    private Ogc_Filter ogc_Filter;
    private String se_Name;
}
