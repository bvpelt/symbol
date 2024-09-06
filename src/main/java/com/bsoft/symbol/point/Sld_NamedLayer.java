package com.bsoft.symbol.point;

import com.bsoft.symbol.Se_Description;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Sld_NamedLayer {
    private Se_Description se_Description;
    private Sld_UserStyle sld_UserStyle;
    private String se_Name;
}
