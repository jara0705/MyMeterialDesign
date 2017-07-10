package com.jara.mymeterialdesign.enumset;

/**
 * Created by jara on 2017-6-30.
 */

public enum SubFragmentEnum {

    SUB_NEWS("1", "新闻"),
    SUB_RANK("2", "排行榜"),
    SUB_COMPUTER("3", "电脑"),
    SUB_MOBILE("4", "手机"),
    SUB_WEAR("5", "智能穿戴"),
    SUB_PAD("6", "平板"),
    SUB_VR("7", "VR"),
    SUB_TEST("8", "评测"),
    SUB_OTHER("9", "其他");

    String code;
    String name;

    SubFragmentEnum(String code, String name){
        this.code = code;
        this.name = name;
    }

    public static SubFragmentEnum ofCode(String code) {
        for (SubFragmentEnum sub : SubFragmentEnum.values()) {
            if (code.equals(sub.getCode())) {
                return sub;
            }
        }
        return null;
    }

    public static String getNameByCode(String code) {
        for (SubFragmentEnum sub : SubFragmentEnum.values()) {
            if (code.equals(sub.getCode())) {
                return sub.getName();
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
