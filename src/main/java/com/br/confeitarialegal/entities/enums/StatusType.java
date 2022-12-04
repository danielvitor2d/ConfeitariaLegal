package com.br.confeitarialegal.entities.enums;

public enum StatusType {
    DONE("finalizada"),
    DRAFT("rascunho"),
    AWAITING_PAYMENT("aguardando"),
    CANCELED("cancelada"),
    REPAID("reembolsada");

    private String type;

    StatusType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return this.type;
    }
}
