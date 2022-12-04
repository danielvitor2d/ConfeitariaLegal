package com.br.confeitarialegal.views.utils;

import com.br.confeitarialegal.App;
import com.br.confeitarialegal.views.enums.Screens;

import java.io.IOException;

public class ManageScreens {
    public static void switchToSales() {
        redirectTo(Screens.SALES.getRoute());
    }

    public static void switchToCustomers() {
        redirectTo(Screens.CUSTOMERS.getRoute());
    }

    public static void switchToProducts() {
        redirectTo(Screens.PRODUCTS.getRoute());
    }

    public static void switchToLogin() {
        redirectTo(Screens.LOGIN.getRoute());
    }

    public static void switchToRegistration() {
        redirectTo(Screens.REGISTRATION.getRoute());
    }

    private static void redirectTo(String to) {
        try {
            App.setRoot(to);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
