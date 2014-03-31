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

}
