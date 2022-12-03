package com.br.confeitarialegal.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class FormatData {

    public static String toCurrencyBRL(Float value) {
        Locale locale = new Locale("pt", "BR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(value);
    }

}
