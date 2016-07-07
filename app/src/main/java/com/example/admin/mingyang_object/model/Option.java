package com.example.admin.mingyang_object.model;

import java.io.Serializable;

/**
 * Created by think on 2016/6/27.
 */
public class Option implements Serializable {
    String Name;
    String Desc;
    String Value1;
    String Value2;
    String Value3;
    String Value4;
    String Value5;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getValue1() {
        return Value1;
    }

    public void setValue1(String value1) {
        Value1 = value1;
    }

    public String getValue2() {
        return Value2;
    }

    public void setValue2(String value2) {
        Value2 = value2;
    }

    public String getValue3() {
        return Value3;
    }

    public void setValue3(String value3) {
        Value3 = value3;
    }

    public String getValue4() {
        return Value4;
    }

    public void setValue4(String value4) {
        Value4 = value4;
    }

    public String getValue5() {
        return Value5;
    }

    public void setValue5(String value5) {
        Value5 = value5;
    }
}
