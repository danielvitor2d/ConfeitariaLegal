package com.br.confeitarialegal.entity.enums;

public enum UnitaryTypes {
    UNIT("unid"),
    GRAM("g"),
    KILOGRAM("Kg"),
    LITER("L");

    private String type;

    UnitaryTypes(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}
