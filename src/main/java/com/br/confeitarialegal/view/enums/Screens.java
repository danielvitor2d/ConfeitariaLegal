package com.br.confeitarialegal.view.enums;

public enum Screens {
    REGISTRATION("view/registration"),
    LOGIN("view/login"),
    CUSTOMERS("view/customers"),
    PRODUCTS("view/products"),
    SALES("view/sales");

    private String routeToFXML;

    Screens(String route) {
        this.routeToFXML = route;
    }

    public String getRoute() {
        return this.routeToFXML;
    }

}
