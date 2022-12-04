package com.br.confeitarialegal.views.enums;

public enum Screens {
    REGISTRATION("views/registration"),
    LOGIN("views/login"),
    CUSTOMERS("views/customers"),
    PRODUCTS("views/products"),
    SALES("views/sales");

    private String routeToFXML;

    Screens(String route) {
        this.routeToFXML = route;
    }

    public String getRoute() {
        return this.routeToFXML;
    }

}
