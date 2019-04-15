package com.ifd.mijnapi;

import java.util.ArrayList;
import java.util.List;

public class Honden {
    private List<Hond> hondList;

    public List<Hond> getHondList() {
        if (hondList == null) {
            hondList = new ArrayList<>();
        }
        return hondList;
    }

    public void setHondList(List<Hond> hondList) {
        this.hondList = hondList;
    }
}
