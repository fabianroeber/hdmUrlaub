package de.hdm.hdmUrlaub.bo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import de.hdm.hdmUrlaub.util.HolidayUtil;

/**
 * Diese Klasse enth&auml;lt einen Zeitraum eines Urlaubsantrags. Ein
 * Urlaubsantrag kann beliebig viele davon haben.
 * 
 * @author Fabian
 *
 */
public class ZeitraumBo extends HdmUrlaubBusinessObject {

	private static final long serialVersionUID = 3440962018375324263L;

	private Date beginn;

	private Date ende;

	private UrlaubsantragBo urlaubsantrag;

	private static final String DATE_PATTERN = "EEE dd.MM.yyyy";

	public ZeitraumBo() {
		super();

	}

	public ZeitraumBo(Integer id, Date beginn, Date ende,
			UrlaubsantragBo urlaubsantrag) {
		super();
		this.setId(id);
		this.beginn = beginn;
		this.ende = ende;
		this.urlaubsantrag = urlaubsantrag;
	}

	/**
	 * Diese Methode formatiert den Beginn eines {@link ZeitraumBo} in ein
	 * geeignetes Format f&uuml;r die Anzeige.
	 * 
	 * @return String
	 */
	public String getBeginnAsString() {
		return new SimpleDateFormat(DATE_PATTERN).format(beginn);
	}

	/**
	 * Diese Methode formatiert das Ende eines {@link ZeitraumBo} in ein
	 * geeignetes Format f&uuml;r die Anzeige.
	 * 
	 * @return String
	 */
	public String getEndeAsString() {
		return new SimpleDateFormat(DATE_PATTERN).format(ende);
	}

	public Date getBeginn() {
		return beginn;
	}

	public void setBeginn(Date beginn) {
		this.beginn = beginn;
	}

	public Date getEnde() {
		return ende;
	}

	public void setEnde(Date ende) {
		this.ende = ende;
	}

	public UrlaubsantragBo getUrlaubsantrag() {
		return urlaubsantrag;
	}

	public void setUrlaubsantrag(UrlaubsantragBo urlaubsantrag) {
		this.urlaubsantrag = urlaubsantrag;
	}

	public int getAnzahltage() {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(beginn);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(ende);

		int anzahltage = 0;

		// Eins zurückgeben, wenn Start und Ende der gleiche Tag und kein
		// Wochenende oder Feiertag
		if (!HolidayUtil.isHoliday(startCal) && startCal.equals(endCal)) {
			return 1;
		}

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(beginn);
			endCal.setTime(ende);
		}

		do {
			startCal.add(Calendar.DAY_OF_MONTH, 1);
			if (!HolidayUtil.isHoliday(startCal)) {
				anzahltage++;
			}
		} while (startCal.getTimeInMillis() <= endCal.getTimeInMillis());

		return anzahltage;
	}

}
