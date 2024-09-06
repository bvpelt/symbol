package com.bsoft.symbol.area;

import com.bsoft.symbol.Se_SvgParameter;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Se_Fill {
    private ArrayList<Se_SvgParameter> se_SvgParameter = new ArrayList<Se_SvgParameter>();
    private Se_GraphicFill se_GraphicFill;
}
