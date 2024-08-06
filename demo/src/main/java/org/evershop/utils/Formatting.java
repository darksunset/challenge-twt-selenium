package org.evershop.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Formatting {
    public static Float formatStringToFloat(Locale locale, String localeFloatString){
        NumberFormat numberFormat = NumberFormat.getInstance(locale);
        try {
            Number number = numberFormat.parse(localeFloatString);
            return number.floatValue();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
