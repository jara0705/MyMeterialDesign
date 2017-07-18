package com.jara.mymeterialdesign.enumset;


/**
 * Created by jara on 2017-7-18.
 */

public enum MainFragmentEnum {

    MAIN_HOME(0, "主页"),
    MAIN_HOT(1, "热门"),
    MAIN_FIND(2, "发现"),
    MAIN_PERSONAL(3, "我")
    ;


    int code;
    String name;
    MainFragmentEnum(int code, String name){
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
