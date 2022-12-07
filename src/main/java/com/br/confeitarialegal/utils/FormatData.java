package com.br.confeitarialegal.utils;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatData {

    static Locale locale = new Locale("pt", "BR");

    public static String toCurrencyBRL(Double value) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
        return currencyFormatter.format(value);
    }

    public static String toLocaleDate(LocalDate date) {
        if (date == null) return null;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", locale);
        return dateFormatter.format(date);
    }

    public static String maskNull(String text, String defaultText) {
        if (text == null) return defaultText;
        return text;
    }

}
