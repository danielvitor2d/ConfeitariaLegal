package com.br.confeitarialegal.views.enums;

public enum Screens {
    REGISTRATION("views/registration"),
    LOGIN("views/login"),
    CUSTOMERS("views/customers"),
    PRODUCTS("views/products"),
    SALES("views/sales"),
    MANAGE_CUSTOMER("views/manage_customer"),
    MANAGE_PRODUCT("views/manage_product");

    private final String routeToFXML;

    Screens(String route) {
        this.routeToFXML = route;
    }

    public String getRoute() {
        return this.routeToFXML;
    }

}
