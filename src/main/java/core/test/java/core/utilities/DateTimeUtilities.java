package core.test.java.core.utilities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtilities {

	public static String formatDate(LocalDate date, String formatType) {
		return date.format(DateTimeFormatter.ofPattern(formatType));
	}
	
	public static LocalDate getCurrentDate() {
		return LocalDate.now();
	}
	
	public static LocalDate plusDate(LocalDate date, int duration) {
		return date.plusDays(duration);
	}
	
	public static LocalDate plusMonth(LocalDate date, int duration) {
		return date.plusMonths(duration);
	}
	
	public static String getCurrentDate(String formatType) {
		return formatDate(LocalDate.now(),formatType);
	}
	
	public static String plusDate(LocalDate date, int duration, String formatType) {
		return formatDate(date.plusDays(duration), formatType);
	}
	
	public static LocalDate convertStrToDate(String date, String formatType) {
		return LocalDate.parse(date, DateTimeFormatter.ofPattern(formatType));
	}
	
	/**
	 * Get current time with input format
     * @param format
     */
	public static String getCurrentDateTime(String format) {
		
		LocalDateTime date = LocalDateTime.now();
		
		DateTimeFormatter dtf = null;
		if (format.trim().isEmpty()) {
			dtf = DateTimeFormatter.ISO_LOCAL_TIME;
		} else {
			dtf = DateTimeFormatter.ofPattern(format.trim());
		}
		
		return date.format(dtf);
	}
}
