package com.company;

public enum Operation {

    ADD(1),
    CHECK(1),
    GET(2),
    DELETE(2),
    CLEAN_ALL(0),
    EXIT(0);
    private int typeArgs;
    Operation(int i) {
        typeArgs = i;
    }

    public int getTypeArgs() {
        return typeArgs;
    }
}
