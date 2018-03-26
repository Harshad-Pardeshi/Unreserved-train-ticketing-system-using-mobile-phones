package net.java.railway.core;

import static net.java.railway.Constants.DATE_PATTERN;
import static net.java.railway.Constants.DB_DATE_PATTERN;
import static net.java.railway.Constants.DISPLAY_DATE_PATTERN;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Util {

    public static String getFormattedDate(Date date) {
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(DB_DATE_PATTERN);
        return simpleDateFormatter.format(date);
    }

    public static String getDisplayDate(Date date) {
        SimpleDateFormat lSimpleDateFormatter = new SimpleDateFormat(DISPLAY_DATE_PATTERN);
        return lSimpleDateFormatter.format(date);
    }

    public static String getDisplayDate() {
        return getDisplayDate(Calendar.getInstance().getTime());
    }

    public static Date getParseDate(String date) {
        SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(DATE_PATTERN);
        try {
            return simpleDateFormatter.parse(date);
        } catch (ParseException e) {
            System.out.println("ERROR : " + e.getMessage());
        }
        return null;
    }
}
