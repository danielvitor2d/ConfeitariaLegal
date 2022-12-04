package com.br.confeitarialegal.utils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

public class FormatData {

    static Locale locale = new Locale("pt", "BR");

    public static String toCurrencyBRL(Double value) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(value);
    }

    public static String toLocaleDate(Date date) {
        if (date == null) return null;
        DateFormat dateFormatter = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        return dateFormatter.format(date);
    }

    public static String maskNull(String text, String defaultText) {
        if (text == null) return defaultText;
        return text;
    }

}
