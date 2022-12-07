package com.br.confeitarialegal.entities.enums;

public enum StatusType {
    DONE("Finalizada"),
    DRAFT("Rascunho"),
    AWAITING_PAYMENT("Aguardando"),
    CANCELED("Cancelada"),
    REPAID("Reembolsada");

    private String type;

    StatusType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return this.type;
    }

    @Override
    public String toString() {
        return type;
    }
}
