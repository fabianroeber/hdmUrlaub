package de.hdm.hdmUrlaub.util;

import java.util.Calendar;

import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;

/**
 * Util-Klasse zum Umgang mit Feiertagen und Wochenende. Feiertage werden über
 * die 3rd Party Bibliothek Jollyday bezogen.
 * 
 * @author Fabian
 *
 */
public class HolidayUtil {

	private static HolidayManager holidayManager = HolidayManager
			.getInstance(HolidayCalendar.GERMANY);

	/**
	 * 
	 * &Uuml;berpr&uuml;fung, ob ein Kalendertag ein Feiertag ist oder sich am
	 * Wochenende befindet.
	 * 
	 * @param Calendar
	 * @return boolean
	 */
	public static boolean isHoliday(Calendar cal) {

		if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
				&& cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
				&& !holidayManager.isHoliday(cal, "bw")) {
			return false;
		} else {
			return true;
		}

	}
}
