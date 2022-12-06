package com.br.confeitarialegal.views.enums;

public enum Screens {
    REGISTRATION("views/registration"),
    LOGIN("views/login"),
    CUSTOMERS("views/customers"),
    PRODUCTS("views/products"),
    SALES("views/sales"),
    UPDATE_CUSTOMER("views/update_customer");

    private String routeToFXML;

    Screens(String route) {
        this.routeToFXML = route;
    }

    public String getRoute() {
        return this.routeToFXML;
    }

}
