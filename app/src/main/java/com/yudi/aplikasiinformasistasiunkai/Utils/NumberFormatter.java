package com.yudi.aplikasiinformasistasiunkai.Utils;

import java.text.NumberFormat;
import java.util.Locale;

public class NumberFormatter {
    public static String formatNumber(int number) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        return numberFormat.format(number);
    }
}
