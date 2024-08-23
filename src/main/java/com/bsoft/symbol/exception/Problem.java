package com.bsoft.symbol.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Problem {
    private int status;
    private List<String> detail = new ArrayList<String>();

    public void setDetail(String message) {
        detail.add(message);
    }
}
