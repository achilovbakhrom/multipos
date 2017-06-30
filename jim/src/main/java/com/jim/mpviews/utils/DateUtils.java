package com.jim.mpviews.utils;


import com.jim.mpviews.App;
import com.jim.mpviews.R;

import java.util.Calendar;

/**
 * Created by Пользователь on 15.06.2017.
 */
public class DateUtils {

    public static String monthToString(int month) {
        switch (month) {
            case Calendar.JANUARY:
                return App.getAppContext().getString(R.string.january);

            case Calendar.FEBRUARY:
                return App.getAppContext().getString(R.string.february);

            case Calendar.MARCH:
                return App.getAppContext().getString(R.string.march);

            case Calendar.APRIL:
                return App.getAppContext().getString(R.string.april);

            case Calendar.MAY:
                return App.getAppContext().getString(R.string.may);

            case Calendar.JUNE:
                return App.getAppContext().getString(R.string.june);

            case Calendar.JULY:
                return App.getAppContext().getString(R.string.july);

            case Calendar.AUGUST:
                return App.getAppContext().getString(R.string.august);

            case Calendar.SEPTEMBER:
                return App.getAppContext().getString(R.string.september);

            case Calendar.OCTOBER:
                return App.getAppContext().getString(R.string.october);

            case Calendar.NOVEMBER:
                return App.getAppContext().getString(R.string.november);

            case Calendar.DECEMBER:
                return App.getAppContext().getString(R.string.december);
        }
        return "";
    }

    public static String dayOfWeekToString(int day) {
        switch (day) {
            case Calendar.SUNDAY:
                return App.getAppContext().getString(R.string.sunday);

            case Calendar.MONDAY:
                return App.getAppContext().getString(R.string.monday);

            case Calendar.TUESDAY:
                return App.getAppContext().getString(R.string.tuesday);

            case Calendar.WEDNESDAY:
                return App.getAppContext().getString(R.string.wednesday);

            case Calendar.THURSDAY:
                return App.getAppContext().getString(R.string.thursday);

            case Calendar.FRIDAY:
                return App.getAppContext().getString(R.string.friday);

            case Calendar.SATURDAY:
                return App.getAppContext().getString(R.string.saturday);
        }
        return "";
    }
}
