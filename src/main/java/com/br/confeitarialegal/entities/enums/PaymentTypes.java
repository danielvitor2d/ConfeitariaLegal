package com.br.confeitarialegal.entities.enums;

public enum PaymentTypes {
    CASH("Dinheiro"),
    DEBIT_CARD("Cartão de Débito"),
    CREDIT_CARD("Cartão de Crédito"),
    CHECK("Cheque"),
    PIX("Pix"),
    DEFAULT("Não pago");

    private String paymentType;

    PaymentTypes(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    @Override
    public String toString() {
        return paymentType;
    }
}
