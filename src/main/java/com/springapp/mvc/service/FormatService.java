package com.springapp.mvc.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatService {
	public static String searchString(String query) {
		// TODO: sanitize inputs
		return "%" + query + "%";
	}

	public static String formatDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.set(1900, 0, 1);
		date = date == null ? cal.getTime() : date;
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(date);
	}

    public static long findDiffDays(Date start, Date last) {
        Date startDate = start;
        Date endDate = last;
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long diffTime = endTime - startTime;
        long diffDays = (long) Math.ceil(diffTime / (1000.0 * 60.0 * 60.0 * 24.0));
        return diffDays;
    }

}
