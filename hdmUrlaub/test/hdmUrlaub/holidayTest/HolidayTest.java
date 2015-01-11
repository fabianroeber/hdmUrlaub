package hdmUrlaub.holidayTest;

import java.util.Set;

import org.junit.Test;

import de.jollyday.Holiday;
import de.jollyday.HolidayCalendar;
import de.jollyday.HolidayManager;

/**
 * Test, der Feiertage abruft
 * @author Fabian
 *
 */
public class HolidayTest {

	@Test
	public void test() {
		HolidayManager manager = HolidayManager
				.getInstance(HolidayCalendar.GERMANY);

		Set<Holiday> holidays = manager.getHolidays(2015, "bw");
		for (Holiday h : holidays) {
			System.out.println(h.getDate() + " " + h.getDescription());
		}
	}

}
