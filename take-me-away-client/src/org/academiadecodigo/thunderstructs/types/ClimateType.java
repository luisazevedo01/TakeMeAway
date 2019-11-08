package org.academiadecodigo.thunderstructs.types;

public enum ClimateType {
    HOT(1, ""),
    COLD(2, "");

    private int option;
    private String msg;

    ClimateType(int option, String msg){
        this.option = option;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
