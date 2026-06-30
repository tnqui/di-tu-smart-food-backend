package com.tranngocqui.ditusmartfoodbackend.enums;

public enum VietMapDisplayType {
    NEW_FORMAT(1),
    OLD_FORMAT(2),
    INPUT_FORMAT(3),
    NEW_OLD_FORMAT(5);

    private final int typeNumber;

    VietMapDisplayType(int type) {
        this.typeNumber = type;
    }

    public int getTypeNumber() {
        return typeNumber;
    }
}
